package com.payghost.tpaaluminium;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.payghost.tpaaluminium.Post.PostAdapter;
import com.payghost.tpaaluminium.Post.PostItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String JSON_STRING;
    String title;
    String image;
    String description;

    LinearLayoutManager linearlayout;
    RecyclerView recyclerView;
    PostAdapter postviewAdapter;


    RelativeLayout layout;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_dashboard:
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    return true;

                case R.id.navigation_home:
                    startActivity(new Intent(MainActivity.this,GalleryImages.class));
                    return true;

                case R.id.navigation_notifications:
                    startActivity(new Intent(MainActivity.this,RetrivesMessages.class));
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView)findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        recyclerView = (RecyclerView)findViewById(R.id.listMessage);
        linearlayout = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearlayout);

        getJSON();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
        startActivity(new Intent(getApplicationContext(),GalleryImages.class));
        }else if (id == R.id.nav_website) {
            startActivity(new Intent(getApplicationContext(),Website.class));
        }
        else if (id == R.id.nav_login) {
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        } else if (id == R.id.nav_about) {
            android.app.AlertDialog dialog;
            android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(MainActivity.this);
            View mView = getLayoutInflater().inflate(R.layout.aboutus_dialog, null);
            mBuilder.setView(mView);

            dialog = mBuilder.create();
            dialog.show();

        } else if (id == R.id.nav_share) {

           Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,"Check out \"TPA App\"https://play.google.com/store/app/details?id=com.payghost.tpaaluminium");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        } else if (id == R.id.nav_contsct_us) {
            startActivity(new Intent(getApplicationContext(),ContactUs.class));
        } else if (id == R.id.nav_exit){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setMessage(Html.fromHtml("<font color='#627984'>Are you sure you want to exit?</font>"))
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            System.exit(0);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showMessages(){

        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        List<PostItems> arrList = new ArrayList<PostItems>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);

                title = jo.getString(Config.TAG_POST_TITLE);
                image = jo.getString(Config.TAG_IMAGE);
                description = jo.getString(Config.TAG_POST_DESRCRIPTION);


                HashMap<String,String> Tenants = new HashMap<>();

                Tenants.put(Config.TAG_POST_TITLE,title);
                Tenants.put(Config.TAG_POST_DESRCRIPTION,description);
                Tenants.put(Config.TAG_IMAGE,image);

                list.add(Tenants);

                arrList.add(new PostItems(image,title,description));
            }
            postviewAdapter = new PostAdapter(getApplicationContext(),arrList);
            recyclerView.setAdapter(postviewAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                MainActivity.this, list, R.layout.list_row_messages,
                new String[]{Config.TAG_MESSAGE_DISPLAY,Config.TAG_MESSAGE_TIME},
                new int[]{R.id.displayMessage,R.id.messageTime});
    }


    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Fetching Post"," Please Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showMessages();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_ALL_POST);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
}
