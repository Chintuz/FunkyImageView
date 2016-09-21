package com.library.multipletouchzoomableimageview;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

public class ViewPort extends View {
    public List<Layer> layers = new LinkedList<Layer>();

    public ViewPort(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Layer l : layers) {
            l.draw(canvas);
        }
    }

    private Layer target;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            target = null;
            for (int i = layers.size() - 1; i >= 0; i--) {
                Layer l = layers.get(i);
                if (l.contains(event)) {
                    target = l;
                    layers.remove(l);
                    layers.add(l);
                    invalidate();
                    break;
                }
            }
        }
        if (target == null) {
            return false;
        }
        return target.onTouchEvent(event);
    }
}