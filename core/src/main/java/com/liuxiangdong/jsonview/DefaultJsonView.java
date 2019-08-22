package com.liuxiangdong.jsonview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * A default implementation of {@link JsonView} that provides functions of expanding
 * a JSONObject or a JSONArray, collapsing a JSONObject or a JSONArray and copy the string
 * value of a JSONObject or a JSONArray.
 * Note: You can long click the expanding view to expand the JSONObject or JSONArray and all
 * its children, and the same with collapsing view.
 */
public class DefaultJsonView extends JsonView {
    public DefaultJsonView(@NonNull Context context) {
        super(context);
        init();
    }

    public DefaultJsonView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DefaultJsonView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setAdapter(new DefaultJsonAdapter());
    }

    /**
     * Set the {@link ElementProvider}
     * @param elementProvider
     */
    public void setElementProvider(ElementProvider elementProvider) {
        if (getAdapter() != null) {
            ((DefaultJsonAdapter) getAdapter()).setElementProvider(elementProvider);
        }
    }
}
