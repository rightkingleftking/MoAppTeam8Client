package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class MarketSelectActivity extends AppCompatActivity {
    CheckBox[] t_ary = new CheckBox[9];
    CheckBox[] s_ary = new CheckBox[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_select);
        //전통시장
        t_ary[1] = (CheckBox) findViewById(R.id.t1);
        t_ary[2] = (CheckBox) findViewById(R.id.t2);
        t_ary[3] = (CheckBox) findViewById(R.id.t3);
        t_ary[4] = (CheckBox) findViewById(R.id.t4);
        t_ary[5] = (CheckBox) findViewById(R.id.t5);
        t_ary[6] = (CheckBox) findViewById(R.id.t6);
        t_ary[7] = (CheckBox) findViewById(R.id.t7);
        t_ary[8] = (CheckBox) findViewById(R.id.t8);

        //대형마트
        s_ary[1] = (CheckBox) findViewById(R.id.s1);
        s_ary[2] = (CheckBox) findViewById(R.id.s2);
        s_ary[3] = (CheckBox) findViewById(R.id.s3);
        s_ary[4] = (CheckBox) findViewById(R.id.s4);
        s_ary[5] = (CheckBox) findViewById(R.id.s5);
        s_ary[6] = (CheckBox) findViewById(R.id.s6);
        s_ary[7] = (CheckBox) findViewById(R.id.s7);
        s_ary[8] = (CheckBox) findViewById(R.id.s8);
    }

    public void onsave_btn_clicked(View view) {
        SharedPreferences sp1 = getSharedPreferences("tradition", MODE_PRIVATE);
        SharedPreferences sp2 = getSharedPreferences("super", MODE_PRIVATE);
        SharedPreferences.Editor trad_editor = sp1.edit();
        SharedPreferences.Editor super_editor = sp2.edit();

        trad_editor.clear();
        super_editor.clear();
        trad_editor.commit();
        super_editor.commit();

        for (int i = 1; i < 9; i++) {
            if (t_ary[i].isChecked()) {
                trad_editor.putString("t" + i, "p" + i);  //key, value   //value to string
                trad_editor.commit();
            }
            if (s_ary[i].isChecked()) {
                super_editor.putString("s" + i, "p" + i);  //key, value  //value to string
                super_editor.commit();
            }
        }
        Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
    }

    public void onload_btn_clicked(View view) {
        int i;
        String [] value_list1 = get_check_info("tradition");
        String [] value_list2 = get_check_info("super");

        for(i=0; i<value_list1.length; i++)
            t_ary[Character.getNumericValue(value_list1[i].charAt(1))].setChecked(true);

        for(i=0; i<value_list2.length; i++)
            s_ary[Character.getNumericValue(value_list2[i].charAt(1))].setChecked(true);

        Toast.makeText(this, "불러오기 하였습니다.", Toast.LENGTH_SHORT).show();
    }

    public String [] get_check_info(String tag) {
        SharedPreferences sp = getSharedPreferences(tag, MODE_PRIVATE);
        String value_list [] = new String [8];
        Map<String, ?> info;
        int cnt = 0;

        info = sp.getAll();
        for(Map.Entry<String, ?> entry : info.entrySet())
            value_list[cnt++] = (String)entry.getValue();

        value_list = Arrays.copyOfRange(value_list, 0, cnt);
        Arrays.sort(value_list);
        return value_list;
    }

    public void onback_btn_clicked(View view) {
        finish();
    }
}
