package practice.others.logging;


import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.contrib.json.classic.JsonLayout;
import org.springframework.util.ObjectUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import static practice.others.logging.LogType.NONE;

public class CustomLayout extends JsonLayout {

    @Override
    protected Map toJsonMap(ILoggingEvent event) {

        Map<String, Object> map = new LinkedHashMap<>();
        add(LEVEL_ATTR_NAME, this.includeLevel, String.valueOf(event.getLevel()), map);
        addTimestamp(TIMESTAMP_ATTR_NAME, this.includeTimestamp, event.getTimeStamp(), map);
        add("logType", true , getLogType(event), map);
        add(THREAD_ATTR_NAME, this.includeThreadName, event.getThreadName(), map);
        add("loggedAt", true, event.getLoggerName(), map);
        add(FORMATTED_MESSAGE_ATTR_NAME, this.includeFormattedMessage, event.getFormattedMessage(), map);
        addThrowableInfo(EXCEPTION_ATTR_NAME, this.includeException, event, map);

        return map;
    }

    private String getLogType(ILoggingEvent event) {
        if (event.getMarker() != null) {
            return event.getMarker().getName();
        }

        return NONE.name();
    }

    private String getCaller(ILoggingEvent event) {
        if (!ObjectUtils.isEmpty(event.getCallerData())) {
            String methodName = event.getCallerData()[0].getMethodName();
            int lineNumber = event.getCallerData()[0].getLineNumber();
            return methodName + "[" + lineNumber + "]";
        }

        return "";
    }
}

