package com.capstone.donghang.community;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
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
    private Fragment_Community_MainListItem post; // 현재 쓴 글
    private  List<Fragment_Community_MainListItem> itemList; // 전체 글 리스트
    private int catNum; //카테고리 번호
    private String catName; //카테고리 이름

    public Fragment_Community_Write() {}


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
        final Spinner categorySp = getView().findViewById(R.id.community_write_category);
        final Spinner headerSp = getView().findViewById(R.id.community_write_head);
        final EditText titleEd = getView().findViewById(R.id.community_write_title);
        final EditText contentEd = getView().findViewById(R.id.community_write_content);
        // final EditText tagEd = getView().findViewById(R.id.community_write_tag);


        int id = item.getItemId();

        AlertDialog.Builder dlg = new AlertDialog.Builder(getContext());
        switch (id) {
            case R.id.community_temporal_save_btn: // 임시저장 다이얼로그
                dlg.setTitle("임시저장");
                dlg.setMessage("임시로 저장하시겠습니까?");
                dlg.setPositiveButton("임시저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /* 프래그먼트 이동 */
                        getActivity().getSupportFragmentManager().popBackStack();
                        Toast.makeText(getContext(), "임시저장 되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.setNegativeButton("계속작성", null);

                break;
            case R.id.community_save_btn: // 저장 다이얼로그
                dlg.setTitle("저장");
                dlg.setMessage("저장하시겠습니까?");
                dlg.setNegativeButton("계속작성", null);
                dlg.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String title = titleEd.getText().toString();
                        String content = contentEd.getText().toString();
                        int categoryNum = categorySp.getSelectedItemPosition(); // 선택한 인덱스 저장
                        catNum = getId(categoryNum); // 선택한 인덱스에 대한 게시판의 R.id 저장
                        catName = categorySp.getSelectedItem().toString(); // 선택한 인덱스의 텍스트값 저장
                        // int header = headerSp.getSelectedItemPosition();

                        AlertDialog.Builder dateNulldlg = new AlertDialog.Builder(getContext());
                        dateNulldlg.setTitle("확인");
                        if (categoryNum == 0) { // 카테고리 스피너 0번은 제목에 해당되므로 선택안한 것으로 간주
                            dateNulldlg.setMessage("게시판을 선택해주세요");
                            dateNulldlg.setPositiveButton("확인", null);
                            dateNulldlg.show();
                        } else if (title.equals("") || content.equals("")) {
                            dateNulldlg.setMessage("제목과 내용을 작성해주세요");
                            dateNulldlg.setPositiveButton("확인", null);
                            dateNulldlg.show();
                        } else {
                            Date date = new Date(); // 시간 객체
                            SimpleDateFormat time = new SimpleDateFormat("hh:mm"); // 시간 형식

                            // 게시글을객체로 생성
                            post = new Fragment_Community_MainListItem("test", time.format(date), title,
                                                                            content, null,
                                                                    0, 0, 0, R.drawable.community_icon, catNum);

                            /* 프래그먼트 이동 */
                            Fragment_Community_MainList mainList_fragment = new Fragment_Community_MainList(post, catNum, catName);
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_main, mainList_fragment);
                            transaction.commit();
                        }
                    }
                });
        }
        dlg.show();

        return super.onOptionsItemSelected(item);
    }
    /* 카테고리 스피너 인덱스 값에 대해 R.id값 반환 */
    private int getId(int i) {
        switch (i) {
            case 1:
                return R.id.content_notice;
            case 2:
                return R.id.content_domestic_review;
            case 3:
                return R.id.content_foreign_review;
            case 4:
                return R.id.content_travel_picture;
            case 5:
                return R.id.content_domestic_donghang;
            case 6:
                return R.id.content_foreign_donghang;
            case 7:
                return R.id.content_info;
            case 8:
                return R.id.content_tip;
            case 9:
                return R.id.content_qna;
            case 10:
                return R.id.content_news;
            case 11:
                return R.id.content_sale;
            case 12:
                return R.id.content_talk;
            case 13:
                return R.id.content_free;
            case 14:
                return R.id.content_request;
        }
        return 0;
    }
}
