package softwarecenter.wt.com.softwarecenter.common;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import softwarecenter.wt.com.softwarecenter.R;
import softwarecenter.wt.com.softwarecenter.bean.Job;


/**
 * Created by tanghuailong on 2016/10/16.
 */
public class JobViewHolder extends ParentViewHolder {

    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;


    @BindView(R.id.parent_image)
    ImageView imageView;
    @BindView(R.id.parent_text)
    TextView textView;

    public JobViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(@NonNull Job job) {
        textView.setText(job.getProduct_desc());
    }

    @SuppressLint("NewApi")
    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);
        if(expanded) {
            imageView.setImageResource(R.mipmap.icon_aps_process_arrow_down);
        }else {
            imageView.setImageResource(R.mipmap.icon_aps_process_arrow_up);
        }
    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        super.onExpansionToggled(expanded);

    }
}
