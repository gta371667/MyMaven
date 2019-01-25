# Xview

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

## 1.MyAttrView  2.HeaderView  3.DuckListView  4.DuckRecyclerView

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


| 1 | 2 |
| --- | --- |
| 11 | 22 |
| 33 | 44 |


# 1
## 2
### 3
#### 4
##### 5
###### 6
*斜體*

list
+ 1
+ 2
+ 3
+ 4

1. 1
1. 2
1. 3
1. 4



```gradle
allprojects {
    repositories {
        google()
        jcenter()
//        maven { url 'E:\\maven' }
        maven { url 'https://raw.github.com/gta371667/duck-maven/master/' }
        maven { url 'https://jitpack.io' }
        mavenCentral()
    }

    // jCenter; 加上這些為了防止編碼失敗
    tasks.withType(Javadoc) {
        options {
            encoding "UTF-8"
            charSet 'UTF-8'
            links "http://docs.oracle.com/javase/7/docs/api"
        }
    }
}

dependencies {
       implementation 'org.duck:xview:1.0.1'
 }

```
