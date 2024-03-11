package com.nvc.lab5_music;

import androidx.appcompat.app.AppCompatActivity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnStream, btnStop;
    EditText etURL;
    Boolean isStreaming = false;
    MediaPlayer mediaPlayer;
    Spinner sp_music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> Music = new ArrayList<String>();
        Music.add("Music 1");
        Music.add("Music 2");
        Music.add("Music 3");

        sp_music = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,Music);
        sp_music.setAdapter(adapter);






        btnStream = findViewById(R.id.btnStream);
        btnStop = findViewById(R.id.btnPause);

        btnStream.setOnClickListener(view -> {
            String url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";
            if (sp_music.getSelectedItemPosition() == 0)
            {
                url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";
            }
            else  if (sp_music.getSelectedItemPosition() == 1)
            {
                url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3";
            }
            else {
                url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3";
            }


            new MediaPlayerAsyncTask().execute(url);
        });

        btnStop.setOnClickListener(view -> stopPlaying());
    }

    private void startAudioStream(String url) {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.setVolume(1f, 1f);
            mediaPlayer.setLooping(false);
            mediaPlayer.start();
        } catch (Exception e) {
            Log.d("mylog", "Error playing in SoundHandler: " + e.toString());
        }
    }

    private void stopPlaying() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.start();
            }
        }
    }


    private class MediaPlayerAsyncTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... urls) {
            String mp3Url = urls[0];
            startAudioStream(mp3Url);
            return null;
        }
    }
}