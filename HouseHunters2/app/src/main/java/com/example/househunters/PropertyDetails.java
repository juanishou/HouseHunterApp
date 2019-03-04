package com.example.househunters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.househunters.Adapters.ViewPagerAdapter;
import com.example.househunters.Generator.ServiceGenerator;
import com.example.househunters.Generator.TipoAutenticacion;
import com.example.househunters.Generator.UtilToken;
import com.example.househunters.Model.Property;
import com.example.househunters.Model.ResponseContainerDetail;
import com.example.househunters.Services.PropertyService;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyDetails extends AppCompatActivity {

    ViewPager viewPager;
    TextView tv_address_details, tv_city_details, tv_price_details, tv_description_details;
    Property property;
    private List<String> img;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);

        Intent intent = getIntent();

        String id = intent.getStringExtra("id");

        findsId();

        PropertyService propertiesService = ServiceGenerator.createService(PropertyService.class);

        Call<ResponseContainerDetail<Property>> call = propertiesService.getOneProperty(id);

        call.enqueue(new Callback<ResponseContainerDetail<Property>>() {
            @Override
            public void onResponse(Call<ResponseContainerDetail<Property>> call, Response<ResponseContainerDetail<Property>> response) {
                if (response.code() != 200) {
                    Toast.makeText(PropertyDetails.this, "Error al ver Inmueble", Toast.LENGTH_SHORT).show();
                } else {
                    property = response.body().getRows();
                    sets();
                }
            }

            @Override
            public void onFailure(Call<ResponseContainerDetail<Property>> call, Throwable t) {
                Log.e("NetworkFailure", t.getMessage());
                Toast.makeText(PropertyDetails.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.btn_edit:
                startActivity(new Intent(PropertyDetails.this, AddProperty.class).putExtra("idpropiedad", property.getId()));
                return true;

            case R.id.btn_delete:
                deleteProperty(property.getId());
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteProperty(String id) {
        PropertyService service = ServiceGenerator.createService(PropertyService.class, UtilToken.getToken(this), TipoAutenticacion.JWT);
        Call<ResponseContainerDetail<Property>> call = service.deleteProperty(id);

        call.enqueue(new Callback<ResponseContainerDetail<Property>>() {
            @Override
            public void onResponse(Call<ResponseContainerDetail<Property>> call, Response<ResponseContainerDetail<Property>> response) {
                if (response.code() != 204) {
                    Toast.makeText(PropertyDetails.this, "Fallo al borrar", Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseContainerDetail<Property>> call, Throwable t) {
                Log.e("NetworkFailure", t.getMessage());
                Toast.makeText(PropertyDetails.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    private void sets() {
        tv_description_details.setText(property.getDescription());
        tv_city_details.setText(property.getCity() + " , " + property.getProvince());
        tv_address_details.setText(property.getAddress());
        tv_price_details.setText(property.getPrice() + " €/mes");

        img = Arrays.asList(property.getPhotos());

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(PropertyDetails.this, img);

        viewPager.setAdapter(viewPagerAdapter);
    }

    private void findsId() {

        viewPager = findViewById(R.id.viewPager);
        tv_description_details = findViewById(R.id.tv_description_details);
        tv_city_details = findViewById(R.id.tv_city_details);
        tv_address_details = findViewById(R.id.tv_address_details);
        tv_price_details = findViewById(R.id.tv_price_details);

    }
}
