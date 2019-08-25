package com.liuxiangdong.jsonview;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.liuxiangdong.jsonview.vh.JsonViewHolder;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * The required {@link android.support.v7.widget.RecyclerView.Adapter} that is needed by
 * {@link JsonView}.
 */
public abstract class JsonAdapter extends RecyclerView.Adapter<JsonViewHolder<? extends JsonViewModel>> {
    /**
     * A list of {@link JsonViewModel}s
     */
    private final List<JsonViewModel> viewModels = new ArrayList<>();
    /**
     * Reset the list of {@link JsonViewModel}s
     */
    void setViewModels(Collection<JsonViewModel> viewModels) {
        this.viewModels.clear();
        if (viewModels != null && !viewModels.isEmpty()) {
            this.viewModels.addAll(viewModels);
        }
        notifyDataSetChanged();
    }

    /**
     * A quick method to retrieve the {@link JsonViewModel} at the position.
     * @param position the adapter position
     * @return
     */
    @SuppressWarnings("WeakerAccess")
    protected JsonViewModel getItem(int position) {
        return viewModels.get(position);
    }

    @Override
    public int getItemCount() {
        return viewModels.size();
    }
}