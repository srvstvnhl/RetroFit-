package com.example.nihal.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nihal.retrofit.Interface.onItemClickListener;
import com.example.nihal.retrofit.Pojo.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    onItemClickListener onItemClickListener;

    ArrayList<Post> arrayList;
    Context context;

    public RecyclerViewAdapter(ArrayList<Post> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(context).inflate(R.layout.layout_row_item,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
        Post data = arrayList.get(i);

        viewHolder.tvTitle.setText(data.getTitle());
        viewHolder.tvDate.setText(data.getDate());
        viewHolder.tvPlatform.setText(data.getPlatform());

        Picasso.with(context).load(data.getImage()).into(viewHolder.img);
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener !=null){
                    onItemClickListener.onItemClick(i,view);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle,tvPlatform,tvDate;
        ImageView img;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPlatform = itemView.findViewById(R.id.tvplatform);
            tvDate = itemView.findViewById(R.id.tvdate);

            img = itemView.findViewById(R.id.img);
            linearLayout = itemView.findViewById(R.id.linear_layout);
        }
    }

}
