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
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.liuxiangdong.jsonview.ElementProvider;
import com.liuxiangdong.jsonview.R;
import com.liuxiangdong.jsonview.vm.JsonObjectBeginViewModel;

/**
 * The {@link android.support.v7.widget.RecyclerView.ViewHolder} for
 * {@link JsonObjectBeginViewModel}.
 */
public class JsonObjectBeginViewHolder extends CopyJsonStringViewHolder<JsonObjectBeginViewModel> implements View.OnLongClickListener {
    private final TextView keyTextView;
    private final View collapseView;

    public JsonObjectBeginViewHolder(Context context, ElementProvider elementProvider) {
        super(context, elementProvider);
        keyTextView = elementProvider.createKeyView(linearLayout);
        linearLayout.addView(keyTextView);
        collapseView = elementProvider.createCollapseView(linearLayout);
        if (collapseView != null) {
            linearLayout.addView(collapseView);
            collapseView.setOnClickListener(this);
            collapseView.setOnLongClickListener(this);
        }
        if (copyView != null) {
            linearLayout.addView(copyView);
        }
        TextView leftBrace = elementProvider.createOpeningBraceView(linearLayout);
        if (leftBrace != null) {
            leftBrace.setText(R.string.json_view_opening_brace);
            linearLayout.addView(leftBrace);
        }
    }

    @Override
    public void onBind(JsonObjectBeginViewModel jsonObjectBeginViewModel) {
        super.onBind(jsonObjectBeginViewModel);
        CharSequence key = jsonObjectBeginViewModel.getKey();
        if (TextUtils.isEmpty(key)) {
            keyTextView.setVisibility(View.GONE);
        } else {
            keyTextView.setText(key);
            keyTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == collapseView) {
            getViewModel().collapse();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (v == collapseView) {
            getViewModel().collapseAll();
            return true;
        }
        return false;
    }
}
