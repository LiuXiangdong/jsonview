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
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.liuxiangdong.jsonview.entry.JsonArrayEntry;
import com.liuxiangdong.jsonview.entry.JsonBooleanEntry;
import com.liuxiangdong.jsonview.entry.JsonCompoundEntry;
import com.liuxiangdong.jsonview.entry.JsonDoubleEntry;
import com.liuxiangdong.jsonview.entry.JsonEntry;
import com.liuxiangdong.jsonview.entry.JsonIntegerEntry;
import com.liuxiangdong.jsonview.entry.JsonLongEntry;
import com.liuxiangdong.jsonview.entry.JsonNullEntry;
import com.liuxiangdong.jsonview.entry.JsonObjectEntry;
import com.liuxiangdong.jsonview.entry.JsonStringEntry;
import com.liuxiangdong.jsonview.entry.converter.JsonArrayEntryConverter;
import com.liuxiangdong.jsonview.entry.converter.JsonBooleanEntryConverter;
import com.liuxiangdong.jsonview.entry.converter.JsonDoubleEntryConverter;
import com.liuxiangdong.jsonview.entry.converter.JsonEntryConverter;
import com.liuxiangdong.jsonview.entry.converter.JsonEntryConverterRegistry;
import com.liuxiangdong.jsonview.entry.converter.JsonIntegerEntryConverter;
import com.liuxiangdong.jsonview.entry.converter.JsonLongEntryConverter;
import com.liuxiangdong.jsonview.entry.converter.JsonNullEntryConverter;
import com.liuxiangdong.jsonview.entry.converter.JsonObjectEntryConverter;
import com.liuxiangdong.jsonview.entry.converter.JsonStringEntryConverter;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A custom view that displays Json in an interactive manner. Basically this class provides a list of
 * {@link JsonViewModel}s which represent each element that displayed in a Json list. And accept a
 * {@link JsonAdapter} that render each {@link JsonViewModel} as ViewHolder.
 * class
 */
public class JsonView extends RecyclerView implements JsonCompoundEntry.OnStateChangeListener {
    /**
     * A list of {@link JsonViewModel}s
     */
    private final Collection<JsonViewModel> mViewModels = new ArrayList<>();
    /**
     * The root of {@link com.liuxiangdong.jsonview.entry.JsonEntry}
     */
    private JsonCompoundEntry<?> mJsonEntryRoot;
    /**
     * {@link OnCopyJsonStringListener}
     */
    private OnCopyJsonStringListener mOnCopyJsonStringListener;
    /**
     * {@link ConfigurationProvider}
     */
    private ConfigurationProvider mConfigurationProvider;

    /**
     * {@link JsonEntryConverterRegistry}
     */
    private final JsonEntryConverterRegistry mJsonEntryConverterRegistry = new JsonEntryConverterRegistry();

    public JsonView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public JsonView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public JsonView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        registerConverters();
        //Display the Json list as a linear vertical list.
        super.setLayoutManager(new HorizontalScrollLinearLayoutManager(context));
    }

    /**
     * Register all the basic {@link JsonEntryConverter}s.
     */
    @SuppressWarnings("OverlyCoupledMethod")
    private void registerConverters() {
        //primitive types
        registerJsonEntryConverter(JsonBooleanEntry.class, new JsonBooleanEntryConverter());
        registerJsonEntryConverter(JsonDoubleEntry.class, new JsonDoubleEntryConverter());
        registerJsonEntryConverter(JsonIntegerEntry.class, new JsonIntegerEntryConverter());
        registerJsonEntryConverter(JsonLongEntry.class, new JsonLongEntryConverter());
        registerJsonEntryConverter(JsonNullEntry.class, new JsonNullEntryConverter());
        registerJsonEntryConverter(JsonStringEntry.class, new JsonStringEntryConverter());

        //compound types
        registerJsonEntryConverter(JsonArrayEntry.class, new JsonArrayEntryConverter());
        registerJsonEntryConverter(JsonObjectEntry.class, new JsonObjectEntryConverter());
    }

    @Override
    public void setLayoutManager(@Nullable LayoutManager layout) {
        throw new IllegalStateException("LayoutManager cannot be changed.");
    }

    /**
     * Provide a way to register a custom {@link JsonEntryConverter}.
     * @param clazz
     * @param converter
     * @param <T>
     */
    public <T extends JsonEntry<?>> void registerJsonEntryConverter(Class<T> clazz, JsonEntryConverter<T> converter) {
        mJsonEntryConverterRegistry.registerJsonEntryConverter(clazz, converter);
    }

    /**
     * Set the Json string that is to be displayed in the view.
     *
     * @param jsonString the Json string to be displayed
     */
    public void setJson(String jsonString) {
        try {
            JSONTokener tokener = new JSONTokener(jsonString);
            Object object = tokener.nextValue();
            if (object instanceof JSONObject) {
                setJsonObject((JSONObject) object);
            } else if (object instanceof JSONArray) {
                setJsonArray((JSONArray) object);
            }
        } catch (JSONException ignored) {
        }
    }

    /**
     * Set the JSONObject that is to be displayed in the view.
     *
     * @param jsonObject the JSONObject to be displayed
     */
    public void setJsonObject(JSONObject jsonObject) {
        if (jsonObject != null) {
            mJsonEntryRoot = new JsonObjectEntry("", jsonObject, 0, 0, mJsonEntryConverterRegistry);
            initJsonItemRoot();
        }
    }

    /**
     * Set the JSONArray that is to be displayed in the view.
     *
     * @param jsonArray the JSONArray to be displayed
     */
    public void setJsonArray(JSONArray jsonArray) {
        if (jsonArray != null) {
            mJsonEntryRoot = new JsonArrayEntry("", jsonArray, 0, 0, mJsonEntryConverterRegistry);
            initJsonItemRoot();
        }
    }

    /**
     * Initialize the root of the {@link com.liuxiangdong.jsonview.entry.JsonEntry}
     * after a Json string or JSONObject or JSONArray is set.
     */
    private void initJsonItemRoot() {
        mJsonEntryRoot.setOnCopyJsonStringListener(mOnCopyJsonStringListener);
        mJsonEntryRoot.setConfigurationProvider(mConfigurationProvider);
        mJsonEntryRoot.setOnStateChangeListener(this);
        invalidateViewModels();
    }

    /**
     * Set the {@link OnCopyJsonStringListener}.
     *
     * @param listener {@link OnCopyJsonStringListener}
     */
    public void setOnCopyJsonStringListener(OnCopyJsonStringListener listener) {
        mOnCopyJsonStringListener = listener;
        if (mJsonEntryRoot != null) {
            mJsonEntryRoot.setOnCopyJsonStringListener(mOnCopyJsonStringListener);
        }
    }

    /**
     * Set the {@link ConfigurationProvider}.
     *
     * @param configurationProvider {@link ConfigurationProvider}
     */
    public void setConfigurationProvider(ConfigurationProvider configurationProvider) {
        mConfigurationProvider = configurationProvider;
    }

    /**
     * Somewhere of the tree of the {@link com.liuxiangdong.jsonview.entry.JsonEntry} is dirty.
     * So the list of {@link JsonViewModel}s need to be rebuilt.
     * Basically it is called during the initialization or a JSONObject or a JSONArray is expanded
     * or collapsed.
     */
    private void invalidateViewModels() {
        mViewModels.clear();
        if (mJsonEntryRoot != null) {
            List<JsonViewModel> viewModels = mJsonEntryRoot.getViewModels();
            if (viewModels != null && !viewModels.isEmpty()) {
                mViewModels.addAll(viewModels);
            }
        }
        Adapter<?> adapter = getAdapter();
        if (adapter instanceof JsonAdapter) {
            ((JsonAdapter) adapter).setViewModels(mViewModels);
            requestLayout();
        }
    }

    @Override
    public void onStateChange() {
        invalidateViewModels();
    }

    /**
     * A {@link LinearLayoutManager} that can scroll horizontally.
     */
    private static class HorizontalScrollLinearLayoutManager extends LinearLayoutManager {
        /**
         * Keep track of the current left of the children
         */
        private int mCurrentLeft;

        HorizontalScrollLinearLayoutManager(Context context) {
            super(context);
        }

        @Override
        public void layoutDecoratedWithMargins(@NonNull View child, int left, int top, int right, int bottom) {
            super.layoutDecoratedWithMargins(child, left, top, right, bottom);
            ViewCompat.offsetLeftAndRight(child, mCurrentLeft - child.getLeft());
        }

        @SuppressWarnings("ConstantConditions")
        @Override
        public int scrollHorizontallyBy(int dx, Recycler recycler, State state) {
            int childCount = getChildCount();
            if (childCount == 0 || dx == 0) {
                return 0;
            }
            int scrolled = 0;
            //noinspection IfStatementWithIdenticalBranches
            if (dx > 0) {
                int maxRightDiff = 0;
                for (int i = 0; i < childCount; i++) {
                    int rightDiff = getChildAt(i).getRight() - getWidth();
                    if (rightDiff > maxRightDiff) {
                        maxRightDiff = rightDiff;
                    }
                }
                if (maxRightDiff > 0) {
                    scrolled = Math.min(dx, maxRightDiff);
                    for (int i = 0; i < childCount; i++) {
                        ViewCompat.offsetLeftAndRight(getChildAt(i), -scrolled);
                    }
                }
            } else {
                int minLeft = 0;
                for (int i = 0; i < childCount; i++) {
                    if (getChildAt(i).getLeft() < minLeft) {
                        minLeft = getChildAt(i).getLeft();
                    }
                }
                if (minLeft < 0) {
                    scrolled = Math.max(dx, minLeft);
                    for (int i = 0; i < childCount; i++) {
                        ViewCompat.offsetLeftAndRight(getChildAt(i), -scrolled);
                    }
                }
            }
            mCurrentLeft = getChildAt(0).getLeft();
            return scrolled;
        }

        @Override
        public boolean canScrollHorizontally() {
            return true;
        }
    }
}
