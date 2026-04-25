package com.example.spitifi.core.utils;

public class UrlUtils {
    public static boolean isTikTokURL(String inputURL){
        if(inputURL == null || inputURL.trim().isEmpty()) return false;
        String changeInputUrlToLowercase = inputURL.trim().toLowerCase();
        return changeInputUrlToLowercase.contains("tiktok.com")
                || changeInputUrlToLowercase.contains("vt.tiktok.com")
                || changeInputUrlToLowercase.contains("vm.tiktok.com");
    }
}
