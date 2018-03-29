package com.example.juniorsantos.barberapp.model;

/**
 * Created by Junior Santos on 30/11/2017.
 */

import android.content.Context;
import android.widget.ImageView;
import com.example.juniorsantos.barberapp.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Oclemy on 6/5/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class PicassoClient {
    public static void downloadImage(Context c,String imageUrl,ImageView img)
    {
        if(imageUrl.length()>0 && imageUrl.length()>0)
        {
            Picasso.with(c).load(imageUrl).placeholder(R.drawable.img).into(img);
        }else {
            Picasso.with(c).load(R.drawable.img).into(img);
        }
    }
}