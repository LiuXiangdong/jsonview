package com.liuxiangdong.jsonview.vm;

import android.content.Context;
import android.text.TextUtils;

import com.liuxiangdong.jsonview.R;

/**
 * A base class for which item displays the key.
 */
public class JsonKeyViewModel extends JsonViewModel {
    private final String key;

    JsonKeyViewModel(String key, int depth, int parentEntryCount, int index) {
        super(depth, parentEntryCount, index);
        this.key = key;
    }

    /**
     * This method provides the information text of the key of a {@link com.liuxiangdong.jsonview.entry.JsonEntry}.
     * @param context
     * @return
     */
    public CharSequence getKey(Context context) {
        if (TextUtils.isEmpty(key)) {
            return key;
        }
        return context.getString(R.string.json_view_json_string, key);
    }
}
