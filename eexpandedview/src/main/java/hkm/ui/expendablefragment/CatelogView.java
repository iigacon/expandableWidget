package hkm.ui.expendablefragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import hkm.ui.expendablefragment.R;

import hkm.ui.expendablefragment.databindingmodel.BasicDataBind;
import hkm.ui.expendablefragment.header.FeatureImage;
import hkm.ui.expendablefragment.header.FragmentInterface;
import hkm.ui.expendablefragment.list.BasicListingAdapter;

import static hkm.ui.expendablefragment.R.styleable;

/**
 * Created by hesk on 2/24/15.
 */
public class CatelogView<T extends Fragment & FragmentClickable> extends LinearLayout implements View.OnClickListener, SpringListener, FragmentInterface {
    private static String TAG = ".CatelogView";
    // Create a system to run the physics loop for a set of springs.
    private static SpringSystem springSystem = SpringSystem.create();
    private LinearLayout.LayoutParams mCompressedParams;
    // private RelativeLayout.LayoutParams mCompressedParams;
    private LinearLayout.LayoutParams mExpandedParams;
    // private RelativeLayout.LayoutParams mExpandedParams;
    private static int ViewId = 0;

    private boolean mExpanded = false;
    private int
            resLayout = 0,
            color = 0,
            red = 0, green = 0, blue = 0, viewHeightHalf = 0, viewWidthHalf = 0;
    private String src_url = "";
    //  private RelativeLayout frame;

    private ListView childLayout;
    private TextView text_view;
    private CatelogViewBuilder cateb;
    private ArrayAdapter<BasicDataBind> listAdapter;
    private LinearLayout child;
    private FrameLayout mframeLayout;
    private Spring spring;
    private SpringSupport springSystemsupport;
    private int headerFrameId;

    public CatelogView(Context context) {
        this(context, null, 0);

    }

    public CatelogView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CatelogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CatelogView(Context context, CatelogViewBuilder cb) {
        super(context);
        cateb = cb;
        init(null);
    }

    /**
     * only called from using xml
     *
     * @param attrs
     */
    private void init(AttributeSet attrs) {

        viewWidthHalf = this.getMeasuredWidth() / 2;
        viewHeightHalf = this.getMeasuredHeight() / 2;
        int radius = 0;
        if (viewWidthHalf > viewHeightHalf)
            radius = viewHeightHalf - 10;
        else
            radius = viewWidthHalf - 10;

        int mred = (int) (Math.random() * 128 + 127);
        int mgreen = (int) (Math.random() * 128 + 127);
        int mblue = (int) (Math.random() * 128 + 127);
        // init from attrs
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, styleable.CatelogView);
            try {
                // setCollapseOffset(a.getDimensionPixelSize(R.styleable.CatelogView_red, collapseOffset));
                red = a.getInteger(styleable.CatelogView_green, mred);
                green = a.getInteger(styleable.CatelogView_blue, mgreen);
                blue = a.getInteger(styleable.CatelogView_red, mblue);
                src_url = a.getString(styleable.CatelogView_src);
                resLayout = a.getResourceId(styleable.CatelogView_childlayout, 0);
                // initOpen(a.getBoolean(R.styleable.CatelogView_dtlOpen, true));
            } finally {
                a.recycle();
                color = 0xff << 24 | (red << 16) | (green << 8) | blue;
            }
        }
        init();
    }

    private RelativeLayout.LayoutParams getParamsR(int h) {
        // RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, h);
        // params.addRule(RelativeLayout.CENTER_IN_PARENT);
        return new RelativeLayout.LayoutParams(-1, h);
    }

    private LinearLayout.LayoutParams getParamsL(int h) {
        return new LinearLayout.LayoutParams(-1, h);
    }

    private int getCustomInflatLayoutXMLid() {
        return resLayout == 0 ? cateb.getChildItemLayoutResId() : resLayout;
    }

    private ViewGroup layoutnow;
    private View shadow;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void inflate() {
        headerFrameId = getRootView().generateViewId();
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutnow = (ViewGroup) inflater.inflate(R.layout.base_layout, this, true);
        child = (LinearLayout) inflater.inflate(getCustomInflatLayoutXMLid(), null, false);
        LinearLayout h = (LinearLayout) layoutnow.getChildAt(0);
        mframeLayout = (FrameLayout) h.getChildAt(0);
        mframeLayout.setId(headerFrameId);
        childLayout = (ListView) layoutnow.findViewById(android.R.id.list);
        RelativeLayout n = (RelativeLayout) h.getChildAt(1);
        shadow = (View) n.getChildAt(1);

    }

    private void setFragment(Fragment object, String tag) {
        ViewId++;
        FragmentTransaction t = cateb.getFTrans();
        Log.d(TAG, "view ID: " + mframeLayout.getId());
        t.add(headerFrameId, object, tag + ViewId);
        t.commit();
    }

    private void init_header() throws Exception {

        if (cateb.getContainerData().shadowdrawable > 0) {
            shadow.setBackgroundResource(cateb.getContainerData().shadowdrawable);
        }

        if (cateb.getContainerData().shadowheight > 0) {
            shadow.setLayoutParams(getParamsL(cateb.getContainerData().shadowheight));
        }

        if (cateb.getContainerData().shadowheight == CatelogViewBuilder.NOSHADOW) {
            shadow.setVisibility(GONE);
        }

        if (cateb.getHeight() > 0f) {
            //  mCompressedParams = getParamsL(cateb.getHeightWhole());
            mframeLayout.setLayoutParams(getParamsL(cateb.getHeightWhole()));
            if (cateb.useFragment()) {
                setFragment(getCustomerFragmentInit(), "CUSTOM");
            } else {
                setFragment(FeatureImage.newInstance(
                        cateb.getBannerImageUrl(),
                        cateb.getTitleOnSecondLayer(),
                        this), "DEFAULT");
                mframeLayout.setBackgroundResource(R.drawable.normal_gradient);
            }
        }
    }

    private Fragment getCustomerFragmentInit() throws IllegalAccessException, InstantiationException {
        T name = (T) cateb.getCustomFragment().newInstance();
        name.setFragmentOnClickListener(this);
        return name;
    }

    private void init_spring() throws Exception {
        if (cateb.hasSpring()) {
            // Add a spring to the system.
            spring = springSystem.createSpring();
            // Add a listener to observe the motion of the spring.
            spring.addListener(this);
            // Set the spring in motion; moving from 0 to 1
            spring.setEndValue(0);
            // -------------------------------------- \\
            Log.d(TAG, "ID:" + spring.getId());
            // -------------------------------------- \\
        }
    }

    /*

        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.EXACTLY);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.EXACTLY);
        child.measure(widthMeasureSpec, heightMeasureSpec);
        final int height = child.getMeasuredHeight() == 0 ? 400 : child.getMeasuredHeight();

    */

    private void init_listview() throws Exception {
        if (cateb.getPrimaryList().size() > 0) {
            final int height = 400;
            //  TextView tvc = (TextView) child.findViewById(R.id.label_field);
            listAdapter = new BasicListingAdapter(getContext(), getCustomInflatLayoutXMLid(), cateb.getPrimaryList());
            childLayout.setAdapter(listAdapter);
            mExpandedParams = getParamsL((int) (height + cateb.getHeight()));
            springSystemsupport = new SpringSupport(height, (int) cateb.getHeight());
            Log.d(TAG, "layout height: " + height);
        }
    }

    private void init() {
        // setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.TOP);
        setBackgroundColor(color == 0 ? cateb.getColor() == 0 ? 0 : cateb.getColor() : color);
        try {
            inflate();
            init_header();
            init_listview();
            init_spring();
            requestLayout();
        } catch (Exception e) {
            //  e.printStackTrace();
            Log.d(TAG, e.getMessage());
        }
    }

    private void changeLayout(LayoutParams l) {
        setLayoutParams(l);
        requestLayout();
    }

    public void triggerClose() {
        if (mExpanded) {
            if (cateb.hasSpring()) {
                //if (spring.isAtRest())
                spring.setEndValue(0);
                Log.d(toggleWatcher.TAG, "Trigger Spring Operation: " + springSystemsupport.getFcompressed() + " at rest?" + spring.isAtRest() + " id:" + spring.getId());
            } else {
                mExpanded = false;
                changeLayout(mCompressedParams);
            }
        }
        //  Log.d(toggleWatcher.TAG, "child item current state: " + mExpanded);
    }

    private void perform_expand_view_action() {
        if (cateb.hasSpring()) {
            if (spring.isAtRest()) {
                //  boolean f = !mExpanded;
                //  float t = mExpanded ? 1 : springSystemsupport.getFcompressed();
                spring.setEndValue(!mExpanded ? 1 : 0);
                Log.d(TAG, "onclick val expanded:" + !mExpanded + " id:" + spring.getId());
            }
        } else {
            mExpanded = !mExpanded;
            //invalidate();
            changeLayout(mExpanded ? mCompressedParams : mExpandedParams);
        }
        if (cateb.hasWatcher()) {
            cateb.notifyWatcher(this);
        }
    }

    @Override
    public void openStack() {

    }

    @Override
    public void closeStack() {

    }

    @Override
    public void toggleStack() {
        perform_expand_view_action();
    }

    @Override
    public void onClick(View v) {
        perform_expand_view_action();
    }

    @Override
    public void onSpringAtRest(Spring spring) {
        mExpanded = !mExpanded;

    }

    @Override
    public void onSpringActivate(Spring spring) {
        Log.d(toggleWatcher.TAG, "onSpringActivate:" + spring.getId());
    }

    @Override
    public void onSpringEndStateChange(Spring spring) {
        Log.d(toggleWatcher.TAG, "onSpringEndStateChange:" + spring.getId());
    }

    @Override
    public void onSpringUpdate(Spring S) {
        if (cateb.hasSpring()) {
            float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(S.getCurrentValue(),
                    0, 1, springSystemsupport.getFcompressed(), springSystemsupport.getFHeight()
            );
            changeLayout(getParamsL((int) mappedValue));
        }
        // myView.setScaleX(scale);
        // myView.setScaleY(scale);
    }
}
