package com.liuxiangdong.jsonview.vm;

import com.liuxiangdong.jsonview.entry.JsonObjectEntry;

/**
 * A opening JSONObject item.
 */
public class JsonObjectBeginViewModel extends JsonCompoundViewModel<JsonObjectEntry> {
    public JsonObjectBeginViewModel(String key, JsonObjectEntry compoundEntry, int depth, int parentEntryCount, int index) {
        super(key, compoundEntry, depth, parentEntryCount, index);
    }
}
