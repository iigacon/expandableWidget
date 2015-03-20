package com.hb.hkm.eexpandedviewsample;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.hb.hkm.eexpandedviewsample.fragmentdemo.ly;
import hkm.ui.expendablefragment.CatelogViewBuilder;
import hkm.ui.expendablefragment.databindingmodel.BasicDataBind;
import hkm.ui.expendablefragment.toggleWatcher;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    final String[] sample_images = new String[]{
            "http://1.bp.blogspot.com/-OOr0aY6bIVw/VOC-CtmeaMI/AAAAAAAAk-o/Szc9_sOtwUE/s1600/Shot%2BOf%2BThe%2BWeek.jpg",
            "http://2.bp.blogspot.com/-Bmwh2nKu3Dg/VJxhEpmcL_I/AAAAAAAAkBQ/W4RNm2tEi4U/s1600/Jump%2BFesta%2B2015%2Bmain.png",
            "http://4.bp.blogspot.com/-FZE142qPpEw/URKaFmLmMyI/AAAAAAAAWY8/KgO4mq8b83U/s1600/Armoed+Franky+SA-MAX01+main.png",
            "http://2.bp.blogspot.com/-WCedbQmPqmU/Ud6Bep7c2EI/AAAAAAAAZV0/BPs6HV87ti8/s1600/Nico+Robin+EZ2+ZOOM00.png",
            "http://2.bp.blogspot.com/-6ECE0JJVi7k/Ur79GaghC7I/AAAAAAAAdZk/cGlOLqZAbm8/s1600/ZOOM+Kalifa+L15+main.png",
            "http://4.bp.blogspot.com/-ACcwF_O2sxA/VNkeKkLxadI/AAAAAAAAkz0/uMEM_sjkvO4/s1600/EXPO02%2BNeo%2BDX%2BMr.2%2BBon%2BKure%2Bmain.png"
    };
    final String[] image_title = new String[]{
            "Shot",
            "Jump Festa 2015",
            "Armoed Franky",
            "Nico Robin Edition-Z",
            "Kalifa - P.O.P Limited Edition",
            "Mr.2 Bon Kure Dome Tour Limitation Ver. - P.O.P Neo EX"
    };
    final String[] ItemsTitle = new String[]{
            "Robin Nice!",
            "K-Kill!",
            "Luffy!",
            "O - Z!",
            "Zoro",
            "Sixth",
            "Best Ass"
    };

    protected void example_2() throws Resources.NotFoundException, Exception {
        // final toggleWatcher tw = new toggleWatcher();
        final LinearLayout container = (LinearLayout) findViewById(R.id.expanded_menu_list);
        // Start with two views
        int len = ItemsTitle.length;
        //int len = 4;
        for (int i = 0; i < len; i++) {
            ArrayList<BasicDataBind> bb = new ArrayList<>();
            for (int h = 0; h < len; h++) {
                bb.add(new BasicDataBind(ItemsTitle[h], "zxczx zxczxc"));
            }
            CatelogViewBuilder cb = new CatelogViewBuilder(this);
            if (i == 5) {
                cb
                        .setHeaderFragment(ly.class)
                        .setDataList(bb)
                        .setFragmentHeight(R.dimen.home_collapsed);
            } else
                cb
                        .preset_src(sample_images[i], getResources().getDimension(R.dimen.home_collapsed))
                        .rndColor()
                        .setImageTitle(image_title[i])
                        .setDataList(bb);
            // cb.setWatcher(tw);

            container.addView(cb.create());
        }
        // tw.addContainer(container);
    }

    protected void example_1() throws Resources.NotFoundException {

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

            CatelogViewBuilder cb = new CatelogViewBuilder(this);
            cb.enableFBSpring(true)
                    .preset_src(R.drawable.bike, getResources().getDimension(R.dimen.home_collapsed))
                    .rndColor()
                    .setDataList(h)
                    .setWatcher(tw);
            container.addView(cb.create());

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
        try {
            example_2();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if (id == R.id.f1) {
            return true;
        }
        if (id == R.id.f2) {
            return true;
        }
        if (id == R.id.g) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/jjhesk/expandableWidget"));
            startActivity(browserIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
