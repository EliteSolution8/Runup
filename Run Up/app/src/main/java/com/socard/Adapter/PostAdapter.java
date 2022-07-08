package com.socard.Adapter;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.socard.Main.MainActivity;
import com.socard.Main.SplashActivity;
import com.socard.Main.SuccessActivity;
import com.socard.Model.PostEntity;
import com.socard.R;
import com.socard.Widget.CustomTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    public ArrayList<PostEntity> ticketModels;

    public Context _context;

    public PostAdapter(Context context ,ArrayList<PostEntity> ticketModels){

        this.ticketModels = new ArrayList<>();
        this.ticketModels.clear();
        this.ticketModels.addAll(ticketModels);
        _context = context;
    }

    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent,false);

        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final PostAdapter.ViewHolder holder, final int position) {

        final View view = holder.mView;

        final PostEntity PostEntity = ticketModels.get(position);


           holder.txtTitle.setText(PostEntity.getTitle());



        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(view.getContext(), SuccessActivity.class);
                intent.putExtra("post", PostEntity);
                view.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {

        return ticketModels.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder   {

        public View mView;
        public ImageView imgLogo;
        public CustomTextView txtTitle;


        public ViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            txtTitle = (CustomTextView) itemView.findViewById(R.id.txtTitle);

        }
    }
}
