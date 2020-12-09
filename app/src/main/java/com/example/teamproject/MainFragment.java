package com.example.teamproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class MainFragment extends Fragment {
    private String mParam2;

    private TextView trad_textView, super_textView;
    private JsonParserRetrofit jsonParserRetrofit;
    private HashMap<String, String> trad_map = new HashMap<String, String>();
    private HashMap<String, String> super_map = new HashMap<String, String>();

    ArrayList<String> arr_division1 = new ArrayList<String>();
    ArrayList<String> arr_division2 = new ArrayList<String>();

    Spinner spinner_division1, spinner_division2;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://18.225.5.118:8080/api/datas/gets/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        // 검색어 직접입력을 위한 화면 전환
//        Button searchButton = (Button) v.findViewById(R.id.searchbar_btn);
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity().getApplicationContext(), SearchActivity.class);
//                intent.putExtra("list", arr_division2);
//                startActivity(intent);
//            }
//        });

        init_map();
        Button selectButton = (Button) v.findViewById(R.id.M_select_btn);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MarketSelectActivity.class);
                startActivity(intent);
            }
        });

        Button searchButton = (Button) v.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String category = spinner_division1.getSelectedItem().toString();  //대분류 선택 정보 가져오기
                String id = spinner_division2.getSelectedItem().toString();  //분류 선택 정보 가져오기
                String [] value_list1, value_list2;

                value_list1 = get_check_info("tradition");
                value_list2 = get_check_info("super");

                trad_textView.setText(null);
                super_textView.setText(null);

                search(category, id, "tradition", "current", value_list1, trad_textView);
                search(category, id, "super", "current", value_list2, super_textView);
            }
        });

        // 분류를 이용한 검색
        spinner_division1 = (Spinner) v.findViewById(R.id.spinner_division1);
        trad_textView = v.findViewById(R.id.trad_text_view);
        super_textView = v.findViewById(R.id.super_text_view);

        jsonParserRetrofit = retrofit.create(JsonParserRetrofit.class);

        //초기화면 예시 데이터
        getDatas("곡물", "*", "tradition", "current", "p1", trad_textView);
        getDatas("곡물", "*", "super", "current", "p1", super_textView);

        getCategoryList(arr_division1);
        ArrayAdapter<String> adapter_division1 = new ArrayAdapter<String>(getActivity(), R.layout.spin_div1, R.id.spinner_division1_contents, arr_division1) {
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

        spinner_division2 = (Spinner) v.findViewById(R.id.spinner_division2);

        getIdList(arr_division2);

        ArrayAdapter<String> adapter_division2 = new ArrayAdapter<String>(getActivity(), R.layout.spin_div2, R.id.spinner_division2_contents, arr_division2) {
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

        return v;
    }

    private void getIdList(final ArrayList<String> arr_division) {
        arr_division.add("분류 선택");
        Call<List<Get>> call = jsonParserRetrofit.getDatas("idList", "null", "null", "null", "null");
        call.enqueue(new Callback<List<Get>>() {
            @Override
            public void onResponse(Call<List<Get>> call, Response<List<Get>> response) {
                if (!response.isSuccessful()) {
                    trad_textView.setText("Code: " + response.code());
                    super_textView.setText("Code: " + response.code());
                    return;
                }
                List<Get> gets = response.body();
                for (Get get : gets) {
                    arr_division.add(get.getId());
                }
            }

            @Override
            public void onFailure(Call<List<Get>> call, Throwable t) {
                trad_textView.setText((t.getMessage()));
                super_textView.setText((t.getMessage()));
            }
        });
    }

    private void getCategoryList(final ArrayList<String> arr_division) {
        arr_division.add("대분류 선택");
        Call<List<Get>> call = jsonParserRetrofit.getDatas("categoryList", "null", "null", "null", "null");
        call.enqueue(new Callback<List<Get>>() {
            @Override
            public void onResponse(Call<List<Get>> call, Response<List<Get>> response) {
                if (!response.isSuccessful()) {
                    trad_textView.setText("Code: " + response.code());
                    super_textView.setText("Code: " + response.code());
                    return;
                }
                List<Get> gets = response.body();
                for (Get get : gets) {
                    arr_division.add(get.getCategory());
                }
            }

            @Override
            public void onFailure(Call<List<Get>> call, Throwable t) {
                trad_textView.setText((t.getMessage()));
                super_textView.setText((t.getMessage()));
            }
        });
    }

    private void getDatas(String category, String id, final String tag, String week, final String market, final TextView textView) {
        Call<List<Get>> call = jsonParserRetrofit.getDatas(category, id, tag, week, market);
        call.enqueue(new Callback<List<Get>>() {
            @Override
            public void onResponse(Call<List<Get>> call, Response<List<Get>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code: " + response.code());
                    return;
                }
                List<Get> gets = response.body();
                print_market(tag, market, textView);

                for (Get get : gets) {
                    String content = "";
                    content += "Category: " + get.getCategory() + "\n";
                    content += "Id: " + get.getId() + "\n";
                    content += "Unit: " + get.getUnit() + "\n";
                    content += "P: " + return_p(get.getP()) + "\n\n";
                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Get>> call, Throwable t) {
                textView.setText((t.getMessage()));
            }
        });
    }

    public void print_market(String tag, String market, TextView textView) {
        if(tag.equals("tradition"))
            textView.append(trad_map.get(market) + "\n");
        else
            textView.append(super_map.get(market) + "\n");
    }

    public String return_p(int p) {
        if(p == 0)
            return "일시품절";
        else if(p == -1)
            return "비매";
        else if(p == -2)
            return "품절";
        else return Integer.toString(p);
    }

    /*public void onM_map_btn_clicked(View view) {
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(intent);
    }*/

    public void search(String category, String id, String tag, String week, String [] value_list, TextView textView) { // //순서 바뀌는 거 방지 - synchronized
        value_list = get_check_info(tag);
        for(int i=0; i<value_list.length; i++)
            getDatas(category, id, tag, week, value_list[i], textView);
    }

    public String [] get_check_info(String tag) {
        SharedPreferences sp = this.getActivity().getSharedPreferences(tag, MODE_PRIVATE);
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

    public void init_map() {
        trad_map.put("p1", "남문시장(중구)");
        trad_map.put("p2", "동구시장(동구)");
        trad_map.put("p3", "서문시장(중구)");
        trad_map.put("p4", "봉덕시장(남구)");
        trad_map.put("p5", "칠성시장(북구)");
        trad_map.put("p6", "수성시장(수성구)");
        trad_map.put("p7", "서남시장(달서구)");
        trad_map.put("p8", "팔달시장(북구)");

        super_map.put("p1", "동아쇼핑");
        super_map.put("p2", "홈플러스 내당점");
        super_map.put("p3", "롯데마트 율하점");
        super_map.put("p4", "롯데슈퍼 수성점");
        super_map.put("p5", "북대구농협 하나로마트");
        super_map.put("p6", "이마트 만촌점");
        super_map.put("p7", "홈플러스 성서점");
        super_map.put("p8", "이마트 칠성점");
    }
}
