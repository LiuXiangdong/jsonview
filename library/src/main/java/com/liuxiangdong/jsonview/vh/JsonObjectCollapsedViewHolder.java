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
import android.widget.TextView;

import com.liuxiangdong.jsonview.ElementProvider;
import com.liuxiangdong.jsonview.vm.JsonObjectCollapsedViewModel;

/**
 * The {@link android.support.v7.widget.RecyclerView.ViewHolder} for
 * {@link JsonObjectCollapsedViewModel}.
 */
public class JsonObjectCollapsedViewHolder<T extends JsonObjectCollapsedViewModel> extends JsonObjectViewHolder<T> implements View.OnLongClickListener {
    private final View expandView;
    private final TextView collapsedInfo;

    public JsonObjectCollapsedViewHolder(Context context, ElementProvider elementProvider) {
        super(context, elementProvider);
        expandView = elementProvider.createExpandView(linearLayout);
        if (expandView != null) {
            linearLayout.addView(expandView);
            expandView.setOnClickListener(this);
            expandView.setOnLongClickListener(this);
        }
        if (copyView != null) {
            linearLayout.addView(copyView);
        }
        collapsedInfo = elementProvider.createCollapsedObjectInfoView(linearLayout);
        linearLayout.addView(collapsedInfo);
    }

    @Override
    public void onBind(T t) {
        super.onBind(t);
        collapsedInfo.setText(t.getCollapsedInfo(collapsedInfo.getContext()));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == expandView) {
            getViewModel().expand();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (v == expandView) {
            getViewModel().expandAll();
            return true;
        }
        return false;
    }
}
