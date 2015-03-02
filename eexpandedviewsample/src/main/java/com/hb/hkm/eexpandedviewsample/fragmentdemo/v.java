package com.hb.hkm.eexpandedviewsample.fragmentdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by hesk on 3/3/2015.
 */
public class v extends Fragment {

        @Override
        public void onStart() {
            Bundle data = getArguments();
            int containerId = data.getInt("containerID");
            String tag = data.getString("tag");
            //Class<?> c = data.get???("class");
            //Fragment f = (Fragment) c.newInstance();
            Fragment f = (Fragment) new v();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            f.setArguments(data);
            fragmentTransaction.replace(containerId, f, tag);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

  /*  data.setInt("Layout index",i);
    data.setInt("containerID",R.id.fragmentContent);
    data.setString("tag","MY");
    fragmentTab0 = (Fragment) new JumpFragment();
    fragmentTab0.setArguments(data);
    fragmentTransaction.replace(R.id.fragmentContent, fragmentTab0);
    fragmentTransaction.commit();

    http://stackoverflow.com/questions/17148285/replacing-a-fragment-with-another-fragment-of-the-same-class

    https://books.google.com.hk/books?id=zaoK0Z2STlkC&pg=PA102&lpg=PA102&dq=initiate+generic+class+extend+fragment&source=bl&ots=6Yonc8Y-EM&sig=AtCwXT_2ROwe0SQr735TyVPiXxU&hl=zh-TW&sa=X&ei=go70VLHrJcbOmwWD7oK4CA&ved=0CFoQ6AEwBw#v=onepage&q=initiate%20generic%20class%20extend%20fragment&f=false

    */
}
