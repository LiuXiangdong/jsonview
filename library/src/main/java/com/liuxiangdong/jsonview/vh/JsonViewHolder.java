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
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liuxiangdong.jsonview.ElementProvider;
import com.liuxiangdong.jsonview.IndentationView;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * The {@link android.support.v7.widget.RecyclerView.ViewHolder} for a {@link JsonViewModel}.
 * Basically it contains a {@link IndentationView} and a {@link LinearLayout} as a container
 * for other {@link android.view.View}s.
 * @param <T>
 */
public class JsonViewHolder<T extends JsonViewModel> extends RecyclerView.ViewHolder implements Bindable<T> {
    /**
     * The {@link JsonViewModel} that provides the information for the {@link JsonViewHolder}
     */
    private T viewModel;
    private final IndentationView indentationView;
    private final TextView indexView;
    final LinearLayout linearLayout;
    private final ElementProvider elementProvider;

    JsonViewHolder(Context context, ElementProvider elementProvider) {
        super(new LinearLayout(context));
        this.elementProvider = elementProvider;
        linearLayout = (LinearLayout) itemView;
        linearLayout.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        indentationView = new IndentationView(context);
        indentationView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        indentationView.setLineColor(elementProvider.indentationViewLineColor(context));
        indentationView.setLineWidth(elementProvider.indentationViewLineWidth(context));
        indentationView.setIndentation(elementProvider.indentationWidth(context));
        linearLayout.addView(indentationView);
        indexView = elementProvider.createIndexView(linearLayout);
        if (indexView != null) {
            linearLayout.addView(indexView);
        }
    }

    /**
     * Bind the data to the {@link JsonViewHolder}
     * @param t
     */
    @Override
    public void onBind(T t) {
        viewModel = t;
        indentationView.setDepth(t.getDepth());
        if (indexView != null) {
            if (elementProvider.shouldDisplayIndex(itemView.getContext(), t)) {
                CharSequence indexText = t.getIndexText();
                if (!TextUtils.isEmpty(indexText)) {
                    indexView.setText(t.getIndexText());
                    indexView.setVisibility(View.VISIBLE);
                } else {
                    indexView.setVisibility(View.GONE);
                }
            } else {
                indexView.setVisibility(View.GONE);
            }
        }
    }

    T getViewModel() {
        return viewModel;
    }
}
