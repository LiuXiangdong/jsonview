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
