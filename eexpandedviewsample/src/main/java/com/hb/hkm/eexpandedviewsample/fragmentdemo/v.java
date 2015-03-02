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
    */
}
