package com.example.teamproject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonPaser {
    static JSONArray arr;
    public static JSONArray getData() throws ParseException {

        BufferedReader in = null;
        String url = "http://13.58.103.212:8080/api/datas/gets/곡류/*/tradition/last/p1 ";
        String line = "";
        try {
            URL obj_url = new URL(url); // 호출할 url
            HttpURLConnection con = (HttpURLConnection) obj_url.openConnection();

            con.setRequestMethod("GET");

            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));


            line = in.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        JSONParser parser = new JSONParser();
        line = "{\"data\": "+line+"}";
        JSONObject obj = (JSONObject) parser.parse(line);
        arr = (JSONArray) obj.get("data");
        return arr;
    }

}
