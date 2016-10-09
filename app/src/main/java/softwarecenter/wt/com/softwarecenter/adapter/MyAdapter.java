package softwarecenter.wt.com.softwarecenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import softwarecenter.wt.com.softwarecenter.R;
import softwarecenter.wt.com.softwarecenter.bean.Order;

/**
 * Created by chogo on 2016/9/26.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.viewHolder>{
    private LayoutInflater mInflater;
    private View mView;
    private List<Order> mDatas;
    private Context mContext;

    public MyAdapter(Context mContext, List<Order> datas) {
        this.mContext = mContext;
        this.mDatas = datas;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater=LayoutInflater.from(mContext);
        mView=mInflater.inflate(R.layout.template_grid_list,null);
        return new viewHolder(mView);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
      /*  if(mDatas!=null){

        }*/
        Order order=mDatas.get(position);
        holder.text_name.setText(order.getName());
        //holder.text_name.setText(Html.fromHtml("<u>"+order.getName()+"</u>"));
        holder.text_product_line.setText(order.getProduct_line());
        holder.text_order_qty.setText(order.getOrder_qty()+"");
        holder.text_start_time.setText(order.getStart_time());
        holder.text_end_date.setText(order.getEnd_date());
        holder.text_product.setText(order.getProduct());
        holder.text_status.setText(order.getStatus());
        holder.text_priority.setText(order.getPriority()+"");
        holder.text_notes.setText(order.getNotes());
        holder.text_order_bom.setText(order.getOrder_bom());
        if(order.isPostpone_label()){
            holder.text_postpone_label.setText("可延迟");
        }else{
            holder.text_postpone_label.setText("不可延迟");
        }
        if(order.isAhead_time_label()){
            holder.text_ahead_time_label.setText("可以提前");
        }else{
            holder.text_ahead_time_label.setText("不可提前");
        }
    }

    @Override
    public int getItemCount() {
        if(mDatas!=null && mDatas.size()>0){
            return mDatas.size();
        }else{
            return 0;
        }

    }

    class viewHolder extends RecyclerView.ViewHolder{
        private TextView text_name;//订单名称
        private TextView text_product_line;//生产线
        private TextView text_order_qty;//订单数量
        private TextView text_start_time;//开始时间
        private TextView text_end_date;//结束时间
        private TextView text_product;//产品
        private TextView text_status;//订单状态
        private TextView text_priority;//优先级
        private TextView text_notes;//备注
        private TextView text_order_bom;//物料清单
        private TextView text_postpone_label;//是否可延迟订单
        private TextView text_ahead_time_label;//是否可提前

        public viewHolder(View itemView) {
            super(itemView);

            text_name= (TextView) itemView.findViewById(R.id.text_name);
            text_product_line= (TextView) itemView.findViewById(R.id.text_line);
            text_order_qty= (TextView) itemView.findViewById(R.id.text_order_qty);
            text_start_time= (TextView) itemView.findViewById(R.id.text_start_time);
            text_end_date= (TextView) itemView.findViewById(R.id.text_end_date);
            text_product= (TextView) itemView.findViewById(R.id.text_product);
            text_status= (TextView) itemView.findViewById(R.id.text_status);
            text_priority= (TextView) itemView.findViewById(R.id.text_priority);
            text_notes= (TextView) itemView.findViewById(R.id.text_notes);
            text_order_bom= (TextView) itemView.findViewById(R.id.text_order_bom);
            text_postpone_label= (TextView) itemView.findViewById(R.id.text_postpone_label);
            text_ahead_time_label= (TextView) itemView.findViewById(R.id.text_ahead_time_label);

        }
    }
}
