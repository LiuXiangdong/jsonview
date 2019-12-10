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

import com.liuxiangdong.jsonview.entry.converter.JsonEntryConverterRegistry;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

import java.util.List;

/**
 * An integer value entry.
 */
public class JsonIntegerEntry extends JsonKeyValueEntry<Integer> {

    JsonIntegerEntry(String key, Integer value, int depth, int index, JsonEntryConverterRegistry registry) {
        super(key, value, depth, index, registry);
    }

    @Override
    protected List<? extends JsonViewModel> provideViewModels() {
        return getRegistry().getJsonEntryConverter(JsonIntegerEntry.class).convert(this);
    }
}