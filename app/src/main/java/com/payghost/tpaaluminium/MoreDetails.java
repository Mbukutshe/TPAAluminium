package com.payghost.tpaaluminium;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MoreDetails extends AppCompatActivity {
    String img,tit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);
        Bundle bundle = getIntent().getExtras();

        String des = bundle.getString("description");
        img = bundle.getString("image");
        tit = bundle.getString("title");
        getSupportActionBar().setTitle(tit);

        final TextView title = (TextView)findViewById(R.id.more_title);
        final TextView description = (TextView)findViewById(R.id.more_description);
        final ImageView image = (ImageView)findViewById(R.id.more_image);
        final TextView back = (TextView)findViewById(R.id.more_back);

        title.setText("");
        description.setText(des);

        Picasso.with(getApplicationContext()).load(img).into(image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ZoomIn.class);
                intent.putExtra("image",img);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}
