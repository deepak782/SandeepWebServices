package com.deepak.retrofitapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MutliAdapter extends BaseAdapter {
    Context context;
    List<MutipleObjModel> mutipleObjModelList=new ArrayList<>();
    LayoutInflater layoutInflater;

    public MutliAdapter(Context context, List<MutipleObjModel> mutipleObjModelList) {
        this.context = context;
        this.mutipleObjModelList = mutipleObjModelList;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mutipleObjModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View root=layoutInflater.inflate(R.layout.custom_list,null);
        ImageView imageView=root.findViewById(R.id.image);
        TextView title=root.findViewById(R.id.Title);
        TextView albumId=root.findViewById(R.id.AlbumID);

        title.setText(""+mutipleObjModelList.get(i).getS1());
        albumId.setText(""+mutipleObjModelList.get(i).getId());

        Glide.with(context)
                .load(mutipleObjModelList.get(i).getS2())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);

        return root;
    }
}
