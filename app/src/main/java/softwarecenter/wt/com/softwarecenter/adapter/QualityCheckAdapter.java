package softwarecenter.wt.com.softwarecenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import softwarecenter.wt.com.softwarecenter.R;
import softwarecenter.wt.com.softwarecenter.event.EventQuanCheck;

/**
 * Created by chenggong on 2016/10/15.
 */

public class QualityCheckAdapter extends RecyclerView.Adapter<QualityCheckAdapter.ViewHolder> {

    Context mContext;
    private List<EventQuanCheck.RecordInfoBean> recordDatas;
    LayoutInflater mInflater;

    public QualityCheckAdapter(Context mContext, List<EventQuanCheck.RecordInfoBean> recordDatas) {
        this.mContext = mContext;
        this.recordDatas = recordDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater=LayoutInflater.from(mContext);
        View view=mInflater.inflate(R.layout.template_check_quality,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text_product_desc.setText(recordDatas.get(position).getProduct_desc());
        holder.text_product_id.setText(recordDatas.get(position).getProduct_id());
        holder.text_quality_post_name.setText(recordDatas.get(position).getQuality_post_name());
        holder.text_quality_post_id.setText(recordDatas.get(position).getQuality_post_id());
        holder.text_through_post_time.setText(recordDatas.get(position).getThrough_post_time());
        holder.text_category_name.setText(recordDatas.get(position).getCategory_name());
        holder.text_category_code.setText(recordDatas.get(position).getCategory_code());
        holder.text_quality_result.setText(recordDatas.get(position).getQuality_result());
    }

    @Override
    public int getItemCount() {
        if(recordDatas!=null&& recordDatas.size()>0){
            return recordDatas.size();
        }else{
            return 0;
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView text_product_desc;
        private TextView text_product_id;
        private TextView text_quality_post_name;
        private TextView text_quality_post_id;
        private TextView text_through_post_time;
        private TextView text_category_name;
        private TextView text_category_code;
        private TextView text_quality_result;


        public ViewHolder(View itemView) {
            super(itemView);

            text_product_desc= (TextView) itemView.findViewById(R.id.text_product_desc);
            text_product_id= (TextView) itemView.findViewById(R.id.text_product_id);
            text_quality_post_name= (TextView) itemView.findViewById(R.id.text_quality_post_name);
            text_quality_post_id= (TextView) itemView.findViewById(R.id.text_quality_post_id);
            text_through_post_time= (TextView) itemView.findViewById(R.id.text_through_post_time);
            text_category_name= (TextView) itemView.findViewById(R.id.text_category_name);
            text_category_code= (TextView) itemView.findViewById(R.id.text_category_code);
            text_quality_result= (TextView) itemView.findViewById(R.id.text_quality_result);
        }
    }
}
