package com.example.homemedialibrary;

import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoPlayerActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://10.0.2.2:8081/api/values/";
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout rootLayout = new FrameLayout(this);

        videoView = new VideoView(this);
        rootLayout.addView(videoView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        ));

        Button exitButton = new Button(this);
        exitButton.setText("← Назад");
        FrameLayout.LayoutParams buttonParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        buttonParams.gravity = Gravity.TOP | Gravity.START;
        buttonParams.setMargins(32, 32, 0, 0);
        exitButton.setLayoutParams(buttonParams);
        exitButton.setOnClickListener(v -> finish()); // Закрыть Activity и вернуться назад

        rootLayout.addView(exitButton);
        setContentView(rootLayout);

        String filename = getIntent().getStringExtra("filename");
        if (filename == null || filename.isEmpty()) {
            Toast.makeText(this, "Видеофайл не найден", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        String videoUrl = BASE_URL + filename;
        videoView.setVideoURI(Uri.parse(videoUrl));
        videoView.setMediaController(new MediaController(this));
        videoView.setOnPreparedListener(mp -> videoView.start());
        videoView.setOnErrorListener((mp, what, extra) -> {
            Toast.makeText(this, "Ошибка воспроизведения", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}