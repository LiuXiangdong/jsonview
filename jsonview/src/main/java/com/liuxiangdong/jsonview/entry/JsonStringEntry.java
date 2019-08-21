package com.liuxiangdong.jsonview.entry;

import java.util.Collections;
import java.util.List;

import com.liuxiangdong.jsonview.vm.JsonStringViewModel;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * A string value entry.
 */
class JsonStringEntry extends JsonKeyValueEntry<String> {

    JsonStringEntry(String key, String value, int depth, int index) {
        super(key, value, depth, index);
    }

    @Override
    protected List<? extends JsonViewModel> provideViewModels() {
        return Collections.singletonList(new JsonStringViewModel(getKey(), getValue(), getDepth(), getParentEntryCount(), getIndex()));
    }
}
