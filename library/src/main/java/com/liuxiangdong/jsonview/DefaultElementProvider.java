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
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * A default implementation of {@link ElementProvider}
 */
public class DefaultElementProvider implements ElementProvider {
    private static final int DISPLAYABLE_ENTRY_COUNT = 5;
    @Override
    public View createExpandView(ViewGroup parent) {
        Context context = parent.getContext();
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(context.getResources().getDimensionPixelSize(R.dimen.json_view_primary_item_height),
                context.getResources().getDimensionPixelSize(R.dimen.json_view_primary_item_height));
        imageView.setLayoutParams(lp);
        int padding = context.getResources().getDimensionPixelSize(R.dimen.json_view_primary_icon_padding);
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setImageResource(R.drawable.json_view_expand);
        return imageView;
    }

    @Override
    public View createCollapseView(ViewGroup parent) {
        Context context = parent.getContext();
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(context.getResources().getDimensionPixelSize(R.dimen.json_view_primary_item_height),
                context.getResources().getDimensionPixelSize(R.dimen.json_view_primary_item_height));
        imageView.setLayoutParams(lp);
        int padding = context.getResources().getDimensionPixelSize(R.dimen.json_view_primary_icon_padding);
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setImageResource(R.drawable.json_view_collapse);
        return imageView;
    }

    @Override
    public View createCopyView(ViewGroup parent) {
        Context context = parent.getContext();
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(context.getResources().getDimensionPixelSize(R.dimen.json_view_primary_item_height),
                context.getResources().getDimensionPixelSize(R.dimen.json_view_primary_item_height));
        imageView.setLayoutParams(lp);
        int padding = context.getResources().getDimensionPixelSize(R.dimen.json_view_primary_icon_padding);
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setImageResource(R.drawable.json_view_copy);
        return imageView;
    }

    @Override
    public TextView createKeyView(ViewGroup parent) {
        Context context = parent.getContext();
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                context.getResources().getDimensionPixelSize(R.dimen.json_view_primary_item_height));
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setMaxLines(1);
        textView.setSingleLine(true);
        textView.setTextColor(ContextCompat.getColor(context, R.color.json_view_key_color));
        textView.setTextIsSelectable(true);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimensionPixelSize(R.dimen.json_view_primary_text_size));
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        return textView;
    }

    @Override
    public TextView createOpeningBraceView(ViewGroup parent) {
        return createKeyView(parent);
    }

    @Override
    public TextView createClosingBraceView(ViewGroup parent) {
        return createKeyView(parent);
    }

    @Override
    public TextView createOpeningBracketView(ViewGroup parent) {
        return createKeyView(parent);
    }

    @Override
    public TextView createClosingBracketView(ViewGroup parent) {
        return createKeyView(parent);
    }

    @Override
    public TextView createStringValueView(ViewGroup parent) {
        return createValueView(parent);
    }

    @Override
    public TextView createIntegerValueView(ViewGroup parent) {
        return createValueView(parent);
    }

    @Override
    public TextView createBooleanValueView(ViewGroup parent) {
        return createValueView(parent);
    }

    @Override
    public TextView createDoubleValueView(ViewGroup parent) {
        return createValueView(parent);
    }

    @Override
    public TextView createLongValueView(ViewGroup parent) {
        return createValueView(parent);
    }

    @Override
    public TextView createNullValueView(ViewGroup parent) {
        return createValueView(parent);
    }

    @Override
    public TextView createCollapsedObjectInfoView(ViewGroup parent) {
        return createValueView(parent);
    }

    @Override
    public TextView createCollapsedArrayInfoView(ViewGroup parent) {
        return createValueView(parent);
    }

    private static TextView createValueView(ViewGroup parent) {
        Context context = parent.getContext();
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                context.getResources().getDimensionPixelSize(R.dimen.json_view_primary_item_height));
        lp.leftMargin = context.getResources().getDimensionPixelSize(R.dimen.json_view_primary_margin_left);
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setMaxLines(1);
        textView.setSingleLine(true);
        textView.setTextColor(ContextCompat.getColor(context, R.color.json_view_value_color));
        textView.setTextIsSelectable(true);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimensionPixelSize(R.dimen.json_view_primary_text_size));
        return textView;
    }

    @Override
    public int indentationWidth(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.json_view_indentation);
    }

    @Override
    public int indentationViewLineWidth(Context context) {
        return 1;
    }

    @Override
    public int indentationViewLineColor(Context context) {
        return ContextCompat.getColor(context, R.color.json_view_key_color);
    }

    @Nullable
    @Override
    public TextView createIndexView(ViewGroup parent) {
        Context context = parent.getContext();
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.rightMargin = context.getResources().getDimensionPixelSize(R.dimen.json_view_primary_margin_left);
        lp.gravity = Gravity.CENTER_VERTICAL;
        textView.setLayoutParams(lp);
        textView.setMaxLines(1);
        textView.setSingleLine(true);
        textView.setTextColor(ContextCompat.getColor(context, R.color.json_view_index_text_color));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimensionPixelSize(R.dimen.json_view_caption_text_size));
        ViewCompat.setBackground(textView, ContextCompat.getDrawable(context, R.drawable.json_view_index_background));
        int padding = context.getResources().getDimensionPixelSize(R.dimen.json_view_index_padding);
        textView.setPadding(padding, padding, padding, padding);
        return textView;
    }

    @Override
    public <T extends JsonViewModel> boolean shouldDisplayIndex(Context context, T viewModel) {
        return viewModel.getParentEntryCount() >= DISPLAYABLE_ENTRY_COUNT;
    }
}
