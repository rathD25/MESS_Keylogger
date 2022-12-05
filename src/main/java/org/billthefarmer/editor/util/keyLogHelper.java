package org.billthefarmer.editor.util;

import java.util.UUID;

public class keyLogHelper {
    private final static String APP_UUID = UUID.randomUUID().toString();
    private final static String LOG_TAG_PREFIX = "keylogger";
    public static String getLogTag(Class<?> c) {
        return String.format("%s-%s", LOG_TAG_PREFIX, c.getSimpleName());
    }
    public static String getUuid() {
        return APP_UUID;
    }
}
