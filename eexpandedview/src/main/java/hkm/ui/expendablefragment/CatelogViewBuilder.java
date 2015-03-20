package hkm.ui.expendablefragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;

import hkm.ui.expendablefragment.R;

import hkm.ui.expendablefragment.databindingmodel.BasicDataBind;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hesk on 2/24/15.
 */
public class CatelogViewBuilder<T extends Fragment & FragmentClickable> implements Serializable {
    public enum CHILD_LAYOUT_TYPE {
        DEFAULT, ICON_TEXT, CUSTOM
    }

    public static int NOSHADOW = -1;
    public static int NOSETTING = 0;

    public static class dataholder {
        public int resLayoutChildItem = NOSETTING, red = NOSETTING, green = NOSETTING, blue = NOSETTING, viewHeightHalf = 0, viewWidthHalf = 0, header_image_drawable_resId = 0;
        public float collapsed_height = 100.0f;
        public String imgurl = "";
        public boolean spring_enable = false;
        public int resLayoutSecondLayer = NOSETTING;
        public int resStyleId = NOSETTING;
        public int shadowdrawable = NOSETTING;
        public String titleSecondLayer = "";
        public int shadowheight = NOSETTING;
    }


    private dataholder datagroup = new dataholder();
    private ArrayList<BasicDataBind> list_source;
    private toggleWatcher watcher;

    protected Activity activity_context;

    public CatelogViewBuilder(Activity c) {
        setLayoutChildType(CHILD_LAYOUT_TYPE.DEFAULT);
        enableFBSpring(true);
        activity_context = c;
    }

    public CatelogView create() {
        return new CatelogView(activity_context, this);
    }

    public Context getContext() {
        return activity_context;
    }

    public CatelogViewBuilder enableFBSpring(boolean b) {
        datagroup.spring_enable = b;
        return this;
    }

    public CatelogViewBuilder preset_src(String image_uri, float height) {
        datagroup.imgurl = image_uri;
        datagroup.header_image_drawable_resId = 0;
        datagroup.collapsed_height = height;
        return this;
    }

    public CatelogViewBuilder preset_src(int src, float height) {
        datagroup.header_image_drawable_resId = src;
        datagroup.imgurl = "";
        datagroup.collapsed_height = height;
        return this;
    }

    public CatelogViewBuilder rndColor() {
        datagroup.red = (int) (Math.random() * 128 + 127);
        datagroup.green = (int) (Math.random() * 128 + 127);
        datagroup.blue = (int) (Math.random() * 128 + 127);
        return this;
    }

    public CatelogViewBuilder setItemChildLayoutId(int resLayoutId) {
        datagroup.resLayoutChildItem = resLayoutId;
        setLayoutChildType(CHILD_LAYOUT_TYPE.CUSTOM);
        return this;
    }

    public CatelogViewBuilder setLayoutChildType(final CHILD_LAYOUT_TYPE res) {
        switch (res) {
            case DEFAULT:
                datagroup.resLayoutChildItem = R.layout.l_default;
                break;
            case ICON_TEXT:
                datagroup.resLayoutChildItem = R.layout.l_icon_text_c;
                break;
            case CUSTOM:
                break;
            default:
                datagroup.resLayoutChildItem = R.layout.l_default;
                break;
        }
        return this;
    }


    public CatelogViewBuilder setSecondLayerOnBanner(int resId) {
        datagroup.resLayoutSecondLayer = resId;
        return this;

    }

    public int getResLayoutSecondLayer() {
        return datagroup.resLayoutSecondLayer;
    }

    public CatelogViewBuilder setImageTitle(String content) {
        datagroup.titleSecondLayer = content;
        return this;
    }

    /**
     * fragment control display
     */
    private Class<T> customFragment;
    private boolean isUsingCustomFragment = false;

    public CatelogViewBuilder setHeaderFragment(Class<T> clazz) {
        isUsingCustomFragment = true;
        datagroup.titleSecondLayer = "";
        customFragment = clazz;
        return this;
    }

   /* @Override
    public <T extends Fragment & FragmentClickable> T createFragment(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }*/

    public CatelogViewBuilder setFragmentHeight(int ResId) {
        datagroup.collapsed_height = activity_context.getResources().getDimension(ResId);
        return this;
    }

    public FragmentTransaction getFTrans() {
        return activity_context
                .getFragmentManager()
                .beginTransaction();
    }

    public boolean useFragment() {
        return isUsingCustomFragment;
    }

    public Class<T> getCustomFragment() {
        return customFragment;
    }

    /**
     * use custom style to override the default style
     *
     * @param styleId
     * @return
     */
    public CatelogViewBuilder withCustomStyle(int styleId) {
        datagroup.resStyleId = styleId;
        return this;
    }

    /**
     * setup the shadow drawable ID
     *
     * @param resId
     * @return
     */
    public CatelogViewBuilder setShadowDrawable(int resId) {
        datagroup.shadowdrawable = resId;
        return this;
    }

    /**
     * to retrieve the datagroup data
     *
     * @return
     */
    public dataholder getContainerData() {
        return datagroup;
    }

    /**
     * import data listing
     *
     * @param str
     * @return
     */
    public CatelogViewBuilder setDataList(ArrayList<BasicDataBind> str) {
        list_source = str;
        return this;
    }

    public ArrayList<BasicDataBind> getPrimaryList() {
        return list_source;
    }

    /**
     * set up the display title on the header section
     *
     * @return
     */
    public String getTitleOnSecondLayer() {
        return datagroup.titleSecondLayer;
    }

    public int getChildItemLayoutResId() {
        return datagroup.resLayoutChildItem;
    }

    /**
     * using spring interactions
     *
     * @return
     */
    public boolean hasSpring() {
        return datagroup.spring_enable;
    }

    public int getResId() {
        return datagroup.header_image_drawable_resId;
    }

    public boolean useImageURI() {
        return !datagroup.imgurl.equalsIgnoreCase("");
    }

    public String getBannerImageUrl() {
        return datagroup.imgurl;
    }

    public float getHeight() {
        return datagroup.collapsed_height;
    }

    public int getHeightWhole() {
        return (int) datagroup.collapsed_height;
    }

    public int getColor() {
        return 0xff << 24 | (datagroup.red << 16) | (datagroup.green << 8) | datagroup.blue;
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


    public String toJSON() {
        return "";
    }

    public void fromJSON(String setting) {

    }
}
