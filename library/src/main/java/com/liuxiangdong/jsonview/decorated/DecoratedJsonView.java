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
package com.liuxiangdong.jsonview.decorated;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.liuxiangdong.jsonview.DefaultJsonView;
import com.liuxiangdong.jsonview.decorated.converter.DecoratedJsonBooleanEntryConverter;
import com.liuxiangdong.jsonview.decorated.converter.DecoratedJsonStringEntryConverter;
import com.liuxiangdong.jsonview.decorated.renderer.DecoratedJsonBooleanRenderer;
import com.liuxiangdong.jsonview.decorated.renderer.DecoratedJsonStringRenderer;
import com.liuxiangdong.jsonview.entry.JsonBooleanEntry;
import com.liuxiangdong.jsonview.entry.JsonStringEntry;

/**
 * A subclass of {@link DefaultJsonView} that supplies more information for each kind of
 * {@link com.liuxiangdong.jsonview.entry.JsonEntry}.
 */
public class DecoratedJsonView extends DefaultJsonView {
    public DecoratedJsonView(@NonNull Context context) {
        super(context);
        init();
    }

    public DecoratedJsonView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DecoratedJsonView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        registerJsonEntryConverter(JsonBooleanEntry.class, new DecoratedJsonBooleanEntryConverter());
        registerJsonEntryConverter(JsonStringEntry.class, new DecoratedJsonStringEntryConverter());
        registerRender(new DecoratedJsonBooleanRenderer());
        registerRender(new DecoratedJsonStringRenderer());
    }
}
