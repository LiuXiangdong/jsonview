package com.liuxiangdong.jsonview.entry;

/**
 * An interface indicates that this class is a collection of {@link JsonEntry}s.
 * Basically a JSONObject or a JSONArray is a {@link JsonParent}
 */
public interface JsonParent {
    /**
     * This method returns the number of {@link JsonEntry} it contains.
     * @return
     */
    int getEntryCount();

    /**
     * This {@link JsonParent} encounters a state change, it needs rebuild its
     * {@link com.liuxiangdong.jsonview.vm.JsonViewModel} list, as well as its
     * parent.
     */
    void invalidateViewModels();
}
