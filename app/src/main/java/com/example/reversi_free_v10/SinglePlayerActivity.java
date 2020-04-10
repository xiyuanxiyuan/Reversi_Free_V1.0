package com.example.reversi_free_v10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class SinglePlayerActivity extends AppCompatActivity {

    private List<BoardGrid> boardGridList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_player_layout);

        initBoardGrids();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.board);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,8){
            @Override
            public boolean canScrollVertically(){
                return  false;
            }

            public boolean canScrollHorizontally(){
                return false;
            }
        };
        recyclerView.setLayoutManager(gridLayoutManager);
        BoardAdapter adapter=new BoardAdapter(boardGridList);
        recyclerView.setAdapter(adapter);
    }

    private void initBoardGrids(){
        for(int i=0;i<64;i++){
            BoardGrid boardGrid=new BoardGrid(i);
            boardGridList.add(boardGrid);
        }
    }
}
