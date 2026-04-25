package com.example.spitifi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.spitifi.domain.usecase.ValidateTikTokUrlUseCase;

public class MainActivity extends AppCompatActivity {
    private EditText edtTiktokUrl;
    private Button btnConvert;
    private ValidateTikTokUrlUseCase validateTikTokUrlUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtTiktokUrl = findViewById(R.id.edtTikTokUrl);
        btnConvert =  findViewById(R.id.btnConvert);
        validateTikTokUrlUseCase = new ValidateTikTokUrlUseCase();

        btnConvert.setOnClickListener(v->{
            String url = edtTiktokUrl.getText().toString().trim();
            if(!validateTikTokUrlUseCase.execute(url)){
                Toast.makeText(this,"Link tiktok không hợp lệ",Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this,"Link hợp lệ, bắt đầu xử lý...",Toast.LENGTH_SHORT).show();
        });


    }
}