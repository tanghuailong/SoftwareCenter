package softwarecenter.wt.com.softwarecenter;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dalong.library.view.LoopRotarySwitchView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import Lib.FWReader.S8.function_S8;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
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
    private LoopRotarySwitchView loopRotarySwitchView;
    private TextView WelcomeTextview;
    private SimpleDraweeView  FirstSimpledraweeView, SecondSimpledraweeView, ThirdSimpledraweeView, FrothSimpledraweeView,FifthSimpledraweeView;
    private int width;

    private LinearLayout welcomeLinearLayout;
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
       // welcomeRelativelayout = () findViewById(R.id.welcome_rlayout);
        welcomeLinearLayout = (LinearLayout) findViewById(R.id.welcome_llayout);
        initView();
        getwelcometextview();
        Intent intent=new Intent(this,ScanService.class);
        startService(intent);
        //onclick();
        if("Main".equals(getIntent().getStringExtra("from"))){
            startTime = System.currentTimeMillis();
        }
        EventBus.getDefault().register(this);




    }

    private void onclick() {
        welcomeLinearLayout.setOnClickListener(new View.OnClickListener() {
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




        private void initView() {
        loopRotarySwitchView = (LoopRotarySwitchView) findViewById(R.id.mLoopRotarySwitchView);
        WelcomeTextview = (TextView) findViewById(R.id.welcome_tv);
        // SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.test);
        FirstSimpledraweeView = (SimpleDraweeView) findViewById(R.id.welcome_image_first);
        SecondSimpledraweeView = (SimpleDraweeView) findViewById(R.id.welcome_image_second);
        ThirdSimpledraweeView = (SimpleDraweeView) findViewById(R.id.welcome_image_third);
        FrothSimpledraweeView = (SimpleDraweeView) findViewById(R.id.welcome_image_frouth);
        FifthSimpledraweeView = (SimpleDraweeView) findViewById(R.id.welcome_image_fifth);
            DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;
        Uri uri1 = Uri.parse("http://192.168.0.32:8069/api/photo1");
            Uri uri2 = Uri.parse("http://192.168.0.32:8069/api/photo2");
            Uri uri3 = Uri.parse("http://192.168.0.32:8069/api/photo3");
            Uri uri4 = Uri.parse("http://192.168.0.32:8069/api/photo4");
            Uri uri5 = Uri.parse("http://192.168.0.32:8069/api/photo5");

        FirstSimpledraweeView.setImageURI(uri1);
        SecondSimpledraweeView.setImageURI(uri2);
        ThirdSimpledraweeView.setImageURI(uri3);
        FrothSimpledraweeView.setImageURI(uri4);
        FifthSimpledraweeView.setImageURI(uri5);
        loopRotarySwitchView
                .setR(width/3)//设置R的大小
                .setAutoRotation(true)//是否自动切换
                .setAutoRotationTime(2000);//自动切换的时间  单位毫秒
    }
        public void getwelcometextview(){
           // ApiFactory.BASE_URL="http://192.168.0.32:8069/api/";
        ApiService apiService = ApiFactory.getInstance().getApi(ApiService.class);

//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//            builder.connectTimeout(5, TimeUnit.SECONDS)
//                    .addInterceptor(interceptor).addNetworkInterceptor(new StethoInterceptor());
//           Retrofit retrofit = new Retrofit.Builder()
//                    .client(builder.build())
//                    .baseUrl("http://192.168.0.32:8069/api/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .build();

           // ApiService apiService = retrofit.create(ApiService.class);


        apiService.getwelcome()
                .retry(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((r) ->
                {
                    System.out.println("/////////////////////////////////////"+r+"////////////////////////////////////////");
               WelcomeTextview.setText(r);
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
