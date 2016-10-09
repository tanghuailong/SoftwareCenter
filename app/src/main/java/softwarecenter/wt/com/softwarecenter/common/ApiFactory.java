package softwarecenter.wt.com.softwarecenter.common;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tanghuailong on 2016/9/26.
 */
public class ApiFactory {
    private static final long DEFAULT_TIMEOUT  = 5;
    private static final String BASE_URL = "http://192.168.0.72:8069/api/";
    private volatile static ApiFactory INSTANCE = null;
    private Retrofit retrofit = null;
    public static String sessionID = "";




    private  ApiFactory() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor).addNetworkInterceptor(new StethoInterceptor());

         retrofit = new Retrofit.Builder()
                .client(builder.build())
                 .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    public static ApiFactory getInstance() {
        if(INSTANCE == null) {
            synchronized (ApiFactory.class) {
                if(INSTANCE == null) {
                    INSTANCE = new ApiFactory();
                }
            }
        }
        return INSTANCE;
    }


    //添加session ID
    public static Cookie getSessionID() {
        if(!sessionID.isEmpty()) {
            return new Cookie.Builder()
                    .domain("www.wtkj.com")
                    .path("/")
                    .name("JSESSIONID")
                    .value(sessionID)
                    .secure()
                    .build();
        }
        return null;
    }


    public<T> T getApi(Class<T> t) {
        return  retrofit.create(t);
    }
}
