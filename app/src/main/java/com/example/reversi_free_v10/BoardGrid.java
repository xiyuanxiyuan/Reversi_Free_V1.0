package com.example.reversi_free_v10;


import android.widget.Button;

public class BoardGrid {

    private Integer button_id;

    public BoardGrid(Integer button_id){
        this.button_id=button_id;
    }

    public void setButton_id(Integer button_id) {
        this.button_id = button_id;
    }

    public Integer getButton_id() {
        return button_id;
    }
}
