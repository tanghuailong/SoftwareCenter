package softwarecenter.wt.com.softwarecenter.common;

/**
 * Created by tanghuailong on 2016/9/28.
 */

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import softwarecenter.wt.com.softwarecenter.event.EventAlarm;
import softwarecenter.wt.com.softwarecenter.event.EventDevices;
import softwarecenter.wt.com.softwarecenter.event.EventOrder;
import softwarecenter.wt.com.softwarecenter.event.EventProgress;
import softwarecenter.wt.com.softwarecenter.event.EventQuanCheck;


/**
 * 储存所有订阅主图
 */
public enum ConstantEnum{

    //这里添加订阅的主题
    TOPIC_ORDER("orders",new TypeToken<List<EventOrder>>(){}.getType()),
    TOPIC_ALARM("alarm", new TypeToken<List<EventAlarm>>(){}.getType()),
    TOPIC_ORDER_PROGRESS("order.progress.tracking",new TypeToken<EventProgress>(){}.getType()),
    TOPIC_DEVICE_CONTROL("device.info.and.progress",new TypeToken<EventDevices>(){}.getType()),
    TOPIC_QUALITY_CHECK("quality_record_topic",new TypeToken<EventQuanCheck>(){}.getType());




    private String topic;
    private Type someType;


    private ConstantEnum(String topic,Type someType) {
        this.topic = "ciiic."+topic;
        this.someType = someType;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Type getSomeType() {
        return someType;
    }

    public void setSomeType(Type someType) {
        this.someType = someType;
    }
}
