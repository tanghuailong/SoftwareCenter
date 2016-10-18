package softwarecenter.wt.com.softwarecenter.bean;

import lombok.Data;

/**
 * Created by tanghuailong on 2016/10/17.
 */
@Data
public class RawJob {
    private int job_start_time;
    private String job_id;
    private String product_desc;
    private int order_id;
    private int job_content;
    private String order_name;
    private String state;
    private int deadline;
    private String job_start_date;
    private int job_end_time;
    private String job_end_date;
    private String product_id;
}
