package kr.bora.api.common.util;

import java.util.Collection;
import java.util.Collections;

public class StringUtils {

    public String getSafeString(Object obj, String def) {
        if (obj == null) {
            return def;
        } else if (obj instanceof String) {
            return String.valueOf(obj).trim();
        } else if (obj instanceof Collections) {
            return org.springframework.util.StringUtils.collectionToDelimitedString(
                (Collection<?>) obj, ";");
        } else {
            return obj.toString();
        }
    }

}
