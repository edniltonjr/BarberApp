package com.example.juniorsantos.barberapp.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Junior Santos on 21/08/2017.
 */

public class BaseActivity extends AppCompatActivity {

    protected Singleton getSingleton(){
        return (Singleton) getApplication();
    }

    /**
     * Verifica conexão com internet
     * @return true para sim, false para não
     */
    public boolean isOnline(){
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
