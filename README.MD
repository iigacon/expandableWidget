Expandable View
========
Android custom state View Group - Expandable View UI library

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-FlipView-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1073)


ABOUT
------
If you have a need to have a UI component which will act similar to toggle button or be like a view group with state checked/unchecked this is a right place for you.


![image](art/device-2015-02-25-223905.png =250x)
![image](art/device-view.png =250x)


GRADLE
------

```gradle
dependencies {
    compile 'com.github.polok.flipview:library:1.0.0'
}
```

Customization
------

```xml

   <!-- FlipView front view layout resource -->
   app:flip_view_front_layout="" [format="reference"]

   <!-- FlipView back view layout resource -->
   app:flip_view_back_layout="" [format="reference"]

   <!-- Is FlipView checked or not by default-->
   app:is_checked="" [format="boolean"] (default:false)

   <!-- Is FlipView animated when changing status -->
   app:"show_animations="" [format="boolean"] (default:false)

   <!-- FlipView animation to apply when state goes from "not checked" to "checked" -->
   app:fade_in_animation="" [format="reference"]

   <!-- FlipView animation to apply when state goes from "checked" to "not checked" -->
   app:fade_out_animation="" [format="reference"]

   <!-- FlipView animation duration -->
   app:animation_duration="" [format="integer"] (default:100ms)
```

USAGE BY HARD CODE
------

```xml
    <com.hb.hkm.hkmeexpandedview.CatelogView
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

  protected void init_expandtabs() throws Resources.NotFoundException {
        final LinearLayout container = (LinearLayout) findViewById(R.id.expanded_menu_list);
        // Start with two views
        for (int i = 0; i < 10; ++i) {
            ArrayList<DataBind> h = new ArrayList<DataBind>();
            h.add(new DataBind("sfsf6", "Sfs4e"));
            h.add(new DataBind("5s3fsf5", "2Sfs"));
            h.add(new DataBind("grege", "2Sfs"));
            h.add(new DataBind("42gw", "2Sfs"));
            h.add(new DataBind("234regerg", "2Sfs"));
            h.add(new DataBind("7s2ef3", "4Sfs9"));
            h.add(new DataBind("dfg432243", "4Sfs9"));

            CatelogViewBuilder cb = new CatelogViewBuilder();
            cb
                    .preset_src(R.drawable.bike, getResources().getDimension(R.dimen.home_collapsed))
                    .rndColor()
                    .setDataList(h);
            CatelogView c = new CatelogView(this, cb);
            container.addView(c);
        }
        // Note that this assumes a LayoutTransition is set on the container, which is the
        // case here because the container has the attribute "animateLayoutChanges" set to true
        // in the layout file. You can also call setLayoutTransition(new LayoutTransition()) in
        // code to set a LayoutTransition on any container.
        LayoutTransition transition = container.getLayoutTransition();
        // New capability as of Jellybean; monitor the container for *all* layout changes
        // (not just add/remove/visibility changes) and animate these changes as well.
        transition.enableTransitionType(LayoutTransition.CHANGING);
    }
    
```

API
------

```java
    public static interface FlipViewChangeListener {
        void onFlipViewClick(FlipView flipView, boolean isChecked);
    }
```



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
