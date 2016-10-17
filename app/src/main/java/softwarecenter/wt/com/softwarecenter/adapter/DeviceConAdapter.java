package softwarecenter.wt.com.softwarecenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;

import java.util.List;

import softwarecenter.wt.com.softwarecenter.R;
import softwarecenter.wt.com.softwarecenter.event.EventDevices;

/**
 * Created by chenggong on 2016/10/13.
 */

public class DeviceConAdapter extends RecyclerView.Adapter<DeviceConAdapter.ViewHolder> {

    private List<EventDevices.DevicesInfoBean> mDatas;

    private LayoutInflater mInflater;

    private Context mContext;

    public DeviceConAdapter(Context mContext, List<EventDevices.DevicesInfoBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mInflater=LayoutInflater.from(mContext);
        View view=mInflater.inflate(R.layout.template_device_,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text_name.setText(mDatas.get(position).getName());
        holder.text_state.setText(mDatas.get(position).getState());
        holder.device_progress.setProgress((int)(mDatas.get(position).getSpeed()*100));
        holder.text_order_name.setText(mDatas.get(position).getCurr_order_name());
    }

    @Override
    public int getItemCount() {
        if(mDatas!=null && mDatas.size()>0){
            return mDatas.size();
        }else{
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{


        private TextView text_name;
        private  TextView text_state;
        private NumberProgressBar device_progress;
        private TextView text_order_name;

        public ViewHolder(View itemView) {
            super(itemView);

            text_name= (TextView) itemView.findViewById(R.id.text_device_name);
            text_state= (TextView) itemView.findViewById(R.id.text_device_state);
            device_progress= (NumberProgressBar) itemView.findViewById(R.id.device_progress);
            text_order_name= (TextView) itemView.findViewById(R.id.text_device_ordername);
        }
    }
}
