package com.liuxiangdong.jsonview.vm;

import android.content.Context;

import com.liuxiangdong.jsonview.R;
import com.liuxiangdong.jsonview.entry.JsonObjectEntry;

/**
 * A collapsed JSONObject item.
 */
public class JsonObjectCollapsedViewModel extends JsonCollapsedViewModel<JsonObjectEntry> {

    public JsonObjectCollapsedViewModel(String key, JsonObjectEntry compoundEntry, int depth, int parentEntryCount, int index) {
        super(key, compoundEntry, depth, parentEntryCount, index);
    }

    @Override
    public CharSequence getCollapsedInfo(Context context) {
        return context.getString(R.string.json_view_json_object_collapsed_info);
    }
}
