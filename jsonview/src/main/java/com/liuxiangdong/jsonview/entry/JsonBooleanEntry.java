package com.liuxiangdong.jsonview.entry;

import java.util.Collections;
import java.util.List;

import com.liuxiangdong.jsonview.vm.JsonBooleanViewModel;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * A boolean value entry.
 */
class JsonBooleanEntry extends JsonKeyValueEntry<Boolean> {

    JsonBooleanEntry(String key, Boolean value, int depth, int index) {
        super(key, value, depth, index);
    }

    @Override
    protected List<? extends JsonViewModel> provideViewModels() {
        return Collections.singletonList(new JsonBooleanViewModel(getKey(), getValue(), getDepth(), getParentEntryCount(), getIndex()));
    }
}
