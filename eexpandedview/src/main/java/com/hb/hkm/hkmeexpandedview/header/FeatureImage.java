package com.hb.hkm.hkmeexpandedview.header;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hb.hkm.hkmeexpandedview.CatelogViewBuilder;
import com.hb.hkm.hkmeexpandedview.R;
import com.squareup.picasso.Picasso;

/**
 * @teach you how to write parcel http://blog.callumtaylor.net/serialisationjava-android
 * Created by hesk on 3/18/2015.
 */
@SuppressLint("ValidFragment")
public class FeatureImage extends Fragment implements OnImageClick {
    public static String DESCRIBABLE_KEY = "CatelogViewBuilder";
    public static String URL = "FeatureImage.URL";
    public static String CAPTION = "FeatureImage.CAPTION";

    public FeatureImage() {

    }

    public static FeatureImage newInstance(String image_url, String caption) {
        FeatureImage fragment = new FeatureImage();
        Bundle bundle = new Bundle();
        // bundle.putSerializable(DESCRIBABLE_KEY, describable);
        bundle.putString(URL, image_url);
        bundle.putString(CAPTION, caption);
        fragment.setArguments(bundle);
        return fragment;
    }

    //http://www.developerphil.com/parcelable-vs-serializable/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.header, container, false);
    }
    private TextView introtext;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final Picasso theloadingimagepicasso = Picasso.with(getActivity());
        final TextView introtext = (TextView) view.findViewById(R.id.secondlayertext);
        final ImageView image_location = (ImageView) view.findViewById(R.id.image_src);
        final Bundle b = getArguments();
        theloadingimagepicasso
                .load(b.getString(URL))
                .fit().centerCrop()
                .placeholder(R.drawable.load)
                .error(R.drawable.load)
                .into(image_location);
        introtext.setText(b.getString(CAPTION));

        //  image_location.setVisibility(View.VISIBLE);
        //  image_location.setLayoutParams(mCompressedParams);
        //  image_location.setOnClickListener(this);
    }

    @Override
    public void ImageOnClick() {

    }
}
