package com.example.spitifi.domain.usecase;

import com.example.spitifi.data.api.ConvertResponse;
import com.example.spitifi.data.repository.AudioRepositoryImpl;

import java.io.File;
import retrofit2.Call;
public class ExtractAudioUseCase {
    private final AudioRepositoryImpl  audioRepository;
    public ExtractAudioUseCase(){
        audioRepository = new AudioRepositoryImpl();
    }
    public Call<ConvertResponse> execute(String url, String tag, String title) {
        return audioRepository.convertTikTokUrl(url, tag, title);
    }
}
