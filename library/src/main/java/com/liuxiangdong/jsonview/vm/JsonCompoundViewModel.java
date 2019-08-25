/*
 * Copyright 2019 LiuXiangdong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liuxiangdong.jsonview.vm;

import com.liuxiangdong.jsonview.entry.JsonCompoundEntry;

/**
 * A base class for a compound item, basically it's a opening item of {@link JsonCompoundEntry}
 * @param <T>
 */
public class JsonCompoundViewModel<T extends JsonCompoundEntry<?>> extends JsonKeyViewModel {
    private final T compoundEntry;

    JsonCompoundViewModel(String key, T compoundEntry, int depth, int parentEntryCount, int index) {
        super(key, depth, parentEntryCount, index);
        this.compoundEntry = compoundEntry;
    }

    T getCompoundEntry() {
        return compoundEntry;
    }

    public void copyJsonString() {
        compoundEntry.copyJsonString();
    }

    public void expand() {
        compoundEntry.setCollapsed(false);
    }

    public void collapse() {
        compoundEntry.setCollapsed(true);
    }

    public void expandAll() {
        compoundEntry.expandAll();
    }

    public void collapseAll() {
        compoundEntry.collapseAll();
    }
}
