package com.example.reversi_free_v10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ModeSelectionActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    private int playMode=1;
    private int difficulty=1;
    private GlobalData globalData;
    private int pieceSort;
    private boolean isSoundOn;
    private boolean isScreenTransitions;
    private boolean isHideStatusBar;
    Button exchangePerMatch;
    Button playAsBlack;
    Button playAsWhite;
    Button simple;
    Button middle;
    Button hard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_selection_layout);

        globalData=(GlobalData)getApplication();
        pieceSort=globalData.getPieceSort();
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

        exchangePerMatch = (Button) findViewById(R.id.exchange_per_match);
        playAsBlack = (Button) findViewById(R.id.play_as_black);
        playAsWhite = (Button) findViewById(R.id.play_as_white);
        exchangePerMatch.setOnClickListener(this);
        playAsBlack.setOnClickListener(this);
        playAsWhite.setOnClickListener(this);

        switch (pieceSort){
            case 1:
                exchangePerMatch.setBackgroundResource(R.drawable.button_playas_alt_01_h);
                playAsBlack.setBackgroundResource(R.drawable.button_playas_black_01);
                playAsWhite.setBackgroundResource(R.drawable.button_playas_white_01);
                break;
            case 2:
                exchangePerMatch.setBackgroundResource(R.drawable.button_playas_alt_02_h);
                playAsBlack.setBackgroundResource(R.drawable.button_playas_black_02);
                playAsWhite.setBackgroundResource(R.drawable.button_playas_white_02);
                break;
            default:break;
        }

        simple = (Button) findViewById(R.id.simple);
        middle = (Button) findViewById(R.id.middle);
        hard = (Button) findViewById(R.id.hard);
        simple.setOnClickListener(this);
        middle.setOnClickListener(this);
        hard.setOnClickListener(this);


        Button play = (Button) findViewById(R.id.play);
        Button back = (Button) findViewById(R.id.back);
        play.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.exchange_per_match:
                playMode=1;
                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(this,R.raw.select);
                    player.start();
                }
                switch (pieceSort){
                    case 1:
                        exchangePerMatch.setBackgroundResource(R.drawable.button_playas_alt_01_h);
                        playAsBlack.setBackgroundResource(R.drawable.button_playas_black_01);
                        playAsWhite.setBackgroundResource(R.drawable.button_playas_white_01);
                        break;
                    case 2:
                        exchangePerMatch.setBackgroundResource(R.drawable.button_playas_alt_02_h);
                        playAsBlack.setBackgroundResource(R.drawable.button_playas_black_02);
                        playAsWhite.setBackgroundResource(R.drawable.button_playas_white_02);
                        break;
                    default:break;
                }
                break;
            case R.id.play_as_black:
                playMode=2;
                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(this,R.raw.select);
                    player.start();
                }
                switch (pieceSort){
                    case 1:
                        exchangePerMatch.setBackgroundResource(R.drawable.button_playas_alt_01);
                        playAsBlack.setBackgroundResource(R.drawable.button_playas_black_01_h);
                        playAsWhite.setBackgroundResource(R.drawable.button_playas_white_01);
                        break;
                    case 2:
                        exchangePerMatch.setBackgroundResource(R.drawable.button_playas_alt_02);
                        playAsBlack.setBackgroundResource(R.drawable.button_playas_black_02_h);
                        playAsWhite.setBackgroundResource(R.drawable.button_playas_white_02);
                        break;
                    default:break;
                }
                break;
            case R.id.play_as_white:
                playMode=3;
                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(this,R.raw.select);
                    player.start();
                }
                switch (pieceSort){
                    case 1:
                        exchangePerMatch.setBackgroundResource(R.drawable.button_playas_alt_01);
                        playAsBlack.setBackgroundResource(R.drawable.button_playas_black_01);
                        playAsWhite.setBackgroundResource(R.drawable.button_playas_white_01_h);
                        break;
                    case 2:
                        exchangePerMatch.setBackgroundResource(R.drawable.button_playas_alt_02);
                        playAsBlack.setBackgroundResource(R.drawable.button_playas_black_02);
                        playAsWhite.setBackgroundResource(R.drawable.button_playas_white_02_h);
                        break;
                    default:break;
                }
                break;
            case R.id.simple:
                difficulty=1;
                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(this,R.raw.select);
                    player.start();
                }
                simple.setBackgroundResource(R.drawable.button_1_h);
                middle.setBackgroundResource(R.drawable.button_2);
                hard.setBackgroundResource(R.drawable.button_3);
                break;
            case R.id.middle:
                difficulty=2;
                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(this,R.raw.select);
                    player.start();
                }
                simple.setBackgroundResource(R.drawable.button_1);
                middle.setBackgroundResource(R.drawable.button_2_h);
                hard.setBackgroundResource(R.drawable.button_3);
                break;
            case R.id.hard:
                difficulty=3;
                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(this,R.raw.select);
                    player.start();
                }
                simple.setBackgroundResource(R.drawable.button_1);
                middle.setBackgroundResource(R.drawable.button_2);
                hard.setBackgroundResource(R.drawable.button_3_h);
                break;
            case R.id.play:
                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(this,R.raw.select);
                    player.start();
                }
                intent =new Intent(ModeSelectionActivity.this,SinglePlayerActivity.class);
                intent.putExtra("playMode",playMode);
                intent.putExtra("difficulty",difficulty);
                startActivity(intent);
                break;
            case R.id.back:
                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(this,R.raw.select);
                    player.start();
                }
                intent=new Intent(ModeSelectionActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }


}

