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

/**
 * This class represents the item displayed in the {@link com.liuxiangdong.jsonview.JsonView}
 */
public class JsonViewModel {
    /**
     * The depth of the {@link com.liuxiangdong.jsonview.entry.JsonEntry}
     */
    private final int depth;
    /**
     * The number of the entries that the parent of the {@link com.liuxiangdong.jsonview.entry.JsonEntry}
     * contains
     */
    private final int parentEntryCount;
    /**
     * The index of the {@link com.liuxiangdong.jsonview.entry.JsonEntry} in its parent
     */
    private final int index;

    public JsonViewModel(int depth, int parentEntryCount, int index) {
        this.depth = depth;
        this.parentEntryCount = parentEntryCount;
        this.index = index;
    }

    public int getDepth() {
        return depth;
    }

    public int getParentEntryCount() {
        return parentEntryCount;
    }

    public int getIndex() {
        return index;
    }

    public CharSequence getIndexText() {
        return index + 1 + "/" + parentEntryCount;
    }
}
