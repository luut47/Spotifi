package com.example.spitifi.domain.usecase;
import com.arthenica.ffmpegkit.FFmpegKit;
import com.arthenica.ffmpegkit.ReturnCode;

public class ExtractAudioUseCase {
    public interface Callback{
        void onSuccess(String mp3Path);
        void onError(String error);
    }
    private void execute(String videoPath, String mp3Path, Callback callback){
        String cmd = "-i \"" + videoPath + "\" -vn -ar 44100 -ac 2 -b:a 192k \"" + outputPath + "\"";
        FFmpegKit.executeAsync(cmd, session -> {
            if (ReturnCode.isSuccess(session.getReturnCode())) {
                callback.onSuccess(outputPath);
            } else {
                callback.onError("Convert lỗi");
            }
        });
    }
}
