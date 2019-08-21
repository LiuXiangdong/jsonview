package com.liuxiangdong.jsonview.vm;

import android.content.Context;

/**
 * A key-value item.
 * @param <T>
 */
public class JsonKeyValueViewModel<T> extends JsonKeyViewModel {
    private final T value;

    public JsonKeyValueViewModel(String key, T value, int depth, int parentEntryCount, int index) {
        super(key, depth, parentEntryCount, index);
        this.value = value;
    }

    /**
     * This method provides the information text of the value of a {@link com.liuxiangdong.jsonview.entry.JsonEntry}.
     * @param context
     * @return
     */
    public T getValue(Context context) {
        return value;
    }
}
