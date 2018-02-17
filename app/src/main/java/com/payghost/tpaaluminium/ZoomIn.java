package com.payghost.tpaaluminium;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Wiseman on 2018-02-17.
 */

public class ZoomIn extends AppCompatActivity{
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zoom_layout);
        image =(ImageView)findViewById(R.id.zoom_image);
        Bundle bundle = getIntent().getExtras();
        Picasso.with(getApplicationContext()).load(bundle.getString("image")).into(image);
    }
}
