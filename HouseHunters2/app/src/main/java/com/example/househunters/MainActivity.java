package com.example.househunters;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.househunters.Fragments.ProfileFragment;
import com.example.househunters.Fragments.PropertiesFavFragment;
import com.example.househunters.Fragments.PropertiesFragment;
import com.example.househunters.Fragments.UserPropertiesListFragment;
import com.example.househunters.Generator.UtilUser;
import com.example.househunters.Listener.PropertyListener;

public class MainActivity extends AppCompatActivity implements PropertyListener {

    Button btnVerLogin;
    private FloatingActionButton fab;
    private Menu menu;
    private FrameLayout contenedor;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        Fragment f = null;




        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_inicio:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contenedor_main, new PropertiesFragment())
                            .commit();
                    fab.show();
                    return true;
                case R.id.navigation_favoritos:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contenedor_main, new PropertiesFavFragment())
                            .commit();
                    fab.show();
                    return true;

                case R.id.navigation_propiedades:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contenedor_main, new UserPropertiesListFragment())
                            .commit();
                    fab.show();
                    return true;
                case R.id.navigation_perfil:

                    if(UtilUser.getEmail(MainActivity.this) == null) {
                        Intent i =  new Intent(MainActivity.this, SessionActivity.class);
                        startActivity(i);
                    } else {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contenedor_main, new ProfileFragment())
                                .commit();
                        fab.hide();
                    }

                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();




        contenedor = findViewById(R.id.contenedor_main);
        fab = findViewById(R.id.fab);

        events();


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor_main, new PropertiesFragment())
                .commit();

        hideMenu(menu);

        MenuItem inicio = menu.findItem(R.id.navigation_inicio);
        inicio.setChecked(true);

    }

    private void events() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddProperty.class);
                startActivity(i);
            }
        });
    }


    @Override
    public void addFavProperty(String id) {
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();

        // Defino qué quiero hacer cuando el usuario pulse el botón
        // volver o atrás del móvil

        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2.1. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("¿Estás seguro?")
                .setTitle("¿Quieres cerrar sesión?");

        // 2.2. Añadir botones al diálogo
        builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                UtilUser.clearSharedPreferences(MainActivity.this);
                finish();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();
    }

    @Override
    public void verProperty(String property) {

    }

    public void hideMenu(Menu menu) {
        if (UtilUser.getEmail(this) == null) {
            MenuItem item = menu.findItem(R.id.navigation_favoritos);
            item.setVisible(false);
            MenuItem item2 = menu.findItem(R.id.navigation_propiedades);
            item2.setVisible(false);

            fab.hide();
        }
    }
}
