package com.hb.hkm.hkmeexpandedview.databindingmodel;

/**
 * Created by hesk on 2/25/15.
 */
public class BasicDataBind extends bindBase {
    protected String
            mlabel,
            murl;

    public BasicDataBind() {
    }

    public BasicDataBind(String label, String url) {
        mlabel = label;
        murl = url;
    }

    @Override
    public String label() {
        return mlabel;
    }

    @Override
    public String toString() {
        return mlabel;
    }

    public String url() {
        return murl;
    }

}
