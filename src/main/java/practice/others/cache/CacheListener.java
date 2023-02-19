package practice.others.cache;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

@Slf4j
public class CacheListener implements CacheEventListener<Object, Object> {

    @Override
    public void onEvent(CacheEvent<? extends Object, ? extends Object> event) {
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        objectNode.put("key", event.getKey().toString());
        objectNode.put("type", event.getType().toString());
        objectNode.put("newValue", event.getNewValue() == null ? null : event.getNewValue().toString());
        objectNode.put("oldValue", event.getOldValue() == null ? null : event.getOldValue().toString());

        log.info(objectNode.toString());
    }
}
