package com.singlemultiplezoomableimageview;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.library.multipletouchzoomableimageview.Layer;
import com.library.multipletouchzoomableimageview.ViewPort;

/**
 * Created by OneSnaps on 9/21/2016.
 */
public class MultipleTouchImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);

        final ViewPort viewPort = new ViewPort(getApplicationContext());
        layout.addView(viewPort);

        viewPort.layers.add(new Layer(MultipleTouchImageViewActivity.this, viewPort, BitmapFactory.decodeResource(getResources(), R.drawable.es_02)));
        viewPort.invalidate();

        setTitle("Multiple Touch Zoomable ImageView");

        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewPort.layers.add(new Layer(MultipleTouchImageViewActivity.this, viewPort, BitmapFactory.decodeResource(getResources(), R.drawable.es_05)));
                viewPort.invalidate();

            }
        });

        findViewById(R.id.remove_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPort.layers.size() > 0) {
                    viewPort.layers.remove(0);
                    viewPort.invalidate();
                }
            }
        });
    }
}
