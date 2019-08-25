package com.liuxiangdong.jsonview.vm;

import com.liuxiangdong.jsonview.entry.JsonCompoundEntry;

/**
 * A base class for a compound item, basically it's a opening item of {@link JsonCompoundEntry}
 * @param <T>
 */
public class JsonCompoundViewModel<T extends JsonCompoundEntry<?>> extends JsonKeyViewModel {
    private final T compoundEntry;

    JsonCompoundViewModel(String key, T compoundEntry, int depth, int parentEntryCount, int index) {
        super(key, depth, parentEntryCount, index);
        this.compoundEntry = compoundEntry;
    }

    T getCompoundEntry() {
        return compoundEntry;
    }

    public void copyJsonString() {
        compoundEntry.copyJsonString();
    }

    public void expand() {
        compoundEntry.setCollapsed(false);
    }

    public void collapse() {
        compoundEntry.setCollapsed(true);
    }

    public void expandAll() {
        compoundEntry.expandAll();
    }

    public void collapseAll() {
        compoundEntry.collapseAll();
    }
}
