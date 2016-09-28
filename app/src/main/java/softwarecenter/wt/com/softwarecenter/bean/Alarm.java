package softwarecenter.wt.com.softwarecenter.bean;

/**
 * Created by kouzeping on 2016/9/27.
 * email：kouzeping@shmingjiang.org.cn
 */

public class Alarm {
    private  String id;
    private int station;
    private String device;
    private String warning_time;
    private String warning_info;

    public Alarm(String id, int station, String device, String warning_time, String warning_info) {
        this.id = id;
        this.station = station;
        this.device = device;
        this.warning_time = warning_time;
        this.warning_info = warning_info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getWarning_time() {
        return warning_time;
    }

    public void setWarning_time(String warning_time) {
        this.warning_time = warning_time;
    }

    public String getWarning_info() {
        return warning_info;
    }

    public void setWarning_info(String warning_info) {
        this.warning_info = warning_info;
    }
}
