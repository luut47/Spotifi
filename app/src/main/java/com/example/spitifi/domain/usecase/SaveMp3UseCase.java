package com.example.spitifi.domain.usecase;

import android.content.Context;
import com.example.spitifi.core.constants.AppConstants;
import com.example.spitifi.core.utils.FileUtils;

public class SaveMp3UseCase {
    private final Context context;

    public SaveMp3UseCase(Context context) {
        this.context = context;
    }

    public void execute(String mp3Path, String fileName, String title) {
        String fullUrl = AppConstants.SERVER_URL + mp3Path;
        FileUtils.downloadMp3(context, fullUrl, fileName, title);
    }
}
