package com.example.juniorsantos.barberapp.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.juniorsantos.barberapp.R;

public class Main2Activity extends AppCompatActivity implements LoginFragment.onLoginFormActivityListener {

    public static PrefConfig prefConfig;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        prefConfig = new PrefConfig(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


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

    @Override
    public void performUserLogin(String name) {

        prefConfig.writeName(name);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, new BlankFragment()).commit();

    }
}
