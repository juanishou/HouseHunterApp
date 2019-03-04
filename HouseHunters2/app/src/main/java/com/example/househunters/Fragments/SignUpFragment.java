package com.example.househunters.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.househunters.Generator.ServiceGenerator;
import com.example.househunters.Generator.UtilToken;
import com.example.househunters.Generator.UtilUser;
import com.example.househunters.MainActivity;
import com.example.househunters.Model.LoginResponse;
import com.example.househunters.Model.UserDto;
import com.example.househunters.R;
import com.example.househunters.Services.AuthService;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
import de.hdodenhof.circleimageview.CircleImageView;
*/

public class SignUpFragment extends Fragment {
    private static final int READ_REQUEST_CODE = 42;
    private EditText editTextEmailRegistro;
    private EditText etNombre;
    private EditText etPassword;
    private EditText etPasswordRep;
    private ImageView ivImagenPerfil;
    Uri uriSelected;
    private Button btnRegistro, btnRegistroaLogin;
    Context ctx;

    private OnFragmentInteractionListener mListener;

    public SignUpFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        editTextEmailRegistro = view.findViewById(R.id.editTextEmailRegistro);
        etNombre = view.findViewById(R.id.editTextNombreRegistro);
        etPassword = view.findViewById(R.id.editTextPasswordRegistro);
        etPasswordRep = view.findViewById(R.id.editTextPasswordRepeat);
        btnRegistro = view.findViewById(R.id.buttonRegistrar);
        btnRegistroaLogin = view.findViewById(R.id.buttonRegistroaLogin);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRegister();
            }
        });

        btnRegistroaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.navegarLogin();
            }
        });




        return view;
    }

    public void doRegister(){
        String email = editTextEmailRegistro.getText().toString();
        String password = etPasswordRep.getText().toString();
        String name = etNombre.getText().toString();

        UserDto usuarioARegistrar = new UserDto( email, password, name);

        if (validarString(email) && validarString(password)) {

            AuthService service = ServiceGenerator.createService(AuthService.class);
            Call<LoginResponse> call = service.doRegister(usuarioARegistrar);

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        Log.d("Uploaded", "Éxito");
                        Log.d("Uploaded", response.body().toString());
                        UtilUser.setUserInfo(getActivity(), response.body().getUser());
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    } else {
                        Log.e("Upload error", response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.e("Upload error", t.getMessage());
                }
            });

        } else {

            Toast.makeText(getContext(), "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }





    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                Log.i("Filechooser URI", "Uri: " + uri.toString());
                //showImage(uri);

                uriSelected = uri;
                btnRegistro.setEnabled(true);
            }
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void navegarLogin();

    }

    Boolean validarString(String texto) {
        return texto != null && texto.trim().length() > 0;
    }

}
