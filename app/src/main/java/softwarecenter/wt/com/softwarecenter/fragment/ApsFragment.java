package softwarecenter.wt.com.softwarecenter.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import softwarecenter.wt.com.softwarecenter.R;
import softwarecenter.wt.com.softwarecenter.adapter.ScheduleAdapter;
import softwarecenter.wt.com.softwarecenter.bean.Job;
import softwarecenter.wt.com.softwarecenter.bean.Process;
import softwarecenter.wt.com.softwarecenter.bean.RawJob;
import softwarecenter.wt.com.softwarecenter.common.ApiFactory;
import softwarecenter.wt.com.softwarecenter.common.RxSchedulerHelper;
import softwarecenter.wt.com.softwarecenter.common.RxSubscriber;
import softwarecenter.wt.com.softwarecenter.event.EventAps;
import softwarecenter.wt.com.softwarecenter.service.ApiService;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApsFragment extends Fragment {


    @BindView(R.id.aps_recylerView)
    RecyclerView recyclerView;

    private  ScheduleAdapter scheduleAdapter;

    private final List<Job> jobList = new ArrayList<>();

    private ApiService apiService = ApiFactory.getInstance().getApi(ApiService.class);

    public ApsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        EventBus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_aps,container,false);
        ButterKnife.bind(this,view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        scheduleAdapter  = new ScheduleAdapter(getActivity(),jobList);
        recyclerView.setAdapter(scheduleAdapter);

        return view;
    }





    @OnClick(R.id.expand_insert_button)
    public void insertWorkOrder(View v) {
        generateApsSchedulerByClick();
    }
    @OnClick(R.id.expand_refresh_button)
    public void refreshWorkOrder(View v) {
        generateApsSchedulerByClick();
    }

    private void generateApsSchedulerByClick() {
        apiService.getApsResult().compose(RxSchedulerHelper.io_main())
                .subscribe(new RxSubscriber<EventAps>() {
                    @Override
                    public void onNext(EventAps eventAps) {
                        generateApsSchedulerByMqtt(handleData(eventAps));
                    }
                });
    }
    private void generateApsSchedulerByMqtt(List<Job> jobs){
        recyclerView.setAdapter(new ScheduleAdapter(getActivity(),jobs));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getJobList(EventAps eventAps) {
        generateApsSchedulerByMqtt(handleData(eventAps));
    }

    private List<Job> handleData(EventAps eventAps){

        List<Process> processes = eventAps.getProcesses();
        Map<String,List<Process>> maps = Stream.of(processes).collect(Collectors.groupingBy(Process::getJob_id));
        processes = Stream.of(maps.entrySet()).flatMap((l) -> {
            List<Process> newList = l.getValue();
            Collections.sort(newList,(p1,p2) -> p1.getStart_time() - p2.getStart_time());
            return Stream.of(newList);
        }).collect(Collectors.toList());

        List<RawJob> rawJobs = eventAps.getJobs();
        List<Job> jobs = Stream.of(rawJobs)
                .sorted((j1, j2) -> Integer.valueOf(j1.getJob_id()) -  Integer.valueOf(j2.getJob_id()))
                .map((j) -> {
                    Job job = new Job();
                    job.setJob_id(j.getJob_id());
                    job.setProduct_desc(j.getProduct_desc());
                    return job;
                }).collect(Collectors.toList());

        for(Job job: jobs) {
            List<Process> sigleJobProcess = Stream.of(processes)
                    .filter((p) -> p.getJob_id().equals(job.getJob_id()))
                    .collect(Collectors.toList());
            job.setMProcesses(sigleJobProcess);
        }
        return jobs;
    }

    /**
     * 保存状态
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        scheduleAdapter.onSaveInstanceState(outState);
    }

    /**
     * 取出状态
     * @param savedInstanceState
     */
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        scheduleAdapter.onRestoreInstanceState(savedInstanceState);
    }
}
