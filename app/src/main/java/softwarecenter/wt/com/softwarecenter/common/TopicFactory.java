package softwarecenter.wt.com.softwarecenter.common;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * Created by tanghuailong on 2016/9/29.
 */
public class TopicFactory {

    private volatile static TopicFactory INSTANCE = null;
    private Map<String,Class> topicMap;
    private String[] topicList;

    private TopicFactory() {
        List<ConstantEnum> lists = Arrays.asList(ConstantEnum.values());
        topicMap = Stream.of(lists).collect(Collectors.toMap(ConstantEnum::getTopic,ConstantEnum::getSomeClass));
        topicList = Stream.of(lists).map((e) -> e.getTopic()).toArray(String[]::new);
    }


    public static TopicFactory getInstance() {
        if(INSTANCE == null) {
            synchronized (TopicFactory.class) {
                if(INSTANCE == null) {
                    INSTANCE = new TopicFactory();
                }
            }
        }
        return INSTANCE;
    }

    public Class getEntityByTopic(String topic) {
        return topicMap.get(topic);
    }

    public String[] getAllTopic() {
        return topicList;
    }

}
