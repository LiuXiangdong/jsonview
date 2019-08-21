package com.liuxiangdong.jsonview.vm;

import android.content.Context;

import com.liuxiangdong.jsonview.R;
import com.liuxiangdong.jsonview.entry.JsonArrayEntry;

/**
 * A collapsed JSONArray item.
 */
public class JsonArrayCollapsedViewModel extends JsonCollapsedViewModel<JsonArrayEntry> {

    public JsonArrayCollapsedViewModel(String key, JsonArrayEntry compoundEntry, int depth, int parentEntryCount, int index) {
        super(key, compoundEntry, depth, parentEntryCount, index);
    }

    @Override
    public CharSequence getCollapsedInfo(Context context) {
        return context.getString(R.string.json_view_json_array_collapsed_info, getCompoundEntry().getValue().length());
    }
}
