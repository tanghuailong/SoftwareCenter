package softwarecenter.wt.com.softwarecenter.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import FWPubLib.pl_javacall;
import Lib.FWReader.S8.function_S8;

public class SwipeCardService extends Service {
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    private function_S8 call_s8;
    private pl_javacall publib;
    public char gl_autoRun = 0, gl_autoRunning = 0;
    public char gl_singleTestInAutoRunning = 0;
    AutoTestThread mAutoThread= null;
    public Handler m_Handler;
    public String gl_msg, gl_autoBtnText;
    String devPath="/dev/ttySAC2";
    int baud=115200;

    public static final char UI_UPDATE_BTN_AUTO = 1;
    public static final char UI_UPDATE_BTN_MANUAL_DISABLE = 2;
    public static final char UI_UPDATE_BTN_MANUAL_ENABLE = 3;
    public static final char UI_UPDATE_MSG_TEXT_APPEND = 4;
    public static final char UI_UPDATE_MSG_TEXT_SET	 = 5;

    public static final char PT_USB = 2;

    int struct_portType = PT_USB;

    private static final  String LOG_TAG = "TestService";
    public  boolean isKill = false;
    private final IBinder myBinder = new MyBinder();


    @Override
    public void onCreate() {
        super.onCreate();
        m_Handler = new Handler()
        {
            public void handleMessage(Message msg)
            {
                switch (msg.what)
                {
                    case UI_UPDATE_MSG_TEXT_APPEND:
                    case UI_UPDATE_MSG_TEXT_SET:

                        //System.out.println(gl_msg.substring(0,8));
                        break;
                    case UI_UPDATE_BTN_AUTO:
                        System.out.println(gl_autoBtnText);
                        break;
                    case UI_UPDATE_BTN_MANUAL_DISABLE:
                        break;
                    case UI_UPDATE_BTN_MANUAL_ENABLE:
                        break;
                    default :
                        break;
                }
            }
        };

        Log.d(LOG_TAG,"onCreate");
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public void test(function_S8 s8) {

        publib = new pl_javacall();
        call_s8 = s8;
        call_s8.SetTransPara(0x20, 1137,41234);
        System.out.println("Test Success");
        if(0 == gl_autoRun)
        {
            gl_autoRunning = 1;

            mAutoThread = new AutoTestThread();
            mAutoThread.start();
        }
        else
        {
            gl_autoRunning = 0;
        }
    }

    public class MyBinder extends Binder {
        public SwipeCardService getService() {
            return SwipeCardService.this;
        }
    }
    public String TestM1(int portType, String path, int baudrate) {
        // TODO Auto-generated method stub
        int result = 0, hdev=1;
        char[] pModVer = new char[512];
        char[] pSnrM1 = new char[255];
        char[] pCharHex = new char[255];
        char[] pCharSingle = new char[255];
        int lenSingleChar=2, lenHex;
        short tblk = 24;
        short tSec = (short)(tblk/4);
        short keymode = 0;
        char[] defKey = {0xff,0xff,0xff,0xff,0xff,0xff};

        lenHex = 2*lenSingleChar;

        pCharHex = new char[lenHex];
        call_s8.hex_a(pCharHex, pCharSingle,lenHex);
        //SendUIMessage(UI_UPDATE_MSG_TEXT_APPEND," hex_a:"+String.valueOf(pCharHex));

        if(portType == PT_USB)hdev = call_s8.fw_init_ex(2, null, 0);
        else hdev = call_s8.fw_init_ex (1, path.toCharArray(), baudrate);
        if (hdev != -1) {

            result = call_s8.fw_getver(hdev, pModVer);
            if(0 == result)
            {
                call_s8.fw_load_key(hdev, keymode, tSec, defKey);

                result = call_s8.fw_card_str(hdev, (short)1, pSnrM1);
                if(0 == result)
                {
                    SendUIMessage(UI_UPDATE_MSG_TEXT_APPEND,String.valueOf(pSnrM1));
                    return String.valueOf(pSnrM1).substring(0,8);
                }
            }

            call_s8.fw_exit(hdev);
        }
        return "";

    }

    private void SendUIMessage(char toWhat, String text)
    {
        switch(toWhat)
        {
            case UI_UPDATE_MSG_TEXT_APPEND:
                gl_msg += text+"\n";
                break;
            case UI_UPDATE_MSG_TEXT_SET:
                gl_msg = text+"\n";
                break;
            case UI_UPDATE_BTN_AUTO:
                gl_autoBtnText = text;
                break;
        }
        m_Handler.obtainMessage(toWhat).sendToTarget();
    }
    public void ClearMsg()
    {
        gl_msg = "";
    }
    public int DoOneTest()
    {
        ClearMsg();
        String cardId=TestM1(struct_portType,devPath, baud);//得到方法返回的卡的id
        if(!cardId.isEmpty()){
                EventBus.getDefault().post(cardId);
        }

        return 0;
    }
    private class AutoTestThread extends Thread {

        @Override
        public void run() {


            try {

                gl_autoRun = 1;

                SendUIMessage(UI_UPDATE_BTN_MANUAL_DISABLE, "");
                SendUIMessage(UI_UPDATE_BTN_AUTO, "stop");

                while (gl_autoRunning == 1 && !isKill) {

                    Thread.sleep(1000);

                    if (1 == gl_singleTestInAutoRunning)
                        continue;

                    gl_singleTestInAutoRunning = 1;

                    DoOneTest();

                    gl_singleTestInAutoRunning = 0;

                }

                gl_autoRun = 0;
                SendUIMessage(UI_UPDATE_BTN_AUTO, "AutoTest");
                SendUIMessage(UI_UPDATE_BTN_MANUAL_ENABLE, "");

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        isKill = true;
        Log.d(LOG_TAG,"onDestory");
    }
}
