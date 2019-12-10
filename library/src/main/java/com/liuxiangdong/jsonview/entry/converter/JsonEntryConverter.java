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
import com.liuxiangdong.jsonview.vm.JsonViewModel;

import java.util.List;

/**
 * A converter that converts a {@link JsonEntry} to a list of {@link JsonViewModel}s.
 * @param <T> A concrete subclass of {@link JsonEntry}.
 */
public interface JsonEntryConverter<T extends JsonEntry<?>> {

    /**
     * Subclass need to override this method to provide the corresponding {@link JsonViewModel}(s).
     * @return
     */
    List<? extends JsonViewModel> convert(T jsonEntry);
}