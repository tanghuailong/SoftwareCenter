package softwarecenter.wt.com.softwarecenter.callback;

import android.util.Log;

import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.greenrobot.eventbus.EventBus;

import softwarecenter.wt.com.softwarecenter.common.TopicFactory;
import softwarecenter.wt.com.softwarecenter.event.EventLost;

/**
 * Created by tanghuailong on 2016/9/28.
 */
public class CallBackOfMqtt implements MqttCallback{

    private static final String LOG_TAG = "CallBackOfMqtt";
    private Gson gson = new Gson();
    @Override
    public void connectionLost(Throwable cause) {
        Log.e(LOG_TAG,"mqtt connection lost ..."+cause.getMessage());
        cause.printStackTrace();
        //发送消息给Service,让其重启
        EventBus.getDefault().post(new EventLost(-1,cause.getMessage()));
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {

        //TODO 给JSON转化添加错误处理
        //取出信息中的数据
        String realTopic = topic.replaceAll("/","\\.");
        String info = message.toString();
        Log.d(LOG_TAG,"message is"+info);

        //获得对应的实体类的Class
        Class someEntity = TopicFactory.getInstance().getEntityByTopic(realTopic);

        //发送出去
        try {
            EventBus.getDefault().post(gson.fromJson(info, someEntity));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
