package com.example.reversi_free_v10;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StatisticsActivity extends AppCompatActivity implements View.OnClickListener{

    Intent intent;
    GlobalData globalData;
    int simpleLevelWins=0;
    int simpleLevelDraws=0;
    int simpleLevelLosses=0;
    int middleLevelWins=0;
    int middleLevelDraws=0;
    int middleLevelLosses=0;
    int hardLevelWins=0;
    int hardLevelDraws=0;
    int hardLevelLosses=0;
    int simpleScore=0;
    int middleScore=0;
    int hardScore=0;
    double wins=0;
    double losses=0;
    double winRate=0;
    private boolean isSoundOn;
    private boolean isScreenTransitions;
    private boolean isHideStatusBar;
    TextView statistics;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.fade);
//        getWindow().setExitTransition(explode);
//        getWindow().setEnterTransition(explode);
//        getWindow().setReenterTransition(explode);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_layout);

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

        SharedPreferences sharedPreferences=getSharedPreferences("statistics",MODE_PRIVATE);

        statistics=(TextView)findViewById(R.id.statistics);
        Button back=(Button)findViewById(R.id.back);
        Button reset=(Button)findViewById(R.id.reset);

        simpleLevelWins=sharedPreferences.getInt("simpleLevelWins",0);
        simpleLevelDraws=sharedPreferences.getInt("simpleLevelDraws",0);
        simpleLevelLosses=sharedPreferences.getInt("simpleLevelLosses",0);
        middleLevelWins=sharedPreferences.getInt("middleLevelWins",0);
        middleLevelDraws=sharedPreferences.getInt("middleLevelDraws",0);
        middleLevelLosses=sharedPreferences.getInt("middleLevelLosses",0);
        hardLevelWins=sharedPreferences.getInt("hardLevelWins",0);
        hardLevelDraws=sharedPreferences.getInt("hardLevelDraws",0);
        hardLevelLosses=sharedPreferences.getInt("hardLevelLosses",0);

        simpleScore=2*simpleLevelWins+simpleLevelDraws-simpleLevelLosses;
        middleScore=2*middleLevelWins+middleLevelDraws-middleLevelLosses;
        hardScore=2*hardLevelWins+hardLevelDraws-hardLevelLosses;

        wins=simpleLevelWins+middleLevelWins+hardLevelWins;
        losses=simpleLevelLosses+middleLevelLosses+hardLevelLosses;
        if(wins>losses){
            winRate=wins/(wins+losses)*100;
        }else {
            winRate=0;
        }

        statistics.setText(
                "Level"+"  "+"Wins"+"  "+"Draws"+"  "+"Losses"+"  "+"Score"+"\n" +"\n"
                +"1"+"            "+simpleLevelWins+"           "+simpleLevelDraws+"            "+simpleLevelLosses+"            "+simpleScore+"\n"
                +"2"+"            "+middleLevelWins+"           "+middleLevelDraws+"            "+middleLevelLosses+"            "+middleScore+"\n"
                +"3"+"            "+hardLevelWins+"           "+hardLevelDraws+"            "+hardLevelLosses+"            "+hardScore+"\n"+"\n"
                +"              "+"Win Rate:"+"   "+winRate+"%"+"\n"
        );

        back.setOnClickListener(this);
        reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.back:
                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(this,R.raw.select);
                    player.start();
                }
                intent=new Intent(StatisticsActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.reset:
                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(this,R.raw.select);
                    player.start();
                }
                SharedPreferences.Editor editor=getSharedPreferences("statistics",MODE_PRIVATE).edit();
                editor.putInt("simpleLevelWins",0);
                editor.putInt("simpleLevelDraws",0);
                editor.putInt("simpleLevelLosses",0);
                editor.putInt("middleLevelWins",0);
                editor.putInt("middleLevelDraws",0);
                editor.putInt("middleLevelLosses",0);
                editor.putInt("hardLevelWins",0);
                editor.putInt("hardLevelDraws",0);
                editor.putInt("hardLevelLosses",0);
                editor.apply();
                simpleLevelWins=0;
                simpleLevelDraws=0;
                simpleLevelLosses=0;
                simpleScore=0;
                middleLevelWins=0;
                middleLevelDraws=0;
                middleLevelLosses=0;
                middleScore=0;
                hardLevelWins=0;
                hardLevelDraws=0;
                hardLevelLosses=0;
                hardScore=0;
                winRate=0;
                statistics.setText(
                        "Level"+"  "+"Wins"+"  "+"Draws"+"  "+"Losses"+"  "+"Score"+"\n" +"\n"
                                +"1"+"            "+simpleLevelWins+"           "+simpleLevelDraws+"            "+simpleLevelLosses+"            "+simpleScore+"\n"
                                +"2"+"            "+middleLevelWins+"           "+middleLevelDraws+"            "+middleLevelLosses+"            "+middleScore+"\n"
                                +"3"+"            "+hardLevelWins+"           "+hardLevelDraws+"            "+hardLevelLosses+"            "+hardScore+"\n"+"\n"
                                +"              "+"Win Rate:"+"   "+winRate+"%"+"\n"
                );
                break;
            default:
                break;
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences sharedPreferences=getSharedPreferences("statistics",MODE_PRIVATE);
        simpleLevelWins=sharedPreferences.getInt("simpleLevelWins",0);
        simpleLevelDraws=sharedPreferences.getInt("simpleLevelDraws",0);
        simpleLevelLosses=sharedPreferences.getInt("simpleLevelLosses",0);
        middleLevelWins=sharedPreferences.getInt("middleLevelWins",0);
        middleLevelDraws=sharedPreferences.getInt("middleLevelDraws",0);
        middleLevelLosses=sharedPreferences.getInt("middleLevelLosses",0);
        hardLevelWins=sharedPreferences.getInt("hardLevelWins",0);
        hardLevelDraws=sharedPreferences.getInt("hardLevelDraws",0);
        hardLevelLosses=sharedPreferences.getInt("hardLevelLosses",0);

        simpleScore=2*simpleLevelWins+simpleLevelDraws-simpleLevelLosses;
        middleScore=2*middleLevelWins+middleLevelDraws-middleLevelLosses;
        hardScore=2*hardLevelWins+hardLevelDraws-hardLevelLosses;

        wins=simpleLevelWins+middleLevelWins+hardLevelWins;
        losses=simpleLevelLosses+middleLevelLosses+hardLevelLosses;
        if(wins>losses){
            winRate=wins/(wins+losses)*100;
        }else {
            winRate=0;
        }

        statistics.setText(
                "Level"+"  "+"Wins"+"  "+"Draws"+"  "+"Losses"+"  "+"Score"+"\n" +"\n"
                        +"1"+"            "+simpleLevelWins+"           "+simpleLevelDraws+"            "+simpleLevelLosses+"            "+simpleScore+"\n"
                        +"2"+"            "+middleLevelWins+"           "+middleLevelDraws+"            "+middleLevelLosses+"            "+middleScore+"\n"
                        +"3"+"            "+hardLevelWins+"           "+hardLevelDraws+"            "+hardLevelLosses+"            "+hardScore+"\n"+"\n"
                        +"              "+"Win Rate:"+"   "+winRate+"%"+"\n"
        );
    }
}
