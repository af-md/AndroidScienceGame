package com.example.cellgame;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MediaPlayer gameBackgroundMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameBackgroundMusic = MediaPlayer.create(this, R.raw.game_sound);
        gameBackgroundMusic.start();
    }
}