package practice.others.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static practice.others.logging.LogType.NONE;

public class CustomLayout extends LayoutBase<ILoggingEvent> {


    @Override
    public String doLayout(ILoggingEvent event) {
        StringBuffer sbuf = new StringBuffer(128);
        sbuf.append(formatTimestamp(event.getTimeStamp(), null));
        sbuf.append(" ");
        sbuf.append(event.getLevel());
        sbuf.append(" [");
        sbuf.append(event.getThreadName());
        sbuf.append("] ");
        sbuf.append("[LOG_TYPE=");
        sbuf.append(getLogType(event));
        sbuf.append("] ");
        sbuf.append(event.getLoggerName());
        sbuf.append(" - ");
        sbuf.append(event.getFormattedMessage());
        sbuf.append(CoreConstants.LINE_SEPARATOR);
        return sbuf.toString();
    }

    protected String formatTimestamp(long timestamp, String timeZone) {
        String timestampFormat = "yyyy-MM-dd HH:mm:ss.SSS";
        if (timestamp < 0) {
            return String.valueOf(timestamp);
        }
        Date date = new Date(timestamp);
        DateFormat format =  new SimpleDateFormat(timestampFormat);

        if (StringUtils.hasText(timeZone)) {
            TimeZone tz = TimeZone.getTimeZone(timeZone);
            format.setTimeZone(tz);
        }

        return format.format(date);
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
