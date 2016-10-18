package softwarecenter.wt.com.softwarecenter.service;


import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import softwarecenter.wt.com.softwarecenter.bean.LoginResult;
import softwarecenter.wt.com.softwarecenter.bean.Staff;
import softwarecenter.wt.com.softwarecenter.event.EventAps;
import softwarecenter.wt.com.softwarecenter.event.EventOrder;

/**
 * Created by tanghuailong on 2016/9/26.
 */
public interface ApiService {
    @GET("orders")
    Observable<List<EventOrder>> getOrders();
    @GET("attendance")
    Observable<List<Staff>> getStaffInfo();
    @GET("ic_login")
    Observable<LoginResult> getLoginResult(@Query("ic_code") String ic_code, @Query("device") String device);
    @GET("logout")
    Observable<LoginResult> getLogoutResult(@Query("ic_code") String ic_code);
    @GET("company")
    Observable<String> getwelcome();
    @GET("aps_result")
    Observable<EventAps> getApsResult();


}
