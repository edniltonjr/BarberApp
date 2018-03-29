package com.example.juniorsantos.barberapp.model;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juniorsantos.barberapp.R;

public class DetaillActivity extends AppCompatActivity {

    TextView nameTxt, propTxt, descTxt;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaill);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        nameTxt= (TextView) findViewById(R.id.nameTxtDetail);
        propTxt= (TextView) findViewById(R.id.propellantTxtDetail);
        descTxt= (TextView) findViewById(R.id.descDetailTxt);
        img= (ImageView) findViewById(R.id.spacecraftImageDetail);

        Intent i= this.getIntent();
        String name = i.getStringExtra("NAME_KEY");
        String prop = i.getStringExtra("PROPPELLANT_KEY");
        String desc = i.getStringExtra("DESCRIPTION_KEY");
        String imageurl = i.getStringExtra("IMAGEURL_KEY");

        nameTxt.setText(name);
        propTxt.setText(prop);
        descTxt.setText(desc);
        PicassoClient.downloadImage(this, imageurl, img);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
