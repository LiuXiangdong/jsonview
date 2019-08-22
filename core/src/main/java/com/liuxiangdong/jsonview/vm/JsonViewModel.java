package com.liuxiangdong.jsonview.vm;

/**
 * This class represents the item displayed in the {@link com.liuxiangdong.jsonview.JsonView}
 */
public class JsonViewModel {
    /**
     * The depth of the {@link com.liuxiangdong.jsonview.entry.JsonEntry}
     */
    private final int depth;
    /**
     * The number of the entries that the parent of the {@link com.liuxiangdong.jsonview.entry.JsonEntry}
     * contains
     */
    private final int parentEntryCount;
    /**
     * The index of the {@link com.liuxiangdong.jsonview.entry.JsonEntry} in its parent
     */
    private final int index;

    public JsonViewModel(int depth, int parentEntryCount, int index) {
        this.depth = depth;
        this.parentEntryCount = parentEntryCount;
        this.index = index;
    }

    public int getDepth() {
        return depth;
    }

    public int getParentEntryCount() {
        return parentEntryCount;
    }

    public int getIndex() {
        return index;
    }
}
