package com.example.teamproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface JsonParserRetrofit {


    @GET("{category}/{id}/{tag}/{week}/{place}/")   //relativeUrl
    Call<List<Get>> getDatas(
            @Path("category") String userClass,
            @Path("id") String userId,
            @Path("tag") String userTag,
            @Path("week") String userWeek,
            @Path("place") String userPlace
    );
}