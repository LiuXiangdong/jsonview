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

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.liuxiangdong.jsonview.renderer.JsonArrayBeginRenderer;
import com.liuxiangdong.jsonview.renderer.JsonArrayCollapsedRenderer;
import com.liuxiangdong.jsonview.renderer.JsonArrayEndRenderer;
import com.liuxiangdong.jsonview.renderer.JsonBooleanRenderer;
import com.liuxiangdong.jsonview.renderer.JsonDoubleRenderer;
import com.liuxiangdong.jsonview.renderer.JsonIntegerRenderer;
import com.liuxiangdong.jsonview.renderer.JsonLongRenderer;
import com.liuxiangdong.jsonview.renderer.JsonNullRenderer;
import com.liuxiangdong.jsonview.renderer.JsonObjectBeginRenderer;
import com.liuxiangdong.jsonview.renderer.JsonObjectCollapsedRenderer;
import com.liuxiangdong.jsonview.renderer.JsonObjectEndRenderer;
import com.liuxiangdong.jsonview.renderer.JsonStringRenderer;
import com.liuxiangdong.jsonview.renderer.Renderer;
import com.liuxiangdong.jsonview.renderer.RendererRegistry;
import com.liuxiangdong.jsonview.vh.JsonViewHolder;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * The default {@link android.support.v7.widget.RecyclerView.Adapter} for the {@link DefaultJsonView}.
 * The adapter will use the {@link DefaultElementProvider} if not set.
 */
@SuppressWarnings("OverlyCoupledClass")
public class DefaultJsonAdapter extends JsonAdapter {

    private ElementProvider elementProvider = new DefaultElementProvider();

    private final RendererRegistry rendererRegistry;

    public DefaultJsonAdapter() {
        rendererRegistry = new RendererRegistry();
        registerAllRenderers();
    }

    @SuppressWarnings("OverlyCoupledMethod")
    private void registerAllRenderers() {
        //primitive types
        registerRender(new JsonBooleanRenderer());
        registerRender(new JsonDoubleRenderer());
        registerRender(new JsonIntegerRenderer());
        registerRender(new JsonLongRenderer());
        registerRender(new JsonNullRenderer());
        registerRender(new JsonStringRenderer());

        //JSONArray
        registerRender(new JsonArrayBeginRenderer());
        registerRender(new JsonArrayEndRenderer());
        registerRender(new JsonArrayCollapsedRenderer());

        //JSONObject
        registerRender(new JsonObjectBeginRenderer());
        registerRender(new JsonObjectEndRenderer());
        registerRender(new JsonObjectCollapsedRenderer());
    }

    public <VM extends JsonViewModel, VH extends JsonViewHolder<VM>> void registerRender(Renderer<VM, VH> renderer) {
        rendererRegistry.registerRender(renderer);
    }

    /**
     * Set the {@link ElementProvider}.
     * @param elementProvider
     */
    public void setElementProvider(ElementProvider elementProvider) {
        if (elementProvider != null) {
            this.elementProvider = elementProvider;
        }
    }

    @SuppressWarnings("OverlyCoupledMethod")
    @NonNull
    @Override
    public JsonViewHolder<? extends JsonViewModel> onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Renderer<? extends JsonViewModel, ? extends JsonViewHolder<?>> renderer = rendererRegistry.getRenderer(i);
        if (renderer == null) {
            throw new IllegalStateException("No renderer for view type " + i);
        }
        return renderer.onCreateViewHolder(viewGroup, elementProvider);
    }

    @SuppressWarnings({"OverlyStrongTypeCast", "OverlyCoupledMethod"})
    @Override
    public void onBindViewHolder(@NonNull JsonViewHolder<? extends JsonViewModel> jsonViewHolder, int i) {
        int viewType = getItemViewType(i);
        onBindViewHolderInternal(viewType, jsonViewHolder, getItem(i));
    }

    @SuppressWarnings("OverlyCoupledMethod")
    @Override
    public int getItemViewType(int position) {
        Class<? extends JsonViewModel> clazz = getItem(position).getClass();
        int viewType = rendererRegistry.getItemViewType(clazz);
        if (viewType == 0) {
            throw new IllegalStateException("Invalid view type for " + clazz.getSimpleName());
        }
        return viewType;
    }

    @SuppressWarnings("unchecked")
    private <VM extends JsonViewModel, VH extends JsonViewHolder<VM>> void onBindViewHolderInternal(int viewType, JsonViewHolder<VM> viewHolder, JsonViewModel viewModel) {
        Renderer<VM, VH> renderer = rendererRegistry.getRenderer(viewType);
        if (renderer == null) {
            throw new IllegalStateException("No renderer for view type " + viewType);
        }
        VH vh = (VH) viewHolder;
        VM vm = (VM) viewModel;
        renderer.onBindViewHolder(vh, vm);
    }

}
