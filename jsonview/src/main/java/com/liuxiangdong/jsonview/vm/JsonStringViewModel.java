package com.liuxiangdong.jsonview.vm;

import android.content.Context;

import com.liuxiangdong.jsonview.R;

/**
 * A string value item.
 */
public class JsonStringViewModel extends JsonKeyValueViewModel<String> {

    public JsonStringViewModel(String key, String value, int depth, int parentEntryCount, int index) {
        super(key, value, depth, parentEntryCount, index);
    }

    @Override
    public String getValue(Context context) {
        return context.getString(R.string.json_view_json_string, super.getValue(context));
    }
}
