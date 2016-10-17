package softwarecenter.wt.com.softwarecenter.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import softwarecenter.wt.com.softwarecenter.R;
import softwarecenter.wt.com.softwarecenter.adapter.DeviceConAdapter;
import softwarecenter.wt.com.softwarecenter.adapter.DividerGridItemDecoration;
import softwarecenter.wt.com.softwarecenter.adapter.DividerItemDecoration;
import softwarecenter.wt.com.softwarecenter.adapter.OrderProgressAdapter;
import softwarecenter.wt.com.softwarecenter.adapter.QualityCheckAdapter;
import softwarecenter.wt.com.softwarecenter.bean.Orders;
import softwarecenter.wt.com.softwarecenter.common.ApiFactory;
import softwarecenter.wt.com.softwarecenter.event.EventDevices;
import softwarecenter.wt.com.softwarecenter.event.EventProgress;
import softwarecenter.wt.com.softwarecenter.event.EventQuanCheck;
import softwarecenter.wt.com.softwarecenter.service.ApiService;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndustrialContrlFragment extends Fragment {

    private final static String TAG="IndustrialContrl";

    @BindView(R.id.recyclerView_progress)
    RecyclerView mRecyclerView;

    @BindView(R.id.recyclerView_info)
    RecyclerView infoRecyclerView;

    @BindView(R.id.pieCahrt)
    PieChart pieChart;


    @BindView(R.id.recyclerView_check)
    RecyclerView recyclerView_check;

    PieData pieData;//饼图数据

    private OrderProgressAdapter mAdapter;//订单进度适配器

    private DeviceConAdapter infoAdapter;//设备控制管理适配器

    private QualityCheckAdapter checkAdapter;

    List<EventQuanCheck.RecordInfoBean> recordDatas=new ArrayList<>();

    double [] ngRadio;//定义不合格项的比例的数组

    List<PieEntry> entryValues=new ArrayList<>();

    int ngLength=0;

    private ApiService apiService= ApiFactory.getInstance().getApi(ApiService.class);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_industrial_contrl, container, false);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this,view);

        //init();
        return view;
    }

    /**
     * 接收订阅的订单进度数据
     * @param eventProgresses
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getOrderProgress(EventProgress eventProgresses){
            showOrderProgress(eventProgresses);


    }

    /**
     * 展示订单进度数据
     * @param eventProgresses
     */
    public void showOrderProgress(EventProgress eventProgresses) {
        List<Orders> orderses=eventProgresses.getOrders();
        mAdapter=new OrderProgressAdapter(getContext(),orderses);
        Log.d(TAG,eventProgresses.getOrders().toString());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 接收设备控制管理信息
     * @param devices
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDeviceControl(EventDevices devices){
        showDeviceInfo(devices);
    }

    /**
     * 展示设备控制管理信息
     * @param devices
     */
    public void showDeviceInfo(EventDevices devices) {
        List<EventDevices.DevicesInfoBean> devicesInfoBeanList=devices.getDevices_info();
        infoAdapter=new DeviceConAdapter(getContext(),devicesInfoBeanList);
        infoRecyclerView.setAdapter(infoAdapter);
        infoRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        infoRecyclerView.addItemDecoration(new DividerGridItemDecoration(getContext()));
        infoRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 接收质检数据
     * @param checkDatas
     */

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCheckData(EventQuanCheck checkDatas){

        List<EventQuanCheck.NgDetailBean> ngLists=checkDatas.getNg_detail();//得到不合格项数组

        recordDatas=checkDatas.getRecord_info();

        showRecordInfo(recordDatas);
        int ngLength=ngLists.size();//不合格项数组大小

        PieData pieData=getPieData(checkDatas,(ngLength+1),100);

        showChart(pieChart,pieData);

    }

    /**
     * 展示质检信息
     */
    private void showRecordInfo(List<EventQuanCheck.RecordInfoBean> mDatas) {
        checkAdapter=new QualityCheckAdapter(getContext(),mDatas);
        recyclerView_check.setAdapter(checkAdapter);
        recyclerView_check.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView_check.addItemDecoration(new DividerGridItemDecoration(getContext()));
        recyclerView_check.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 返回饼状图数据
     * @param count
     * @param range
     * @return
     */
    private PieData getPieData(EventQuanCheck checkDatas,int count,float range){
        entryValues.clear();
        double okradio=checkDatas.getOk_ratio();//得到合格率
        entryValues.add(new PieEntry((int)(okradio*100),"合格项"));

        List<EventQuanCheck.NgDetailBean> ngLists=checkDatas.getNg_detail();
        ngLength=ngLists.size();
        ngRadio=new double[ngLength];
        for(int i=0;i<ngLength;i++){
            ngRadio[i]=ngLists.get(i).getRatio();

            entryValues.add(new PieEntry((int)(ngRadio[i]*100),ngLists.get(i).getCategory_name()));
        }


        PieDataSet pieDataSet=new PieDataSet(entryValues,"质检记录");
        pieDataSet.setSliceSpace(0f);

        List<Integer> colors=new ArrayList<Integer>();

        // 饼图颜色
        colors.add(Color.rgb(198,255,141));
        colors.add(Color.rgb(255,247,141));
        colors.add(Color.rgb(255,211,141));
        colors.add(Color.rgb(141,236,255));

        pieDataSet.setColors(colors);

        DisplayMetrics metrics=getResources().getDisplayMetrics();

        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度

        PieData pieData = new PieData(pieDataSet);


        return pieData;
    }

    /**
     * 显示饼图数据
     * @param pieChart
     * @param pieData
     */
    private void showChart(PieChart pieChart,PieData pieData){

        //pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(40f);  //半径
        pieChart.setTransparentCircleRadius(64f); // 半透明圈
//pieChart.setHoleRadius(0)  //实心圆
        pieChart.setDescription("");
        pieChart.setDrawCenterText(true);  //饼状图中间可以添加文字
        pieChart.setDrawHoleEnabled(true);
        pieChart.setRotationAngle(90); // 初始旋转角度

        pieChart.setRotationEnabled(true); // 可以手动旋转
// display percentage values
        pieChart.setUsePercentValues(true);  //显示成百分比

        pieChart.setCenterText("Quality Check");  //饼状图中间的文字
//设置数据
        pieChart.setData(pieData);

        Legend mLegend = pieChart.getLegend();  //设置比例图
        mLegend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);  //最右边显示
//mLegend.setForm(Legend.LegendForm.LINE);  //设置比例图的形状，默认是方形
        mLegend.setXEntrySpace(10f);
        mLegend.setYEntrySpace(10f);
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
