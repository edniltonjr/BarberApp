package com.example.juniorsantos.barberapp.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juniorsantos.barberapp.R;
import com.example.juniorsantos.barberapp.TelaLogin;
import com.example.juniorsantos.barberapp.Usuario;

import java.util.ArrayList;

import java.util.ArrayList;

import static android.webkit.MimeTypeMap.getSingleton;
import static com.example.juniorsantos.barberapp.R.id.editUser;

/**
 * Created by Junior Santos on 01/12/2017.
 */

public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Spacecraft> spacecrafts;
    LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {

        this.c = c;
        this.spacecrafts = spacecrafts;
        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return spacecrafts.size();
    }
    @Override

    public Object getItem(int position) {
        return spacecrafts.get(position);
    }
    @Override

    public long getItemId(int position) {
        return position;
    }
    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.model,parent,false);
        }
        TextView nametxt= convertView.findViewById(R.id.nameTxt);
        ImageView img = convertView.findViewById(R.id.spacecraftImage);

        //BIND DATA
        final Spacecraft s =spacecrafts.get(position);
        nametxt.setText(s.getName());
        //IMG
        PicassoClient.downloadImage(c,s.getImageUrl(),img);

        //ITEM CLICK
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //OPEN DETAIL ACTIVITY


                openDetailActivity(s.getName(), s.getProppelant(), s.getDesc(), s.getImageUrl());


            }
        });

        return convertView;


    }

    private void openDetailActivity(String name, String prop, String desc, String imageurl)
    {
        Intent i = new Intent (c, DetaillActivity.class);


        i.putExtra("NAME_KEY", name);
        i.putExtra("PROPPELLANT_KEY", prop);
        i.putExtra("DESCRIPTION_KEY", desc);
        i.putExtra("IMAGEURL_KEY", imageurl);

        c.startActivity(i);
    }
}