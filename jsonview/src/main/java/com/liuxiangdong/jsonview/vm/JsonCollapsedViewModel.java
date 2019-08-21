package com.liuxiangdong.jsonview.vm;

import android.content.Context;

import com.liuxiangdong.jsonview.entry.JsonCompoundEntry;

/**
 * A base class for a collapsed compound item.
 * @param <T>
 */
public abstract class JsonCollapsedViewModel<T extends JsonCompoundEntry<?>> extends JsonCompoundViewModel<T> {

    JsonCollapsedViewModel(String key, T compoundEntry, int depth, int parentEntryCount, int index) {
        super(key, compoundEntry, depth, parentEntryCount, index);
    }

    /**
     * This method provides the information text for a collapsed compound item.
     * @param context
     * @return
     */
    public abstract CharSequence getCollapsedInfo(Context context);
}
