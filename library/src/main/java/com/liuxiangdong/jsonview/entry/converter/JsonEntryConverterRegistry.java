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

import com.liuxiangdong.jsonview.entry.JsonEntry;

import java.util.HashMap;
import java.util.Map;

/**
 * A registry for {@link JsonEntryConverter}s. It's useful when {@link JsonEntry}'s {@link JsonEntry#getViewModels()}
 * is called, cause it provides the {@link JsonEntryConverter} for the corresponding {@link JsonEntry}.
 */
public class JsonEntryConverterRegistry {

    /**
     * The map for the {@link JsonEntryConverter}
     */
    private final Map<Class<? extends JsonEntry<?>>, JsonEntryConverter<? extends JsonEntry<?>>> mConverterMap;

    public JsonEntryConverterRegistry() {
        mConverterMap = new HashMap<>();
    }

    /**
     * Register a {@link JsonEntryConverter} for a {@link JsonEntry}.
     * @param clazz The class of the {@link JsonEntry}, it acts as a key.
     * @param converter The actual {@link JsonEntryConverter}.
     * @param <T> A concrete subclass of {@link JsonEntry}.
     */
    public <T extends JsonEntry<?>> void registerJsonEntryConverter(Class<T> clazz, JsonEntryConverter<T> converter) {
        mConverterMap.put(clazz, converter);
    }

    /**
     * Retrieve a {@link JsonEntryConverter} for a {@link JsonEntry}.
     * @param clazz The class of the {@link JsonEntry}.
     * @param <T> A concrete subclass of {@link JsonEntry}.
     * @return The {@link JsonEntryConverter} for the {@link JsonEntry}.
     */
    @SuppressWarnings("unchecked")
    public <T extends JsonEntry<?>> JsonEntryConverter<T> getJsonEntryConverter(Class<T> clazz) {
        return (JsonEntryConverter<T>) mConverterMap.get(clazz);
    }
}