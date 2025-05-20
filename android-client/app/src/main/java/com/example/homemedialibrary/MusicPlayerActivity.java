package com.example.homemedialibrary;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MusicPlayerActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://10.0.2.2:8081/api/values/";

    private MediaPlayer mediaPlayer;
    private TextView trackName;
    private ImageButton pauseButton, resumeButton, stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        trackName = findViewById(R.id.track_name);
        pauseButton = findViewById(R.id.button_pause);
        resumeButton = findViewById(R.id.button_resume);
        stopButton = findViewById(R.id.button_stop);

        String filename = getIntent().getStringExtra("filename");
        String name = getIntent().getStringExtra("name");

        if (filename == null || filename.isEmpty()) {
            Toast.makeText(this, "Файл не найден", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        trackName.setText("🎵 " + (name != null ? name : "Музыка"));

        Uri audioUri = Uri.parse(BASE_URL + filename);
        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(audioUri.toString());
            mediaPlayer.setOnPreparedListener(mp -> mediaPlayer.start());
            mediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ошибка воспроизведения", Toast.LENGTH_SHORT).show();
            finish();
        }

        pauseButton.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        });

        resumeButton.setOnClickListener(v -> {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        });

        stopButton.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}