package com.example.spitifi.domain.usecase;
import com.example.spitifi.core.utils.UrlUtils;
public class ValidateTikTokUrlUseCase {
    public boolean execute(String url){
        return UrlUtils.isTikTokURL(url);
    }
}
