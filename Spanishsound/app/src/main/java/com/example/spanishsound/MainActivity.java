package com.example.spanishsound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private SoundPool soundPool;
private int sound1,sound2,sound3,sound4,sound5,sound6,sound7,sound8,sound9,sound10;
Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=findViewById(R.id.btn1);
        b2=findViewById(R.id.btn2);
        b3=findViewById(R.id.btn3);
        b4=findViewById(R.id.btn4);
        b5=findViewById(R.id.btn5);
        b6=findViewById(R.id.btn6);
        b7=findViewById(R.id.btn7);
        b8=findViewById(R.id.btn8);
        b9=findViewById(R.id.btn9);
        b10=findViewById(R.id.btn10);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool =new SoundPool.Builder()
                    .setMaxStreams(8)
                    .setAudioAttributes(audioAttributes).build();
        } else {
            soundPool = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
        }
        sound1 = soundPool.load(this, R.raw.one, 1);
        sound2 = soundPool.load(this, R.raw.two, 1);
        sound3 = soundPool.load(this, R.raw.three, 1);
        sound4 = soundPool.load(this, R.raw.four, 1);
        sound5 = soundPool.load(this, R.raw.five, 1);
        sound6 = soundPool.load(this, R.raw.six, 1);
        sound7 = soundPool.load(this, R.raw.seven, 1);
        sound8 = soundPool.load(this, R.raw.eight, 1);
    }

    public void playsound(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                soundPool.play(sound1,1,1,0,0,1);
                break;
            case R.id.btn2:
                soundPool.play(sound2,1,1,0,0,1);
                break;
            case R.id.btn3:
                soundPool.play(sound3,1,1,0,0,1);
                break;
            case R.id.btn4:
                soundPool.play(sound4,1,1,0,0,1);
                break;
            case R.id.btn5:
                soundPool.play(sound5,1,1,0,0,1);
                break;
            case R.id.btn6:
                soundPool.play(sound6,1,1,0,0,1);
                break;
            case R.id.btn7:
                soundPool.play(sound7,1,1,0,0,1);
                break;
            case R.id.btn8:
                soundPool.play(sound8,1,1,0,0,1);
                break;
            case R.id.btn9:
                soundPool.play(sound8,1,1,0,0,1);
                break;
            case R.id.btn10:
                soundPool.play(sound8,1,1,0,0,1);
                break;
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}

