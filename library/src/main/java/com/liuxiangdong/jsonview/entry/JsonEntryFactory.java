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

import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Used when building the {@link JsonEntry} tree through a Json.
 */
final class JsonEntryFactory {
    private JsonEntryFactory() {}

    @Nullable
    static JsonEntry<?> createJsonEntry(String key, Object value, int depth, int index) {
        if (value == null) {
            return null;
        }
        if (value instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) value;
            return new JsonObjectEntry(key, jsonObject, depth, index);
        }
        if (value instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) value;
            return new JsonArrayEntry(key, jsonArray, depth, index);
        }
        if (value == JSONObject.NULL) {
            return new JsonNullEntry(key, depth, index);
        }
        if (value instanceof Boolean) {
            return new JsonBooleanEntry(key, (Boolean) value, depth, index);
        }
        if (value instanceof Integer) {
            return new JsonIntegerEntry(key, (Integer) value, depth, index);
        }
        if (value instanceof Long) {
            return new JsonLongEntry(key, (Long) value, depth, index);
        }
        if (value instanceof Double) {
            return new JsonDoubleEntry(key, (Double) value, depth, index);
        }
        if (value instanceof String) {
            return new JsonStringEntry(key, value.toString(), depth, index);
        }
        return null;
    }
}
