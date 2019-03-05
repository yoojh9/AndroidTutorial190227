package com.example.a20_customview;

import android.graphics.PointF;

public class Box {

    private PointF current;
    private PointF origin;

    public Box(PointF origin) {
        this.origin = origin;
    }

    public PointF getCurrent() {
        return current;
    }

    public PointF getOrigin() {
        return origin;
    }

    public void setCurrent(PointF current) {
        this.current = current;
    }

    public void setOrigin(PointF origin) {
        this.origin = origin;
    }
}
