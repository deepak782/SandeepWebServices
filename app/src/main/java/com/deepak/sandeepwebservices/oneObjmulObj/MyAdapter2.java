package com.deepak.sandeepwebservices.oneObjmulObj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.deepak.sandeepwebservices.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyHolder> {
    Context context;
    List<MyModel2> myModel2List=new ArrayList<>();

    public MyAdapter2(Context context, List<MyModel2> myModel2List) {
        this.context = context;
        this.myModel2List = myModel2List;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root= LayoutInflater.from(parent.getContext()).inflate(R.layout.mycustom2,parent,false);
        return new MyHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.ID.setText(""+myModel2List.get(position).getID()+"\n"+myModel2List.get(position).getTitle());
        holder.Title.setText(myModel2List.get(position).getCourse1()+"\n"+myModel2List.get(position).getCourse2());
        Glide.with(context)
                .load(myModel2List.get(position).getImageurl())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        return myModel2List.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView ID,Title;
        ImageView image;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ID=itemView.findViewById(R.id.txt_ID);
            Title=itemView.findViewById(R.id.txt_Title);
            image=itemView.findViewById(R.id.img_url);

        }
    }
}
