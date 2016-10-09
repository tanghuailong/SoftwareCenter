package softwarecenter.wt.com.softwarecenter.bean;

/**
 * Created by chenggong on 2016/9/30.
 */

public class Staff {
    private String staff;
    private String login_device;
    private String login_datetime;
    private String logout_datetime;
    private Double hours;

    public Staff(String staff, String login_device, String login_datetime, String logout_datetime, Double hours) {
        this.staff = staff;
        this.login_device = login_device;
        this.login_datetime = login_datetime;
        this.logout_datetime = logout_datetime;
        this.hours = hours;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getLogin_device() {
        return login_device;
    }

    public void setLogin_device(String login_device) {
        this.login_device = login_device;
    }

    public String getLogin_datetime() {
        return login_datetime;
    }

    public void setLogin_datetime(String login_datetime) {
        this.login_datetime = login_datetime;
    }

    public String getLogout_datetime() {
        return logout_datetime;
    }

    public void setLogout_datetime(String logout_datetime) {
        this.logout_datetime = logout_datetime;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staff='" + staff + '\'' +
                ", login_device='" + login_device + '\'' +
                ", login_datetime='" + login_datetime + '\'' +
                ", logout_datetime='" + logout_datetime + '\'' +
                ", hours=" + hours +
                '}';
    }
}
