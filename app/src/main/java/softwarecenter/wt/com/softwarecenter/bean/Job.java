package softwarecenter.wt.com.softwarecenter.bean;


import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

import lombok.Data;

/**
 * Created by tanghuailong on 2016/10/10
 */

/**
 * 工单类，工单的基本信息
 */


@Data
public class Job implements Parent<Process> {


    private int job_start_time;
    private String job_id;
    private String product_desc;
    private String order_id;
    private int job_content;
    private String order_name;
    private String state;
    private int deadline;
    private String job_start_date;
    private int job_end_time;
    private String job_end_date;
    private String product_id;

    private List<Process> mProcesses;


    @Override
    public List<Process> getChildList() {
        return mProcesses;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return true;
    }


}
