# Xview

```gradle
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}

dependencies {
	implementation 'com.github.gta371667:Xview:Tag'
}

```

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

| 1 | 2 |
| --- | --- |
| 11 | 22 |
| 33 | 44 |

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
