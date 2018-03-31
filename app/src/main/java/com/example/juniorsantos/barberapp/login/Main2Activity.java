package com.example.juniorsantos.barberapp.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.juniorsantos.barberapp.R;

public class Main2Activity extends AppCompatActivity {

    public static PrefConfig prefConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        prefConfig = new PrefConfig(this);
        if(findViewById(R.id.fragment_content) != null){
            if(savedInstanceState != null)

            {
                return;
            }

            if (prefConfig.readLoginStatus()){
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_content, new BlankFragment()).commit();
            }

            else{
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_content, new LoginFragment()).commit();
            }



        }
    }
}
