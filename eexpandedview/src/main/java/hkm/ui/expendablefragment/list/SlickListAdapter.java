package hkm.ui.expendablefragment.list;

import android.content.Context;

import com.hb.hkm.hkmeexpandedview.R;
import hkm.ui.expendablefragment.bindingholder.SlickHolder;
import hkm.ui.expendablefragment.bindingholder.basicViewHolder;
import hkm.ui.expendablefragment.databindingmodel.BasicDataBind;
import hkm.ui.expendablefragment.databindingmodel.SlickBind;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hesk on 2/27/2015.
 */
public class SlickListAdapter extends BasicListingAdapter {
    final Picasso theloadingimagepicasso = Picasso.with(getContext());

    public SlickListAdapter(Context context, int itemLayouti, ArrayList<BasicDataBind> listsrc) {
        super(context, R.layout.l_icon_text_c, listsrc);
    }

    @Override
    protected void trigger_press(BasicDataBind bb) {
        final SlickBind sb = (SlickBind) bb;

    }

    @Override
    protected void feedData(final basicViewHolder vh, final int position) {
        super.feedData(vh, position);
        try {
            final SlickBind p = (SlickBind) getItem(position);
            final SlickHolder sh = (SlickHolder) vh;
            theloadingimagepicasso
                    .load(p.get_url_img())
                    .fit().centerCrop()
                    .placeholder(R.drawable.load)
                    .error(R.drawable.load)
                    .into(sh.imageview);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
