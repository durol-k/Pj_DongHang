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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.capstone.donghang.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 글 작성 프래그먼트
 */
public class Fragment_Community_Write extends Fragment {
    Fragment_Community_MainListItem post;
    List<Fragment_Community_MainListItem> itemList;
    public Fragment_Community_Write(List<Fragment_Community_MainListItem> itemList) {
        // Required empty public constructor
        this.itemList = itemList;
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
            case R.id.community_temporal_save_btn : // 임시저장
                dlg.setTitle("임시저장");
                dlg.setMessage("임시로 저장하시겠습니까?");
                dlg.setPositiveButton("임시저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /* 프래그먼트 이동 */
                        Fragment_Community_MainList mainList_fragment = new Fragment_Community_MainList(itemList);
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_main, mainList_fragment);
                        transaction.commit();
                        Toast.makeText(getContext(), "임시저장 되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.setNegativeButton("계속작성", null);

                break;
            case R.id.community_save_btn : // 저장
                dlg.setTitle("저장");
                dlg.setMessage("저장하시겠습니까?");
                dlg.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Date date = new Date(); // 시간 객체
                        SimpleDateFormat time = new SimpleDateFormat("hh:mm"); // 시간 형식
                        post = new Fragment_Community_MainListItem("test303", time.format(date), title.getText().toString(),
                                                                    content.getText().toString(), null,
                                                                       0, 0, 0, R.drawable.community_icon); // 게시글 정보 객체 생성
                        itemList.add(0,post); // 리스트 첫번째에 삽입

                        /* 프래그먼트 이동 */
                        Fragment_Community_MainList mainList_fragment = new Fragment_Community_MainList(itemList);
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_main, mainList_fragment);
                        transaction.commit();
                    }
                });
                dlg.setNegativeButton("계속작성",null);
        }
        dlg.show();

        return super.onOptionsItemSelected(item);
    }
}
