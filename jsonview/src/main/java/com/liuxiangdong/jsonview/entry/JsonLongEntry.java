package com.liuxiangdong.jsonview.entry;

import java.util.Collections;
import java.util.List;

import com.liuxiangdong.jsonview.vm.JsonLongViewModel;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * A long value entry.
 */
class JsonLongEntry extends JsonKeyValueEntry<Long> {

    JsonLongEntry(String key, Long value, int depth, int index) {
        super(key, value, depth, index);
    }

    @Override
    protected List<? extends JsonViewModel> provideViewModels() {
        return Collections.singletonList(new JsonLongViewModel(getKey(), getValue(), getDepth(), getParentEntryCount(), getIndex()));
    }
}
