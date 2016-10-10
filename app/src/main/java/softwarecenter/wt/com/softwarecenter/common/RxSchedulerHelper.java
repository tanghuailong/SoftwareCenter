package softwarecenter.wt.com.softwarecenter.common;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tanghuailong on 2016/10/10.
 */

/**
 * RxJava 线程封装的工具类
 */
public class RxSchedulerHelper {

    public static<T> Observable.Transformer<T,T> io_main() {
        return new Observable.Transformer<T,T>(){
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
