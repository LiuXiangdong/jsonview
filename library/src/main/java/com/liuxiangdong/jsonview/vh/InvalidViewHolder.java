package com.liuxiangdong.jsonview.vh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Space;

import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * An invalid {@link android.support.v7.widget.RecyclerView.ViewHolder}.
 */
public class InvalidViewHolder extends JsonViewHolder<JsonViewModel> {
    public InvalidViewHolder(@NonNull Context context) {
        super(new Space(context));
    }

    @Override
    public void onBind(JsonViewModel jsonViewModel) {

    }
}
