package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MarketSelectActivity extends AppCompatActivity {
    CheckBox [] t_ary = new CheckBox[9];
    CheckBox [] s_ary = new CheckBox[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_select);

        t_ary[0] = (CheckBox)findViewById(R.id.t);  //전통 시장
        t_ary[1] = (CheckBox)findViewById(R.id.t1);
        t_ary[2] = (CheckBox)findViewById(R.id.t2);
        t_ary[3] = (CheckBox)findViewById(R.id.t3);
        t_ary[4] = (CheckBox)findViewById(R.id.t4);
        t_ary[5] = (CheckBox)findViewById(R.id.t5);
        t_ary[6] = (CheckBox)findViewById(R.id.t6);
        t_ary[7] = (CheckBox)findViewById(R.id.t7);
        t_ary[8] = (CheckBox)findViewById(R.id.t8);

        s_ary[0] = (CheckBox)findViewById(R.id.s); //대형 마트
        s_ary[1] = (CheckBox)findViewById(R.id.s1);
        s_ary[2] = (CheckBox)findViewById(R.id.s2);
        s_ary[3] = (CheckBox)findViewById(R.id.s3);
        s_ary[4] = (CheckBox)findViewById(R.id.s4);
        s_ary[5] = (CheckBox)findViewById(R.id.s5);
        s_ary[6] = (CheckBox)findViewById(R.id.s6);
        s_ary[7] = (CheckBox)findViewById(R.id.s7);
        s_ary[8] = (CheckBox)findViewById(R.id.s8);
    }

    public void onsave_btn_clicked(View view) {  //1은 check, 0은 uncheck
        SharedPreferences sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String name;
        editor.clear();
        for(int i=0; i<9; i++) {
            if (t_ary[i].isChecked()) {
                editor.putString("t"+i, "1");  //key, value
            }
            else {
                editor.putString("t"+i, "0");
            }
            editor.commit();
            if (s_ary[i].isChecked()) {
                editor.putString("s"+i, "1");  //key, value
            }
            else {
                editor.putString("s"+i, "0");
            }
            editor.commit();
        }
        Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
    }

    public void onload_btn_clicked(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
        String checked;
        for(int i=0; i<9; i++) {
            checked = sharedPreferences.getString("t"+i, "");
            if(checked.equals("1")) {
                t_ary[i].setChecked(true);
            }
            checked = sharedPreferences.getString("s"+i, "");
            if(checked.equals("1")) {
                s_ary[i].setChecked(true);
            }
        }
        Toast.makeText(this, "불러오기 하였습니다.", Toast.LENGTH_SHORT).show();
    }

    public void onback_btn_clicked(View view) {
        finish();
    }
}