package com.capstone.donghang.community;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.capstone.donghang.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * 글 작성 프래그먼트
 */
public class Fragment_Community_Write extends Fragment {
    ArrayList<String> list;
    public Fragment_Community_Write() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_community_write, container, false);
        Spinner category = view.findViewById(R.id.community_write_category);
        Spinner header = view.findViewById(R.id.community_write_head);
        ArrayAdapter catAdapter = ArrayAdapter.createFromResource(getContext(), R.array.community_category, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter headAdapter = ArrayAdapter.createFromResource(getContext(), R.array.community_header_notice, android.R.layout.simple_spinner_dropdown_item);

        category.setAdapter(catAdapter);
        header.setAdapter(headAdapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.community_write_toolbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final Spinner category = getView().findViewById(R.id.community_write_category);
        final Spinner header = getView().findViewById(R.id.community_write_head);
        final EditText title = getView().findViewById(R.id.community_write_title);
        final EditText content = getView().findViewById(R.id.community_write_content);
        final EditText tag = getView().findViewById(R.id.community_write_tag);


        int id = item.getItemId();

        AlertDialog.Builder dlg = new AlertDialog.Builder(getContext());
        switch (id) {
            case R.id.community_temporal_save_btn :
                dlg.setTitle("임시저장");
                dlg.setMessage("임시로 저장하시겠습니까?");
                break;
            case R.id.community_save_btn :
                dlg.setTitle("저장");
                dlg.setMessage("저장하시겠습니까?");
                dlg.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list = new ArrayList<>();
                        list.add(category.getSelectedItem().toString());
                        list.add(header.getSelectedItem().toString());
                        list.add(title.getText().toString());
                        list.add(content.getText().toString());
                        list.add(tag.getText().toString());

                        Fragment_Community_MainList mainList_fragment = new Fragment_Community_MainList(list);
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_main, mainList_fragment);
                        transaction.commit();
                    }
                });
                dlg.setNegativeButton("나가기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Fragment_Community_MainList mainList_fragment = new Fragment_Community_MainList();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_main, mainList_fragment);
                        transaction.commit();
                    }
                });

                dlg.show();
        }


        return super.onOptionsItemSelected(item);
    }
}
