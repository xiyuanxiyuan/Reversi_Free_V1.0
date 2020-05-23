package com.example.reversi_free_v10;

import android.app.Application;

import java.util.List;

public class Undo extends Application {

    private List<Board> mBoardList;

    public void save(Board board){
        mBoardList.add(board);
    }
    public void read(){

        if(mBoardList.size()>=1){
            mBoardList.get(mBoardList.size()-1);
        }
    }

}
