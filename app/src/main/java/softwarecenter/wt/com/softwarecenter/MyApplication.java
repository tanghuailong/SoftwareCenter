package softwarecenter.wt.com.softwarecenter;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by duanshiyao on 2016/10/10.
 * email：duanshiyao@vtstar.net
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
