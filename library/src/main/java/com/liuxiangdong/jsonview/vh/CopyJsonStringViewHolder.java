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
import android.view.View;

import com.liuxiangdong.jsonview.ElementProvider;
import com.liuxiangdong.jsonview.vm.JsonCompoundViewModel;

/**
 * A {@link android.support.v7.widget.RecyclerView.ViewHolder} that provides the function
 * of copying the Json value of a {@link com.liuxiangdong.jsonview.entry.JsonCompoundEntry}.
 * @param <T>
 */
public class CopyJsonStringViewHolder<T extends JsonCompoundViewModel<?>> extends JsonViewHolder<T> implements View.OnClickListener {
    final View copyView;

    CopyJsonStringViewHolder(Context context, ElementProvider elementProvider) {
        super(context, elementProvider);
        copyView = elementProvider.createCopyView(linearLayout);
        if (copyView != null) {
            copyView.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == copyView) {
            getViewModel().copyJsonString();
        }
    }
}
