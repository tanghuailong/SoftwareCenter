package softwarecenter.wt.com.softwarecenter.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by tanghuailong on 2016/10/10.
 */

/**
 * 工序类，工序的一些基本信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Process {

    private String process_id;
    private String operation_dct_name;
    private int start_time;
    private int over_time;
    private String job_id;
    private String over_date;
    private String state;
    private String arranged_machine_station_name;
    private String curr_step_id;
    private String arranged_machine_station_id;
    private String start_date;
    private String curr_step_dct_name;
    private String operation_id;

}
