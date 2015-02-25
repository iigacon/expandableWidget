package com.hb.hkm.hkmeexpandedview;

/**
 * Created by hesk on 2/26/2015.
 */
public class SpringSupport {
    private float full_height = 1, fcompressed;

    public SpringSupport(int expanded, int compressed) {
        float t = (float) compressed / (float) expanded;
        fcompressed = t;
        full_height = (float) expanded;
    }

    public int getScaledHeight(float scale) {
        return (int) (full_height * scale);
    }

    public float getFcompressed() {
        return fcompressed;
    }
}
