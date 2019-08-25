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
import com.liuxiangdong.jsonview.R;
import com.liuxiangdong.jsonview.vm.JsonObjectEndViewModel;

/**
 * The {@link android.support.v7.widget.RecyclerView.ViewHolder} for
 * {@link JsonObjectEndViewModel}.
 */
public class JsonObjectEndViewHolder extends ValidViewHolder<JsonObjectEndViewModel> {
    public JsonObjectEndViewHolder(Context context, ElementProvider elementProvider) {
        super(context, elementProvider);
        TextView rightBrace = elementProvider.createClosingBraceView(linearLayout);
        if (rightBrace != null) {
            rightBrace.setText(R.string.json_view_closing_brace);
            linearLayout.addView(rightBrace);
        }
    }
}
