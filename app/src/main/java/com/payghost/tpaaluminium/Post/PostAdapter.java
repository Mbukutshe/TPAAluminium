package com.payghost.tpaaluminium.Post;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.payghost.tpaaluminium.MoreDetails;
import com.payghost.tpaaluminium.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class PostAdapter extends RecyclerView.Adapter<PostviewHolder>{
    public List<PostItems> list ;
    private Context context;

    public PostAdapter(Context context, List<PostItems> list) {
        this.list = list;
        this.context = context;

    }
    @Override
    public PostviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_cardview,null);
        PostviewHolder viewHolder = new PostviewHolder(v);

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final PostviewHolder holder, final int position) {

    try {

        Picasso.with(context).load(list.get(position).getImage()).resize(150, 120).centerCrop().into(holder.image);
        holder.tvtitle.setText(list.get(position).getTitle());
        holder.tvdescription.setText(list.get(position).getDescription());

        Animation upAnim = AnimationUtils.loadAnimation(context, R.anim.translate);
        upAnim.reset();
        holder.itemView.clearAnimation();
        holder.itemView.setAnimation(upAnim);
        holder.itemView.setAnimation(upAnim);

        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,MoreDetails.class);
                intent.putExtra("image",list.get(position).getImage());
                intent.putExtra("title",list.get(position).getTitle());
                intent.putExtra("description",list.get(position).getDescription());
                context.startActivity(intent);
            }
        });


    }catch (Exception e){
        Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
    }

    }
    @Override
    public int getItemCount() {
        return this.list.size();
    }
}

