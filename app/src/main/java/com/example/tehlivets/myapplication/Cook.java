package com.example.tehlivets.myapplication;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Tehlivets on 29/06/16.
 */
public class Cook extends Activity {
    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;
    private TextView remainedSec;
    private CountDownTimer timer;
    private Button start;
    private Button stop;
    private Button timerStop;
    private EditText getMinuts;
    private long totalTimeCountInMilliseconds;
    private long timeBlinkInMilliseconds;
    private boolean blink;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook);
        remainedSec = (TextView) findViewById(R.id.remained_text_view_sec);
        getMinuts = (EditText) findViewById(R.id.timerEdit);
        start = (Button) findViewById(R.id.buttonStart);
        stop = (Button) findViewById(R.id.buttonStop);
        stop.setClickable(false);
        timerStop = (Button) findViewById(R.id.timer_stop);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();
        setShakeListener();

    }

    private void setShakeListener() {
        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {
            public void onShake() {
                getMinuts.setText("");
            }
        });
    }

    private boolean setTimer() {
        int time = 0;
        if (!getMinuts.getText().toString().equals("")) {
            try {
                time = Integer.parseInt(getMinuts.getText().toString());
            } catch (NumberFormatException e) {
                showToast("Please Enter Minutes...");
                return false;
            }
        } else {
            showToast("Please Enter Minutes...");
            return false;
        }
        totalTimeCountInMilliseconds = 60 * time * 1000;
        timeBlinkInMilliseconds = 30 * 1000;
        return true;
    }

    public void onClickStart(View view) {
        stop.setClickable(true);
        start.setClickable(false);
        if (setTimer()) startTimer();
    }

    private void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void onClickStop(View view) {
        start.setClickable(true);
        remainedSec.setText("00:00");
        timer.cancel();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    private void startTimer() {
        timer = new CountDownTimer(totalTimeCountInMilliseconds, 1000) {

            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                if (leftTimeInMilliseconds < timeBlinkInMilliseconds) {
                    remainedSec.setTextAppearance(getApplicationContext(),
                            R.style.blinkText);
                    if (blink) {
                        remainedSec.setVisibility(View.VISIBLE);
                    } else {
                        remainedSec.setVisibility(View.INVISIBLE);
                    }
                    blink = !blink;
                }
                remainedSec.setText(String.format("%02d", seconds / 60)
                        + ":" + String.format("%02d", seconds % 60));
            }

            @Override
            public void onFinish() {
                remainedSec.setText("Time up!");
                remainedSec.setVisibility(View.VISIBLE);
                remainedSec.setVisibility(View.VISIBLE);
                remainedSec.setVisibility(View.GONE);
                remainedSec.setVisibility(View.VISIBLE);
                timerStop.setVisibility(View.VISIBLE);
                playSound();
            }
        }.start();
    }

    private void playSound() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
        mediaPlayer.start();
    }


    public void stopSound(View view) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        timerStop.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }
}

