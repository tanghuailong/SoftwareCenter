package softwarecenter.wt.com.softwarecenter.common;

/**
 * Created by tanghuailong on 2016/9/28.
 */

import softwarecenter.wt.com.softwarecenter.event.EventAlarm;
import softwarecenter.wt.com.softwarecenter.event.EventOrder;

/**
 * 储存所有订阅主图
 */
public enum ConstantEnum{

    //这里添加订阅的主题
    TOPIC_ORDER("orders",EventOrder[].class),
    TOPIC_ALARM("alarm", EventAlarm[].class);



    private String topic;
    private Class someClass;


    private ConstantEnum(String topic,Class someClass) {
        this.topic = "ciiic."+topic;
        this.someClass = someClass;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Class getSomeClass() {
        return someClass;
    }

    public void setSomeClass(Class someClass) {
        this.someClass = someClass;
    }
}
