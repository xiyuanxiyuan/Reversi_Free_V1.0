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
            BoardGrid boardGrid=new BoardGrid(0x1L<<i);
            boardGridList.add(boardGrid);
        }
    }

    private void changeBoardGrids(long white,long black){

        if((white&0x1L)!=0x0L){
            boardGridList.get(0).setBoardgrid_piece(1);
        }else if((black&0x1L)!=0x0L) {
            boardGridList.get(0).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(0).setBoardgrid_piece(0);
        }

        if((white&0x2L)!=0x0L){
            boardGridList.get(1).setBoardgrid_piece(1);
        }else if((black&0x2L)!=0x0L) {
            boardGridList.get(1).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(1).setBoardgrid_piece(0);
        }

        if((white&0x4L)!=0x0L){
            boardGridList.get(2).setBoardgrid_piece(1);
        }else if((black&0x4L)!=0x0L) {
            boardGridList.get(2).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(2).setBoardgrid_piece(0);
        }

        if((white&0x8L)!=0x0L){
            boardGridList.get(3).setBoardgrid_piece(1);
        }else if((black&0x8L)!=0x0L) {
            boardGridList.get(3).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(3).setBoardgrid_piece(0);
        }

        if((white&0x10L)!=0x0L){
            boardGridList.get(4).setBoardgrid_piece(1);
        }else if((black&0x10L)!=0x0L) {
            boardGridList.get(4).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(4).setBoardgrid_piece(0);
        }

        if((white&0x20L)!=0x0L){
            boardGridList.get(5).setBoardgrid_piece(1);
        }else if((black&0x20L)!=0x0L) {
            boardGridList.get(5).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(5).setBoardgrid_piece(0);
        }

        if((white&0x40L)!=0x0L){
            boardGridList.get(6).setBoardgrid_piece(1);
        }else if((black&0x40L)!=0x0L) {
            boardGridList.get(6).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(6).setBoardgrid_piece(0);
        }

        if((white&0x80L)!=0x0L){
            boardGridList.get(7).setBoardgrid_piece(1);
        }else if((black&0x80L)!=0x0L) {
            boardGridList.get(7).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(7).setBoardgrid_piece(0);
        }

        //---------------------------------------------------------------------------

        if((white&0x100L)!=0x0L){
            boardGridList.get(8).setBoardgrid_piece(1);
        }else if((black&0x100L)!=0x0L) {
            boardGridList.get(8).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(8).setBoardgrid_piece(0);
        }

        if((white&0x200L)!=0x0L){
            boardGridList.get(9).setBoardgrid_piece(1);
        }else if((black&0x200L)!=0x0L) {
            boardGridList.get(9).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(9).setBoardgrid_piece(0);
        }

        if((white&0x400L)!=0x0L){
            boardGridList.get(10).setBoardgrid_piece(1);
        }else if((black&0x400L)!=0x0L) {
            boardGridList.get(10).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(10).setBoardgrid_piece(0);
        }

        if((white&0x800L)!=0x0L){
            boardGridList.get(11).setBoardgrid_piece(1);
        }else if((black&0x800L)!=0x0L) {
            boardGridList.get(11).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(11).setBoardgrid_piece(0);
        }

        if((white&0x1000L)!=0x0L){
            boardGridList.get(12).setBoardgrid_piece(1);
        }else if((black&0x1000L)!=0x0L) {
            boardGridList.get(12).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(12).setBoardgrid_piece(0);
        }

        if((white&0x2000L)!=0x0L){
            boardGridList.get(13).setBoardgrid_piece(1);
        }else if((black&0x2000L)!=0x0L) {
            boardGridList.get(13).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(13).setBoardgrid_piece(0);
        }

        if((white&0x4000L)!=0x0L){
            boardGridList.get(14).setBoardgrid_piece(1);
        }else if((black&0x4000L)!=0x0L) {
            boardGridList.get(14).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(14).setBoardgrid_piece(0);
        }

        if((white&0x8000L)!=0x0L){
            boardGridList.get(15).setBoardgrid_piece(1);
        }else if((black&0x8000L)!=0x0L) {
            boardGridList.get(15).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(15).setBoardgrid_piece(0);
        }

        //---------------------------------------------------------------------

        if((white&0x10000L)!=0x0L){
            boardGridList.get(16).setBoardgrid_piece(1);
        }else if((black&0x10000L)!=0x0L) {
            boardGridList.get(16).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(16).setBoardgrid_piece(0);
        }

        if((white&0x20000L)!=0x0L){
            boardGridList.get(17).setBoardgrid_piece(1);
        }else if((black&0x20000L)!=0x0L) {
            boardGridList.get(17).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(17).setBoardgrid_piece(0);
        }

        if((white&0x40000L)!=0x0L){
            boardGridList.get(18).setBoardgrid_piece(1);
        }else if((black&0x40000L)!=0x0L) {
            boardGridList.get(18).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(18).setBoardgrid_piece(0);
        }

        if((white&0x80000L)!=0x0L){
            boardGridList.get(19).setBoardgrid_piece(1);
        }else if((black&0x80000L)!=0x0L) {
            boardGridList.get(19).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(19).setBoardgrid_piece(0);
        }

        if((white&0x100000L)!=0x0L){
            boardGridList.get(20).setBoardgrid_piece(1);
        }else if((black&0x100000L)!=0x0L) {
            boardGridList.get(20).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(20).setBoardgrid_piece(0);
        }

        if((white&0x200000L)!=0x0L){
            boardGridList.get(21).setBoardgrid_piece(1);
        }else if((black&0x200000L)!=0x0L) {
            boardGridList.get(21).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(21).setBoardgrid_piece(0);
        }

        if((white&0x400000L)!=0x0L){
            boardGridList.get(22).setBoardgrid_piece(1);
        }else if((black&0x400000L)!=0x0L) {
            boardGridList.get(22).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(22).setBoardgrid_piece(0);
        }

        if((white&0x800000L)!=0x0L){
            boardGridList.get(23).setBoardgrid_piece(1);
        }else if((black&0x800000L)!=0x0L) {
            boardGridList.get(23).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(23).setBoardgrid_piece(0);
        }

        //------------------------------------------------------------------------

        if((white&0x1000000L)!=0x0L){
            boardGridList.get(24).setBoardgrid_piece(1);
        }else if((black&0x1000000L)!=0x0L) {
            boardGridList.get(24).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(24).setBoardgrid_piece(0);
        }


        if((white&0x2000000L)!=0x0L){
            boardGridList.get(25).setBoardgrid_piece(1);
        }else if((black&0x2000000L)!=0x0L) {
            boardGridList.get(25).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(25).setBoardgrid_piece(0);
        }


        if((white&0x4000000L)!=0x0L){
            boardGridList.get(26).setBoardgrid_piece(1);
        }else if((black&0x4000000L)!=0x0L) {
            boardGridList.get(26).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(26).setBoardgrid_piece(0);
        }


        if((white&0x8000000L)!=0x0L){
            boardGridList.get(27).setBoardgrid_piece(1);
        }else if((black&0x8000000L)!=0x0L) {
            boardGridList.get(27).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(27).setBoardgrid_piece(0);
        }


        if((white&0x10000000L)!=0x0L){
            boardGridList.get(28).setBoardgrid_piece(1);
        }else if((black&0x10000000L)!=0x0L) {
            boardGridList.get(28).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(28).setBoardgrid_piece(0);
        }


        if((white&0x20000000L)!=0x0L){
            boardGridList.get(29).setBoardgrid_piece(1);
        }else if((black&0x20000000L)!=0x0L) {
            boardGridList.get(29).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(29).setBoardgrid_piece(0);
        }


        if((white&0x40000000L)!=0x0L){
            boardGridList.get(30).setBoardgrid_piece(1);
        }else if((black&0x40000000L)!=0x0L) {
            boardGridList.get(30).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(30).setBoardgrid_piece(0);
        }


        if((white&0x80000000L)!=0x0L){
            boardGridList.get(31).setBoardgrid_piece(1);
        }else if((black&0x80000000L)!=0x0L) {
            boardGridList.get(31).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(31).setBoardgrid_piece(0);
        }

        //---------------------------------------------------------------------------

        if((white&0x100000000L)!=0x0L){
            boardGridList.get(32).setBoardgrid_piece(1);
        }else if((black&0x100000000L)!=0x0L) {
            boardGridList.get(32).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(32).setBoardgrid_piece(0);
        }


        if((white&0x200000000L)!=0x0L){
            boardGridList.get(33).setBoardgrid_piece(1);
        }else if((black&0x200000000L)!=0x0L) {
            boardGridList.get(33).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(33).setBoardgrid_piece(0);
        }


        if((white&0x400000000L)!=0x0L){
            boardGridList.get(34).setBoardgrid_piece(1);
        }else if((black&0x400000000L)!=0x0L) {
            boardGridList.get(34).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(34).setBoardgrid_piece(0);
        }


        if((white&0x800000000L)!=0x0L){
            boardGridList.get(35).setBoardgrid_piece(1);
        }else if((black&0x800000000L)!=0x0L) {
            boardGridList.get(35).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(35).setBoardgrid_piece(0);
        }


        if((white&0x1000000000L)!=0x0L){
            boardGridList.get(36).setBoardgrid_piece(1);
        }else if((black&0x1000000000L)!=0x0L) {
            boardGridList.get(36).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(36).setBoardgrid_piece(0);
        }


        if((white&0x2000000000L)!=0x0L){
            boardGridList.get(37).setBoardgrid_piece(1);
        }else if((black&0x2000000000L)!=0x0L) {
            boardGridList.get(37).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(37).setBoardgrid_piece(0);
        }


        if((white&0x4000000000L)!=0x0L){
            boardGridList.get(38).setBoardgrid_piece(1);
        }else if((black&0x4000000000L)!=0x0L) {
            boardGridList.get(38).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(38).setBoardgrid_piece(0);
        }


        if((white&0x8000000000L)!=0x0L){
            boardGridList.get(39).setBoardgrid_piece(1);
        }else if((black&0x8000000000L)!=0x0L) {
            boardGridList.get(39).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(39).setBoardgrid_piece(0);
        }

        //-------------------------------------------------------------------------

        if((white&0x10000000000L)!=0x0L){
            boardGridList.get(40).setBoardgrid_piece(1);
        }else if((black&0x10000000000L)!=0x0L) {
            boardGridList.get(40).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(40).setBoardgrid_piece(0);
        }


        if((white&0x20000000000L)!=0x0L){
            boardGridList.get(41).setBoardgrid_piece(1);
        }else if((black&0x20000000000L)!=0x0L) {
            boardGridList.get(41).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(41).setBoardgrid_piece(0);
        }


        if((white&0x40000000000L)!=0x0L){
            boardGridList.get(42).setBoardgrid_piece(1);
        }else if((black&0x40000000000L)!=0x0L) {
            boardGridList.get(42).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(42).setBoardgrid_piece(0);
        }


        if((white&0x80000000000L)!=0x0L){
            boardGridList.get(43).setBoardgrid_piece(1);
        }else if((black&0x80000000000L)!=0x0L) {
            boardGridList.get(43).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(43).setBoardgrid_piece(0);
        }


        if((white&0x100000000000L)!=0x0L){
            boardGridList.get(44).setBoardgrid_piece(1);
        }else if((black&0x100000000000L)!=0x0L) {
            boardGridList.get(44).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(44).setBoardgrid_piece(0);
        }


        if((white&0x200000000000L)!=0x0L){
            boardGridList.get(45).setBoardgrid_piece(1);
        }else if((black&0x200000000000L)!=0x0L) {
            boardGridList.get(45).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(45).setBoardgrid_piece(0);
        }


        if((white&0x400000000000L)!=0x0L){
            boardGridList.get(46).setBoardgrid_piece(1);
        }else if((black&0x400000000000L)!=0x0L) {
            boardGridList.get(46).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(46).setBoardgrid_piece(0);
        }


        if((white&0x800000000000L)!=0x0L){
            boardGridList.get(47).setBoardgrid_piece(1);
        }else if((black&0x800000000000L)!=0x0L) {
            boardGridList.get(47).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(47).setBoardgrid_piece(0);
        }

        //-----------------------------------------------------------------------

        if((white&0x1000000000000L)!=0x0L){
            boardGridList.get(48).setBoardgrid_piece(1);
        }else if((black&0x1000000000000L)!=0x0L) {
            boardGridList.get(48).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(48).setBoardgrid_piece(0);
        }

        if((white&0x2000000000000L)!=0x0L){
            boardGridList.get(49).setBoardgrid_piece(1);
        }else if((black&0x2000000000000L)!=0x0L) {
            boardGridList.get(49).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(49).setBoardgrid_piece(0);
        }

        if((white&0x4000000000000L)!=0x0L){
            boardGridList.get(50).setBoardgrid_piece(1);
        }else if((black&0x4000000000000L)!=0x0L) {
            boardGridList.get(50).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(50).setBoardgrid_piece(0);
        }


        if((white&0x8000000000000L)!=0x0L){
            boardGridList.get(51).setBoardgrid_piece(1);
        }else if((black&0x8000000000000L)!=0x0L) {
            boardGridList.get(51).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(51).setBoardgrid_piece(0);
        }


        if((white&0x10000000000000L)!=0x0L){
            boardGridList.get(52).setBoardgrid_piece(1);
        }else if((black&0x10000000000000L)!=0x0L) {
            boardGridList.get(52).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(52).setBoardgrid_piece(0);
        }


        if((white&0x20000000000000L)!=0x0L){
            boardGridList.get(53).setBoardgrid_piece(1);
        }else if((black&0x20000000000000L)!=0x0L) {
            boardGridList.get(53).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(53).setBoardgrid_piece(0);
        }


        if((white&0x40000000000000L)!=0x0L){
            boardGridList.get(54).setBoardgrid_piece(1);
        }else if((black&0x40000000000000L)!=0x0L) {
            boardGridList.get(54).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(54).setBoardgrid_piece(0);
        }


        if((white&0x80000000000000L)!=0x0L){
            boardGridList.get(55).setBoardgrid_piece(1);
        }else if((black&0x80000000000000L)!=0x0L) {
            boardGridList.get(55).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(55).setBoardgrid_piece(0);
        }

        //----------------------------------------------------------------------

        if((white&0x100000000000000L)!=0x0L){
            boardGridList.get(56).setBoardgrid_piece(1);
        }else if((black&0x100000000000000L)!=0x0L) {
            boardGridList.get(56).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(56).setBoardgrid_piece(0);
        }


        if((white&0x200000000000000L)!=0x0L){
            boardGridList.get(57).setBoardgrid_piece(1);
        }else if((black&0x200000000000000L)!=0x0L) {
            boardGridList.get(57).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(57).setBoardgrid_piece(0);
        }


        if((white&0x400000000000000L)!=0x0L){
            boardGridList.get(58).setBoardgrid_piece(1);
        }else if((black&0x400000000000000L)!=0x0L) {
            boardGridList.get(58).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(58).setBoardgrid_piece(0);
        }


        if((white&0x800000000000000L)!=0x0L){
            boardGridList.get(59).setBoardgrid_piece(1);
        }else if((black&0x800000000000000L)!=0x0L) {
            boardGridList.get(59).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(59).setBoardgrid_piece(0);
        }


        if((white&0x1000000000000000L)!=0x0L){
            boardGridList.get(60).setBoardgrid_piece(1);
        }else if((black&0x1000000000000000L)!=0x0L) {
            boardGridList.get(60).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(60).setBoardgrid_piece(0);
        }


        if((white&0x2000000000000000L)!=0x0L){
            boardGridList.get(61).setBoardgrid_piece(1);
        }else if((black&0x2000000000000000L)!=0x0L) {
            boardGridList.get(61).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(61).setBoardgrid_piece(0);
        }


        if((white&0x4000000000000000L)!=0x0L){
            boardGridList.get(62).setBoardgrid_piece(1);
        }else if((black&0x4000000000000000L)!=0x0L) {
            boardGridList.get(62).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(62).setBoardgrid_piece(0);
        }


        if((white&0x8000000000000000L)!=0x0L){
            boardGridList.get(63).setBoardgrid_piece(1);
        }else if((black&0x8000000000000000L)!=0x0L) {
            boardGridList.get(63).setBoardgrid_piece(-1);
        }else{
            boardGridList.get(63).setBoardgrid_piece(0);
        }

    }


}
