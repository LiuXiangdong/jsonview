package com.liuxiangdong.jsonview;

import com.liuxiangdong.jsonview.entry.JsonCompoundEntry;

/**
 * An interface for providing the default configuration.
 */
public interface ConfigurationProvider {
    /**
     * Determine whether the JSONObject or JSONArray should be displayed expanded
     * or collapsed for the first appearance.
     * @param compoundEntry
     * @return true if should be collapsed, false otherwise
     */
    boolean shouldCollapse(JsonCompoundEntry<?> compoundEntry);
}
