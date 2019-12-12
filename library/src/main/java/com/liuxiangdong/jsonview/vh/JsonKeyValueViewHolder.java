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
package com.liuxiangdong.jsonview.vh;

import android.content.Context;
import android.widget.TextView;

import com.liuxiangdong.jsonview.ElementProvider;
import com.liuxiangdong.jsonview.vm.JsonKeyValueViewModel;

/**
 * The {@link android.support.v7.widget.RecyclerView.ViewHolder} for
 * {@link JsonKeyValueViewModel}.
 */
public class JsonKeyValueViewHolder<T extends JsonKeyValueViewModel<?>> extends JsonViewHolder<T> {
    private final TextView key;

    JsonKeyValueViewHolder(Context context, ElementProvider elementProvider) {
        super(context, elementProvider);
        key = elementProvider.createKeyView(linearLayout);
        linearLayout.addView(key);
    }

    @Override
    public void onBind(T t) {
        super.onBind(t);
        key.setText(t.getKeyText());
    }
}
