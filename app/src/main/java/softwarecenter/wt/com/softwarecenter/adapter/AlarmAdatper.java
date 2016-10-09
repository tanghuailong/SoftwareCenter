package softwarecenter.wt.com.softwarecenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import softwarecenter.wt.com.softwarecenter.R;
import softwarecenter.wt.com.softwarecenter.event.EventAlarm;


public class AlarmAdatper extends RecyclerView.Adapter<AlarmAdatper.ViewHolder> {
    private LayoutInflater inflater;
    private List<EventAlarm> mDatas;
    private Context mContext;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.alarm_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EventAlarm alarmInfo=mDatas.get(position);

        holder.DeviceName.setText(alarmInfo.getDevice());
        holder.Station.setText(alarmInfo.getStation());
        holder.AlarmDate.setText(alarmInfo.getDate());
        holder.AlarmMeaasge.setText(alarmInfo.getMessage());
        holder.state.setText(alarmInfo.getState());
    }
    public AlarmAdatper(Context context,List<EventAlarm> datas){
        this.mContext=context;
        mDatas = datas;
    }

    @Override
    public int getItemCount() {

            return mDatas.size();

    }
    class  ViewHolder extends  RecyclerView.ViewHolder {


        private TextView DeviceName;
        private TextView Station;
        private TextView AlarmDate;
        private TextView AlarmMeaasge;
        private TextView state;


        public ViewHolder(View itemView) {

            super(itemView);
            DeviceName = (TextView) itemView.findViewById(R.id.text_devicename);
            Station = (TextView) itemView.findViewById(R.id.text_station);
            AlarmDate = (TextView) itemView.findViewById(R.id.text_alarmdate);
            AlarmMeaasge = (TextView) itemView.findViewById(R.id.text_alarminfo);
            state= (TextView) itemView.findViewById(R.id.text_state);

        }
    }
}
