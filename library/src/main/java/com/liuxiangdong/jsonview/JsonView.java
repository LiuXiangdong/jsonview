package com.liuxiangdong.jsonview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.liuxiangdong.jsonview.entry.JsonArrayEntry;
import com.liuxiangdong.jsonview.entry.JsonCompoundEntry;
import com.liuxiangdong.jsonview.entry.JsonObjectEntry;
import com.liuxiangdong.jsonview.vm.JsonViewModel;

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
        //Display the Json list as a linear vertical list.
        setLayoutManager(new LinearLayoutManager(context));
    }

    /**
     * Set the Json string that is to be displayed in the view.
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
     * @param jsonObject the JSONObject to be displayed
     */
    public void setJsonObject(JSONObject jsonObject) {
        if (jsonObject != null) {
            mJsonEntryRoot = new JsonObjectEntry("", jsonObject, 0, 0);
            initJsonItemRoot();
        }
    }

    /**
     * Set the JSONArray that is to be displayed in the view.
     * @param jsonArray the JSONArray to be displayed
     */
    public void setJsonArray(JSONArray jsonArray) {
        if (jsonArray != null) {
            mJsonEntryRoot = new JsonArrayEntry("", jsonArray, 0, 0);
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
     * @param configurationProvider {@link ConfigurationProvider}
     */
    public void setConfigurationProvider(ConfigurationProvider configurationProvider) {
        mConfigurationProvider = configurationProvider;
    }

    /**
     * Somewhere of the tree of the {@link com.liuxiangdong.jsonview.entry.JsonEntry} is dirty.
     * So the list of {@link JsonViewModel}s need to be rebuilt.
     * Basically it is called during the initialization or a JSONObject or a JSONArray is expaned
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
}
