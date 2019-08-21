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
