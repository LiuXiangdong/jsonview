package com.liuxiangdong.jsonview.vm;

/**
 * A long value item.
 */
public class JsonLongViewModel extends JsonKeyValueViewModel<Long> {
    public JsonLongViewModel(String key, Long value, int depth, int parentEntryCount, int index) {
        super(key, value, depth, parentEntryCount, index);
    }
}
