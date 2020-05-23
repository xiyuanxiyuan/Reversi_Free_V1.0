package com.example.reversi_free_v10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OptionsActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    private GlobalData globalData;
    private int boardSort;
    private int pieceSort;
    private boolean isSoundOn;
    private boolean isScreenTransitions;
    private boolean isHideStatusBar;
    private boolean isShowLegalMoves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_layout);

        globalData = (GlobalData) getApplication();
        boardSort = globalData.getBoardSort();
        pieceSort = globalData.getPieceSort();
        isSoundOn = globalData.isSoundOn();
        isScreenTransitions = globalData.isScreenTransitions();
        isHideStatusBar = globalData.isHideStatusBar();
        isShowLegalMoves = globalData.isShowLegalMoves();

        Button lastBoard = (Button) findViewById(R.id.last_board);
        final ImageView boardImage = (ImageView) findViewById(R.id.board_image);
        Button nextBoard = (Button) findViewById(R.id.next_board);

        Button lastPiece = (Button) findViewById(R.id.last_piece);
        final ImageView pieceImage = (ImageView) findViewById(R.id.piece_image);
        Button nextPiece = (Button) findViewById(R.id.next_piece);

        final CheckBox soundOn = (CheckBox) findViewById(R.id.sound_on);
        final CheckBox screenTransitions = (CheckBox) findViewById(R.id.screen_transitions);
        final CheckBox hideStatusBar = (CheckBox) findViewById(R.id.hide_status_bar);
        final CheckBox showLegalMoves = (CheckBox) findViewById(R.id.show_legal_moves);

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(this);

        lastBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(getApplicationContext(),R.raw.select);
                    player.start();
                }
                boardSort--;
                if (boardSort <= 0) {
                    boardSort = 6;
                }
                switch (boardSort) {
                    case 1:
                        boardImage.setImageResource(R.drawable.button_board_01);
                        break;
                    case 2:
                        boardImage.setImageResource(R.drawable.button_board_02);
                        break;
                    case 3:
                        boardImage.setImageResource(R.drawable.button_board_03);
                        break;
                    case 4:
                        boardImage.setImageResource(R.drawable.button_board_04);
                        break;
                    case 5:
                        boardImage.setImageResource(R.drawable.button_board_05);
                        break;
                    case 6:
                        boardImage.setImageResource(R.drawable.button_board_06);
                        break;
                    default:
                        boardImage.setImageResource(R.drawable.button_board_01);
                        break;
                }
                globalData.setBoardSort(boardSort);
            }
        });

        switch (boardSort) {
            case 1:
                boardImage.setImageResource(R.drawable.button_board_01);
                break;
            case 2:
                boardImage.setImageResource(R.drawable.button_board_02);
                break;
            case 3:
                boardImage.setImageResource(R.drawable.button_board_03);
                break;
            case 4:
                boardImage.setImageResource(R.drawable.button_board_04);
                break;
            case 5:
                boardImage.setImageResource(R.drawable.button_board_05);
                break;
            case 6:
                boardImage.setImageResource(R.drawable.button_board_06);
                break;
            default:
                boardImage.setImageResource(R.drawable.button_board_01);
                break;
        }

        nextBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(getApplicationContext(),R.raw.select);
                    player.start();
                }
                boardSort++;
                if (boardSort >= 7) {
                    boardSort = 1;
                }
                switch (boardSort) {
                    case 1:
                        boardImage.setImageResource(R.drawable.button_board_01);
                        break;
                    case 2:
                        boardImage.setImageResource(R.drawable.button_board_02);
                        break;
                    case 3:
                        boardImage.setImageResource(R.drawable.button_board_03);
                        break;
                    case 4:
                        boardImage.setImageResource(R.drawable.button_board_04);
                        break;
                    case 5:
                        boardImage.setImageResource(R.drawable.button_board_05);
                        break;
                    case 6:
                        boardImage.setImageResource(R.drawable.button_board_06);
                        break;
                    default:
                        boardImage.setImageResource(R.drawable.button_board_01);
                        break;
                }
                globalData.setBoardSort(boardSort);
            }
        });

        lastPiece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(getApplicationContext(),R.raw.select);
                    player.start();
                }
                pieceSort--;
                if (pieceSort <= 0) {
                    pieceSort = 2;
                }
                switch (pieceSort) {
                    case 1:
                        pieceImage.setImageResource(R.drawable.button_pieces_01);
                        break;
                    case 2:
                        pieceImage.setImageResource(R.drawable.button_pieces_02);
                        break;
                    default:
                        pieceImage.setImageResource(R.drawable.button_pieces_01);
                        break;
                }
                globalData.setPieceSort(pieceSort);
            }
        });

        switch (pieceSort) {
            case 1:
                pieceImage.setImageResource(R.drawable.button_pieces_01);
                break;
            case 2:
                pieceImage.setImageResource(R.drawable.button_pieces_02);
                break;
            default:
                pieceImage.setImageResource(R.drawable.button_pieces_01);
                break;
        }

        nextPiece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(getApplicationContext(),R.raw.select);
                    player.start();
                }
                pieceSort++;
                if (pieceSort >= 3) {
                    pieceSort = 1;
                }
                switch (pieceSort) {
                    case 1:
                        pieceImage.setImageResource(R.drawable.button_pieces_01);
                        break;
                    case 2:
                        pieceImage.setImageResource(R.drawable.button_pieces_02);
                        break;
                    default:
                        pieceImage.setImageResource(R.drawable.button_pieces_01);
                        break;
                }
                globalData.setPieceSort(pieceSort);
            }
        });

        soundOn.setChecked(isSoundOn);
        screenTransitions.setChecked(isScreenTransitions);
        hideStatusBar.setChecked(isHideStatusBar);
        showLegalMoves.setChecked(isShowLegalMoves);

        soundOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (soundOn.isChecked()) {
                    isSoundOn = true;
                } else {
                    isSoundOn = false;
                }

                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(getApplicationContext(),R.raw.select);
                    player.start();
                }
            }
        });

        screenTransitions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (screenTransitions.isChecked()) {
                    isScreenTransitions = true;
                } else {
                    isScreenTransitions = false;
                }

                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(getApplicationContext(),R.raw.select);
                    player.start();
                }
            }
        });


        hideStatusBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (hideStatusBar.isChecked()) {
                    isHideStatusBar = true;
                } else {
                    isHideStatusBar = false;
                }

                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(getApplicationContext(),R.raw.select);
                    player.start();
                }

                if (hideStatusBar.isChecked()) {
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
                }else {
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                }
            }
        });

        showLegalMoves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (showLegalMoves.isChecked()) {
                    isShowLegalMoves = true;
                } else {
                    isShowLegalMoves = false;
                }

                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(getApplicationContext(),R.raw.select);
                    player.start();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(getApplicationContext(),R.raw.select);
                    player.start();
                }

                globalData.setSoundOn(isSoundOn);
                globalData.setScreenTransitions(isScreenTransitions);
                globalData.setHideStatusBar(isHideStatusBar);
                globalData.setShowLegalMoves(isShowLegalMoves);
                intent = new Intent(OptionsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
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
