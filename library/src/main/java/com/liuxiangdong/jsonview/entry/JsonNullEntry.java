package com.liuxiangdong.jsonview.entry;

import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

import com.liuxiangdong.jsonview.vm.JsonNullViewModel;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * A {@link JSONObject#NULL} value entry.
 */
class JsonNullEntry extends JsonKeyValueEntry<Object> {
    JsonNullEntry(String key, int depth, int index) {
        super(key, JSONObject.NULL, depth, index);
    }

    @Override
    protected List<? extends JsonViewModel> provideViewModels() {
        return Collections.singletonList(new JsonNullViewModel(getKey(), getValue(), getDepth(), getParentEntryCount(), getIndex()));
    }
}
