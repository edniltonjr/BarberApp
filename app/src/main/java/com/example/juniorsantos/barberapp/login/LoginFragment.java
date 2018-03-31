package com.example.juniorsantos.barberapp.login;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.juniorsantos.barberapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    onLoginFormActivityListener loginFormActivityListener;

    private TextView edtUsername, edtPassword;
    private Button btnLogin;



    public interface onLoginFormActivityListener
    {
        public void performUserLogin(String name);
    }


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        edtUsername = view.findViewById(R.id.edtUsername);
        edtPassword = view.findViewById(R.id.edtPassword);
        btnLogin = view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                performUserLogin();

            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        loginFormActivityListener = (onLoginFormActivityListener) activity;
    }


    private void performUserLogin(){
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

        Call<User> call = Main2Activity.apiInterface.performUserLogin(username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok")){

                    Main2Activity.prefConfig.writeLoginStatus(true);
                    loginFormActivityListener.performUserLogin(response.body().getName());
                }

                else if(response.body().getResponse().equals("failed")){

                    Main2Activity.prefConfig.displayToast("LOGIN FAILED");



                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });



        edtUsername.setText("");
        edtPassword.setText("");

    }



}
