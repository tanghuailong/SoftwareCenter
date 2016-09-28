package softwarecenter.wt.com.softwarecenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import softwarecenter.wt.com.softwarecenter.bean.Alarm;

/**
 * Created by kouzeping on 2016/9/23.
 * email：kouzeping@shmingjiang.org.cn
 */

public class AlarmAdatper extends RecyclerView.Adapter<AlarmAdatper.ViewHolder> {
    private LayoutInflater inflater;
    private List<Alarm> mDatas;
    @Override
    public AlarmAdatper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.alarm_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlarmAdatper.ViewHolder holder, int position) {
        Alarm arm=mDatas.get(position);

        holder.StationTextview.setText(String.valueOf(arm.getStation()));
        holder.DeivceTextView.setText(arm.getDevice());
        holder.AlarmtimeTextview.setText(arm.getWarning_time());
        holder.AlarmhintTextview.setText(arm.getWarning_info());
    }
    public AlarmAdatper(List<Alarm> datas){

        mDatas = datas;
    }

    @Override
    public int getItemCount() {

            return mDatas.size();

    }
    class  ViewHolder extends  RecyclerView.ViewHolder {


        private TextView StationTextview;
        private TextView DeivceTextView;
        private TextView AlarmtimeTextview;
        private TextView AlarmhintTextview;


        public ViewHolder(View itemView) {

            super(itemView);
            StationTextview = (TextView) itemView.findViewById(R.id.stationtextview);
            DeivceTextView = (TextView) itemView.findViewById(R.id.devicetextview);
            AlarmtimeTextview = (TextView) itemView.findViewById(R.id.alarm_time_tv);
            AlarmhintTextview = (TextView) itemView.findViewById(R.id.alarm_hint_tv);

        }
    }
}
