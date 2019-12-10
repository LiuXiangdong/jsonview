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

import com.liuxiangdong.jsonview.entry.JsonDoubleEntry;
import com.liuxiangdong.jsonview.vm.JsonDoubleViewModel;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

import java.util.Collections;
import java.util.List;

/**
 * A converter that converts a {@link JsonDoubleEntry} to a list of {@link JsonViewModel}s.
 */
public class JsonDoubleEntryConverter implements JsonEntryConverter<JsonDoubleEntry> {

    @Override
    public List<? extends JsonViewModel> convert(JsonDoubleEntry jsonEntry) {
        return Collections.singletonList(new JsonDoubleViewModel(jsonEntry.getKey(), jsonEntry.getValue(), jsonEntry.getDepth(), jsonEntry.getParentEntryCount(), jsonEntry.getIndex()));
    }
}