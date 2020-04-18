package com.example.reversi_free_v10;


import android.media.Image;
import android.widget.Button;

public class BoardGrid {

    private long button_id;
    private int boardgrid_piece;

    public BoardGrid(long button_id){
        this.button_id=button_id;
    }

    public void setButton_id(long button_id) {
        this.button_id = button_id;
    }

    public long getButton_id() {
        return button_id;
    }

    public void setBoardgrid_piece(int boardgrid_piece) {
        this.boardgrid_piece = boardgrid_piece;
    }

    public int getBoardgrid_piece() {
        return boardgrid_piece;
    }


}
