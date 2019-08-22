package com.liuxiangdong.jsonview.vh;

import android.content.Context;
import android.widget.TextView;

import com.liuxiangdong.jsonview.ElementProvider;
import com.liuxiangdong.jsonview.vm.JsonKeyValueViewModel;

/**
 * The {@link android.support.v7.widget.RecyclerView.ViewHolder} for
 * {@link JsonKeyValueViewModel}.
 */
public class JsonKeyValueViewHolder extends ValidViewHolder<JsonKeyValueViewModel<?>> {
    private final TextView key;

    JsonKeyValueViewHolder(Context context, ElementProvider elementProvider) {
        super(context, elementProvider);
        key = elementProvider.createKeyView(linearLayout);
        linearLayout.addView(key);
    }

    @Override
    public void onBind(JsonKeyValueViewModel<?> jsonKeyValueViewModel) {
        super.onBind(jsonKeyValueViewModel);
        key.setText(jsonKeyValueViewModel.getKey(key.getContext()));
    }
}
