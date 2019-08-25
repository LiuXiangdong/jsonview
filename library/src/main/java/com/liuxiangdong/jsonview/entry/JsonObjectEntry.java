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
