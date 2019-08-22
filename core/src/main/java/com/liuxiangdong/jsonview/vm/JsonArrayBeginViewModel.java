package com.liuxiangdong.jsonview.vm;

import com.liuxiangdong.jsonview.entry.JsonArrayEntry;

/**
 * A opening JSONArray item.
 */
public class JsonArrayBeginViewModel extends JsonCompoundViewModel<JsonArrayEntry> {
    public JsonArrayBeginViewModel(String key, JsonArrayEntry compoundEntry, int depth, int parentEntryCount, int index) {
        super(key, compoundEntry, depth, parentEntryCount, index);
    }
}
