package com.capstone.donghang.plan;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.donghang.MainActivity;
import com.capstone.donghang.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;

public class FragmentMakePlan extends Fragment implements OnMapReadyCallback {
    GoogleMap gMap;
    Context context;

    public static FragmentMakePlan newInstance() {
        return new FragmentMakePlan();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_plan_make, container, false);

        setHasOptionsMenu(true);

        Toolbar toolbar = getActivity().findViewById(R.id.main_toolbar);

        MapView mapView = root.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        RecyclerView recyclerView = root.findViewById(R.id.plan_make_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        ArrayList<ItemPlanDetail> dataSet = new ArrayList<ItemPlanDetail>();
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.PARENT, "2020.06.10"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "숭례문", "서울 종로구 사직로 161 경복궁"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "경복궁", "서울 중구 세종대로 40"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "남산타워", "서울 용산구 남산공원길 103 서울타워우편물취급소"));

        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.PARENT, "2020.06.11"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "명지전문대", "서울특별시 서대문구 가좌로 134"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "명지대", "서울특별시 서대문구 거북골로 34"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "남산", "서울 중구 회현동1가"));

        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.PARENT, "2020.06.12"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "광화문", "서울 종로구 사직로 161"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "서울시청", "서울 중구 세종대로 110 서울특별시청"));
        dataSet.add(new ItemPlanDetail(RecyclerAdapterPlanDetail.CHILD, "덕수궁", "서울 중구 세종대로 99 덕수궁"));

        RecyclerAdapterPlanDetail adapter = new RecyclerAdapterPlanDetail(dataSet);
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.568256, 126.897240),15));
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.plan_menu_savesearch, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.plan_menu_savesearch_save:
                Toast.makeText(getActivity(), "저장되었습니다", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).replaceFragment(FragmentPlan.newInstance());
        }

        return true;
    }
}
