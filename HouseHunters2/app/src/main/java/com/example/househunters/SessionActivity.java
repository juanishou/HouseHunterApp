package com.example.househunters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.househunters.Fragments.LoginFragment;
import com.example.househunters.Fragments.SignUpFragment;
import com.example.househunters.Generator.UtilUser;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;

public class SessionActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener, SignUpFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contenedor, new LoginFragment())
                .commit();

    }


    @Override
    protected void onStart() {
        super.onStart();


 /*       if (UtilUser.getEmail(this) != null) {
            startActivity(new Intent(SessionActivity.this, MainActivity.class));
        }*/
    }

    @Override
    public void navegarRegistro() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor, new SignUpFragment())
                .commit();

    }


    @Override
    public void navegarLogin() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor, new LoginFragment())
                .commit();
    }

}
