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

import android.util.SparseArray;

import com.liuxiangdong.jsonview.vh.JsonViewHolder;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * A registry for the {@link Renderer}s.
 */
public class RendererRegistry {
    /**
     * The view type and the {@link Renderer} mapping array
     */
    private final SparseArray<Renderer<? extends JsonViewModel, ? extends JsonViewHolder<?>>> renderers;
    /**
     * The view type and the {@link JsonViewModel} class mapping array
     */
    private final SparseArray<Class<? extends JsonViewModel>> viewTypeViewModelMapping;



    public RendererRegistry() {
        renderers = new SparseArray<>();
        viewTypeViewModelMapping = new SparseArray<>();
    }

    /**
     * Retrieve a {@link Renderer} through a view type.
     * @param viewType A view type specified by a {@link Renderer}
     * @param <VM> A subclass of {@link JsonViewModel}
     * @param <VH> A subclass of {@link JsonViewHolder}
     * @return The corresponding {@link Renderer}
     */
    @SuppressWarnings("unchecked")
    public final <VM extends JsonViewModel, VH extends JsonViewHolder<VM>> Renderer<VM, VH> getRenderer(int viewType) {
        return (Renderer<VM, VH>) renderers.get(viewType);
    }

    /**
     * Register a {@link Renderer}.
     * @param renderer An instance of a {@link Renderer}
     * @param <VM> A subclass of {@link JsonViewModel}
     * @param <VH> A subclass of {@link JsonViewHolder}
     */
    public <VM extends JsonViewModel, VH extends JsonViewHolder<VM>> void registerRender(Renderer<VM, VH> renderer) {
        renderers.put(renderer.getItemViewType(), renderer);
        viewTypeViewModelMapping.put(renderer.getItemViewType(), renderer.getViewModelClass());
    }

    /**
     * Retrieve the view type through a {@link JsonViewModel} class.
     * @param clazz The {@link JsonViewModel} class
     * @return The corresponding view type
     */
    public int getItemViewType(Class<? extends JsonViewModel> clazz) {
        int index = viewTypeViewModelMapping.indexOfValue(clazz);
        if (index == -1) {
            return ItemViewTypeProvider.INVALID_VIEW_TYPE;
        }
        return viewTypeViewModelMapping.keyAt(index);
    }
}
