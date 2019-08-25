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

import android.content.Context;

import com.liuxiangdong.jsonview.entry.JsonCompoundEntry;

/**
 * A base class for a collapsed compound item.
 * @param <T>
 */
public abstract class JsonCollapsedViewModel<T extends JsonCompoundEntry<?>> extends JsonCompoundViewModel<T> {

    JsonCollapsedViewModel(String key, T compoundEntry, int depth, int parentEntryCount, int index) {
        super(key, compoundEntry, depth, parentEntryCount, index);
    }

    /**
     * This method provides the information text for a collapsed compound item.
     * @param context
     * @return
     */
    public abstract CharSequence getCollapsedInfo(Context context);
}
