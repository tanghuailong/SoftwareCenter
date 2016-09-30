package softwarecenter.wt.com.softwarecenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.facebook.stetho.Stetho;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import softwarecenter.wt.com.softwarecenter.bean.Tab;
import softwarecenter.wt.com.softwarecenter.event.EventAlarm;
import softwarecenter.wt.com.softwarecenter.fragment.ApsFragment;
import softwarecenter.wt.com.softwarecenter.fragment.BasicFragment;
import softwarecenter.wt.com.softwarecenter.fragment.IndustrialContrlFragment;
import softwarecenter.wt.com.softwarecenter.fragment.WmsFragment;
import softwarecenter.wt.com.softwarecenter.service.MqttService;
import softwarecenter.wt.com.softwarecenter.widget.FragmentTabHost;

/**
 * Created on 2016/09/22
 */
public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";
    private FragmentTabHost mTabhost;
    private LayoutInflater mInflater;
    private View mView;
    private List<Tab> mTabs=new ArrayList<>(4);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化调试工具
        Stetho.initializeWithDefaults(this);
        //订阅EventBus
        EventBus.getDefault().register(this);

        initTab();

        //启动service
        Intent intent = new Intent(this, MqttService.class);
        startService(intent);

    }

    //初始化tab信息
    private void initTab() {
        Tab tab_basic=new Tab(R.string.basic,R.drawable.selector_icon_basic,BasicFragment.class);
        Tab tab_wms=new Tab(R.string.wmsystem,R.drawable.selector_icon_wms, WmsFragment.class);
        Tab tab_aps=new Tab(R.string.aps,R.drawable.selector_icon_aps, ApsFragment.class);
        Tab tab_industrial=new Tab(R.string.control,R.drawable.selector_icon_control, IndustrialContrlFragment.class);

        mTabs.add(tab_basic);
        mTabs.add(tab_wms);
        mTabs.add(tab_aps);
        mTabs.add(tab_industrial);

        mInflater=LayoutInflater.from(this);

        mTabhost= (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        mTabhost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);

        for(Tab tab:mTabs){
            TabHost.TabSpec tabSpec=mTabhost.newTabSpec(getString(tab.getTitle()));

            tabSpec.setIndicator(buildIndicator(tab));

            mTabhost.addTab(tabSpec,tab.getFragment(),null);

        }
        //去掉tabSpec之间的分割线
        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabhost.setCurrentTab(0);
    }


    private View buildIndicator(Tab tab){
        mView=mInflater.inflate(R.layout.tab_indicator,null);

        ImageView img= (ImageView) mView.findViewById(R.id.icon_tab);
        TextView text= (TextView) mView.findViewById(R.id.txt_indicator);

        img.setImageResource(tab.getIcon());
        text.setText(tab.getTitle());
        return mView;


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void  getAlarm(EventAlarm eventAlarm) {
        //把获得的数据放入View中
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG,"onDestory");
        //取消订阅
        EventBus.getDefault().unregister(this);
        Intent intent = new Intent(this,MqttService.class);
        stopService(intent);
    }
}