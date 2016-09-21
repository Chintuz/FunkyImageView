package com.singlemultiplezoomableimageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.library.singletouchzoomableimageview.StickerImageView;

/**
 * Created by OneSnaps on 9/21/2016.
 */
public class SingleTouchImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);

        findViewById(R.id.remove_btn).setVisibility(View.GONE);

        setTitle("Single Touch Zoomable ImageView");

        StickerImageView iv1_sticker = new StickerImageView(SingleTouchImageViewActivity.this);
        iv1_sticker.setImageDrawable(getResources().getDrawable(R.drawable.es_03));
        layout.addView(iv1_sticker);

        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StickerImageView iv1_sticker = new StickerImageView(SingleTouchImageViewActivity.this);
                iv1_sticker.setImageDrawable(getResources().getDrawable(R.drawable.es_04));
                layout.addView(iv1_sticker);
            }
        });
    }
}
