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

import org.json.JSONArray;

import com.liuxiangdong.jsonview.vm.JsonArrayBeginViewModel;
import com.liuxiangdong.jsonview.vm.JsonArrayCollapsedViewModel;
import com.liuxiangdong.jsonview.vm.JsonArrayEndViewModel;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * A JSONArray value entry.
 */
public class JsonArrayEntry extends JsonCompoundEntry<JSONArray> {

    public JsonArrayEntry(String key, JSONArray value, int depth, int index) {
        super(key, value, depth, index);
    }

    @Override
    protected void inflateChildren() {
        JSONArray jsonArray = getValue();
        for (int i = 0; i < jsonArray.length(); i++) {
            JsonEntry<?> child = JsonEntryFactory.createJsonEntry("", jsonArray.opt(i), getDepth() + 1, i);
            if (child != null) {
                addChild(child);
            }
        }
    }

    @Override
    public JsonViewModel provideExpandedBeginViewModel() {
        return new JsonArrayBeginViewModel(getKey(), this, getDepth(), getParentEntryCount(), getIndex());
    }

    @Override
    public JsonViewModel provideExpandedEndViewModel() {
        return new JsonArrayEndViewModel(getDepth(), getParentEntryCount(), getIndex());
    }

    @Override
    public JsonViewModel provideCollapsedViewModel() {
        return new JsonArrayCollapsedViewModel(getKey(), this, getDepth(), getParentEntryCount(), getIndex());
    }

    @Override
    public int getEntryCount() {
        return getValue().length();
    }
}
