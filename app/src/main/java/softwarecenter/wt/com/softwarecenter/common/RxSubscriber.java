package softwarecenter.wt.com.softwarecenter.common;

import android.util.Log;

import rx.Subscriber;

/**
 * Created by tanghuailong on 2016/10/10.
 */
public abstract class RxSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {
        Log.d("RxSubscriber","completed");
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }
}
