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

import com.liuxiangdong.jsonview.entry.JsonArrayEntry;
import com.liuxiangdong.jsonview.vm.JsonArrayBeginViewModel;
import com.liuxiangdong.jsonview.vm.JsonArrayCollapsedViewModel;
import com.liuxiangdong.jsonview.vm.JsonArrayEndViewModel;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * A converter that converts a {@link JsonArrayEntry} to a list of {@link JsonViewModel}s.
 */
public class JsonArrayEntryConverter extends JsonCompoundEntryConverter<JsonArrayEntry> {
    @Override
    public JsonViewModel provideExpandedBeginViewModel(JsonArrayEntry jsonEntry) {
        return new JsonArrayBeginViewModel(jsonEntry.getKey(), jsonEntry, jsonEntry.getDepth(), jsonEntry.getParentEntryCount(), jsonEntry.getIndex());
    }

    @Override
    public JsonViewModel provideExpandedEndViewModel(JsonArrayEntry jsonEntry) {
        return new JsonArrayEndViewModel(jsonEntry.getDepth(), jsonEntry.getParentEntryCount(), jsonEntry.getIndex());
    }

    @Override
    public JsonViewModel provideCollapsedViewModel(JsonArrayEntry jsonEntry) {
        return new JsonArrayCollapsedViewModel(jsonEntry.getKey(), jsonEntry, jsonEntry.getDepth(), jsonEntry.getParentEntryCount(), jsonEntry.getIndex());
    }
}
