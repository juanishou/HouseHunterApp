package com.example.househunters.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.househunters.Adapters.MyPropertiesRecyclerViewAdapter;
import com.example.househunters.Generator.ServiceGenerator;
import com.example.househunters.Generator.TipoAutenticacion;
import com.example.househunters.Generator.UtilToken;
import com.example.househunters.Listener.PropertyListener;
import com.example.househunters.Model.PropertyFavDto;
import com.example.househunters.Model.ResponseContainer;
import com.example.househunters.R;
import com.example.househunters.Services.PropertyService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserPropertiesListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private PropertyListener mListener;
    private List<PropertyFavDto> userPropertyList;
    private MyPropertiesRecyclerViewAdapter adapter;
    private Context cxt;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UserPropertiesListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static UserPropertiesListFragment newInstance(int columnCount) {
        UserPropertiesListFragment fragment = new UserPropertiesListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_properties_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
             cxt = view.getContext();
            final RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(cxt));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(cxt, mColumnCount));
            }

            userPropertyList = new ArrayList<>();

            PropertyService service = ServiceGenerator.createService(PropertyService.class, UtilToken.getToken(getContext()), TipoAutenticacion.JWT);
            Call<ResponseContainer<PropertyFavDto>> call = service.getMineProperties();

            call.enqueue(new Callback<ResponseContainer<PropertyFavDto>>() {
                @Override
                public void onResponse(Call<ResponseContainer<PropertyFavDto>> call, Response<ResponseContainer<PropertyFavDto>> response) {
                    if (response.code() != 200) {
                        Toast.makeText(getActivity(), "Error en petición", Toast.LENGTH_SHORT).show();
                    } else {
                        userPropertyList = response.body().getRows();

                        adapter = new MyPropertiesRecyclerViewAdapter(
                                cxt,
                                mListener,
                                userPropertyList
                        );
                        recyclerView.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<ResponseContainer<PropertyFavDto>> call, Throwable t) {
                    Log.e("NetworkFailure", t.getMessage());
                    Toast.makeText(getActivity(), "Error de conexión", Toast.LENGTH_SHORT).show();
                }
            });

        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PropertyListener) {
            mListener = (PropertyListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
