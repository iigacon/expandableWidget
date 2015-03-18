package com.hb.hkm.eexpandedviewsample.fragmentdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hb.hkm.eexpandedviewsample.R;

/**
 * Created by hesk on 3/2/2015.
 */
public class ly extends Fragment {
    private TextView introtext;

    public static ly newInstance(String text) {
        ly f = new ly();
        Bundle b = new Bundle();
        b.putString("text", text);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_for, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        introtext = ((TextView) view.findViewById(R.id.tvFragText));
        // introtext.setText(getArguments().getString("text"));
        // introtext.setText("eonfieo ioenf ");
    }

}
