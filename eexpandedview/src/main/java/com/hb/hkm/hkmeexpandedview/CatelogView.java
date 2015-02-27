package com.hb.hkm.hkmeexpandedview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.hb.hkm.hkmeexpandedview.bindingholder.SlickHolder;
import com.hb.hkm.hkmeexpandedview.databindingmodel.BasicDataBind;
import com.hb.hkm.hkmeexpandedview.databindingmodel.SlickBind;
import com.hb.hkm.hkmeexpandedview.list.BasicListingAdapter;
import com.squareup.picasso.Picasso;

import static com.hb.hkm.hkmeexpandedview.R.styleable;

/**
 * Created by hesk on 2/24/15.
 */
public class CatelogView extends LinearLayout implements View.OnClickListener, SpringListener {
    private static String TAG = ".CatelogView";
    // Create a system to run the physics loop for a set of springs.
    private static SpringSystem springSystem = SpringSystem.create();
    private LinearLayout.LayoutParams mCompressedParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 50);

    private LinearLayout.LayoutParams mExpandedParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 200);

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

    private void init(AttributeSet attrs) {

        viewWidthHalf = this.getMeasuredWidth() / 2;
        viewHeightHalf = this.getMeasuredHeight() / 2;
        int radius = 0;
        if (viewWidthHalf > viewHeightHalf)
            radius = viewHeightHalf - 10;
        else
            radius = viewWidthHalf - 10;

        int color = 0;
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
        } else {
            if (cateb != null) {
                color = cateb.getColor();
                resLayout = cateb.getLayoutResId();
            }

        }

        init(color);
    }

    private boolean mExpanded = false;
    private ImageView image_location;
    private int
            resLayout,
            red = 0, green = 0, blue = 0, viewHeightHalf = 0, viewWidthHalf = 0;
    private String src_url = "";
    private View frame;
    private LinearLayout layoutnow;
    private ListView childLayout;
    private CatelogViewBuilder cateb;
    private ArrayAdapter<BasicDataBind> listAdapter;

    private Spring spring;
    private SpringSupport springSystemsupport;

    private RelativeLayout.LayoutParams getParams() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        return params;
    }

    final Picasso theloadingimagepicasso = Picasso.with(getContext());

    private void init(int color) {
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.TOP);
        setBackgroundColor(color);


        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutnow = (LinearLayout) inflater.inflate(R.layout.base_layout, this, true);
        image_location = (ImageView) findViewById(R.id.image_src);
        frame = findViewById(R.id.base_frame);
        childLayout = (ListView) findViewById(R.id.list);
        if (cateb != null) {
            if (cateb.getHeight() > 0f) {
                mCompressedParams = new LinearLayout.LayoutParams(-1, (int) cateb.getHeight());
                frame.setLayoutParams(mCompressedParams);
                frame.setBackgroundResource(R.drawable.normal_gradient);
                if (cateb.getResId() == 0) {
                    try {
                        theloadingimagepicasso
                                .load(cateb.getBannerImageUrl())
                                .fit().centerCrop()
                                .placeholder(R.drawable.load)
                                .error(R.drawable.load)
                                .into(image_location);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    image_location.setImageDrawable(getResources().getDrawable(cateb.getResId()));
                }

                if (cateb.getResLayoutSecondLayer() != 0 || !cateb.getTitleOnSecondLayer().equalsIgnoreCase("")) {
                    if (cateb.getResLayoutSecondLayer() == 0) {

                    } else {

                    }
                }
                image_location.setVisibility(View.VISIBLE);
                image_location.setLayoutParams(mCompressedParams);
                image_location.setOnClickListener(this);
                setLayoutParams(mCompressedParams);
            }

            if (cateb.getPrimaryList().size() > 0) {
                LinearLayout child = (LinearLayout) inflater.inflate(resLayout, null, false);
                //  TextView tvc = (TextView) child.findViewById(R.id.label_field);
                int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.AT_MOST);
                int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                child.measure(widthMeasureSpec, heightMeasureSpec);

                listAdapter = new BasicListingAdapter(getContext(), resLayout, cateb.getPrimaryList());
                childLayout.setAdapter(listAdapter);
                final int height = child.getMeasuredHeight();
                Log.d(TAG, "layout height: " + height);
                float tp = height + cateb.getHeight();
                mExpandedParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, (int) tp);

                springSystemsupport = new SpringSupport(height, (int) cateb.getHeight());

                //
                // childLayout.setLayoutParams();
            }
            if (cateb.hasSpring()) {
                // Add a spring to the system.
                spring = springSystem.createSpring();
                // Add a listener to observe the motion of the spring.
                spring.addListener(this);
                // Set the spring in motion; moving from 0 to 1
                spring.setEndValue(0);
            }
        } else {
            setLayoutParams(mCompressedParams);
        }
     /*   if (getMeasuredHeight() == 0)
            mExpandedParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 400);
        else mExpandedParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, getMeasuredHeight());*/
    }

    public void setImageVisible(boolean visible) {
        image_location.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private void changeLayout(LayoutParams l) {
        setLayoutParams(l);
        requestLayout();
    }

    public void triggerClose() {
        if (mExpanded) {
            if (cateb.hasSpring()) {
                // if (spring.isAtRest())
                spring.setEndValue(0);
                Log.d(toggleWatcher.TAG, "Trigger Spring Operation: " + springSystemsupport.getFcompressed() + " at rest?" + spring.isAtRest() + " id:" + spring.getId());
            } else {
                mExpanded = false;
                changeLayout(mCompressedParams);
            }
        }
        //        Log.d(toggleWatcher.TAG, "child item current state: " + mExpanded);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw the View
    }

    @Override
    public void onClick(View v) {
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
            changeLayout(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    (int) mappedValue));
        }

        //   myView.setScaleX(scale);
        //    myView.setScaleY(scale);
    }
}
