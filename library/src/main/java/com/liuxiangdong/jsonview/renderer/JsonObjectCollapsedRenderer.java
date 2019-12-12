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
import com.liuxiangdong.jsonview.vh.JsonObjectCollapsedViewHolder;
import com.liuxiangdong.jsonview.vm.JsonObjectCollapsedViewModel;

/**
 * A {@link Renderer} for a {@link JsonObjectCollapsedViewModel}.
 */
public class JsonObjectCollapsedRenderer extends Renderer<JsonObjectCollapsedViewModel, JsonObjectCollapsedViewHolder<JsonObjectCollapsedViewModel>> {
    @Override
    public Class<JsonObjectCollapsedViewModel> getViewModelClass() {
        return JsonObjectCollapsedViewModel.class;
    }

    @Override
    public JsonObjectCollapsedViewHolder<JsonObjectCollapsedViewModel> onCreateViewHolder(@NonNull ViewGroup viewGroup, ElementProvider elementProvider) {
        return new JsonObjectCollapsedViewHolder<>(viewGroup.getContext(), elementProvider);
    }
}
