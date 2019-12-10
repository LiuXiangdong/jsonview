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
package com.liuxiangdong.jsonview.entry.converter;

import com.liuxiangdong.jsonview.entry.JsonCompoundEntry;
import com.liuxiangdong.jsonview.entry.JsonEntry;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A converter that converts a {@link JsonCompoundEntry} to a list of {@link JsonViewModel}s.
 */
public abstract class JsonCompoundEntryConverter<T extends JsonCompoundEntry<?>> implements JsonEntryConverter<T> {

    @Override
    public List<? extends JsonViewModel> convert(T jsonEntry) {
        List<JsonViewModel> result = new ArrayList<>();
        if (jsonEntry.isCollapsed()) {
            JsonViewModel viewModel = provideCollapsedViewModel(jsonEntry);
            if (viewModel != null) {
                result.add(viewModel);
            }
        } else {
            result.add(provideExpandedBeginViewModel(jsonEntry));
            for (int i = 0; i < jsonEntry.getChildCount(); i++) {
                JsonEntry<?> child = jsonEntry.getChildAt(i);
                if (child != null) {
                    result.addAll(child.getViewModels());
                }
            }
            result.add(provideExpandedEndViewModel(jsonEntry));
        }
        return result;
    }

    /**
     * Provide the opening {@link JsonViewModel} when it is expanded.
     * @return
     */
    @SuppressWarnings("WeakerAccess")
    public abstract JsonViewModel provideExpandedBeginViewModel(T jsonEntry);

    /**
     * Provide the closing {@link JsonViewModel} when it is expanded.
     * @return
     */
    @SuppressWarnings("WeakerAccess")
    public abstract JsonViewModel provideExpandedEndViewModel(T jsonEntry);

    /**
     * Provide the collapsed {@link JsonViewModel} when it is collapsed.
     * @return
     */
    @SuppressWarnings("WeakerAccess")
    public abstract JsonViewModel provideCollapsedViewModel(T jsonEntry);
}