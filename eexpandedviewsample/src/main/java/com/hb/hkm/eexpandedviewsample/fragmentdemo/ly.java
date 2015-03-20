package com.hb.hkm.eexpandedviewsample.fragmentdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hb.hkm.eexpandedviewsample.R;
import hkm.ui.expendablefragment.FragmentClickable;
import hkm.ui.expendablefragment.header.FragmentInterface;

/**
 * Created by hesk on 3/2/2015.
 */
public class ly extends Fragment implements View.OnClickListener, FragmentClickable {
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
        introtext = ((TextView) view.findViewById(R.id.tv_hk));
        Button b1 = (Button) view.findViewById(R.id.sexy);
        Button b2 = (Button) view.findViewById(R.id.no_sexy);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sexy:
                nh.toggleStack();
                break;
            case R.id.no_sexy:
                nh.toggleStack();
                break;
        }
    }

    FragmentInterface nh;

    @Override
    public void setFragmentOnClickListener(FragmentInterface h) {
        nh = h;
    }
}
