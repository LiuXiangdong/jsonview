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
package com.liuxiangdong.jsonview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * A default implementation of {@link JsonView} that provides functions of expanding
 * a JSONObject or a JSONArray, collapsing a JSONObject or a JSONArray and copy the string
 * value of a JSONObject or a JSONArray.
 * Note: You can long click the expanding view to expand the JSONObject or JSONArray and all
 * its children, and the same with collapsing view.
 */
public class DefaultJsonView extends JsonView {
    public DefaultJsonView(@NonNull Context context) {
        super(context);
        init();
    }

    public DefaultJsonView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DefaultJsonView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setAdapter(new DefaultJsonAdapter());
    }

    /**
     * Set the {@link ElementProvider}
     * @param elementProvider
     */
    public void setElementProvider(ElementProvider elementProvider) {
        if (getAdapter() != null) {
            ((DefaultJsonAdapter) getAdapter()).setElementProvider(elementProvider);
        }
    }
}
