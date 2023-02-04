package practice.others.logging;


import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.contrib.json.classic.JsonLayout;

import java.util.LinkedHashMap;
import java.util.Map;

public class CustomLayout extends JsonLayout {

    @Override
    protected Map toJsonMap(ILoggingEvent event) {

        Map<String, Object> map = new LinkedHashMap<>();
        add(LEVEL_ATTR_NAME, this.includeLevel, String.valueOf(event.getLevel()), map);
        addTimestamp(TIMESTAMP_ATTR_NAME, this.includeTimestamp, event.getTimeStamp(), map);
        if (event.getMarker() != null) {
            add("marker", true ,event.getMarker().getName().toString(), map);
        }
        add(THREAD_ATTR_NAME, this.includeThreadName, event.getThreadName(), map);
        add(FORMATTED_MESSAGE_ATTR_NAME, this.includeFormattedMessage, event.getFormattedMessage(), map);
        addThrowableInfo(EXCEPTION_ATTR_NAME, this.includeException, event, map);

        return map;
    }
}

