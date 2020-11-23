package com.example.teamproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface JsonParserRetrofit {

    @GET("{class}/{id}/{tag}/{week}/{place}/")   //relativeUrl
//    @GET("api/datas/gets/{class}/{id}/{tag}/{week}/{place}");
    //Call<List<Get>> getDatas();
    Call<List<Get>> getDatas(
            @Path("class") String userClass,
            @Path("id") String userId,
            @Path("tag") String userTag,
            @Path("week") String userWeek,
            @Path("place") String userPlace
    );

}
