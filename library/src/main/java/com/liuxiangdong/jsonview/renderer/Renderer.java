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
package com.liuxiangdong.jsonview.renderer;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.liuxiangdong.jsonview.ElementProvider;
import com.liuxiangdong.jsonview.vh.JsonViewHolder;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * A class that binds the {@link JsonViewModel} and the corresponding ViewHolder
 * and it's view type together. It's used in the {@link com.liuxiangdong.jsonview.DefaultJsonAdapter}
 * to supply the ViewHolder for the data. You can provide a different {@link JsonViewModel} via the
 * {@link com.liuxiangdong.jsonview.entry.converter.JsonEntryConverter} for a {@link com.liuxiangdong.jsonview.entry.JsonEntry},
 * and then supply your own ViewHolder by extending this class. Then register your class to the
 * {@link com.liuxiangdong.jsonview.DefaultJsonView} via {@link com.liuxiangdong.jsonview.DefaultJsonView#registerRender(Renderer)}
 * method.
 * @param <VM> A subclass of {@link JsonViewModel}
 * @param <VH> A subclass of {@link JsonViewHolder}
 */
public abstract class Renderer<VM extends JsonViewModel, VH extends JsonViewHolder<VM>> {
    /**
     * The view type for the ViewHolder, it's generated automatically
     */
    private final int mItemViewType;


    public Renderer() {
        mItemViewType = ItemViewTypeProvider.nextItemViewType();
    }

    int getItemViewType() {
        return mItemViewType;
    }

    /**
     * Specify the class of the {@link JsonViewModel}.
     * @return The class of the {@link JsonViewModel}
     */
    public abstract Class<VM> getViewModelClass();

    /**
     * Create a corresponding ViewHolder that is similar to the method in an {@link android.support.v7.widget.RecyclerView.Adapter}.
     * @param viewGroup The viewGroup parameter from the {@link android.support.v7.widget.RecyclerView.Adapter#onCreateViewHolder(ViewGroup, int)}
     * @param elementProvider {@link ElementProvider}
     * @return
     */
    public abstract VH onCreateViewHolder(@NonNull ViewGroup viewGroup, ElementProvider elementProvider);

    /**
     * Bind the {@link JsonViewModel} to a ViewHolder.
     * @param viewHolder
     * @param viewModel
     */
    public void onBindViewHolder(@NonNull VH viewHolder, VM viewModel) {
        viewHolder.onBind(viewModel);
    }
}
