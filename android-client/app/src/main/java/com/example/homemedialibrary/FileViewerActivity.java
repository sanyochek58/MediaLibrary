package com.example.homemedialibrary;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FileViewerActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://10.0.2.2:8081/api/values/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String filename = getIntent().getStringExtra("filename");
        if (filename == null || filename.isEmpty()) {
            Toast.makeText(this, "Файл не найден", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        String fileUrl = BASE_URL + filename;

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(fileUrl));
        startActivity(intent);

        finish();
    }
}