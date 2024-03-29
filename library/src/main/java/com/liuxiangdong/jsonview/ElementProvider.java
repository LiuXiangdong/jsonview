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
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * An interface for customizing the appearance of the ViewHolder used in {@link DefaultJsonAdapter}.
 */
public interface ElementProvider {
    /**
     * The view that provides the function of expanding a JSONObject or a JSONArray.
     * @param parent
     * @return
     */
    @Nullable
    View createExpandView(ViewGroup parent);

    /**
     * The view that provides the function of collapsing a JSONObject or a JSONArray.
     * @param parent
     * @return
     */
    @Nullable
    View createCollapseView(ViewGroup parent);

    /**
     * The view that provides the function of copy the string of a JSONObject or a JSONArray.
     * @param parent
     * @return
     */
    @Nullable
    View createCopyView(ViewGroup parent);

    /**
     * The view that provides the function of displaying a key.
     * @param parent
     * @return
     */
    @NonNull
    TextView createKeyView(ViewGroup parent);

    /**
     * The view that provides the function of displaying the opening brace of a JSONObject.
     * @param parent
     * @return
     */
    @Nullable
    TextView createOpeningBraceView(ViewGroup parent);

    /**
     * The view that provides the function of displaying the closing brace of a JSONObject.
     * @param parent
     * @return
     */
    @Nullable
    TextView createClosingBraceView(ViewGroup parent);

    /**
     * The view that provides the function of displaying the opening bracket of a JSONObject.
     * @param parent
     * @return
     */
    @Nullable
    TextView createOpeningBracketView(ViewGroup parent);

    /**
     * The view that provides the function of displaying the closing bracket of a JSONObject.
     * @param parent
     * @return
     */
    @Nullable
    TextView createClosingBracketView(ViewGroup parent);

    /**
     * The view that provides the function of displaying a string value.
     * @param parent
     * @return
     */
    @NonNull
    TextView createStringValueView(ViewGroup parent);

    /**
     * The view that provides the function of displaying a integer value.
     * @param parent
     * @return
     */
    @NonNull
    TextView createIntegerValueView(ViewGroup parent);

    /**
     * The view that provides the function of displaying a boolean value.
     * @param parent
     * @return
     */
    @NonNull
    TextView createBooleanValueView(ViewGroup parent);

    /**
     * The view that provides the function of displaying a double value.
     * @param parent
     * @return
     */
    @NonNull
    TextView createDoubleValueView(ViewGroup parent);

    /**
     * The view that provides the function of displaying a long value.
     * @param parent
     * @return
     */
    @NonNull
    TextView createLongValueView(ViewGroup parent);

    /**
     * The view that provides the function of displaying a null value.
     * @param parent
     * @return
     */
    @NonNull
    TextView createNullValueView(ViewGroup parent);

    /**
     * The view that provides the function of displaying the information of a collapsed JSONObject.
     * @param parent
     * @return
     */
    @NonNull
    TextView createCollapsedObjectInfoView(ViewGroup parent);

    /**
     * The view that provides the function of displaying the information of a collapsed JSONArray.
     * @param parent
     * @return
     */
    @NonNull
    TextView createCollapsedArrayInfoView(ViewGroup parent);

    /**
     * The indentation for a {@link IndentationView}
     * @param context
     * @return
     */
    int indentationWidth(Context context);

    /**
     * The line width for a {@link IndentationView}
     * @param context
     * @return
     */
    int indentationViewLineWidth(Context context);

    /**
     * The line color for a {@link IndentationView}
     * @param context
     * @return
     */
    @ColorInt
    int indentationViewLineColor(Context context);

    /**
     * The view that provides the function of displaying the index information of a
     * {@link com.liuxiangdong.jsonview.entry.JsonEntry} within a
     * {@link com.liuxiangdong.jsonview.entry.JsonCompoundEntry}.
     * @param parent
     * @return
     */
    @Nullable
    TextView createIndexView(ViewGroup parent);

    /**
     * Denotes that whether the index of a {@link JsonViewModel} should be displayed.
     * @param context
     * @param viewModel
     * @param <T>
     * @return
     */
    <T extends JsonViewModel> boolean shouldDisplayIndex(Context context, T viewModel);
}
