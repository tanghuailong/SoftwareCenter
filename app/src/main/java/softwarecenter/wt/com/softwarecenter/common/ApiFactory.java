package softwarecenter.wt.com.softwarecenter.common;

import java.util.concurrent.TimeUnit;

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
    private static final String BASE_URL = "";
    private volatile static ApiFactory INSTANCE = null;
    private Retrofit retrofit = null;


    private  ApiFactory() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor);
         retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
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

    public<T> T getApi(Class<T> t) {
        return  retrofit.create(t);
    }
}
