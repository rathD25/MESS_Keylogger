package org.billthefarmer.editor.model;

import java.util.Date;

public class keyLog {
    private String uuid;
    private java.util.Date logDate;
    private String accessibilityEvent;
    private String msg;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getDate() {
        return logDate;
    }

    public void setDate(Date logDate) {
        this.logDate = logDate;
    }

    public String getAccessibilityEvent() {
        return accessibilityEvent;
    }

    public void setAccessibilityEvent(String accessibilityEvent) {
        this.accessibilityEvent = accessibilityEvent;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
