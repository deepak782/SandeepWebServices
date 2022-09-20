package com.deepak.sandeepwebservices.mutilpleObj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.deepak.sandeepwebservices.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    Context context;
    List<MyModel> myModelList=new ArrayList<>();
    LayoutInflater layoutInflater;

    public MyAdapter(Context context, List<MyModel> myModelList) {
        this.context = context;
        this.myModelList = myModelList;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myModelList.size();
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
        View root=layoutInflater.inflate(R.layout.mycustom,null);
        TextView city=root.findViewById(R.id.city_name);
        TextView country=root.findViewById(R.id.country_name);
        TextView code=root.findViewById(R.id.country_code);

        city.setText(myModelList.get(i).getCity());
        country.setText(myModelList.get(i).getCountry());
        code.setText(myModelList.get(i).getCode());
        return root;
    }
}
