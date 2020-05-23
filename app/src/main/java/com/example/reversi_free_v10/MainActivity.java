package com.example.reversi_free_v10;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.ActivityOptions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    private GlobalData globalData;
    private boolean isSoundOn;
    private boolean isScreenTransitions;
    private boolean isHideStatusBar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        globalData=(GlobalData)getApplication();
        isSoundOn=globalData.isSoundOn();
        isScreenTransitions=globalData.isScreenTransitions();
        isHideStatusBar=globalData.isHideStatusBar();
        if (isHideStatusBar) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // 全屏显示，隐藏状态栏和导航栏，拉出状态栏和导航栏显示一会儿后消失。
                    getWindow().getDecorView().setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                } else {
                    // 全屏显示，隐藏状态栏
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
                }
            }
        }

        Button singlePlayer = (Button) findViewById(R.id.single_player);
        Button twoPlayer = (Button) findViewById(R.id.two_player);
        Button options = (Button) findViewById(R.id.options);
        Button statistics = (Button) findViewById(R.id.statistics);
        singlePlayer.setOnClickListener(this);
        twoPlayer.setOnClickListener(this);
        options.setOnClickListener(this);
        statistics.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.single_player:
                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(this,R.raw.select);
                    player.start();
                }
                intent = new Intent(MainActivity.this, ModeSelectionActivity.class);
                startActivity(intent);
                break;
            case R.id.two_player:
                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(this,R.raw.select);
                    player.start();
                }
                intent = new Intent(MainActivity.this, TwoPlayerActivity.class);
                startActivity(intent);
                break;
            case R.id.options:
                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(this,R.raw.select);
                    player.start();
                }
                intent = new Intent(MainActivity.this, OptionsActivity.class);
                startActivity(intent);
                break;
            case R.id.statistics:
                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(this,R.raw.select);
                    player.start();
                }
                intent = new Intent(MainActivity.this, StatisticsActivity.class);
                startActivity(intent);
//                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            default:
                break;
        }
    }

    public void onResume(){
        super.onResume();
        isSoundOn=globalData.isSoundOn();
        isScreenTransitions=globalData.isScreenTransitions();
        isHideStatusBar=globalData.isHideStatusBar();
        if (isHideStatusBar) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // 全屏显示，隐藏状态栏和导航栏，拉出状态栏和导航栏显示一会儿后消失。
                    getWindow().getDecorView().setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                } else {
                    // 全屏显示，隐藏状态栏
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
                }
            }
        }
    }
}
