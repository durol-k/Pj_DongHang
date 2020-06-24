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
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class FragmentSelectSearch extends Fragment implements OnMapReadyCallback{
    TextView tvName,tvSort,tvContent,tvAddress,tvTelnum;
    RatingBar rb;
    RecyclerView rcv;

    FragmentSelectSearchDto info;
    GoogleMap gMap;

    public FragmentSelectSearch(FragmentSelectSearchDto _info){
        info = _info;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_select_location, container, false);

        tvName = view.findViewById(R.id.tvSSLName);
        tvSort = view.findViewById(R.id.tvSSLSort);
        tvContent = view.findViewById(R.id.tvSSLContent);
        tvAddress = view.findViewById(R.id.tvSSLAddress);
        tvTelnum = view.findViewById(R.id.tvSSLTelnum);
        rb = view.findViewById(R.id.rbSSL);

        MapView mapView = view.findViewById(R.id.mapSSL);
        rcv = view.findViewById(R.id.rcvSSL);

        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync( this);

        rcv.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));

        ArrayList<Integer> list = info.getImage();
        SearchSelectAdapter adapter = new SearchSelectAdapter(list);
        rcv.setAdapter(adapter);


        tvName.setText(info.getName());
        tvSort.setText(info.getSort());
        tvContent.setText(info.getContent());
        tvAddress.setText(info.getAddress());
        tvTelnum.setText(info.getTelnum());
        rb.setRating(info.getRating());


        return view;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        LatLng latLng = info.getMapMark();
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        gMap.addMarker(markerOptions);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,13));
    }


    @Override
    public void onStart() {
        super.onStart();


    }
}
