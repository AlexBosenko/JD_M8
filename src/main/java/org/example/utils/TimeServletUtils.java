package org.example.utils;

import javax.servlet.http.HttpServletRequest;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TimeZone;

public class TimeServletUtils {
    public static String getUtcDateTime(String parameterName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss z");

        ZonedDateTime curDateTime = ZonedDateTime.now(ZoneOffset.UTC);

        if (parameterName == null) {
            return curDateTime.format(formatter);
        }

        ZoneId zoneId = ZoneId.of(parameterName.replace(" ", "+"));
        ZonedDateTime utcDateTime = curDateTime.withZoneSameInstant(zoneId);

        return utcDateTime.format(formatter);
    }

    public static boolean validRequest(HttpServletRequest req, String parameterName) {
        Map<String, String[]> parameterMap = req.getParameterMap();

        if (parameterMap.isEmpty()) {
            return true;
        }

        if (parameterMap.containsKey(parameterName)) {
            String[] parameterValues = parameterMap.get(parameterName);
            if (parameterValues.length > 1) {
                return false;
            } else {
                try {
                    String timeZoneValue = parameterValues[0].replace(" ", "+");
                    ZoneId zoneId = ZoneId.of(timeZoneValue);
                    TimeZone timeZone = TimeZone.getTimeZone(zoneId);
                    return timeZone != null;
                } catch (DateTimeException e) {
                    return false;
                }
            }
        }

        return false;
    }
}
