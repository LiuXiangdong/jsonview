package com.liuxiangdong.jsonview.entry;

import java.util.Collections;
import java.util.List;

import com.liuxiangdong.jsonview.vm.JsonKeyValueViewModel;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * A basic key-value entry.
 * @param <T>
 */
class JsonKeyValueEntry<T> extends JsonEntry<T> {

    JsonKeyValueEntry(String key, T value, int depth, int index) {
        super(key, value, depth, index);
    }

    @Override
    protected List<? extends JsonViewModel> provideViewModels() {
        return Collections.singletonList(new JsonKeyValueViewModel<>(getKey(), getValue(), getDepth(), getParentEntryCount(), getIndex()));
    }
}
