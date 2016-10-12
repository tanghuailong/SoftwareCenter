package softwarecenter.wt.com.softwarecenter.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import softwarecenter.wt.com.softwarecenter.R;
import softwarecenter.wt.com.softwarecenter.adapter.AlarmAdatper;
import softwarecenter.wt.com.softwarecenter.adapter.DividerGridItemDecoration;
import softwarecenter.wt.com.softwarecenter.adapter.DividerItemDecoration;
import softwarecenter.wt.com.softwarecenter.adapter.MyAdapter;
import softwarecenter.wt.com.softwarecenter.bean.Staff;
import softwarecenter.wt.com.softwarecenter.common.ApiFactory;
import softwarecenter.wt.com.softwarecenter.event.EventAlarm;
import softwarecenter.wt.com.softwarecenter.event.EventOrder;
import softwarecenter.wt.com.softwarecenter.service.ApiService;

/**
 * A simple {@link Fragment} subclass.
 * Create on 2016/09/22
 */
public class BasicFragment extends Fragment {
    private static final String LOG_TAG = "BasicFragment";
    @BindView(R.id.text_pre_name)
    TextView textPreName;
    @BindView(R.id.text_pre_device)
    TextView textPreDevice;
    @BindView(R.id.text_pre_login)
    TextView textPreLogin;
    @BindView(R.id.text_pre_logout)
    TextView textPreLogout;
    @BindView(R.id.text_pre_hours)
    TextView textPreHours;
    @BindView(R.id.text_now_name)
    TextView textNowName;
    @BindView(R.id.text_now_device)
    TextView textNowDevice;
    @BindView(R.id.text_now_login)
    TextView textNowLogin;
    @BindView(R.id.text_now_logout)
    TextView textNowLogout;
    @BindView(R.id.text_now_hours)
    TextView textNowHours;
    private View mView;
    public  List<Staff> staffs = new ArrayList<>();
    private List<EventOrder> mDatas=new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView AlarmRecyclerview;
    private MyAdapter mAdapter;
    private AlarmAdatper alarmAdatper;
    private SimpleDraweeView LaststaffSimpleview,NowstaffSimpleview;

    private static final String BASE_URL = "http://192.168.0.72:8069/api/";



    ApiService apiService = ApiFactory.getInstance().getApi(ApiService.class);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_basic, container, false);
        EventBus.getDefault().register(this);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
        AlarmRecyclerview = (RecyclerView) mView.findViewById(R.id.alarmrecyview);
        LaststaffSimpleview = (SimpleDraweeView) mView.findViewById(R.id.last_staff_image);
        NowstaffSimpleview = (SimpleDraweeView) mView.findViewById(R.id.now_staff_image);
        apiService.getOrders().retry(3).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((r) -> {
            mDatas = r;
            System.out.println(r);
            showOrderData();
        }, (e) -> {
            Log.e(LOG_TAG, e.getMessage());
        });
        apiService.getStaffInfo().retry(3).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((m) -> {
            staffs = m;
            System.out.println(m);
            showStaffInfo(staffs);

        },(e) -> {
            Log.e(LOG_TAG,e.getMessage());
        });
        ButterKnife.bind(this, mView);
        return mView;

    }



    /**
     * 展示员工信息，当前信息和上一个登录信息
     */
    private void showStaffInfo(List<Staff> staffs) {
        textPreName.setText(staffs.get(0).getStaff());
        textPreDevice.setText(staffs.get(0).getLogin_device());
        textPreLogin.setText(staffs.get(0).getLogin_datetime());
        textPreLogout.setText(staffs.get(0).getLogout_datetime());
        textPreHours.setText(staffs.get(0).getHours().toString());
        LaststaffSimpleview.setImageURI(Uri.parse(BASE_URL+staffs.get(0).getImg()));

        textNowName.setText(staffs.get(1).getStaff());
        textNowDevice.setText(staffs.get(1).getLogin_device());
        textNowLogin.setText(staffs.get(1).getLogin_datetime());
        textNowLogout.setText(staffs.get(1).getLogout_datetime());
        textNowHours.setText(staffs.get(1).getHours().toString());

        NowstaffSimpleview.setImageURI(Uri.parse(BASE_URL+staffs.get(1).getImg()));
    }

    /**
     * 展示订单数据
     */
    private void showOrderData() {
        mAdapter = new MyAdapter(getContext(), mDatas);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(getContext()));

    }

    /**
     * 展示报警信息
     */
    private void showAlarmData(List<EventAlarm> alarms) {
        alarmAdatper = new AlarmAdatper(getContext(), alarms);
        AlarmRecyclerview.setAdapter(alarmAdatper);
        AlarmRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        AlarmRecyclerview.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        AlarmRecyclerview.setItemAnimator(new DefaultItemAnimator());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getAlarm(List eventAlarms) {
        /*List<EventAlarm> eventAlarmList = Arrays.asList(eventAlarms);
        Log.d(LOG_TAG,eventAlarmList.toString());*/
        if(eventAlarms.get(0) instanceof EventAlarm) {
            showAlarmData(eventAlarms);
        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getOrders(List eventOrders){
        /*List<EventOrder> eventOrderList=Arrays.asList(eventOrders);
        Log.d(LOG_TAG,eventOrders[0].toString());*/
        if(eventOrders.get(0) instanceof EventOrder) {
            pushOrderData(eventOrders);
        }
    }

    private void pushOrderData(List<EventOrder> eventOrderList) {
        mAdapter = new MyAdapter(getContext(), eventOrderList);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(getContext()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
