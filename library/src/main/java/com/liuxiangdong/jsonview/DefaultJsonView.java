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

import com.liuxiangdong.jsonview.renderer.Renderer;
import com.liuxiangdong.jsonview.vh.JsonViewHolder;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * A default implementation of {@link JsonView} that provides functions of expanding
 * a JSONObject or a JSONArray, collapsing a JSONObject or a JSONArray and copy the string
 * value of a JSONObject or a JSONArray.
 * Note: You can long click the expanding view to expand the JSONObject or JSONArray and all
 * its children, and the same with collapsing view.
 * The appearance and behavior of DefaultJsonView can be affected by {@link ElementProvider}
 * and {@link Renderer}. Hence you can set an {@link ElementProvider} to provide your own
 * appearance or furthermore register a {@link Renderer} to provide your own ViewHolder.
 */
public class DefaultJsonView extends JsonView {
    private DefaultJsonAdapter mDefaultJsonAdapter;
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
        mDefaultJsonAdapter = new DefaultJsonAdapter();
        setAdapter(mDefaultJsonAdapter);
    }

    public <VM extends JsonViewModel, VH extends JsonViewHolder<VM>> void registerRender(Renderer<VM, VH> renderer) {
        mDefaultJsonAdapter.registerRender(renderer);
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
