package softwarecenter.wt.com.softwarecenter.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.List;

import softwarecenter.wt.com.softwarecenter.R;
import softwarecenter.wt.com.softwarecenter.bean.Job;
import softwarecenter.wt.com.softwarecenter.bean.Process;
import softwarecenter.wt.com.softwarecenter.common.JobViewHolder;
import softwarecenter.wt.com.softwarecenter.common.ProcessViewHolder;


/**
 * Created by tanghuailong on 2016/10/16.
 */
public class ScheduleAdapter extends ExpandableRecyclerAdapter<Job, Process, JobViewHolder, ProcessViewHolder> {

    private static final int PARENT_VEGETARIAN = 0;
    private static final int PARENT_NORMAL = 1;
    private static final int CHILD_VEGETARIAN = 2;
    private static final int CHILD_NORMAL = 3;

    private List<Job> mJobs;
    private LayoutInflater mInflater;

    public ScheduleAdapter(Context context, @NonNull List<Job> jobs) {
        super(jobs);
        mJobs = jobs;
        mInflater = LayoutInflater.from(context);
    }

    public void resetItemValue(List<Job> allJobs){
        mJobs = allJobs;
        this.notifyDataSetChanged();
    }


    @UiThread
    @NonNull
    @Override
    public JobViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View jobView = mInflater.inflate(R.layout.job_view, parentViewGroup, false);
        return new JobViewHolder(jobView);
    }

    @NonNull
    @Override
    public ProcessViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View processView = mInflater.inflate(R.layout.process_view, childViewGroup, false);
        return new ProcessViewHolder(processView);
    }

    @UiThread
    @Override
    public void onBindParentViewHolder(@NonNull JobViewHolder jobViewHolder, int parentPosition, @NonNull Job job) {
        jobViewHolder.bind(job);
    }

    @UiThread
    @Override
    public void onBindChildViewHolder(@NonNull ProcessViewHolder processViewHolder, int parentPosition, int childPosition, @NonNull Process process) {
        processViewHolder.bind(process);
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
    }
}
