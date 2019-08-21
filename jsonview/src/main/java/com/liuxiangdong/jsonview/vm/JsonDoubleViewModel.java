package com.liuxiangdong.jsonview.vm;

/**
 * A double value item.
 */
public class JsonDoubleViewModel extends JsonKeyValueViewModel<Double> {
    public JsonDoubleViewModel(String key, Double value, int depth, int parentEntryCount, int index) {
        super(key, value, depth, parentEntryCount, index);
    }
}
