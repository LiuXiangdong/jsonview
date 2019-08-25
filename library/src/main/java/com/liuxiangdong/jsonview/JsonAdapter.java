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