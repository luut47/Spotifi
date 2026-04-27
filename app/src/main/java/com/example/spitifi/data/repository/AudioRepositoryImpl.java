package com.example.spitifi.data.repository;
import android.content.Context;
import android.net.Uri;

import com.example.spitifi.data.api.ConvertRequest;
import com.example.spitifi.data.api.ConvertResponse;
import com.example.spitifi.data.api.RetrofitClient;
import com.example.spitifi.data.api.TikTokApiService;
import com.example.spitifi.domain.repository.AudioRepository;

import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class AudioRepositoryImpl {
    private final TikTokApiService apiService;
    public AudioRepositoryImpl(){
        apiService = RetrofitClient.getApiService();
    }
    public Call<ConvertResponse> convertTikTokUrl(String url, String tag, String title) {
        return apiService.convertTikTokUrlToMp3(new ConvertRequest(url, tag, title));
    }

}
