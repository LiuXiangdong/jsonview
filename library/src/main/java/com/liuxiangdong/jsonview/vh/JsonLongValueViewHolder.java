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
import com.liuxiangdong.jsonview.vm.JsonLongViewModel;

/**
 * The {@link android.support.v7.widget.RecyclerView.ViewHolder} for
 * {@link com.liuxiangdong.jsonview.vm.JsonLongViewModel}.
 */
public class JsonLongValueViewHolder<T extends JsonLongViewModel> extends JsonKeyValueViewHolder<T> {
    private final TextView value;
    public JsonLongValueViewHolder(Context context, ElementProvider elementProvider) {
        super(context, elementProvider);
        value = elementProvider.createLongValueView(linearLayout);
        linearLayout.addView(value);
    }

    @Override
    public void onBind(T t) {
        super.onBind(t);
        value.setText(t.getValueText(value.getContext()));
    }
}
