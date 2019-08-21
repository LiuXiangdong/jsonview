package com.liuxiangdong.jsonview.vh;

import android.content.Context;
import android.widget.TextView;

import com.liuxiangdong.jsonview.ElementProvider;
import com.liuxiangdong.jsonview.vm.JsonKeyValueViewModel;

/**
 * The {@link android.support.v7.widget.RecyclerView.ViewHolder} for
 * {@link com.liuxiangdong.jsonview.vm.JsonLongViewModel}.
 */
public class JsonLongValueViewHolder extends JsonKeyValueViewHolder {
    private final TextView value;
    public JsonLongValueViewHolder(Context context, ElementProvider elementProvider) {
        super(context, elementProvider);
        value = elementProvider.createLongValueView(linearLayout);
        linearLayout.addView(value);
    }

    @Override
    public void onBind(JsonKeyValueViewModel<?> jsonKeyValueViewModel) {
        super.onBind(jsonKeyValueViewModel);
        value.setText(jsonKeyValueViewModel.getValue(value.getContext()).toString());
    }
}
