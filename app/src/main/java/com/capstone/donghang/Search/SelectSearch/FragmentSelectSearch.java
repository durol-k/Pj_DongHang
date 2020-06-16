package com.capstone.donghang.Search.SelectSearch;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class FragmentSelectSearch extends Fragment implements OnMapReadyCallback {
    TextView tvName,tvSort,tvContent,tvAddress,tvTelnum;
    RatingBar rb;
    RecyclerView rcv;
    GoogleMap gMap;
    MapFragment mapFrag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_select_location, container, false);

        ArrayList<String> list = new ArrayList<>();

        for (int i=0 ; i<5; i++){
            list.add(String.format("%d",i));
        }

        rcv = view.findViewById(R.id.rcvSSL);
        rcv.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));

        SearchSelectAdapter adapter = new SearchSelectAdapter(list);
        rcv.setAdapter(adapter);

//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MODE_PRIVATE);
//
//        mapFrag = (MapFragment) getFragmentManager().findFragmentById(R.id.mapSSL);
//        mapFrag.getMapAsync(this);


        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

    }
}
