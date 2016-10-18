package softwarecenter.wt.com.softwarecenter.common;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import softwarecenter.wt.com.softwarecenter.R;
import softwarecenter.wt.com.softwarecenter.bean.Process;


/**
 * Created by tanghuailong on 2016/10/16.
 */
public class ProcessViewHolder extends ChildViewHolder {


    @BindView(R.id.child_operation_name)
    TextView childOperationName;
    @BindView(R.id.child_start_time)
    TextView childStartTime;
    @BindView(R.id.child_end_time)
    TextView childEndTime;


    public ProcessViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull Process process)  {

        childOperationName.setText(process.getOperation_dct_name());

        childStartTime.setText("开始时间 "+String.valueOf(process.getStart_time()));
        childEndTime.setText("结束时间 "+String.valueOf(process.getOver_time()));
    }
}
