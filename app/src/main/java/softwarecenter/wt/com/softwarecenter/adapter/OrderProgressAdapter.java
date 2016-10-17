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
import softwarecenter.wt.com.softwarecenter.bean.Orders;

/**
 * Created by chenggong on 2016/10/11.
 */

public class OrderProgressAdapter extends RecyclerView.Adapter<OrderProgressAdapter.ViewHolder> {

    private Context mContext;
    private List<Orders> mDatas;
    private LayoutInflater mInflater;

    public OrderProgressAdapter(Context context, List<Orders> progresses){
        this.mContext=context;
        this.mDatas=progresses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater=LayoutInflater.from(mContext);
        View view=mInflater.inflate(R.layout.template_progress_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Orders orders = mDatas.get(position);
        holder.text_order_progress.setText(orders.getProduct_desc());
        //holder.numberProgressBar.setProgress(Integer.parseInt(new java.text.DecimalFormat("0").format(100*orders.getOrder_ratio())));
        holder.numberProgressBar.setProgress((int)(orders.getOrder_ratio()*100));
    }

    @Override
    public int getItemCount() {
        if(mDatas!=null&&mDatas.size()>0){
            return mDatas.size();
        }else{
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text_order_progress;
        private NumberProgressBar numberProgressBar;
        public ViewHolder(View itemView) {
            super(itemView);

            text_order_progress= (TextView) itemView.findViewById(R.id.text_product_name);
            numberProgressBar= (NumberProgressBar) itemView.findViewById(R.id.number_progress_bar);
        }
    }

}
