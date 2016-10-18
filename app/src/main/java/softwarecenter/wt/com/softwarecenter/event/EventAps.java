package softwarecenter.wt.com.softwarecenter.event;

import java.util.List;

import lombok.Data;
import softwarecenter.wt.com.softwarecenter.bean.Process;
import softwarecenter.wt.com.softwarecenter.bean.RawJob;

/**
 * Created by tanghuailong on 2016/10/17.
 */
@Data
public class EventAps {
    private String msg;
    private List<Process> processes;
    private List<RawJob> jobs;
    private String result;
}
