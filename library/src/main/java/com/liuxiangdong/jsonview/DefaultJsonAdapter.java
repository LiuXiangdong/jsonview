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

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.liuxiangdong.jsonview.vh.InvalidViewHolder;
import com.liuxiangdong.jsonview.vh.JsonArrayBeginViewHolder;
import com.liuxiangdong.jsonview.vh.JsonArrayCollapsedViewHolder;
import com.liuxiangdong.jsonview.vh.JsonArrayEndViewHolder;
import com.liuxiangdong.jsonview.vh.JsonBooleanValueViewHolder;
import com.liuxiangdong.jsonview.vh.JsonDoubleValueViewHolder;
import com.liuxiangdong.jsonview.vh.JsonIntegerValueViewHolder;
import com.liuxiangdong.jsonview.vh.JsonLongValueViewHolder;
import com.liuxiangdong.jsonview.vh.JsonNullValueViewHolder;
import com.liuxiangdong.jsonview.vh.JsonObjectBeginViewHolder;
import com.liuxiangdong.jsonview.vh.JsonObjectCollapsedViewHolder;
import com.liuxiangdong.jsonview.vh.JsonObjectEndViewHolder;
import com.liuxiangdong.jsonview.vh.JsonStringValueViewHolder;
import com.liuxiangdong.jsonview.vh.JsonViewHolder;
import com.liuxiangdong.jsonview.vm.JsonArrayBeginViewModel;
import com.liuxiangdong.jsonview.vm.JsonArrayCollapsedViewModel;
import com.liuxiangdong.jsonview.vm.JsonArrayEndViewModel;
import com.liuxiangdong.jsonview.vm.JsonBooleanViewModel;
import com.liuxiangdong.jsonview.vm.JsonDoubleViewModel;
import com.liuxiangdong.jsonview.vm.JsonIntegerViewModel;
import com.liuxiangdong.jsonview.vm.JsonLongViewModel;
import com.liuxiangdong.jsonview.vm.JsonNullViewModel;
import com.liuxiangdong.jsonview.vm.JsonObjectBeginViewModel;
import com.liuxiangdong.jsonview.vm.JsonObjectCollapsedViewModel;
import com.liuxiangdong.jsonview.vm.JsonObjectEndViewModel;
import com.liuxiangdong.jsonview.vm.JsonStringViewModel;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * The default {@link android.support.v7.widget.RecyclerView.Adapter} for the {@link DefaultJsonView}.
 * The adapter will use the {@link DefaultElementProvider} if not set.
 */
@SuppressWarnings("OverlyCoupledClass")
public class DefaultJsonAdapter extends JsonAdapter {

    private static final int TYPE_INVALID = -1;
    private static final int TYPE_STRING_VALUE = 0;
    private static final int TYPE_INTEGER_VALUE = 1;
    private static final int TYPE_BOOLEAN_VALUE = 2;
    private static final int TYPE_DOUBLE_VALUE = 3;
    private static final int TYPE_LONG_VALUE = 4;
    private static final int TYPE_NULL_VALUE = 5;
    private static final int TYPE_JSON_OBJECT_BEGIN = 6;
    private static final int TYPE_JSON_OBJECT_END = 7;
    private static final int TYPE_JSON_OBJECT_COLLAPSED = 8;
    private static final int TYPE_JSON_ARRAY_BEGIN = 9;
    private static final int TYPE_JSON_ARRAY_END = 10;
    private static final int TYPE_JSON_ARRAY_COLLAPSED = 11;
    private ElementProvider elementProvider = new DefaultElementProvider();

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
        Context context = viewGroup.getContext();
        if (i == TYPE_STRING_VALUE) {
            return new JsonStringValueViewHolder(context, elementProvider);
        } else if (i == TYPE_INTEGER_VALUE) {
            return new JsonIntegerValueViewHolder(context, elementProvider);
        } else if (i == TYPE_BOOLEAN_VALUE) {
            return new JsonBooleanValueViewHolder(context, elementProvider);
        } else if (i == TYPE_DOUBLE_VALUE) {
            return new JsonDoubleValueViewHolder(context, elementProvider);
        } else if (i == TYPE_LONG_VALUE) {
            return new JsonLongValueViewHolder(context, elementProvider);
        } else if (i == TYPE_NULL_VALUE) {
            return new JsonNullValueViewHolder(context, elementProvider);
        } else if (i == TYPE_JSON_OBJECT_BEGIN) {
            return new JsonObjectBeginViewHolder(context, elementProvider);
        } else if (i == TYPE_JSON_OBJECT_END) {
            return new JsonObjectEndViewHolder(context, elementProvider);
        } else if (i == TYPE_JSON_OBJECT_COLLAPSED) {
            return new JsonObjectCollapsedViewHolder(context, elementProvider);
        } else if (i == TYPE_JSON_ARRAY_BEGIN) {
            return new JsonArrayBeginViewHolder(context, elementProvider);
        } else if (i == TYPE_JSON_ARRAY_END) {
            return new JsonArrayEndViewHolder(context, elementProvider);
        } else if (i == TYPE_JSON_ARRAY_COLLAPSED) {
            return new JsonArrayCollapsedViewHolder(context, elementProvider);
        }
        return new InvalidViewHolder(viewGroup.getContext());
    }

    @SuppressWarnings({"OverlyStrongTypeCast", "OverlyCoupledMethod"})
    @Override
    public void onBindViewHolder(@NonNull JsonViewHolder<? extends JsonViewModel> jsonViewHolder, int i) {
        int type = getItemViewType(i);
        if (type == TYPE_STRING_VALUE) {
            ((JsonStringValueViewHolder) jsonViewHolder).onBind((JsonStringViewModel) getItem(i));
        } else if (type == TYPE_INTEGER_VALUE) {
            ((JsonIntegerValueViewHolder) jsonViewHolder).onBind((JsonIntegerViewModel) getItem(i));
        } else if (type == TYPE_BOOLEAN_VALUE) {
            ((JsonBooleanValueViewHolder) jsonViewHolder).onBind((JsonBooleanViewModel) getItem(i));
        } else if (type == TYPE_DOUBLE_VALUE) {
            ((JsonDoubleValueViewHolder) jsonViewHolder).onBind((JsonDoubleViewModel) getItem(i));
        } else if (type == TYPE_LONG_VALUE) {
            ((JsonLongValueViewHolder) jsonViewHolder).onBind((JsonLongViewModel) getItem(i));
        } else if (type == TYPE_NULL_VALUE) {
            ((JsonNullValueViewHolder) jsonViewHolder).onBind((JsonNullViewModel) getItem(i));
        } else if (type == TYPE_JSON_OBJECT_BEGIN) {
            ((JsonObjectBeginViewHolder) jsonViewHolder).onBind((JsonObjectBeginViewModel) getItem(i));
        } else if (type == TYPE_JSON_OBJECT_END) {
            ((JsonObjectEndViewHolder) jsonViewHolder).onBind((JsonObjectEndViewModel) getItem(i));
        } else if (type == TYPE_JSON_OBJECT_COLLAPSED) {
            ((JsonObjectCollapsedViewHolder) jsonViewHolder).onBind((JsonObjectCollapsedViewModel) getItem(i));
        } else if (type == TYPE_JSON_ARRAY_BEGIN) {
            ((JsonArrayBeginViewHolder) jsonViewHolder).onBind((JsonArrayBeginViewModel) getItem(i));
        } else if (type == TYPE_JSON_ARRAY_END) {
            ((JsonArrayEndViewHolder) jsonViewHolder).onBind((JsonArrayEndViewModel) getItem(i));
        } else if (type == TYPE_JSON_ARRAY_COLLAPSED) {
            ((JsonArrayCollapsedViewHolder) jsonViewHolder).onBind((JsonArrayCollapsedViewModel) getItem(i));
        }
    }

    @SuppressWarnings("OverlyCoupledMethod")
    @Override
    public int getItemViewType(int position) {
        JsonViewModel viewModel = getItem(position);
        if (viewModel instanceof JsonStringViewModel) {
            return TYPE_STRING_VALUE;
        } else if (viewModel instanceof JsonIntegerViewModel) {
            return TYPE_INTEGER_VALUE;
        } else if (viewModel instanceof JsonBooleanViewModel) {
            return TYPE_BOOLEAN_VALUE;
        } else if (viewModel instanceof JsonDoubleViewModel) {
            return TYPE_DOUBLE_VALUE;
        } else if (viewModel instanceof JsonLongViewModel) {
            return TYPE_LONG_VALUE;
        } else if (viewModel instanceof JsonNullViewModel) {
            return TYPE_NULL_VALUE;
        } else if (viewModel instanceof JsonObjectBeginViewModel) {
            return TYPE_JSON_OBJECT_BEGIN;
        } else if (viewModel instanceof JsonObjectEndViewModel) {
            return TYPE_JSON_OBJECT_END;
        } else if (viewModel instanceof JsonObjectCollapsedViewModel) {
            return TYPE_JSON_OBJECT_COLLAPSED;
        } else if (viewModel instanceof JsonArrayBeginViewModel) {
            return TYPE_JSON_ARRAY_BEGIN;
        } else if (viewModel instanceof JsonArrayEndViewModel) {
            return TYPE_JSON_ARRAY_END;
        } else if (viewModel instanceof JsonArrayCollapsedViewModel) {
            return TYPE_JSON_ARRAY_COLLAPSED;
        }
        return TYPE_INVALID;
    }

}
