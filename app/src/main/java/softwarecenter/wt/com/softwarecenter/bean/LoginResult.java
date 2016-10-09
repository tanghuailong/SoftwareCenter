package softwarecenter.wt.com.softwarecenter.bean;

/**
 * Created by chenggong on 2016/10/8.
 */

public class LoginResult {
    private String msg;
    private boolean code;

    public LoginResult(String msg, boolean code) {
        this.msg = msg;
        this.code = code;
    }
    public LoginResult(){

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }


}
