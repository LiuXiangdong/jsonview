package com.liuxiangdong.jsonview;

/**
 * The listener for copying a single JSONObject or JSONArray action.
 */
public interface OnCopyJsonStringListener {
    /**
     * Called when the action is performed.
     * @param json the string value of JSONObject or JSONArray
     */
    void onCopy(String json);
}
