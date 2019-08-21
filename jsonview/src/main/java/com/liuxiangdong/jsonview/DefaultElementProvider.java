package com.liuxiangdong.jsonview;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A default implementation of {@link ElementProvider}
 */
public class DefaultElementProvider implements ElementProvider {
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
}
