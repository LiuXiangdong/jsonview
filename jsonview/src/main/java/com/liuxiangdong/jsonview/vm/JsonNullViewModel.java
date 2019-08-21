package com.liuxiangdong.jsonview.vm;

/**
 * A {@link org.json.JSONObject#NULL} value item.
 */
public class JsonNullViewModel extends JsonKeyValueViewModel<Object> {
    public JsonNullViewModel(String key, Object value, int depth, int parentEntryCount, int index) {
        super(key, value, depth, parentEntryCount, index);
    }
}
