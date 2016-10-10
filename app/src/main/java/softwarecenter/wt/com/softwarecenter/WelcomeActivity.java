package softwarecenter.wt.com.softwarecenter;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import Lib.FWReader.S8.function_S8;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import softwarecenter.wt.com.softwarecenter.common.ApiFactory;
import softwarecenter.wt.com.softwarecenter.event.EventScan;
import softwarecenter.wt.com.softwarecenter.service.ApiService;
import softwarecenter.wt.com.softwarecenter.service.ScanService;
import softwarecenter.wt.com.softwarecenter.service.SwipeCardService;


/**
 * Created by duanshiyao on 2016/9/22.
 * email：
 */

public class WelcomeActivity extends Activity {

    private RelativeLayout  welcomeRelativelayout;
    private static final String LOG_TAG = "WelcomeActivity";

    private SwipeCardService swipeCardService;
    private boolean mBound = false;

    long startTime = 0;
    private String lastLoginId = "";

    private ApiService apiService = ApiFactory.getInstance().getApi(ApiService.class);

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            SwipeCardService.MyBinder binder = (SwipeCardService.MyBinder)iBinder;
            swipeCardService = binder.getService();
            mBound = true;
            swipeCardService.test(new function_S8(WelcomeActivity.this));
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        welcomeRelativelayout = (RelativeLayout) findViewById(R.id.welcome_rlayout);
        Intent intent=new Intent(this,ScanService.class);
        startService(intent);
        onclick();
        if("Main".equals(getIntent().getStringExtra("from"))){
            startTime = System.currentTimeMillis();
        }
        EventBus.getDefault().register(this);




    }

    private void onclick() {
        welcomeRelativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(LOG_TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this,SwipeCardService.class);
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
        Log.d(LOG_TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mBound) {
            Log.d(LOG_TAG,"unbind in MainActivity");
            unbindService(mConnection);
        }
        Log.d(LOG_TAG,"onPause");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG,"onRestart");
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginEventBus(String cardId){
        long endTime = System.currentTimeMillis();
        if(!cardId.equals(lastLoginId)) {
            startTime = 0;
        }
        if(endTime - startTime > 60000) {
            Log.d(LOG_TAG,"cardId "+cardId);
            startTime = System.currentTimeMillis();

            apiService.getLoginResult(cardId,"一体机")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((r) -> {
                        if(r.isCode()) {
                            lastLoginId = cardId;
                            EventBus.getDefault().unregister(this);
                            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(WelcomeActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                            lastLoginId  = cardId;
                        }
                    },(e) -> {
                           e.printStackTrace();
                    });
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginByScanMachine(EventScan eventScan) {
        Log.d(LOG_TAG,eventScan.getMessage());
        apiService.getLoginResult(eventScan.getMessage(),"一体机")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((r) -> {
                    if(r.isCode()) {
                        EventBus.getDefault().unregister(this);
                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(WelcomeActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                    }
                },(e) -> {
                    e.printStackTrace();
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
