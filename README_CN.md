[English](https://github.com/LiuXiangdong/jsonview/blob/master/README.md)
# JsonView
可交互的Json自定义控件。
![](https://raw.githubusercontent.com/Liuxiangdong/jsonview/master/image/image0.png)
# 使用
在build.gradle文件中添加依赖：
```xml
dependencies {
    implementation 'com.liuxiangdong.jsonview:library:1.0.0@aar'
}
```
JsonView在底层是一个RecyclerView，所以使用方式也是类似于RecyclerView。JsonView默认使用LinearLayoutManager，所以使用者无需指定LayoutManager。JsonView接收继承自[JsonAdapter](https://github.com/LiuXiangdong/jsonview/blob/master/library/src/main/java/com/liuxiangdong/jsonview/JsonAdapter.java)子类的adapter。
例如，在布局的xml文件中：
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
在Java文件中可通过如下方式使用：
```java
JsonView jsonView = findViewById(R.id.json_view);
jsonView.setJson(/**Your Json String**/);
```
除了可以设置字符串的方式，JsonView也可以通过setJsonObject()和setJsonArray()这两个方法设置Json。 
_注：如果上述三个方法接收了无效的Json，JsonView将不会执行任何操作。_
# 自定义以及工具类
通过[ConfigurationProvider](https://github.com/LiuXiangdong/jsonview/blob/master/library/src/main/java/com/liuxiangdong/jsonview/ConfigurationProvider.java)，使用者可以自定义JsonView：
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
通过[OnCopyJsonStringListener](https://github.com/LiuXiangdong/jsonview/blob/master/library/src/main/java/com/liuxiangdong/jsonview/OnCopyJsonStringListener.java)，使用者可以获取JSONObject或者JSONArray条目的字符串形式值：
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
此库提供了一个[JsonAdapter](https://github.com/LiuXiangdong/jsonview/blob/master/library/src/main/java/com/liuxiangdong/jsonview/JsonAdapter.java)的默认实现，即[DefaultJsonAdapter](https://github.com/LiuXiangdong/jsonview/blob/master/library/src/main/java/com/liuxiangdong/jsonview/DefaultJsonAdapter.java)。也即是[DefaultJsonView](https://github.com/LiuXiangdong/jsonview/blob/master/library/src/main/java/com/liuxiangdong/jsonview/DefaultJsonView.java)的默认adapter。这个控件的样式如上图展示。
## 功能
[DefaultJsonView](https://github.com/LiuXiangdong/jsonview/blob/master/library/src/main/java/com/liuxiangdong/jsonview/DefaultJsonView.java)提供了如下功能：
1. 可点击“-”或“+”元素展开或收起JSONObject或JSONArray条目。
2. 可长按“-”或“+”元素展开或收起全部JSONObject或JSONArray条目。
3. 可点击“复制”元素复制JSONObject或JSONArray条目。
4. 可长按“键”或“值”元素复制键值对条目的键值。因为，键值对对应的TextView的文字是可选的，所以长按时会弹出系统的弹框。
## 自定义
如果使用者希望自定义[DefaultJsonView](https://github.com/LiuXiangdong/jsonview/blob/master/library/src/main/java/com/liuxiangdong/jsonview/DefaultJsonView.java)的样式,可通过[ElementProvider](https://github.com/LiuXiangdong/jsonview/blob/master/library/src/main/java/com/liuxiangdong/jsonview/ElementProvider.java)来定义每个元素的样式：
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
