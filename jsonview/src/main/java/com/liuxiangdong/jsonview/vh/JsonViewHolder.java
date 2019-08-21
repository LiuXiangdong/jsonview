package com.liuxiangdong.jsonview.vh;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * The {@link android.support.v7.widget.RecyclerView.ViewHolder} for a {@link JsonViewModel}.
 * @param <T>
 */
public class JsonViewHolder<T extends JsonViewModel> extends RecyclerView.ViewHolder {
    /**
     * The {@link JsonViewModel} that provides the information for the {@link JsonViewHolder}
     */
    private T viewModel;

    JsonViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * Bind the data to the {@link JsonViewHolder}
     * @param t
     */
    public void onBind(T t) {
        viewModel = t;
    }

    T getViewModel() {
        return viewModel;
    }
}
