package softwarecenter.wt.com.softwarecenter.event;

/**
 * Created by tanghuailong on 2016/9/29.
 */
public class EventAlarm {

    private String device;
    private String date;
    private String message;
    private String state;
    private String station;
    private String device_code;


    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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


    @Override
    public String toString() {
        return "EventAlarm{" +
                "device='" + device + '\'' +
                ", date='" + date + '\'' +
                ", message='" + message + '\'' +
                ", state='" + state + '\'' +
                ", device_code='" + device_code + '\'' +
                ", station='" + station + '\'' +
                '}';
    }

    public EventAlarm(String device, String date, String message, String state, String device_code, String station) {
        this.device = device;
        this.date = date;
        this.message = message;
        this.state = state;
        this.device_code = device_code;
        this.station = station;
    }
}
