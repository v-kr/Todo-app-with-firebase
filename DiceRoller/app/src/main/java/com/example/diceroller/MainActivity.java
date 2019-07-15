package com.example.diceroller;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ImageView dice_picture;
    Random rng=new Random();
    SoundPool dice_sound;
    int sound_id;
    Handler handler;
    Timer timer=new Timer();
    boolean rolling=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Our function to initialise sound playing
        InitSound();
        //Get a reference to image widget
        dice_picture = (ImageView) findViewById(R.id.imageView);
        dice_picture.setOnClickListener(new HandleClick());
        //link handler to callback
        handler=new Handler(callback);
    }
    private class HandleClick implements View.OnClickListener {
        public void onClick(View arg0) {
            if (!rolling) {
                rolling = true;
                dice_picture.setImageResource(R.drawable.die);
                dice_sound.play(sound_id, 1.0f, 1.0f, 0, 0, 1.0f);
                timer.schedule(new Roll(), 400);
            }
        }
    }

    void InitSound() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes aa = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            dice_sound= new SoundPool.Builder().setAudioAttributes(aa).build();

        } else {
            dice_sound=PreLollipopSoundPool.NewSoundPool();
        }
        sound_id=dice_sound.load(this,R.raw.one,1);
    }

    class Roll extends TimerTask {
        public void run() {
            handler.sendEmptyMessage(0);
        }
    }

    Handler.Callback callback = new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            switch(rng.nextInt(6)+1) {
                case 1:
                    dice_picture.setImageResource(R.drawable.one);
                    break;
                case 2:
                    dice_picture.setImageResource(R.drawable.two);
                    break;
                case 3:
                    dice_picture.setImageResource(R.drawable.three);
                    break;
                case 4:
                    dice_picture.setImageResource(R.drawable.four);
                    break;
                case 5:
                    dice_picture.setImageResource(R.drawable.five);
                    break;
                case 6:
                    dice_picture.setImageResource(R.drawable.six);
                    break;
                default:
            }
            rolling=false;
            return true;
        }
    };

    protected void onPause() {
        super.onPause();
        dice_sound.pause(sound_id);
    }
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}