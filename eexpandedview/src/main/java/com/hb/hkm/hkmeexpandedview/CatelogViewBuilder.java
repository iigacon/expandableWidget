package com.hb.hkm.hkmeexpandedview;

import com.hb.hkm.hkmeexpandedview.databindingmodel.BasicDataBind;
import com.hb.hkm.hkmeexpandedview.databindingmodel.SlickBind;

import java.util.ArrayList;

/**
 * Created by hesk on 2/24/15.
 */
public class CatelogViewBuilder {
    public enum CHILD_LAYOUT_TYPE {
        DEFAULT, ICON_TEXT, CUSTOM
    }

    private int resLayout = 0, red = 0, green = 0, blue = 0, viewHeightHalf = 0, viewWidthHalf = 0, resSrcId = 0;
    private float collapsed_height;
    private ArrayList<BasicDataBind> list_source;
    private boolean spring_enable = false;
    private toggleWatcher watcher;
    private String imgurl = "";

    public CatelogViewBuilder() {
        setLayout(CHILD_LAYOUT_TYPE.DEFAULT);
        enableFBSpring(true);
    }

    public CatelogViewBuilder enableFBSpring(boolean b) {
        spring_enable = b;
        return this;
    }

    public CatelogViewBuilder preset_src(String image_uri, float height) {
        imgurl = image_uri;
        resSrcId = 0;
        collapsed_height = height;
        return this;
    }

    public CatelogViewBuilder preset_src(int src, float height) {
        resSrcId = src;
        imgurl = "";
        collapsed_height = height;
        return this;
    }

    public CatelogViewBuilder rndColor() {
        red = (int) (Math.random() * 128 + 127);
        green = (int) (Math.random() * 128 + 127);
        blue = (int) (Math.random() * 128 + 127);
        return this;
    }

    public CatelogViewBuilder setItemLayout(int resLayoutId) {
        resLayout = resLayoutId;
        setLayout(CHILD_LAYOUT_TYPE.CUSTOM);
        return this;
    }

    public CatelogViewBuilder setLayout(CHILD_LAYOUT_TYPE res) {
        switch (res) {
            case DEFAULT:
                resLayout = R.layout.l_default;
                break;
            case ICON_TEXT:
                resLayout = R.layout.l_icon_text_c;
                break;
            case CUSTOM:
                break;
            default:
                resLayout = R.layout.l_default;
                break;
        }
        return this;
    }

    private int resLayoutSecondLayer = 0;
    private int resStyleId = 0;
    private String titleSecondLayer = "";

    public CatelogViewBuilder setSecondLayerOnBanner(int resId) {
        resLayoutSecondLayer = resId;
        return this;

    }

    public int getResLayoutSecondLayer() {
        return resLayoutSecondLayer;
    }

    public CatelogViewBuilder setImageTitle(String content) {
        titleSecondLayer = content;
        resLayout = 0;
        return this;
    }

    public CatelogViewBuilder setCustomLayout(int resId) {
        resLayout = resId;
        titleSecondLayer = "";
        return this;
    }

    public CatelogViewBuilder withCustomStyle(int styleId) {
        resStyleId = styleId;
        return this;
    }

    public CatelogViewBuilder setDataList(ArrayList<BasicDataBind> str) {
        list_source = str;
        return this;
    }

    public ArrayList<BasicDataBind> getPrimaryList() {
        return list_source;
    }

    public String getTitleOnSecondLayer() {
        return titleSecondLayer;
    }

    public int getLayoutResId() {
        if (resLayout == 0) {
            resLayout = R.layout.l_default;
        }
        return resLayout;
    }

    public boolean hasSpring() {
        return spring_enable;
    }

    public int getResId() {
        return resSrcId;
    }

    public String getBannerImageUrl() {
        return imgurl;
    }

    public float getHeight() {
        return collapsed_height;
    }

    public int getColor() {
        return 0xff << 24 | (red << 16) | (green << 8) | blue;
    }

    public CatelogViewBuilder setWatcher(toggleWatcher t) {
        watcher = t;
        return this;
    }

    public boolean hasWatcher() {
        return watcher != null;
    }

    public void notifyWatcher(CatelogView view) {
        watcher.onSelect(view);
    }
}
