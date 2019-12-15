[English](https://github.com/LiuXiangdong/jsonview/blob/master/README.md)
# JsonView
可交互的Json自定义控件。
![](https://raw.githubusercontent.com/Liuxiangdong/jsonview/master/image/image0.png)
# 使用
在build.gradle文件中添加依赖：
```xml
dependencies {
    implementation 'com.liuxiangdong.jsonview:library:2.0.0@aar'
}
```
JsonView在底层是一个RecyclerView，所以使用方式也是类似于RecyclerView。~~JsonView默认使用LinearLayoutManager，所以使用者无需指定LayoutManager~~ （从1.1.0版本开始，JsonView使用了自定义的LayoutManager，使用setLayoutManager会抛出异常）。JsonView接收继承自[JsonAdapter](https://github.com/LiuXiangdong/jsonview/blob/master/library/src/main/java/com/liuxiangdong/jsonview/JsonAdapter.java)子类的adapter。
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
_注：从1.1.0版本开始，JsonView可以左右滑动，所以无需嵌入HorizontalScrollView中。_
在布局的xml文件中，可以直接使用：
```xml
<com.liuxiangdong.jsonview.DefaultJsonView
    android:id="@+id/json_view"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"/>
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
从2.0.0开始, DefaultJsonView可以为每个条目展示索引。如下所示：
![](https://raw.githubusercontent.com/Liuxiangdong/jsonview/master/image/image1.png)
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

    /**
     * The view that provides the function of displaying the index information of a
     * {@link com.liuxiangdong.jsonview.entry.JsonEntry} within a
     * {@link com.liuxiangdong.jsonview.entry.JsonCompoundEntry}.
     * @param parent
     * @return
     */
    @Nullable
    TextView createIndexView(ViewGroup parent);

    /**
     * Denotes that whether the index of a {@link JsonViewModel} should be displayed.
     * @param context
     * @param viewModel
     * @param <T>
     * @return
     */
    <T extends JsonViewModel> boolean shouldDisplayIndex(Context context, T viewModel);
}
```
## JsonEntryConverter
从2.0.0开始, 使用者可以为相应的JsonEntry提供自定义的JsonViewModel，使用者需实现以下接口：
```java 
/**
 * A converter that converts a {@link JsonEntry} to a list of {@link JsonViewModel}s.
 * @param <T> A concrete subclass of {@link JsonEntry}.
 */
public interface JsonEntryConverter<T extends JsonEntry<?>> {

    /**
     * Subclass need to override this method to provide the corresponding {@link JsonViewModel}(s).
     * @return
     */
    List<? extends JsonViewModel> convert(T jsonEntry);
}
```
或者你可以使用库中已有的JsonEntryConverter。然后在DefaultJsonView中注册自定义的JsonEntryConverter。
使用自定义的JsonViewModel，使用者可以自定义每一个JsonEntry展示的文案。
## Renderer
Renderer的思想深受[这篇好文章](https://android.jlelse.eu/a-recyclerview-with-multiple-item-types-dfba3979050)的启发。
如果DefaultJsonView提供的默认ViewHolder已然不能满足需求，使用者可继承如下抽象类以提供JsonViewModel对应的自定义ViewHolder：
```java
/**
 * A class that binds the {@link JsonViewModel} and the corresponding ViewHolder
 * and it's view type together. It's used in the {@link com.liuxiangdong.jsonview.DefaultJsonAdapter}
 * to supply the ViewHolder for the data. You can provide a different {@link JsonViewModel} via the
 * {@link com.liuxiangdong.jsonview.entry.converter.JsonEntryConverter} for a {@link com.liuxiangdong.jsonview.entry.JsonEntry},
 * and then supply your own ViewHolder by extending this class. Then register your class to the
 * {@link com.liuxiangdong.jsonview.DefaultJsonView} via {@link com.liuxiangdong.jsonview.DefaultJsonView#registerRender(Renderer)}
 * method.
 * @param <VM> A subclass of {@link JsonViewModel}
 * @param <VH> A subclass of {@link JsonViewHolder}
 */
public abstract class Renderer<VM extends JsonViewModel, VH extends JsonViewHolder<VM>> {
    /**
     * The view type for the ViewHolder, it's generated automatically
     */
    private final int mItemViewType;


    public Renderer() {
        mItemViewType = ItemViewTypeProvider.nextItemViewType();
    }

    int getItemViewType() {
        return mItemViewType;
    }

    /**
     * Specify the class of the {@link JsonViewModel}.
     * @return The class of the {@link JsonViewModel}
     */
    public abstract Class<VM> getViewModelClass();

    /**
     * Create a corresponding ViewHolder that is similar to the method in an {@link android.support.v7.widget.RecyclerView.Adapter}.
     * @param viewGroup The viewGroup parameter from the {@link android.support.v7.widget.RecyclerView.Adapter#onCreateViewHolder(ViewGroup, int)}
     * @param elementProvider {@link ElementProvider}
     * @return
     */
    public abstract VH onCreateViewHolder(@NonNull ViewGroup viewGroup, ElementProvider elementProvider);

    /**
     * Bind the {@link JsonViewModel} to a ViewHolder.
     * @param viewHolder
     * @param viewModel
     */
    public void onBindViewHolder(@NonNull VH viewHolder, VM viewModel) {
        viewHolder.onBind(viewModel);
    }
}
```
接下来已然是在DefaultJsonView中注册之。
_注：如果你没有为自定义的JsonViewModel提供相应的Renderer，控件会抛出异常。_
# DecoratedJsonView
DecoratedJsonView从2.0.0开始在库中提供, 是一个继承了DefaultJsonView的控件，对每种JsonEntry提供了更多的展示细节。如下所示：
![](https://raw.githubusercontent.com/Liuxiangdong/jsonview/master/image/image2.png)
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
