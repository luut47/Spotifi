package com.example.spitifi;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spitifi.data.api.ConvertResponse;
import com.example.spitifi.domain.usecase.ExtractAudioUseCase;
import com.example.spitifi.domain.usecase.SaveMp3UseCase;
import com.example.spitifi.domain.usecase.ValidateTikTokUrlUseCase;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText edtTiktokUrl;
    private Button btnConvert;
    private Spinner spinnerTag;

    private ValidateTikTokUrlUseCase validateTikTokUrlUseCase;
    private ExtractAudioUseCase extractAudioUseCase;
    private SaveMp3UseCase saveMp3UseCase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtTiktokUrl = findViewById(R.id.edtTikTokUrl);
        btnConvert = findViewById(R.id.btnConvert);
        spinnerTag = findViewById(R.id.spinnerTag);

        validateTikTokUrlUseCase = new ValidateTikTokUrlUseCase();
        extractAudioUseCase = new ExtractAudioUseCase();
        saveMp3UseCase = new SaveMp3UseCase(this);

        String[] tags = {"audio", "truyện ngắn", "nhạc"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                tags
        );
        spinnerTag.setAdapter(adapter);

        btnConvert.setOnClickListener(v -> {
            String url = edtTiktokUrl.getText().toString().trim();

            if (!validateTikTokUrlUseCase.execute(url)) {
                Toast.makeText(this, "Link TikTok không hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }

            String selectedTag = spinnerTag.getSelectedItem().toString();
            String title = "TikTok Audio";

            Toast.makeText(this, "Đang chuyển TikTok sang MP3...", Toast.LENGTH_SHORT).show();

            extractAudioUseCase.execute(url, selectedTag, title).enqueue(new Callback<ConvertResponse>() {
                @Override
                public void onResponse(Call<ConvertResponse> call, Response<ConvertResponse> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().success) {
                        ConvertResponse data = response.body();

                        Toast.makeText(MainActivity.this,
                                "Chuyển thành công: " + data.fileName,
                                Toast.LENGTH_SHORT).show();

                        // Tải file mp3 về máy
                        saveMp3UseCase.execute(data.mp3Url, data.fileName, data.title);

                        // Sau bước này:
                        // 1. tải file mp3 từ data.mp3Url
                        // 2. tải ảnh thumbnail nếu backend trả về thumbnailUrl
                        // 3. lưu vào Room AudioEntity
                        // 4. load lại list trong máy
                    } else {
                        String errorMsg = "Lỗi từ server: " + response.code();
                        if (response.errorBody() != null) {
                            try {
                                errorMsg += " - " + response.errorBody().string();
                            } catch (Exception e) { e.printStackTrace(); }
                        }
                        Toast.makeText(MainActivity.this, "Chuyển thất bại: " + errorMsg, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ConvertResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this,
                            "Lỗi kết nối backend: " + t.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}