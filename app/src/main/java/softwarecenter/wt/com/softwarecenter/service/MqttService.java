package softwarecenter.wt.com.softwarecenter.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import softwarecenter.wt.com.softwarecenter.common.MqttManager;
import softwarecenter.wt.com.softwarecenter.event.EventLost;

/**
 * Created by tanghuailong on 2016/9/27.
 */

/**
 * Mqtt处理Service ，主要处理包括连接，断开重连，已经订阅节点
 */
public class MqttService extends Service  {

    public static final String LOG_TAG = "MqttService";

    private String mDeviceId;
    private static final String DEVICE_ID_FORMAT = "wt_%s";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG,"onCreate");
        EventBus.getDefault().register(this);
        mDeviceId = String.format(DEVICE_ID_FORMAT, Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
        connectRabbitmq(mDeviceId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG,"execute startCommand");
        return START_REDELIVER_INTENT;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void resetMqtt(EventLost eventLost) {
        connectRabbitmq(mDeviceId);
    }

    public void connectRabbitmq(String deviceId) {

        Observable.create((r) -> {
            Boolean result = MqttManager.getInstance().createConnection(deviceId);
            if (result.equals(true)) {
                result = MqttManager.getInstance().subscribe();
            }else {
                result = false;
            }
            r.onNext(result);
        }).repeatWhen((o) -> {
            Log.d(LOG_TAG,"不能连接到rabbitmq,重试中...");
            return o;
        }).takeUntil((r) ->  r.equals(true)).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((r) -> {
                    Log.d(LOG_TAG,"连接订阅...");
                });
    }






    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
