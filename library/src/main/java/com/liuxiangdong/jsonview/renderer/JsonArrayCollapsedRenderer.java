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
import com.liuxiangdong.jsonview.vh.JsonArrayCollapsedViewHolder;
import com.liuxiangdong.jsonview.vm.JsonArrayCollapsedViewModel;

/**
 * A {@link Renderer} for a {@link JsonArrayCollapsedViewModel}.
 */
public class JsonArrayCollapsedRenderer extends Renderer<JsonArrayCollapsedViewModel, JsonArrayCollapsedViewHolder<JsonArrayCollapsedViewModel>> {
    @Override
    public Class<JsonArrayCollapsedViewModel> getViewModelClass() {
        return JsonArrayCollapsedViewModel.class;
    }

    @Override
    public JsonArrayCollapsedViewHolder<JsonArrayCollapsedViewModel> onCreateViewHolder(@NonNull ViewGroup viewGroup, ElementProvider elementProvider) {
        return new JsonArrayCollapsedViewHolder<>(viewGroup.getContext(), elementProvider);
    }
}
