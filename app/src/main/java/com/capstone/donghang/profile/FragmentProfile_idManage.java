package com.capstone.donghang.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.capstone.donghang.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FragmentProfile_idManage extends Fragment {

    ArrayList<String> dataLists;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    AppCompatActivity activity;
    TextView email, birth;
    EditText current_pw, new_pw1, new_pw2, nikName, phone, address;
    Button pw_chage_btn, change_btn;
    ConstraintLayout change_pw_layout;
    String passwd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataLists = new ArrayList<>();
        getActivity().setTitle("계정 관리");
        passwd="1234";
        return inflater.inflate(R.layout.fragment_profile_id_manage, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        activity = (AppCompatActivity)getActivity();

        email = getView().findViewById(R.id.profile_manage_id);
        nikName = getView().findViewById(R.id.profile_manage_name);
        birth = getView().findViewById(R.id.profile_manage_birth);
        phone = getView().findViewById(R.id.profile_manage_phone);
        address = getView().findViewById(R.id.profile_manage_address);
        pw_chage_btn = getView().findViewById(R.id.profile_pw_change_btn);
        change_pw_layout = getView().findViewById(R.id.profile_pw_change_layout);
        change_btn = getView().findViewById(R.id.profile_manage_changeOk);
        current_pw = getView().findViewById(R.id.current_pw);
        new_pw1 = getView().findViewById(R.id.new_pw1);
        new_pw2 = getView().findViewById(R.id.new_pw2);

        email.setText(getString(R.string.connect_id));
        nikName.setText(getString(R.string.nikname));
        birth.setText("1990.07.23");
        phone.setText("010-3799-8569");
        address.setText("서울시 은평구 진흥로1가길");

        pw_chage_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(change_pw_layout.getVisibility() == View.GONE)
                    change_pw_layout.setVisibility(View.VISIBLE);
                else
                    change_pw_layout.setVisibility(View.GONE);

            }
        });

        change_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean pw_change=false;
                boolean final_check = false;

                if(change_pw_layout.getVisibility() == View.VISIBLE) // 비밀번호 변경 여부확인
                    pw_change = true;


                final_check = check(pw_change);

                if(final_check)
                    Toast.makeText(getActivity(), "변경완료", Toast.LENGTH_SHORT).show();

            }
        });



        super.onActivityCreated(savedInstanceState);
    }

    public boolean check(boolean pw_change){

        boolean pw_check_result = true;
        boolean final_result = false;

        if(pw_change){
           pw_check_result = pw_check();
           if(pw_check_result)
               final_result = text_check();
        }
        else{
            final_result = text_check();
        }

        return final_result;
    }

    public boolean pw_check(){
        boolean check = false;
        String current_pw_s = current_pw.getText().toString();
        //Toast.makeText(getActivity(), current_pw_s, Toast.LENGTH_SHORT).show();
        if(current_pw_s.equals("1234")){
            String new_pw1_s = new_pw1.getText().toString();
            String new_pw2_s = new_pw2.getText().toString();
            if(!new_pw1_s.equals("") && !new_pw2_s.equals(""))
                if(new_pw1_s.equals(new_pw2_s))
                    check = true;
                else
                    makeDialog("새 비밀번호가 일치하지 않습니다.");
            else
                makeDialog("새 비밀번호를 입력해주세요");
        }
        else
            makeDialog("현재 비밀번호가 일치하지 않습니다.");

        return check;


    }

    public boolean text_check(){

        boolean check = false;

        String name_s = nikName.getText().toString();
        String phone_s = phone.getText().toString();
        String address_s = address.getText().toString();

        if(name_s.equals("")) {
            makeDialog("닉네임을 입력해주세요");
        }

        else if(phone_s.equals("")){
            makeDialog("휴대폰 번호를 입력해주세요");
        }

        else if(address_s.equals("")){
            makeDialog("주소를 입력해주세요");
        }
        else
            check = true;

        return check;

    }

    public void makeDialog(String str){

        AlertDialog.Builder dlg = new AlertDialog.Builder(getContext());
        dlg.setTitle("알림");
        dlg.setMessage(str);
        dlg.show();
    }
}


