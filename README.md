[中文](https://github.com/LiuXiangdong/jsonview/blob/master/README_CN.md)
# JsonView
A custom view that displays Json in an interactive manner.
![](https://raw.githubusercontent.com/Liuxiangdong/jsonview/master/image/image0.png)
# Usage
Add the dependency in your build.gradle file
```xml
dependencies {
    implementation 'com.liuxiangdong.jsonview:library:1.0.0@aar'
}
```
JsonView is a RecyclerView under the hood, so basically you can use it like a RecyclerView. ~~You don’t have to set the LayoutManager, because JsonView uses the LinearLayoutManager as default~~(Starting from 1.1.0 JsonView can scroll horizontally, an exception will be thrown if you use setLayoutManager method). Then you have to set the adapter which extend the [JsonAdapter](https://github.com/LiuXiangdong/jsonview/blob/master/library/src/main/java/com/liuxiangdong/jsonview/JsonAdapter.java).
For example, in your xml file
```xml
<HorizontalScrollView
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:orientation="vertical">
    <com.liuxiangdong.jsonview.DefaultJsonView
        android:id="@+id/json_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
</HorizontalScrollView>
```
_Note: Starting from 1.1.0, JsonView can scroll horizontally, so you don't have to put it into a HorizontalScrollView._
Then in your Java file
```java
JsonView jsonView = findViewById(R.id.json_view);
jsonView.setJson(/**Your Json String**/);
```
Besides accepting a Json string, the JsonView accepts JSONObject or JSONArray as well by using the method of setJsonObject() and setJsonArray().
_Note: If either of the three method receive an invalid Json, the JsonView will do nothing._
# Customization & Utility
There is one way you can customize the JsonView through [ConfigurationProvider](https://github.com/LiuXiangdong/jsonview/blob/master/library/src/main/java/com/liuxiangdong/jsonview/ConfigurationProvider.java)
```java
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
```
The JsonView provides a utility to copy a JSONObject entry or a JSONArray entry through the [OnCopyJsonStringListener](https://github.com/LiuXiangdong/jsonview/blob/master/library/src/main/java/com/liuxiangdong/jsonview/OnCopyJsonStringListener.java)
```java
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
```
# DefaultJsonView
The library provides a default implementation of [JsonAdapter](https://github.com/LiuXiangdong/jsonview/blob/master/library/src/main/java/com/liuxiangdong/jsonview/JsonAdapter.java), namely the [DefaultJsonAdapter](https://github.com/LiuXiangdong/jsonview/blob/master/library/src/main/java/com/liuxiangdong/jsonview/DefaultJsonAdapter.java) as its name indicates, which is the adapter of [DefaultJsonView](https://github.com/LiuXiangdong/jsonview/blob/master/library/src/main/java/com/liuxiangdong/jsonview/DefaultJsonView.java). The appearance is displayed as above image.
## Functions
The [DefaultJsonView](https://github.com/LiuXiangdong/jsonview/blob/master/library/src/main/java/com/liuxiangdong/jsonview/DefaultJsonView.java) provides functions as below
1. Collapse or expand a JSONObject or a JSONArray entry by clicking the “-” or the “+” element.
2. Collapse all or expand all the JSONObject or the JSONArray entries by long clicking the “-” or the “+” element.
3. Copy the JSONObject or the JSONArray entry by clicking the “copy” element.
4. Copy the key or value of a key-value entry by long clicking the key or the value element. Because the text of the key and value TextViews are selectable, so a context menu will be shown by the framework.
## Customization
If you want to customize the style of the [DefaultJsonView](https://github.com/LiuXiangdong/jsonview/blob/master/library/src/main/java/com/liuxiangdong/jsonview/DefaultJsonView.java), you can provide a [ElementProvider](https://github.com/LiuXiangdong/jsonview/blob/master/library/src/main/java/com/liuxiangdong/jsonview/ElementProvider.java) to customize the element of it.
```java
/**
 * An interface for customizing the appearance of the ViewHolder used in {@link DefaultJsonAdapter}.
 */
public interface ElementProvider {
    /**
     * The view that provides the function of expanding a JSONObject or a JSONArray.
     * @param parent
     * @return
     */
    @Nullable
    View createExpandView(ViewGroup parent);

    /**
     * The view that provides the function of collapsing a JSONObject or a JSONArray.
     * @param parent
     * @return
     */
    @Nullable
    View createCollapseView(ViewGroup parent);

    /**
     * The view that provides the function of copy the string of a JSONObject or a JSONArray.
     * @param parent
     * @return
     */
    @Nullable
    View createCopyView(ViewGroup parent);

    /**
     * The view that provides the function of displaying a key.
     * @param parent
     * @return
     */
    @NonNull
    TextView createKeyView(ViewGroup parent);

    /**
     * The view that provides the function of displaying the opening brace of a JSONObject.
     * @param parent
     * @return
     */
    @Nullable
    TextView createOpeningBraceView(ViewGroup parent);

    /**
     * The view that provides the function of displaying the closing brace of a JSONObject.
     * @param parent
     * @return
     */
    @Nullable
    TextView createClosingBraceView(ViewGroup parent);

    /**
     * The view that provides the function of displaying the opening bracket of a JSONObject.
     * @param parent
     * @return
     */
    @Nullable
    TextView createOpeningBracketView(ViewGroup parent);

    /**
     * The view that provides the function of displaying the closing bracket of a JSONObject.
     * @param parent
     * @return
     */
    @Nullable
    TextView createClosingBracketView(ViewGroup parent);

    /**
     * The view that provides the function of displaying a string value.
     * @param parent
     * @return
     */
    @NonNull
    TextView createStringValueView(ViewGroup parent);

    /**
     * The view that provides the function of displaying a integer value.
     * @param parent
     * @return
     */
    @NonNull
    TextView createIntegerValueView(ViewGroup parent);

    /**
     * The view that provides the function of displaying a boolean value.
     * @param parent
     * @return
     */
    @NonNull
    TextView createBooleanValueView(ViewGroup parent);

    /**
     * The view that provides the function of displaying a double value.
     * @param parent
     * @return
     */
    @NonNull
    TextView createDoubleValueView(ViewGroup parent);

    /**
     * The view that provides the function of displaying a long value.
     * @param parent
     * @return
     */
    @NonNull
    TextView createLongValueView(ViewGroup parent);

    /**
     * The view that provides the function of displaying a null value.
     * @param parent
     * @return
     */
    @NonNull
    TextView createNullValueView(ViewGroup parent);

    /**
     * The view that provides the function of displaying the information of a collapsed JSONObject.
     * @param parent
     * @return
     */
    @NonNull
    TextView createCollapsedObjectInfoView(ViewGroup parent);

    /**
     * The view that provides the function of displaying the information of a collapsed JSONArray.
     * @param parent
     * @return
     */
    @NonNull
    TextView createCollapsedArrayInfoView(ViewGroup parent);

    /**
     * The indentation for a {@link IndentationView}
     * @param context
     * @return
     */
    int indentationWidth(Context context);

    /**
     * The line width for a {@link IndentationView}
     * @param context
     * @return
     */
    int indentationViewLineWidth(Context context);

    /**
     * The line color for a {@link IndentationView}
     * @param context
     * @return
     */
    @ColorInt
    int indentationViewLineColor(Context context);
}
```
# LICENSE
```
Copyright 2019 LiuXiangdong

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
