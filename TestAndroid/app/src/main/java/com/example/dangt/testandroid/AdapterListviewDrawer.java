package com.example.dangt.testandroid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterListviewDrawer extends ArrayAdapter<MenuDrawer> {
    Context context;
    int resID;
    List<MenuDrawer> objects;
    public AdapterListviewDrawer(@NonNull Context context, int resID, List <MenuDrawer> objects) {
        super(context, resID, objects);
        this.context = context;
        this.resID = resID;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = View.inflate(context, resID, null);
        ImageView hinhanh = (ImageView) view.findViewById(R.id.iconDrawer);
        TextView noidung = (TextView) view.findViewById(R.id.contentDrawer);

        MenuDrawer item = objects.get(position);
        hinhanh.setImageResource(item.getHinhAnh());
        noidung.setText(item.getTenMenu());
        return view;
    }
}
