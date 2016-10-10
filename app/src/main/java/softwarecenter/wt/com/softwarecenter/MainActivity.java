package softwarecenter.wt.com.softwarecenter;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import Lib.FWReader.S8.function_S8;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import softwarecenter.wt.com.softwarecenter.bean.Tab;
import softwarecenter.wt.com.softwarecenter.common.ApiFactory;
import softwarecenter.wt.com.softwarecenter.event.EventScan;
import softwarecenter.wt.com.softwarecenter.fragment.ApsFragment;
import softwarecenter.wt.com.softwarecenter.fragment.BasicFragment;
import softwarecenter.wt.com.softwarecenter.fragment.IndustrialContrlFragment;
import softwarecenter.wt.com.softwarecenter.fragment.WmsFragment;
import softwarecenter.wt.com.softwarecenter.service.ApiService;
import softwarecenter.wt.com.softwarecenter.service.MqttService;
import softwarecenter.wt.com.softwarecenter.service.SwipeCardService;
import softwarecenter.wt.com.softwarecenter.widget.FragmentTabHost;

/**
 * Created by chenggong on 2016/09/22
 */
public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";
    private FragmentTabHost mTabhost;
    private LayoutInflater mInflater;
    private View mView;
    private List<Tab> mTabs = new ArrayList<>(4);

    private SwipeCardService swipeCardService;
    private boolean mBound = false;

    private boolean isAccess = false;

    private String lastLoginId = "";

    long startTime=0;



    private ApiService apiService = ApiFactory.getInstance().getApi(ApiService.class);

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            SwipeCardService.MyBinder binder = (SwipeCardService.MyBinder) iBinder;
            swipeCardService = binder.getService();
            mBound = true;
            swipeCardService.test(new function_S8(MainActivity.this));
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化调试工具
        Stetho.initializeWithDefaults(this);
        initTab();
        //启动service
        Intent intent = new Intent(this, MqttService.class);
        startService(intent);

        EventBus.getDefault().register(this);


    }

    //初始化tab信息
    private void initTab() {
        Tab tab_basic = new Tab(R.string.basic, R.drawable.selector_icon_basic, BasicFragment.class);
        Tab tab_wms = new Tab(R.string.wmsystem, R.drawable.selector_icon_wms, WmsFragment.class);
        Tab tab_aps = new Tab(R.string.aps, R.drawable.selector_icon_aps, ApsFragment.class);
        Tab tab_industrial = new Tab(R.string.control, R.drawable.selector_icon_control, IndustrialContrlFragment.class);

        mTabs.add(tab_basic);
        mTabs.add(tab_wms);
        mTabs.add(tab_aps);
        mTabs.add(tab_industrial);

        mInflater = LayoutInflater.from(this);

        mTabhost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (Tab tab : mTabs) {
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));

            tabSpec.setIndicator(buildIndicator(tab));

            mTabhost.addTab(tabSpec, tab.getFragment(), null);

        }
        //去掉tabSpec之间的分割线
        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabhost.setCurrentTab(0);
    }


    private View buildIndicator(Tab tab) {
        mView = mInflater.inflate(R.layout.tab_indicator, null);

        ImageView img = (ImageView) mView.findViewById(R.id.icon_tab);
        TextView text = (TextView) mView.findViewById(R.id.txt_indicator);

        img.setImageResource(tab.getIcon());
        text.setText(tab.getTitle());
        return mView;


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestory");
        Intent intent = new Intent(this, MqttService.class);
        stopService(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void logoutEventBus(String cardId) {
            Log.d(LOG_TAG,"cardId "+cardId);

        long endTime = System.currentTimeMillis();

        if(!cardId.equals(lastLoginId)){
            startTime=0;
        }
        if(endTime-startTime>60000){
            startTime=System.currentTimeMillis();
            apiService.getLogoutResult(cardId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((r) -> {
                        if (r.isCode()) {
                            lastLoginId = cardId;
                            EventBus.getDefault().unregister(this);
                            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                            intent.putExtra("from", "Main");
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "登出失败", Toast.LENGTH_SHORT).show();
                            lastLoginId = cardId;
                        }
                    }, (e) -> {
                        e.printStackTrace();
                    });
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void logoutByScanMachine(EventScan eventScan) {
        Log.d(LOG_TAG,eventScan.getMessage());
        apiService.getLogoutResult(eventScan.getMessage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((r) -> {
                    if (r.isCode()) {
                        EventBus.getDefault().unregister(this);
                        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                        intent.putExtra("from","Main");
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "登出失败", Toast.LENGTH_SHORT).show();
                    }
                }, (e) -> {
                    e.printStackTrace();
                });
    }
    @Override
    protected void onResume() {

        super.onResume();
        Intent intent = new Intent(this,SwipeCardService.class);
        bindService(intent,mConnection,BIND_AUTO_CREATE);

        Log.d(LOG_TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mBound) {
            Log.d(LOG_TAG,"unbindService in TestActivity");
            unbindService(mConnection);
        }
        Log.d(LOG_TAG,"onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG,"onRestart");
    }
}