package com.example.reversi_free_v10;

import android.app.Application;

public class GlobalData extends Application {
    private int pieceSort = 1;
    private int boardSort = 1;
    private boolean soundOn = true;
    private boolean screenTransitions = false;
    private boolean hideStatusBar = false;
    private boolean showLegalMoves = true;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void setPieceSort(int pieceSort) {
        this.pieceSort = pieceSort;
    }

    public void setBoardSort(int boardSort) {
        this.boardSort = boardSort;
    }

    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }

    public void setScreenTransitions(boolean screenTransitions) {
        this.screenTransitions = screenTransitions;
    }

    public void setHideStatusBar(boolean hideStatusBar) {
        this.hideStatusBar = hideStatusBar;
    }

    public void setShowLegalMoves(boolean showLegalMoves) {
        this.showLegalMoves = showLegalMoves;
    }

    public int getPieceSort() {
        return pieceSort;
    }

    public int getBoardSort() {
        return boardSort;
    }

    public boolean isSoundOn() {
        return soundOn;
    }

    public boolean isScreenTransitions() {
        return screenTransitions;
    }

    public boolean isHideStatusBar() {
        return hideStatusBar;
    }

    public boolean isShowLegalMoves() {
        return showLegalMoves;
    }

}
