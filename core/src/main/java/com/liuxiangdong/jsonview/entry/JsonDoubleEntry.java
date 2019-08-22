package com.liuxiangdong.jsonview.entry;

import java.util.Collections;
import java.util.List;

import com.liuxiangdong.jsonview.vm.JsonDoubleViewModel;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * A double value entry.
 */
class JsonDoubleEntry extends JsonKeyValueEntry<Double> {

    JsonDoubleEntry(String key, Double value, int depth, int index) {
        super(key, value, depth, index);
    }

    @Override
    protected List<? extends JsonViewModel> provideViewModels() {
        return Collections.singletonList(new JsonDoubleViewModel(getKey(), getValue(), getDepth(), getParentEntryCount(), getIndex()));
    }
}
