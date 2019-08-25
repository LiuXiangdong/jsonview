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
package com.liuxiangdong.jsonview.entry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.liuxiangdong.jsonview.ConfigurationProvider;
import com.liuxiangdong.jsonview.OnCopyJsonStringListener;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * A compound entry that contains a list of {@link JsonEntry}s as child.
 * As a parent, this class provides not only pure key-value {@link JsonViewModel}s.
 * If this entry is collapsed, it provides a subclass of {@link com.liuxiangdong.jsonview.vm.JsonCollapsedViewModel}.
 * Otherwise it provides a list contains a opening model, a list of {@link JsonViewModel}s
 * provided by the children, appended with a closing model, just like a JSONObject or a JSONArray.
 * @param <T> Basically JSONObject or JSONArray
 */
public abstract class JsonCompoundEntry<T> extends JsonEntry<T> implements JsonParent {

    /**
     * The children entry
     */
    private List<JsonEntry<?>> children;
    /**
     * Indicates whether this compound entry is collapsed
     */
    private boolean collapsed = true;
    /**
     * The {@link JsonViewModel} when the entry is collapsed
     */
    private List<JsonViewModel> collapsedViewModels;
    /**
     * {@link OnStateChangeListener}
     */
    private OnStateChangeListener onStateChangeListener;
    /**
     * {@link OnCopyJsonStringListener}
     */
    private OnCopyJsonStringListener onCopyJsonStringListener;
    /**
     * {@link ConfigurationProvider}
     */
    private ConfigurationProvider provider;

    JsonCompoundEntry(String key, T value, int depth, int index) {
        super(key, value, depth, index);
    }

    /**
     * Add a child entry.
     * @param child
     */
    void addChild(JsonEntry<?> child) {
        if (child != null) {
            child.setParent(this);
            children.add(child);
            if (child instanceof JsonCompoundEntry) {
                JsonCompoundEntry<?> compoundItem = (JsonCompoundEntry<?>) child;
                compoundItem.onStateChangeListener = onStateChangeListener;
                compoundItem.onCopyJsonStringListener = onCopyJsonStringListener;
                compoundItem.setConfigurationProvider(provider);
            }
        }
    }

    public void setConfigurationProvider(ConfigurationProvider provider) {
        this.provider = provider;
        if (provider != null) {
            if (provider.shouldCollapse(this)) {
                setCollapsed(true);
            } else {
                setCollapsed(false);
            }
        }
    }

    public void setOnStateChangeListener(OnStateChangeListener listener) {
        onStateChangeListener = listener;
        if (children != null && !children.isEmpty()) {
            for (JsonEntry<?> child : children) {
                if (child instanceof JsonCompoundEntry) {
                    ((JsonCompoundEntry<?>) child).setOnStateChangeListener(onStateChangeListener);
                }
            }
        }
    }

    public void setOnCopyJsonStringListener(OnCopyJsonStringListener listener) {
        onCopyJsonStringListener = listener;
        if (children != null && !children.isEmpty()) {
            for (JsonEntry<?> child : children) {
                if (child instanceof JsonCompoundEntry) {
                    ((JsonCompoundEntry<?>) child).setOnCopyJsonStringListener(onCopyJsonStringListener);
                }
            }
        }
    }

    public boolean isCollapsed() {
        return collapsed;
    }

    /**
     * This method invokes the {@link OnCopyJsonStringListener} listener.
     */
    public void copyJsonString() {
        if (onCopyJsonStringListener != null) {
            onCopyJsonStringListener.onCopy(getValue().toString());
        }
    }

    /**
     * Unlike {@link #setCollapsed(boolean)}, this method expands this compound entry,
     * and all of its children who are compound entries as well.
     */
    public void expandAll() {
        setCollapsedInternal(false);
        boolean notifyStateChange = true;
        if (children != null && !children.isEmpty()) {
            for (JsonEntry<?> child : children) {
                if (child instanceof JsonCompoundEntry) {
                    ((JsonCompoundEntry<?>) child).expandAll();
                    notifyStateChange = false;
                }
            }
        }
        if (notifyStateChange) {
            notifyStateChange();
        }
    }

    /**
     * Similar to {@link #expandAll()} this method collapses all the compound entries.
     */
    public void collapseAll() {
        setCollapsedInternal(true);
        boolean notifyStateChange = true;
        if (children != null && !children.isEmpty()) {
            for (JsonEntry<?> child : children) {
                if (child instanceof JsonCompoundEntry) {
                    ((JsonCompoundEntry<?>) child).collapseAll();
                    notifyStateChange = false;
                }
            }
        }
        if (notifyStateChange) {
            notifyStateChange();
        }
    }

    /**
     * Set the state of the current compound entry.
     * @param collapsed
     */
    public void setCollapsed(boolean collapsed) {
        if (this.collapsed == collapsed) {
            return;
        }
        setCollapsedInternal(collapsed);
        notifyStateChange();
    }

    /**
     * Internal method of setting the state.
     * @param collapsed
     */
    private void setCollapsedInternal(boolean collapsed) {
        if (this.collapsed == collapsed) {
            return;
        }
        this.collapsed = collapsed;
        if (!collapsed && children == null) {
            children = new ArrayList<>();
            inflateChildren();
        }
    }

    /**
     * Invalidate the {@link JsonViewModel} list, and also invoke the {@link OnStateChangeListener} listener.
     */
    private void notifyStateChange() {
        invalidateViewModels();
        if (onStateChangeListener != null) {
            onStateChangeListener.onStateChange();
        }
    }

    /**
     * Inflate the children lazily.
     */
    protected abstract void inflateChildren();

    @Override
    public void invalidateViewModels() {
        viewModels = null;
        if (getParent() != null) {
            getParent().invalidateViewModels();
        }
    }

    @Override
    public List<JsonViewModel> getViewModels() {
        if (collapsed) {
            return getCollapsedViewModels();
        } else {
            return super.getViewModels();
        }
    }

    /**
     * Provide the {@link JsonViewModel}s when it is collapsed.
     * @return
     */
    private List<JsonViewModel> getCollapsedViewModels() {
        if (collapsedViewModels == null) {
            collapsedViewModels = new ArrayList<>();
            JsonViewModel viewModel = provideCollapsedViewModel();
            if (viewModel != null) {
                collapsedViewModels.add(viewModel);
            }
        }
        return Collections.unmodifiableList(collapsedViewModels);
    }

    @Override
    protected List<? extends JsonViewModel> provideViewModels() {
        List<JsonViewModel> result = new ArrayList<>();
        result.add(provideExpandedBeginViewModel());
        for (int i = 0; i < children.size(); i++) {
            result.addAll(children.get(i).getViewModels());
        }
        result.add(provideExpandedEndViewModel());
        return result;
    }

    /**
     * Provide the opening {@link JsonViewModel} when it is expanded.
     * @return
     */
    public abstract JsonViewModel provideExpandedBeginViewModel();

    /**
     * Provide the closing {@link JsonViewModel} when it is expanded.
     * @return
     */
    public abstract JsonViewModel provideExpandedEndViewModel();

    /**
     * Provide the collapsed {@link JsonViewModel} when it is collapsed.
     * @return
     */
    public abstract JsonViewModel provideCollapsedViewModel();

    /**
     * When the compound entry is expanded or collapsed, it's a state change.
     */
    public interface OnStateChangeListener {
        /**
         * Invoked when it is expanded or collapsed.
         */
        void onStateChange();
    }

}
