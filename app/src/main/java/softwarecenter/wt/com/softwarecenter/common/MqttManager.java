package softwarecenter.wt.com.softwarecenter.common;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

import softwarecenter.wt.com.softwarecenter.callback.CallBackOfMqtt;

/**
 * Created by tanghuailong on 2016/9/28.
 */
public class MqttManager {

    private static final String LOG_TAG = "MqttManager";

    private static MqttManager mqttManager = null;
    private MqttCallback mqttCallback;
    private MqttClient client;
    private MqttConnectOptions conOpt;
    private boolean clean = false;
    private String[] topics;
    private int[] qosLevel;


    private static final String rabbitmqUrl = "tcp://192.168.0.72:1883";
    private static final String userName = "admin";
    private static final String password = "admin";



    private MqttManager() {
        //设置回调
        mqttCallback = new CallBackOfMqtt();
        //获取所有Topic
        topics = TopicFactory.getInstance().getAllTopic();
        qosLevel = new int[topics.length];
    }

    public static MqttManager getInstance() {
        if(mqttManager == null) {
            synchronized (MqttMessage.class) {
                if(mqttManager == null) {
                    mqttManager = new MqttManager();
                }
            }
        }
        return mqttManager;
    }

    public static void release() {
        try {
            if(mqttManager != null) {
                mqttManager.disConnect();
                mqttManager = null;
            }
        }catch (Exception e) {
            Log.d(LOG_TAG,"some error occur in release");
        }
    }

    public boolean createConnection(String mDeviceId) {

        boolean flag  = false;
        //获得系统下的临时文件夹
        String tmDir = System.getProperty("java.io.tmpdir");
        MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmDir);
        try{
            conOpt = new MqttConnectOptions();
            conOpt.setCleanSession(clean);
            conOpt.setUserName(userName);
            conOpt.setPassword(password.toCharArray());

            client = new MqttClient(rabbitmqUrl,mDeviceId,dataStore);
            client.setCallback(mqttCallback);
            flag = doConnect();
        }catch (MqttException e) {
            Log.e(LOG_TAG,e.getMessage());
        }
        return flag;
    }

    public boolean doConnect() {
        boolean flag = false;
        if(client != null) {
            try {
                client.connect(conOpt);
                Log.d(LOG_TAG,"Connected to " + client.getServerURI() +" with device ID " +client.getClientId());
                flag = true;

            }catch (Exception e) {

            }
        }
        return flag;
    }

    /**
     *
     * @param topicName
     * @param qosLevel  传输的级别，0，1，2. 0 为最低级别，2为最高级。
     *                  0 不执行确认，1 包含确认操作，2 四次握手确认
     * @param payload
     * @return
     */
    public boolean publish(String topicName,int qosLevel,byte[] payload) {
        boolean flag = false;
        if(client != null && client.isConnected()) {
            MqttMessage message = new MqttMessage(payload);
            message.setQos(0);
            message.setRetained(false);
            try {
                client.publish(topicName,message);
                flag = true;
            }catch (MqttException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public boolean subscribe() {
        boolean flag = false;
        if(client != null && client.isConnected()) {
            try {
                client.subscribe(topics,qosLevel);
                flag = true;
            }catch (MqttException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
    public void disConnect() {
        if(client != null && client.isConnected()) {
            client.isConnected();
        }
    }


}
