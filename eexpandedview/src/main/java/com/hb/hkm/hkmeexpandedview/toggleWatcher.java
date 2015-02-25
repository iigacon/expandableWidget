package com.hb.hkm.hkmeexpandedview;

import java.util.ArrayList;

/**
 * Created by hesk on 2/26/2015.
 */
public class toggleWatcher {
    private final ArrayList<CatelogView> list = new ArrayList<CatelogView>();

    public toggleWatcher() {

    }

    public void addItem(CatelogView v) {
        list.add(v);
    }

    public void onSelect(CatelogView v) {
        for (int i = 0; i < list.size(); i++) {
            CatelogView sample = list.get(i);
            if (!sample.equals(v)) {
                sample.triggerClose();
            }
        }
    }
}
