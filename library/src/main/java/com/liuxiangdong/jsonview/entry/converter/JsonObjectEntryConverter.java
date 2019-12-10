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

import com.liuxiangdong.jsonview.entry.JsonObjectEntry;
import com.liuxiangdong.jsonview.vm.JsonObjectBeginViewModel;
import com.liuxiangdong.jsonview.vm.JsonObjectCollapsedViewModel;
import com.liuxiangdong.jsonview.vm.JsonObjectEndViewModel;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * A converter that converts a {@link JsonObjectEntry} to a list of {@link JsonViewModel}s.
 */
public class JsonObjectEntryConverter extends JsonCompoundEntryConverter<JsonObjectEntry> {

    @Override
    public JsonViewModel provideExpandedBeginViewModel(JsonObjectEntry jsonEntry) {
        return new JsonObjectBeginViewModel(jsonEntry.getKey(), jsonEntry, jsonEntry.getDepth(), jsonEntry.getParentEntryCount(), jsonEntry.getIndex());
    }

    @Override
    public JsonViewModel provideExpandedEndViewModel(JsonObjectEntry jsonEntry) {
        return new JsonObjectEndViewModel(jsonEntry.getDepth(), jsonEntry.getParentEntryCount(), jsonEntry.getIndex());
    }

    @Override
    public JsonViewModel provideCollapsedViewModel(JsonObjectEntry jsonEntry) {
        return new JsonObjectCollapsedViewModel(jsonEntry.getKey(), jsonEntry, jsonEntry.getDepth(), jsonEntry.getParentEntryCount(), jsonEntry.getIndex());
    }
}
