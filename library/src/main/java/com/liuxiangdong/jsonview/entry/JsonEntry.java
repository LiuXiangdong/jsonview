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
package com.liuxiangdong.jsonview.entry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * This class represents an entry in a Json. Basically it can be a key-value entry, or
 * a JSONObject entry, or a JSONArray entry. This class provides a list of {@link JsonViewModel}s
 * that represents the mapping of the entry and the item(s) of the {@link com.liuxiangdong.jsonview.JsonView}.
 * @param <T> The type of the value
 */
public class JsonEntry<T> {
    /**
     * The key of the entry
     */
    private final String key;
    /**
     * The value of the entry
     */
    private final T value;
    /**
     * The {@link JsonViewModel}s that represents the entry
     */
    List<JsonViewModel> viewModels;
    /**
     * The depth of the entry
     */
    private final int depth;
    /**
     * The index of the entry in its parent
     */
    private final int index;
    /**
     * The parent of the entry
     */
    private JsonParent parent;

    JsonEntry(String key, T value, int depth, int index) {
        this.key = key;
        this.value = value;
        this.depth = depth;
        this.index = index;
    }

    void setParent(JsonParent parent) {
        this.parent = parent;
    }

    public int getIndex() {
        return index;
    }

    JsonParent getParent() {
        return parent;
    }

    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    public int getDepth() {
        return depth;
    }

    public List<JsonViewModel> getViewModels() {
        if (viewModels == null) {
            viewModels = new ArrayList<>();
            List<? extends JsonViewModel> jsonViewModels = provideViewModels();
            if (jsonViewModels != null && !jsonViewModels.isEmpty()) {
                viewModels.addAll(jsonViewModels);
            }
        }
        return Collections.unmodifiableList(viewModels);
    }

    /**
     * This method returns the number of the entries in the parent.
     * @return
     */
    int getParentEntryCount() {
        if (parent != null) {
            return parent.getEntryCount();
        }
        return 1;
    }

    /**
     * Subclass need to override this method to provide the corresponding {@link JsonViewModel}(s).
     * @return
     */
    protected List<? extends JsonViewModel> provideViewModels() {
        return Collections.emptyList();
    }
}
