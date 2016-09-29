package softwarecenter.wt.com.softwarecenter.event;


/**
 * Created by tanghuailong on 2016/9/28.
 */
public class EventLost {
    private int status;
    private String msg;

    public EventLost(int status,String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
