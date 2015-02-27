package com.hb.hkm.eexpandedviewsample;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.hb.hkm.hkmeexpandedview.CatelogView;
import com.hb.hkm.hkmeexpandedview.CatelogViewBuilder;
import com.hb.hkm.hkmeexpandedview.databindingmodel.BasicDataBind;
import com.hb.hkm.hkmeexpandedview.toggleWatcher;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    protected void init_expandtabs() throws Resources.NotFoundException {

        final toggleWatcher tw = new toggleWatcher();
        final LinearLayout container = (LinearLayout) findViewById(R.id.expanded_menu_list);
        // Start with two views
        for (int i = 0; i < 3; i++) {
            ArrayList<BasicDataBind> h = new ArrayList<BasicDataBind>();
            h.add(new BasicDataBind("sfsf6", "Sfs4e"));
            h.add(new BasicDataBind("5s3fsf5", "2Sfs"));
            h.add(new BasicDataBind("grege", "2Sfs"));
            h.add(new BasicDataBind("42gw", "2Sfs"));
            h.add(new BasicDataBind("234regerg", "2Sfs"));
            h.add(new BasicDataBind("7s2ef3", "4Sfs9"));
            h.add(new BasicDataBind("dfg432243", "4Sfs9"));
            h.add(new BasicDataBind("sfsf6", "Sfs4e"));
            h.add(new BasicDataBind("5s3fsf5", "2Sfs"));
            h.add(new BasicDataBind("grege", "2Sfs"));
            h.add(new BasicDataBind("sfsf6", "Sfs4e"));
            h.add(new BasicDataBind("5s3fsf5", "2Sfs"));
            h.add(new BasicDataBind("grege", "2Sfs"));
            h.add(new BasicDataBind("sfsf6", "Sfs4e"));
            h.add(new BasicDataBind("5s3fsf5", "2Sfs"));
            h.add(new BasicDataBind("grege", "2Sfs"));

            CatelogViewBuilder cb = new CatelogViewBuilder();
            cb.enableFBSpring(true)
                    .preset_src(R.drawable.bike, getResources().getDimension(R.dimen.home_collapsed))
                    .rndColor()
                    .setDataList(h)
                    .setWatcher(tw);
            CatelogView c = new CatelogView(this, cb);
            container.addView(c);

        }
        tw.addContainer(container);
        // Note that this assumes a LayoutTransition is set on the container, which is the
        // case here because the container has the attribute "animateLayoutChanges" set to true
        // in the layout file. You can also call setLayoutTransition(new LayoutTransition()) in
        // code to set a LayoutTransition on any container.
        // LayoutTransition transition = container.getLayoutTransition();
        // New capability as of Jellybean; monitor the container for *all* layout changes
        // (not just add/remove/visibility changes) and animate these changes as well.
        //  transition.enableTransitionType(LayoutTransition.CHANGING);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_expandtabs();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
