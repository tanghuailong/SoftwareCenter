package softwarecenter.wt.com.softwarecenter.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.security.InvalidParameterException;

import android_serialport_api.ComBean;
import android_serialport_api.SerialHelper;


/*
* 串口服务类
* 这里可以控制串口的启动和串口数据的分发
* */
public class ScanService extends Service {
    private final static String TAG = "ComService";
    private final static String STR_BAUD_RATE = "115200"/*"9600"*/;
    private final static String STR_DEV_ONE = "/dev/ttyS2";
    private final static String STR_DEV_TWO = "/dev/ttyS3";

    SerialControl comA, comB;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        closeComPort(comA);
        closeComPort(comB);
        EventBus.getDefault().unregister(this);
    }


    public void initData() {
        comA = new SerialControl();
        comB = new SerialControl();
        comA.setPort(STR_DEV_ONE);
        comB.setPort(STR_DEV_TWO);
        comA.setBaudRate(STR_BAUD_RATE);
        comB.setBaudRate(STR_BAUD_RATE);
        openComPort(comA);
        openComPort(comB);
    }

    private class SerialControl extends SerialHelper {
        public SerialControl() {
        }

        @Override
        protected void onDataReceived(final ComBean comRecData) {
            System.out.println("-----------------------------------");
           // L.d("receive data = " + new String(comRecData.bRec));
            System.out.println("-----------------------------------");
            System.out.println("receive data = " + new String(comRecData.bRec));
 //ApiService apiService = ApiFactory.getApi(ApiService.class);
            //Observable<String> abstring = apiService.getOrdersll(new String(comRecData.bRec));

            //通过EventBus将读取的串口数据交给应用的地方
//            EventScan comevent = new EventScan(new String(comRecData.bRec));
            EventBus.getDefault().post(new String(comRecData.bRec));
        }
    }

    /**
     * 打开串口。
     * @param ComPort
     */
    private void openComPort(SerialHelper ComPort) {
        try {
            ComPort.open();
           // showMessage(R.string.com_start_success);
            System.out.println("-------------------打开串口成功");
        } catch (SecurityException e) {
           // showMessage(R.string.com_start_fail_security);
            System.out.println("-------------------没有串口读写权限");
        } catch (IOException e) {
           // showMessage(R.string.com_start_fail_io);
            System.out.println("-------------------未知错误");
        } catch (InvalidParameterException e) {
          //  showMessage(R.string.com_start_fail_param);
            System.out.println("-------------------参数错误");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 关闭串口。
     * @param ComPort
     */
    private void closeComPort(SerialHelper ComPort){
        ComPort.close();
    }

    private void showMessage(int sMsg) {
        Toast.makeText(this.getApplicationContext(),
                getString(sMsg), Toast.LENGTH_LONG).show();
    }
}
