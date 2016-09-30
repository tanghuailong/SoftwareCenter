package softwarecenter.wt.com.softwarecenter.event;

/**
 * Created by tanghuailong on 2016/9/29.
 */
public class EventAlarm {

    private String device;
    private String data;
    private String message;
    private String state;
    private String device_code;


    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDevice_code() {
        return device_code;
    }

    public void setDevice_code(String device_code) {
        this.device_code = device_code;
    }
}
