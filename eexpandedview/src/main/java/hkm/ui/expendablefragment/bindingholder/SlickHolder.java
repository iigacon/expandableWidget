package hkm.ui.expendablefragment.bindingholder;

import android.view.View;
import android.widget.ImageView;

import hkm.ui.expendablefragment.R;

/**
 * Created by hesk on 2/27/2015.
 */
public class SlickHolder extends basicViewHolder {
    public final ImageView imageview;

    public SlickHolder(View view) {
        super(view);
        imageview = (ImageView) view.findViewById(R.id.iv);
    }
}
