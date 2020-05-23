package com.example.reversi_free_v10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SinglePlayerActivity extends AppCompatActivity{

    private List<BoardGrid> boardGridList = new ArrayList<>();
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
        setContentView(R.layout.single_player_layout);

        ImageView boardBackground=(ImageView)findViewById(R.id.board_background);
        Button undo=(Button)findViewById(R.id.undo);
        TextView blackPieces=(TextView)findViewById(R.id.single_mode_black_pieces);
        TextView whitePieces=(TextView)findViewById(R.id.single_mode_white_pieces);

        globalData=(GlobalData)getApplication();
        boardSort=globalData.getBoardSort();
        pieceSort=globalData.getPieceSort();
        isSoundOn=globalData.isSoundOn();
        isScreenTransitions=globalData.isScreenTransitions();
        isHideStatusBar=globalData.isHideStatusBar();
        isShowLegalMoves=globalData.isShowLegalMoves();
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

        switch (boardSort){
            case 1:boardBackground.setImageResource(R.drawable.board_01_hd);break;
            case 2:boardBackground.setImageResource(R.drawable.board_02_hd);break;
            case 3:boardBackground.setImageResource(R.drawable.board_03_hd);break;
            case 4:boardBackground.setImageResource(R.drawable.board_04_hd);break;
            case 5:boardBackground.setImageResource(R.drawable.board_05_hd);break;
            case 6:boardBackground.setImageResource(R.drawable.board_06_hd);break;
            default:boardBackground.setImageResource(R.drawable.board_01_hd);break;
        }

        Intent intent = getIntent();
        int playMode = intent.getIntExtra("playMode", 1);
        int difficulty = intent.getIntExtra("difficulty", 1);

        initBoardGrids();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.board);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 8) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        recyclerView.setLayoutManager(gridLayoutManager);
        final BoardAdapterAI adapter = new BoardAdapterAI(this,boardGridList, playMode, difficulty,pieceSort,isSoundOn,isShowLegalMoves,blackPieces,whitePieces);
        recyclerView.setAdapter(adapter);

        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isSoundOn){
                    MediaPlayer player = MediaPlayer.create(getApplicationContext(),R.raw.select);
                    player.start();
                }

            }
        });

    }

    private void initBoardGrids() {
        for (int i = 0; i < 64; i++) {
            BoardGrid boardGrid = new BoardGrid(0x1L << i);

            //boardGrid.button_order = i;

            boardGridList.add(boardGrid);
        }
    }

}
