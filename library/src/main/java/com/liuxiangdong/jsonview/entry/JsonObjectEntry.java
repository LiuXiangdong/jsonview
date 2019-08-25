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

import org.json.JSONObject;

import java.util.Iterator;

import com.liuxiangdong.jsonview.vm.JsonObjectBeginViewModel;
import com.liuxiangdong.jsonview.vm.JsonObjectCollapsedViewModel;
import com.liuxiangdong.jsonview.vm.JsonObjectEndViewModel;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

/**
 * A JSONObject value entry.
 */
public class JsonObjectEntry extends JsonCompoundEntry<JSONObject> {

    public JsonObjectEntry(String key, JSONObject value, int depth, int index) {
        super(key, value, depth, index);
    }

    @Override
    protected void inflateChildren() {
        JSONObject jsonObject = getValue();
        Iterator<String> keys = jsonObject.keys();
        int index = 0;
        while (keys.hasNext()) {
            String k = keys.next();
            JsonEntry<?> child = JsonEntryFactory.createJsonEntry(k, jsonObject.opt(k), getDepth() + 1, index);
            if (child != null) {
                addChild(child);
            }
            index++;
        }
    }

    @Override
    public JsonViewModel provideExpandedBeginViewModel() {
        return new JsonObjectBeginViewModel(getKey(), this, getDepth(), getParentEntryCount(), getIndex());
    }

    @Override
    public JsonViewModel provideExpandedEndViewModel() {
        return new JsonObjectEndViewModel(getDepth(), getParentEntryCount(), getIndex());
    }

    @Override
    public JsonViewModel provideCollapsedViewModel() {
        return new JsonObjectCollapsedViewModel(getKey(), this, getDepth(), getParentEntryCount(), getIndex());
    }

    @Override
    public int getEntryCount() {
        return getValue().length();
    }
}
