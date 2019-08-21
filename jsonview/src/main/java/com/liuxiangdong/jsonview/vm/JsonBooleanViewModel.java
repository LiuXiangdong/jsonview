package com.liuxiangdong.jsonview.vm;

/**
 * A boolean value item.
 */
public class JsonBooleanViewModel extends JsonKeyValueViewModel<Boolean> {
    public JsonBooleanViewModel(String key, Boolean value, int depth, int parentEntryCount, int index) {
        super(key, value, depth, parentEntryCount, index);
    }
}
