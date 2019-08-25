package com.liuxiangdong.jsonview.entry;

import java.util.Collections;
import java.util.List;

import com.liuxiangdong.jsonview.vm.JsonIntegerViewModel;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * An integer value entry.
 */
class JsonIntegerEntry extends JsonKeyValueEntry<Integer> {

    JsonIntegerEntry(String key, Integer value, int depth, int index) {
        super(key, value, depth, index);
    }

    @Override
    protected List<? extends JsonViewModel> provideViewModels() {
        return Collections.singletonList(new JsonIntegerViewModel(getKey(), getValue(), getDepth(), getParentEntryCount(), getIndex()));
    }
}
