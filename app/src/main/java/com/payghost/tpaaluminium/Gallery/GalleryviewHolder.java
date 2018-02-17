package com.payghost.tpaaluminium.Gallery;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.payghost.tpaaluminium.R;


public class GalleryviewHolder extends RecyclerView.ViewHolder{
    ImageView image;

    public  GalleryviewHolder(View v){
        super(v);
        image =(ImageView) v.findViewById(R.id.gallery_image);
    }
}
