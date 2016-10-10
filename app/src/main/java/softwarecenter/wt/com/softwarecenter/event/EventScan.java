package softwarecenter.wt.com.softwarecenter.event;

/**
 * Created by tanghuailong on 2016/10/10.
 */
public class EventScan {
    private String message;

    public EventScan() {

    }
    public EventScan(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
