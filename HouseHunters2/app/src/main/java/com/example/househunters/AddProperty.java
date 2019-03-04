package com.example.househunters;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.househunters.Generator.ServiceGenerator;
import com.example.househunters.Generator.TipoAutenticacion;
import com.example.househunters.Generator.UtilToken;
import com.example.househunters.Model.AddPropertyDto;
import com.example.househunters.Model.AddPropertyResponse;
import com.example.househunters.Model.Category;
import com.example.househunters.Model.Property;
import com.example.househunters.Model.ResponseContainer;
import com.example.househunters.Model.ResponseContainerDetail;
import com.example.househunters.Services.CategoryService;
import com.example.househunters.Services.PropertyService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProperty extends AppCompatActivity {


    private EditText et_title, et_description, et_price, et_cp, et_address, et_rooms, et_city, et_province;
    private Button btn_add;
    //private Spinner spinner_category;
    List<Category> categories;
    private String completeAddress;
    private AddPropertyDto addPropertyDto;
    private boolean edit;
    private Property property;
    private String id;
    private double latitud, longitud;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

        findviews();

        events();

        Bundle b = new Bundle();
        b = getIntent().getExtras();
        if (b != null) {
            edit = true;
            id = b.getString("idpropiedad");
            Log.i("IDPROPIEDAD: ", id);
            setearInformacionPropiedad(id);
            btn_add.setText("Editar Propiedad");
        }

        getCategories();


    }

    private void setearInformacionPropiedad(String idpropiedad) {
        PropertyService service = ServiceGenerator.createService(PropertyService.class);

        Call<ResponseContainerDetail<Property>> call = service.getOneProperty(idpropiedad);

        call.enqueue(new Callback<ResponseContainerDetail<Property>>() {
            @Override
            public void onResponse(Call<ResponseContainerDetail<Property>> call, Response<ResponseContainerDetail<Property>> response) {
                if (response.code() != 200) {
                    Toast.makeText(AddProperty.this, "Error al ver Inmueble", Toast.LENGTH_SHORT).show();
                } else {
                    property = response.body().getRows();
                    et_title.setText(property.getTitle());
                    et_description.setText(property.getDescription());
                    et_address.setText(property.getAddress());
                    et_cp.setText(property.getZipcode());
                    et_price.setText(Double.toString(property.getPrice()));
                    et_rooms.setText(Integer.toString(property.getRooms()));
                    et_city.setText(property.getCity());
                    et_province.setText(property.getProvince());
                }
            }

            @Override
            public void onFailure(Call<ResponseContainerDetail<Property>> call, Throwable t) {
                Log.e("NetworkFailure", t.getMessage());
                Toast.makeText(AddProperty.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void getCategories() {

        CategoryService service = ServiceGenerator.createService(CategoryService.class);
        Call<ResponseContainer<Category>> call = service.getCategories();

        call.enqueue(new Callback<ResponseContainer<Category>>() {
            @Override
            public void onResponse(Call<ResponseContainer<Category>> call, Response<ResponseContainer<Category>> response) {
                if (response.code() != 200) {
                    Toast.makeText(AddProperty.this, "Fallo al traer categorias", Toast.LENGTH_SHORT).show();
                } else {
                    categories = response.body().getRows();

                    ArrayAdapter<Category> adapter =
                            new ArrayAdapter<>(AddProperty.this, android.R.layout.simple_spinner_dropdown_item, categories);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    /*
                    spinner_category.setAdapter(adapter);
                    spinner_category.setSelection(categories.size() - 1);
                    */

                }
            }

            @Override
            public void onFailure(Call<ResponseContainer<Category>> call, Throwable t) {
                Log.e("NetworkFailure", t.getMessage());
                Toast.makeText(AddProperty.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void findviews() {

        //spinner_category = findViewById(R.id.spinner_category);
        btn_add = findViewById(R.id.btn_add);
        et_title = findViewById(R.id.et_title);
        et_description = findViewById(R.id.et_description);
        et_address = findViewById(R.id.et_address);
        et_cp = findViewById(R.id.et_cp);
        et_price = findViewById(R.id.et_price);
        et_rooms = findViewById(R.id.et_rooms);
        et_city = findViewById(R.id.et_city);
        et_province = findViewById(R.id.et_province);
    }


    private void events() {

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit) {
                    editProperty();
                } else {
                    addProperty();
                }

            }
        });
    }

    private void editProperty() {
        String completeAddress = "Calle " + et_address.getText().toString() + ", " + et_cp.getText().toString() + " " + ", España";
        String loc = null;

        //Category category = (Category) spinner_category.getSelectedItem();

        addPropertyDto = new AddPropertyDto(et_title.getText().toString(),
                et_description.getText().toString(),
                Double.valueOf(et_price.getText().toString()),
                Integer.valueOf(et_rooms.getText().toString()),
                //category.getId(),
                et_address.getText().toString(),
                et_cp.getText().toString(),
                et_city.getText().toString(),
                et_province.getText().toString(),
                longitud+", "+latitud);

        PropertyService service = ServiceGenerator.createService(PropertyService.class, UtilToken.getToken(this), TipoAutenticacion.JWT);
        Call<AddPropertyResponse> call = service.editProperty(id, addPropertyDto);

        /*Llamada a editar*/
        call.enqueue(new Callback<AddPropertyResponse>() {
            @Override
            public void onResponse(Call<AddPropertyResponse> call, Response<AddPropertyResponse> response) {
                if (response.code() != 200) {
                    Toast.makeText(AddProperty.this, "Fallo al editar inmueble", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddProperty.this, "Editado con éxito", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<AddPropertyResponse> call, Throwable t) {
                Log.e("NetworkFailure", t.getMessage());
                Toast.makeText(AddProperty.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addProperty() {

        String completeAddress = "Calle " + et_address.getText().toString() + ", " + et_cp.getText().toString() + " " + " " + ", España";
        String loc = null;

        //Category category = (Category) spinner_category.getSelectedItem();

        Geocoder coder = new Geocoder(this);
        try {
            ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName(et_address.getText().toString(), 1);
            for(Address add : adresses){
                latitud = add.getLongitude();
                longitud = add.getLatitude();
                /*if (statement) {//Controls to ensure it is right address such as country etc.
                    latitud = add.getLongitude();
                    longitud = add.getLatitude();
                }*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        addPropertyDto = new AddPropertyDto(et_title.getText().toString(),
                et_description.getText().toString(),
                Double.valueOf(et_price.getText().toString()),
                Integer.valueOf(et_rooms.getText().toString()),
                //category.getId(),
                et_description.getText().toString(),
                et_cp.getText().toString(),
                et_city.getText().toString(),
                et_province.getText().toString(),
                longitud+", "+latitud);

        PropertyService service = ServiceGenerator.createService(PropertyService.class, UtilToken.getToken(this), TipoAutenticacion.JWT);
        Call<AddPropertyResponse> call = service.addProperty(addPropertyDto);

        call.enqueue(new Callback<AddPropertyResponse>() {
            @Override
            public void onResponse(Call<AddPropertyResponse> call, Response<AddPropertyResponse> response) {
                if (response.code() != 201) {
                    Toast.makeText(AddProperty.this, "Fallo al crear propiedad", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddProperty.this, "Propiedad creada", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<AddPropertyResponse> call, Throwable t) {
                Log.e("NetworkFailure", t.getMessage());
                Toast.makeText(AddProperty.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
