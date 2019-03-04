package com.example.househunters.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.househunters.Generator.ServiceGenerator;
import com.example.househunters.Generator.TipoAutenticacion;
import com.example.househunters.Generator.UtilToken;
import com.example.househunters.Generator.UtilUser;
import com.example.househunters.Listener.PropertyListener;
import com.example.househunters.Model.Photo;
import com.example.househunters.Model.Property;
import com.example.househunters.Model.PropertyFavDto;
import com.example.househunters.Model.addFavourite;
import com.example.househunters.PropertyDetails;
import com.example.househunters.R;
import com.example.househunters.Services.PropertyService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyPropertiesRecyclerViewAdapter extends RecyclerView.Adapter<MyPropertiesRecyclerViewAdapter.ViewHolder> {

    private List<Property> mValues = null;
    private final PropertyListener mListener;
    private Context contexto;
    private Photo photo;
    private List<PropertyFavDto> valores = null;
    private boolean fav;
    private boolean fragmentFav;

    public MyPropertiesRecyclerViewAdapter(Context cxt, List<Property> items, PropertyListener listener) {
        contexto = cxt;
        mValues = items;
        mListener = listener;
    }

    public MyPropertiesRecyclerViewAdapter(Context cxt, PropertyListener listener, List<PropertyFavDto> objetos) {
        contexto = cxt;
        valores = objetos;
        mListener = listener;
    }

    public MyPropertiesRecyclerViewAdapter(Context cxt, List<Property> items, PropertyListener listener, boolean favFragment) {
        contexto = cxt;
        mValues = items;
        mListener = listener;
        fragmentFav = favFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_properties, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (mValues != null) {
            holder.mItem = mValues.get(position);
            eventsNormal(holder, position);
        } else {
            holder.mItemFav = valores.get(position);
            eventsMine(holder, position);
        }
    }

    private void eventsMine(final ViewHolder holder, final int position) {

        holder.textView_price.setText(valores.get(position).getPrice() + " €");
        holder.textView_titulo.setText(valores.get(position).getTitle());
        holder.textView_city.setText(valores.get(position).getCity());
        holder.imageViewfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PropertyService service = ServiceGenerator.createService(PropertyService.class, UtilToken.getToken(contexto), TipoAutenticacion.JWT);
                Call<addFavourite> call = service.addFavProperty(holder.mItemFav.getId());

                call.enqueue(new Callback<addFavourite>() {
                    @Override
                    public void onResponse(Call<addFavourite> call, Response<addFavourite> response) {
                        if (response.code() == 200) {
                            holder.imageViewfav.setImageResource(R.drawable.ic_star_black_24dp);
                        } else {
                            Toast.makeText(contexto, "Error en petición", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<addFavourite> call, Throwable t) {
                        Log.e("NetworkFailure", t.getMessage());
                        Toast.makeText(contexto, "Error de conexión", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


        holder.imageViewProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(contexto, PropertyDetails.class);
                i.putExtra("id", mValues.get(position).getId());
                contexto.startActivity(i);
            }
        });

        holder.imageViewProperty.setScaleType(ImageView.ScaleType.CENTER_CROP);

        if(valores.get(position).getPhotos() != null) {
            Glide
                    .with(contexto)
                    .load(valores.get(position).getPhotos()[0])
                    .into(holder.imageViewProperty);
        }


    }

    private void eventsNormal(final ViewHolder holder, final int position) {

        holder.textView_price.setText(mValues.get(position).getPrice() + " €");
        holder.textView_titulo.setText(mValues.get(position).getTitle());
        holder.textView_city.setText(mValues.get(position).getCity());

        if (UtilUser.getEmail(contexto) == null) {

            holder.imageViewfav.setVisibility(View.GONE);

        } else {

            fav = mValues.get(position).isFav();

            if (fav || fragmentFav) {

                holder.imageViewfav.setImageResource(R.drawable.ic_star_black_24dp);

            } else {

                holder.imageViewfav.setImageResource(R.drawable.ic_star_border_black_24dp);
            }

            holder.imageViewfav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fav = mValues.get(position).isFav();

                    if (fav || fragmentFav) {

                        eventOnClickIsFav(holder);
                        mValues.get(position).setFav(false);

                    } else {

                        eventOnClickIsNotFav(holder);
                        mValues.get(position).setFav(true);

                    }
                }
            });


        }


        holder.imageViewProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(contexto, PropertyDetails.class);
                i.putExtra("id", mValues.get(position).getId());
                contexto.startActivity(i);
            }
        });

        holder.imageViewProperty.setScaleType(ImageView.ScaleType.CENTER_CROP);

        if(mValues.get(position).getPhotos() != null) {
            Glide
                    .with(contexto)
                    .load(mValues.get(position).getPhotos()[0])
                    .into(holder.imageViewProperty);
        }
    }

    private void eventOnClickIsFav(final ViewHolder holder) {

        PropertyService service = ServiceGenerator.createService(PropertyService.class, UtilToken.getToken(contexto), TipoAutenticacion.JWT);
        Call<addFavourite> call = service.removeFavProperty(holder.mItem.getId());

        call.enqueue(new Callback<addFavourite>() {
            @Override
            public void onResponse(Call<addFavourite> call, Response<addFavourite> response) {

                if (response.code() == 200) {

                    holder.imageViewfav.setImageResource(R.drawable.ic_star_border_black_24dp);
                    Toast.makeText(contexto, "Eliminado de favoritos", Toast.LENGTH_SHORT).show();
                    fav = !fav;

                } else {

                    Toast.makeText(contexto, "Error en petición", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<addFavourite> call, Throwable t) {
                Log.e("NetworkFailure", t.getMessage());
                Toast.makeText(contexto, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void eventOnClickIsNotFav(final ViewHolder holder) {

        PropertyService service = ServiceGenerator.createService(PropertyService.class, UtilToken.getToken(contexto), TipoAutenticacion.JWT);
        Call<addFavourite> call = service.addFavProperty(holder.mItem.getId());

        call.enqueue(new Callback<addFavourite>() {

            @Override
            public void onResponse(Call<addFavourite> call, Response<addFavourite> response) {

                if (response.code() == 200) {

                    holder.imageViewfav.setImageResource(R.drawable.ic_star_black_24dp);
                    Toast.makeText(contexto, "Añadido a favoritos", Toast.LENGTH_SHORT).show();
                    fav = !fav;

                } else {

                    Toast.makeText(contexto, "Error en petición", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<addFavourite> call, Throwable t) {

                Log.e("NetworkFailure", t.getMessage());
                Toast.makeText(contexto, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {

        if (mValues != null) {

            return mValues.size();

        } else {

            return valores.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textView_titulo, textView_city, textView_price;
        public final ImageView imageViewProperty;
        public final ImageView imageViewfav;
        public Property mItem;
        public PropertyFavDto mItemFav;
        public final CardView cardView_property;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textView_titulo = view.findViewById(R.id.textView_titulo);
            textView_city = view.findViewById(R.id.textView_city);
            textView_price = view.findViewById(R.id.textView_price);
            imageViewProperty = view.findViewById(R.id.imageViewProperty);
            imageViewfav = view.findViewById(R.id.imageViewfav);
            cardView_property = view.findViewById(R.id.cardView_property);
        }

    }
}
