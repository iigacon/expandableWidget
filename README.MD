Expandable Catelog View [![API](https://img.shields.io/badge/API-17%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=17)
========
The java library to delivery slick nice expandable widget view. FB Spring Featured Widget.

GET
------
Stay for our [release note](https://github.com/jjhesk/expandableWidget/releases)

Gradle
------

```gradle
dependencies {
	        compile 'com.github.jjhesk:expandableWidget:v0.2'
	}
```

ABOUT
------
If you have a need to have a UI component which can be add as a part of the container layout, you can programmatically adding fragment into the header. This way you can do more interactions anyhow you like.

<img src="https://raw.github.com/jjhesk/expandableWidget/master/art/device-2015-03-02-101454.png" alt="screenshot" width="300px" height="auto" />
<img src="https://raw.github.com/jjhesk/expandableWidget/master/art/device-view.png" alt="screenshot" width="300px" height="auto" />

XML usage
------

```xml
    <hkm.ui.expendablefragment.CatelogView
        android:id="@+id/expandble_item"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:descendantFocusability="blocksDescendants"
        app:red="213"
        app:blue="213"
        app:green="213"
        app:src="@drawable/your_image_here"
        app:childlayout="@layout/flip_text_view_front_layout"
        />
```

BUILD LAYOUT PROGRAMMICALLY
------


```java
/**
BUILDING A CUSTOM FRAGMENT HEADER
**/
CatelogViewBuilder cb = new CatelogViewBuilder(this);
cb.setHeaderFragment(YOUR_FRAGMENT_CLASS.class).setDataList(THE_LIST_DATA_BINDING).setFragmentHeight(RES_ID_OF_DIMENSION);
container.addView(cb.create());

/**
BUILDING DEFAULT IMAGE BACKGROUND
**/
CatelogViewBuilder cb = new CatelogViewBuilder(this);
cb.preset_src(IMAGE_URL, THE_HEIGHT_INT).rndColor().setImageTitle(TITLE_STRING).setDataList(bb);
container.addView(cb.create());
```

[Default Presentation](https://github.com/jjhesk/expandableWidget/wiki#default-sample-code)

[Advanced Presentation](https://github.com/jjhesk/expandableWidget/wiki#advanced-sample-code)

[Fragment Implementations](https://github.com/jjhesk/expandableWidget/wiki#advanced-sample-code)
License
----------

```
Copyright 2014 HESKeyo

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
