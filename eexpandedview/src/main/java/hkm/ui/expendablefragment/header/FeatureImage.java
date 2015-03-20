package hkm.ui.expendablefragment.header;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hb.hkm.hkmeexpandedview.R;
import com.squareup.picasso.Picasso;

/**
 * @teach you how to write parcel http://blog.callumtaylor.net/serialisationjava-android
 * Created by hesk on 3/18/2015.
 */
@SuppressLint("ValidFragment")
public class FeatureImage extends Fragment {
    public static String DESCRIBABLE_KEY = "CatelogViewBuilder";
    public static String URL = "FeatureImage.URL";
    public static String CAPTION = "FeatureImage.CAPTION";
    private TextView introtext;
    private FragmentInterface controlSPAN;

    public FeatureImage() {

    }

    public FeatureImage setFragmentListener(FragmentInterface listen) {
        controlSPAN = listen;
        return this;
    }

    public static FeatureImage newInstance(String image_url, String caption, FragmentInterface g) {
        FeatureImage fragment = new FeatureImage();
        Bundle bundle = new Bundle();
        // bundle.putSerializable(DESCRIBABLE_KEY, describable);
        bundle.putString(URL, image_url);
        bundle.putString(CAPTION, caption);
        fragment.setFragmentListener(g).setArguments(bundle);
        return fragment;
    }

    //http://www.developerphil.com/parcelable-vs-serializable/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.header, null, false);
    }

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

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controlSPAN != null) controlSPAN.toggleStack();
            }
        });

    }

}
