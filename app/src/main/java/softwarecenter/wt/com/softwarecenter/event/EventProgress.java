package softwarecenter.wt.com.softwarecenter.event;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import softwarecenter.wt.com.softwarecenter.bean.Orders;

/**
 * Created by chenggong on 2016/10/11.
 */
@AllArgsConstructor
@Data
public class EventProgress {
    private String msg;
    private String result;
    private List<Orders> orders;
}
