package com.liuxiangdong.jsonview.vm;

/**
 * An integer value item.
 */
public class JsonIntegerViewModel extends JsonKeyValueViewModel<Integer> {
    public JsonIntegerViewModel(String key, Integer value, int depth, int parentEntryCount, int index) {
        super(key, value, depth, parentEntryCount, index);
    }
}
