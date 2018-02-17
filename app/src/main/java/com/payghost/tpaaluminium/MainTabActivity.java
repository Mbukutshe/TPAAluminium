package com.payghost.tpaaluminium;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TabHost;

public class MainTabActivity extends TabActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("TPA Dashboard");

        TabHost tabHost = getTabHost();

        TabHost.TabSpec Gallery = tabHost.newTabSpec("Gallery");
        Gallery.setIndicator("Gallery");
        Intent GroupIntent = new Intent(this,ImageUpload.class);
        Gallery.setContent(GroupIntent);

        TabHost.TabSpec Event = tabHost.newTabSpec("Message");
        Event.setIndicator("Message");
        Intent ShowIntent = new Intent(this,Message.class);
        Event.setContent(ShowIntent);

        TabHost.TabSpec specials = tabHost.newTabSpec("Specials");
        specials.setIndicator("Specials");
        Intent AccountIntent = new Intent(this,AddPost.class);
        specials.setContent(AccountIntent);

        tabHost.addTab(specials);
        tabHost.addTab(Gallery);
        tabHost.addTab(Event);
    }
}
