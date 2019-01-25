# improt

```gradle
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}

dependencies {
	implementation 'com.github.gta371667:Xview:{version}'
}
```

## 包含MyAttrView、HeaderView、DuckListView、DuckRecyclerView

### MyAttrView用法
```xml
 <com.duck.widget.MyAttrView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:av_borderColor="@color/black"
        app:av_borderWidth="1dp"
        app:av_contentBackground="@color/colorAccent"
        app:av_contentTextSizeSp="14sp"
        app:av_contentText="123"
        app:av_contentTextColor="@color/black"
        app:av_icon_drawable_left="@drawable/xview_menu"
        app:av_icon_drawable_mHeight="20dp"
        app:av_icon_drawable_mWidth="20dp"
        app:av_icon_drawable_margin="5dp"
        app:av_icon_drawable_tint="@color/colorPrimaryDark"
        app:av_radius="30dp"
	app:av_radius_bottomLeft="5dp"
        app:av_radius_bottomRight="5dp"
        app:av_radius_topLeft="5dp"
        app:av_radius_topRight="5dp"
        />
```
| xml屬性 | 說明 | 單位 |
| --- | --- | --- |
| av_borderColor | 外框線條顏色 | #ffffff、R.color.red |
| av_borderWidth | 外框大小 | dp |
| av_contentBackground | 內圈顏色 | #ffffff、@color/red、@drawable/bg_drawer |
| av_contentTextSizeSp | 文字大小 | sp |
| av_contentText | 文字 | String、StringRes |
| av_contentTextColor | 文字顏色 | #ffffff、@color/red、@drawable/bg_drawer |
| av_icon_drawable_left | 左icon | #ffffff、@color/red、@drawable/bg_drawer |
| av_icon_drawable_top | 上icon | #ffffff、@color/red、@drawable/bg_drawer |
| av_icon_drawable_right | 右icon | #ffffff、@color/red、@drawable/bg_drawer |
| av_icon_drawable_bottom | 下icon | #ffffff、@color/red、@drawable/bg_drawer |
| av_icon_drawable_mHeight | icon高(目前icon統一) | dp |
| av_icon_drawable_mWidth | icon寬(目前icon統一) | dp |
| av_icon_drawable_margin | icon與文字距離 | dp |
| av_icon_drawable_tint | icon渲染色 | #ffffff、@color/red |
| av_radius | 圓角(優先度比單一設置圓角高) | dp |
| av_radius_bottomLeft | 左下圓角 | dp |
| av_radius_bottomRight | 右下圓角 | dp |
| av_radius_topLeft | 左上圓角 | dp |
| av_radius_topRight | 右上圓角 | dp |

### HeaderView用法
```xml
  <com.duck.widget.HeaderView
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      app:hd_headerBackground="@color/stroke_default_color"
      app:hd_headerDrawable="@mipmap/ic_launcher"
      app:hd_headerDrawableTint="@color/colorAccent"
      app:hd_headerRightIcon="@drawable/xview_menu"
      app:hd_headerRightIconTint="@color/colorAccent"
      app:hd_headerText="@string/app_name"
      app:hd_headerTextColor="@color/colorPrimary"
      app:hd_header_gravity="leftCenter"
      app:hd_icon_drawable_mHeight="40dp"
      app:hd_icon_drawable_mWidth="40dp"
      app:hd_icon_drawable_mPadding="10dp"
      app:hd_showInsideStatusBar="true"
      />
```
| xml屬性 | 說明 | 單位 |
| --- | --- | --- |
| hd_headerBackground | 背景色 | #ffffff、R.color.red、@drawable/bg_drawer |
| hd_headerDrawable | 左icon | #ffffff、@color/red、@drawable/bg_drawer |
| hd_headerDrawableTint | 左icon渲染色 | #ffffff、@color/red、@drawable/bg_drawer |
| hd_headerRightIcon | 右icon | #ffffff、@color/red、@drawable/bg_drawer |
| hd_headerRightIconTint | 右icon渲染色 | #ffffff、@color/red、@drawable/bg_drawer |
| hd_headerText | 文字 | String、StringRes |
| hd_headerTextColor | 文字 | #ffffff、@color/red |
| hd_header_gravity | 文字gravity | enum |
| hd_icon_drawable_mHeight | icon高度 | dp (預設40dp) |
| hd_icon_drawable_mWidth | icon寬度 | dp (預設40dp) |
| hd_icon_drawable_mPadding | iconPadding | dp (預設5dp) |
| hd_showInsideStatusBar | 是否根據狀態列高度marginTop | boolean |

### DuckListView用法
包含RecyclerView、SwipeRefreshLayout、封裝BaseRecyclerViewAdapterHelper
```xml
  <com.duck.widget.DuckListView
	android:id="@+id/mDuckRecyclerView2"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	app:refreshEnabled="false"
	app:swipeColor="@color/colorAccent"
	/>
```
| xml屬性 | 說明 | 單位 |
| --- | --- | --- |
| swipeColor | SwipeRefreshLayout顏色 | #ffffff、@color/red |
| refreshEnabled | 是否可以下拉刷新 | boolean |

### DuckRecyclerView用法
只包含RecyclerView、封裝BaseRecyclerViewAdapterHelper
```xml
  <com.duck.widget.DuckRecyclerView
	android:id="@+id/mDuckRecyclerView2"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	app:nestedScrollingEnabled="false"
	/>
```
| xml屬性 | 說明 | 單位 |
| --- | --- | --- |
| nestedScrollingEnabled | 是否把滾動交給外層 | boolean |
