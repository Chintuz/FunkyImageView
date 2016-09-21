package com.library.multipletouchzoomableimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class Layer {
    Matrix matrix = new Matrix();
    Matrix inverse = new Matrix();
    RectF bounds;
    View parent;
    Bitmap bitmap;
    MoveGestureDetector mgd;
    ScaleGestureDetector sgd;
    RotateGestureDetector rgd;

    public Layer(Context ctx, View p, Bitmap b) {
        parent = p;
        bitmap = b;
        bounds = new RectF(0, 0, b.getWidth(), b.getHeight());
        mgd = new MoveGestureDetector(ctx, mgl);
        sgd = new ScaleGestureDetector(ctx, sgl);
        rgd = new RotateGestureDetector(ctx, rgl);
        matrix.postTranslate(50 + (float) Math.random() * 50, 50 + (float) Math.random() * 50);
    }

    public boolean contains(MotionEvent event) {
        matrix.invert(inverse);
        float[] pts = {event.getX(), event.getY()};
        inverse.mapPoints(pts);
        if (!bounds.contains(pts[0], pts[1])) {
            return false;
        }
        return Color.alpha(bitmap.getPixel((int) pts[0], (int) pts[1])) != 0;
    }

    public boolean onTouchEvent(MotionEvent event) {
        mgd.onTouchEvent(event);
        sgd.onTouchEvent(event);
        rgd.onTouchEvent(event);
        return true;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, matrix, null);
    }

    MoveGestureDetector.SimpleOnMoveGestureListener mgl = new MoveGestureDetector.SimpleOnMoveGestureListener() {
        @Override
        public boolean onMove(MoveGestureDetector detector) {
            PointF delta = detector.getFocusDelta();
            matrix.postTranslate(delta.x, delta.y);
            parent.invalidate();
            return true;
        }
    };

    ScaleGestureDetector.SimpleOnScaleGestureListener sgl = new ScaleGestureDetector.SimpleOnScaleGestureListener() {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scale = detector.getScaleFactor();
            matrix.postScale(scale, scale, detector.getFocusX(), detector.getFocusY());
            parent.invalidate();
            return true;
        }
    };

    RotateGestureDetector.SimpleOnRotateGestureListener rgl = new RotateGestureDetector.SimpleOnRotateGestureListener() {
        @Override
        public boolean onRotate(RotateGestureDetector detector) {
            matrix.postRotate(-detector.getRotationDegreesDelta(), detector.getFocusX(), detector.getFocusY());
            parent.invalidate();
            return true;
        }

        ;
    };
}