package softwarecenter.wt.com.softwarecenter.event;

/**
 * Created by tanghuailong on 2016/9/28.
 */
public class EventTest {

    private String msg;

    public EventTest() {

    }
    public EventTest(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "EventTest{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
