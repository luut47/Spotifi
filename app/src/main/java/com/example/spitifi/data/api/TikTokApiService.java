package com.example.spitifi.data.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TikTokApiService {
    @POST("api/convert")
    Call<ConvertResponse> convertTikTokUrlToMp3(@Body ConvertRequest request);
}