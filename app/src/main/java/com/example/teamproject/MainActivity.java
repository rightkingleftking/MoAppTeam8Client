package com.example.teamproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;


//test

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent intent = new Intent(this, LoadingActivity.class); // 로딩페이지
//        startActivity(intent);

        // 검색어 직접입력을 위한 화면 전환
        Button searchButton = (Button) findViewById(R.id.searchbar_btn);
        searchButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        // 분류를 이용한 검색
        Spinner spinner_division1 = (Spinner) findViewById(R.id.spinner_division1);
        ArrayList<String> arr_division1 = new ArrayList<String>();

        /* thread issue
        JSONArray arr = null;
        try {
            arr = JsonPaser.getData();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject obj = (JSONObject) arr.get(0);
        String div1 = (String)obj.get("class");
        String name = (String)obj.get("id");
        String unit = (String)obj.get("unit");
        String ppap = (String)obj.get("p1");

        System.out.println("분류 : " + div1);
        System.out.println("품명 : " + name);
        System.out.println("규격 : " + unit);
        System.out.println("p : " + ppap);
         */

        arr_division1.add("대분류 선택");
        arr_division1.add("곡류");
        arr_division1.add("육류");
        ArrayAdapter<String> adapter_division1 = new ArrayAdapter<String>(this, R.layout.spin_div1, R.id.spinner_division1_contents, arr_division1) {
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        adapter_division1.setDropDownViewResource(R.layout.spin_div1);
        spinner_division1.setAdapter(adapter_division1);
        Spinner spinner_division2 = (Spinner) findViewById(R.id.spinner_division2);
        ArrayList<String> arr_division2 = new ArrayList<String>();
        arr_division2.add("품목 선택");
        arr_division2.add("쌀");
        arr_division2.add("소고기");

        ArrayAdapter<String> adapter_division2 = new ArrayAdapter<String>(this, R.layout.spin_div2, R.id.spinner_division2_contents, arr_division2) {
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        adapter_division2.setDropDownViewResource(R.layout.spin_div2);
        spinner_division2.setAdapter(adapter_division2);

    }



}