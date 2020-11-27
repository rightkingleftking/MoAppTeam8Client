package com.example.teamproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//test

public class MainActivity extends Activity {

    private TextView textViewResult;
    private JsonParserRetrofit jsonParserRetrofit;


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

        textViewResult = findViewById(R.id.text_view_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.58.103.212:8080/api/datas/gets/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        jsonParserRetrofit = retrofit.create(JsonParserRetrofit.class);
        getDatas();

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

    private void getDatas() {
        Call<List<Get>> call = jsonParserRetrofit.getDatas("곡류", "*", "tradition", "last", "p1");
        call.enqueue(new Callback<List<Get>>() {
            @Override
            public void onResponse(Call<List<Get>> call, Response<List<Get>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Get> gets = response.body();
                for (Get get : gets) {
                    String content = "";
                    content += "Category: " + get.getCategory() + "\n";
                    content += "Id: " + get.getId() + "\n";
                    content += "Unit: " + get.getUnit() + "\n";
                    content += "P: " + get.getP() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Get>> call, Throwable t) {
                textViewResult.setText((t.getMessage()));
            }
        });
    }
    
    public void onM_select_btn_clicked(View view) {
        Intent intent = new Intent(getApplicationContext(), MarketSelectActivity.class);
        startActivity(intent);
    }
}
