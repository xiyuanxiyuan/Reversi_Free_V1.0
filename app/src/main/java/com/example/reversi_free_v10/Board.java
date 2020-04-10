package com.example.reversi_free_v10;

import android.util.Log;

import static android.content.ContentValues.TAG;

public class Board {

    long white = 0x1008000000L;//16进制
    //0b00000000 00000000 00000000 00010000 00001000 00000000 00000000 00000000
    //------------------------->
    //1  0  0  0  0  0  0  0  0 |
    //2  0  0  0  0  0  0  0  0 |
    //3  0  0  0  0  0  0  0  0 |
    //4  0  0  0  1  0  0  0  0 |
    //5  0  0  0  0  1  0  0  0 |
    //6  0  0  0  0  0  0  0  0 |
    //7  0  0  0  0  0  0  0  0 |
    //8  0  0  0  0  0  0  0  0 |
    //   A  B  C  D  E  F  G  H ↓
    long black = 0x810000000L;
    //------------------------->
    //1  0  0  0  0  0  0  0  0 |
    //2  0  0  0  0  0  0  0  0 |
    //3  0  0  0  0  0  0  0  0 |
    //4  0  0  0  0  1  0  0  0 |
    //5  0  0  0  1  0  0  0  0 |
    //6  0  0  0  0  0  0  0  0 |
    //7  0  0  0  0  0  0  0  0 |
    //8  0  0  0  0  0  0  0  0 |
    //   A  B  C  D  E  F  G  H ↓
    long mask = 0xffffffffffffffffL;
    //------------------------->
    //1  1  1  1  1  1  1  1  1 |
    //2  1  1  1  1  1  1  1  1 |
    //3  1  1  1  1  1  1  1  1 |
    //4  1  1  1  1  1  1  1  1 |
    //5  1  1  1  1  1  1  1  1 |
    //6  1  1  1  1  1  1  1  1 |
    //7  1  1  1  1  1  1  1  1 |
    //8  1  1  1  1  1  1  1  1 |
    //   A  B  C  D  E  F  G  H ↓
    long location[] = new long[64];
    long legal_Drop_Location_For_Black = 0x102004080000L;
    //------------------------->
    //1  0  0  0  0  0  0  0  0 |
    //2  0  0  0  0  0  0  0  0 |
    //3  0  0  0  1  0  0  0  0 |
    //4  0  0  1  0  0  0  0  0 |
    //5  0  0  0  0  0  1  0  0 |
    //6  0  0  0  0  1  0  0  0 |
    //7  0  0  0  0  0  0  0  0 |
    //8  0  0  0  0  0  0  0  0 |
    //   A  B  C  D  E  F  G  H ↓
    long legal_Drop_Location_For_White = 0x80420100000L;
    //------------------------->
    //1  0  0  0  0  0  0  0  0 |
    //2  0  0  0  0  0  0  0  0 |
    //3  0  0  0  0  1  0  0  0 |
    //4  0  0  0  0  0  1  0  0 |
    //5  0  0  1  0  0  0  0  0 |
    //6  0  0  0  1  0  0  0  0 |
    //7  0  0  0  0  0  0  0  0 |
    //8  0  0  0  0  0  0  0  0 |
    //   A  B  C  D  E  F  G  H ↓

    public Board() {
        long init = 0x1L;
        for (int i = 0; i < 64; i++) {
            location[i] = init << i;
        }
        ;
    }

    public long get_Location(long location){
        return location;
    }

    public void drop_White(long location) {

        if((location&search_Legal_Location(black,white))!=0x0L)
        {
            white |=location;
        white|=reversal(location,black,white);
        black&=~reversal(location,black,white);
        }
    }

    public void drop_Black(long location) {
        if((location&search_Legal_Location(white,black))!=0x0L){
            black|=location;
            black|=reversal(location,white,black);
            white&=~reversal(location,white,black);
        }

    }

    //------------------------------------------------------------------------------------------

    public long search_Legal_Location(long opponent_Board, long my_Board) {
        long legal_Location = 0x0L;

        if ((my_Board & 0x1L) != 0x0L) {//A1
            legal_Location |= down(0x1L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x1L, opponent_Board, my_Board);
            legal_Location |= right(0x1L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x2L) != 0x0L) {//B1
            legal_Location |= down(0x2L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x2L, opponent_Board, my_Board);
            legal_Location |= right(0x2L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x4L) != 0x0L) {//C1
            legal_Location |= left(0x4L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x4L, opponent_Board, my_Board);
            legal_Location |= down(0x4L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x4L, opponent_Board, my_Board);
            legal_Location |= right(0x4L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x8L) != 0x0L) {//D1
            legal_Location |= left(0x8L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x8L, opponent_Board, my_Board);
            legal_Location |= down(0x8L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x8L, opponent_Board, my_Board);
            legal_Location |= right(0x8L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x10L) != 0x0L) {//E1
            legal_Location |= left(0x10L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x10L, opponent_Board, my_Board);
            legal_Location |= down(0x10L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x10L, opponent_Board, my_Board);
            legal_Location |= right(0x10L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x20L) != 0x0L) {//F1
            legal_Location |= left(0x20L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x20L, opponent_Board, my_Board);
            legal_Location |= down(0x20L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x20L, opponent_Board, my_Board);
            legal_Location |= right(0x20L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x40L) != 0x0L) {//G1
            legal_Location |= left(0x40L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x40L, opponent_Board, my_Board);
            legal_Location |= down(0x40L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x80L) != 0x0L) {//H1
            legal_Location |= left(0x80L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x80L, opponent_Board, my_Board);
            legal_Location |= down(0x80L, opponent_Board, my_Board);
        }

        //---------------------------------------------------------------------------

        if ((my_Board & 0x100L) != 0x0L) {//A2
            legal_Location |= down(0x100L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x100L, opponent_Board, my_Board);
            legal_Location |= right(0x100L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x200L) != 0x0L) {//B2
            legal_Location |= down(0x200L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x200L, opponent_Board, my_Board);
            legal_Location |= right(0x200L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x400L) != 0x0L) {//C2
            legal_Location |= left(0x400L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x400L, opponent_Board, my_Board);
            legal_Location |= down(0x400L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x400L, opponent_Board, my_Board);
            legal_Location |= right(0x400L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x800L) != 0x0L) {//D2
            legal_Location |= left(0x800L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x800L, opponent_Board, my_Board);
            legal_Location |= down(0x800L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x800L, opponent_Board, my_Board);
            legal_Location |= right(0x800L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x1000L) != 0x0L) {//E2
            legal_Location |= left(0x1000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x1000L, opponent_Board, my_Board);
            legal_Location |= down(0x1000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x1000L, opponent_Board, my_Board);
            legal_Location |= right(0x1000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x2000L) != 0x0L) {//F2
            legal_Location |= left(0x2000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x2000L, opponent_Board, my_Board);
            legal_Location |= down(0x2000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x2000L, opponent_Board, my_Board);
            legal_Location |= right(0x2000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x4000L) != 0x0L) {//G2
            legal_Location |= left(0x4000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x4000L, opponent_Board, my_Board);
            legal_Location |= down(0x4000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x8000L) != 0x0L) {//H2
            legal_Location |= left(0x8000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x8000L, opponent_Board, my_Board);
            legal_Location |= down(0x8000L, opponent_Board, my_Board);
        }

        //---------------------------------------------------------------------------

        if ((my_Board & 0x10000L) != 0x0L) {//A3
            legal_Location |= down(0x10000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x10000L, opponent_Board, my_Board);
            legal_Location |= right(0x10000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x10000L, opponent_Board, my_Board);
            legal_Location |= up(0x10000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x20000L) != 0x0L) {//B3
            legal_Location |= down(0x20000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x20000L, opponent_Board, my_Board);
            legal_Location |= right(0x20000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x20000L, opponent_Board, my_Board);
            legal_Location |= up(0x20000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x40000L) != 0x0L) {//C3
            legal_Location |= left(0x40000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x40000L, opponent_Board, my_Board);
            legal_Location |= down(0x40000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x40000L, opponent_Board, my_Board);
            legal_Location |= right(0x40000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x40000L, opponent_Board, my_Board);
            legal_Location |= up(0x40000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x40000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x80000L) != 0x0L) {//D3
            legal_Location |= left(0x80000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x80000L, opponent_Board, my_Board);
            legal_Location |= down(0x80000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x80000L, opponent_Board, my_Board);
            legal_Location |= right(0x80000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x80000L, opponent_Board, my_Board);
            legal_Location |= up(0x80000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x80000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x100000L) != 0x0L) {//E3
            legal_Location |= left(0x100000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x100000L, opponent_Board, my_Board);
            legal_Location |= down(0x100000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x100000L, opponent_Board, my_Board);
            legal_Location |= right(0x100000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x100000L, opponent_Board, my_Board);
            legal_Location |= up(0x100000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x100000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x200000L) != 0x0L) {//F3
            legal_Location |= left(0x200000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x200000L, opponent_Board, my_Board);
            legal_Location |= down(0x200000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x200000L, opponent_Board, my_Board);
            legal_Location |= right(0x200000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x200000L, opponent_Board, my_Board);
            legal_Location |= up(0x200000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x200000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x400000L) != 0x0L) {//G3
            legal_Location |= left(0x400000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x400000L, opponent_Board, my_Board);
            legal_Location |= down(0x400000L, opponent_Board, my_Board);
            legal_Location |= up(0x400000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x400000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x800000L) != 0x0L) {//H3
            legal_Location |= left(0x800000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x800000L, opponent_Board, my_Board);
            legal_Location |= down(0x800000L, opponent_Board, my_Board);
            legal_Location |= up(0x800000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x800000L, opponent_Board, my_Board);
        }

        //-------------------------------------------------------------------

        if ((my_Board & 0x1000000L) != 0x0L) {//A4
            legal_Location |= down(0x1000000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x1000000L, opponent_Board, my_Board);
            legal_Location |= right(0x1000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x1000000L, opponent_Board, my_Board);
            legal_Location |= up(0x1000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x2000000L) != 0x0L) {//B4
            legal_Location |= down(0x2000000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x2000000L, opponent_Board, my_Board);
            legal_Location |= right(0x2000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x2000000L, opponent_Board, my_Board);
            legal_Location |= up(0x2000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x4000000L) != 0x0L) {//C4
            legal_Location |= left(0x4000000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x4000000L, opponent_Board, my_Board);
            legal_Location |= down(0x4000000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x4000000L, opponent_Board, my_Board);
            legal_Location |= right(0x4000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x4000000L, opponent_Board, my_Board);
            legal_Location |= up(0x4000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x4000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x8000000L) != 0x0L) {//D4
            legal_Location |= left(0x8000000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x8000000L, opponent_Board, my_Board);
            legal_Location |= down(0x8000000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x8000000L, opponent_Board, my_Board);
            legal_Location |= right(0x8000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x8000000L, opponent_Board, my_Board);
            legal_Location |= up(0x8000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x8000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x10000000L) != 0x0L) {//E4
            legal_Location |= left(0x10000000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x10000000L, opponent_Board, my_Board);
            legal_Location |= down(0x10000000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x10000000L, opponent_Board, my_Board);
            legal_Location |= right(0x10000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x10000000L, opponent_Board, my_Board);
            legal_Location |= up(0x10000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x10000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x20000000L) != 0x0L) {//F4
            legal_Location |= left(0x20000000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x20000000L, opponent_Board, my_Board);
            legal_Location |= down(0x20000000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x20000000L, opponent_Board, my_Board);
            legal_Location |= right(0x20000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x20000000L, opponent_Board, my_Board);
            legal_Location |= up(0x20000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x20000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x40000000L) != 0x0L) {//G4
            legal_Location |= left(0x40000000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x40000000L, opponent_Board, my_Board);
            legal_Location |= down(0x40000000L, opponent_Board, my_Board);
            legal_Location |= up(0x40000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x40000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x80000000L) != 0x0L) {//H4
            legal_Location |= left(0x80000000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x80000000L, opponent_Board, my_Board);
            legal_Location |= down(0x80000000L, opponent_Board, my_Board);
            legal_Location |= up(0x80000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x80000000L, opponent_Board, my_Board);
        }

        //-------------------------------------------------------------------

        if ((my_Board & 0x100000000L) != 0x0L) {//A5
            legal_Location |= down(0x100000000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x100000000L, opponent_Board, my_Board);
            legal_Location |= right(0x100000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x100000000L, opponent_Board, my_Board);
            legal_Location |= up(0x100000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x200000000L) != 0x0L) {//B5
            legal_Location |= down(0x200000000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x200000000L, opponent_Board, my_Board);
            legal_Location |= right(0x200000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x200000000L, opponent_Board, my_Board);
            legal_Location |= up(0x200000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x400000000L) != 0x0L) {//C5
            legal_Location |= left(0x400000000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x400000000L, opponent_Board, my_Board);
            legal_Location |= down(0x400000000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x400000000L, opponent_Board, my_Board);
            legal_Location |= right(0x400000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x400000000L, opponent_Board, my_Board);
            legal_Location |= up(0x400000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x400000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x800000000L) != 0x0L) {//D5
            legal_Location |= left(0x800000000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x800000000L, opponent_Board, my_Board);
            legal_Location |= down(0x800000000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x800000000L, opponent_Board, my_Board);
            legal_Location |= right(0x800000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x800000000L, opponent_Board, my_Board);
            legal_Location |= up(0x800000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x800000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x1000000000L) != 0x0L) {//E5
            legal_Location |= left(0x1000000000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x1000000000L, opponent_Board, my_Board);
            legal_Location |= down(0x1000000000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x1000000000L, opponent_Board, my_Board);
            legal_Location |= right(0x1000000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x1000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x1000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x1000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x2000000000L) != 0x0L) {//F5
            legal_Location |= left(0x2000000000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x2000000000L, opponent_Board, my_Board);
            legal_Location |= down(0x2000000000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x2000000000L, opponent_Board, my_Board);
            legal_Location |= right(0x2000000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x2000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x2000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x2000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x4000000000L) != 0x0L) {//G5
            legal_Location |= left(0x4000000000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x4000000000L, opponent_Board, my_Board);
            legal_Location |= down(0x4000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x4000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x4000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x8000000000L) != 0x0L) {//H5
            legal_Location |= left(0x8000000000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x8000000000L, opponent_Board, my_Board);
            legal_Location |= down(0x8000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x8000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x8000000000L, opponent_Board, my_Board);
        }

        //-------------------------------------------------------------------

        if ((my_Board & 0x10000000000L) != 0x0L) {//A6
            legal_Location |= down(0x10000000000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x10000000000L, opponent_Board, my_Board);
            legal_Location |= right(0x10000000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x10000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x10000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x20000000000L) != 0x0L) {//B6
            legal_Location |= down(0x20000000000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x20000000000L, opponent_Board, my_Board);
            legal_Location |= right(0x20000000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x20000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x20000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x40000000000L) != 0x0L) {//C6
            legal_Location |= left(0x40000000000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x40000000000L, opponent_Board, my_Board);
            legal_Location |= down(0x40000000000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x40000000000L, opponent_Board, my_Board);
            legal_Location |= right(0x40000000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x40000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x40000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x40000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x80000000000L) != 0x0L) {//D6
            legal_Location |= left(0x80000000000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x80000000000L, opponent_Board, my_Board);
            legal_Location |= down(0x80000000000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x80000000000L, opponent_Board, my_Board);
            legal_Location |= right(0x80000000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x80000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x80000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x80000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x100000000000L) != 0x0L) {//E6
            legal_Location |= left(0x100000000000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x100000000000L, opponent_Board, my_Board);
            legal_Location |= down(0x100000000000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x100000000000L, opponent_Board, my_Board);
            legal_Location |= right(0x100000000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x100000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x100000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x100000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x200000000000L) != 0x0L) {//F6
            legal_Location |= left(0x200000000000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x200000000000L, opponent_Board, my_Board);
            legal_Location |= down(0x200000000000L, opponent_Board, my_Board);
            legal_Location |= down_Right(0x200000000000L, opponent_Board, my_Board);
            legal_Location |= right(0x200000000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x200000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x200000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x200000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x400000000000L) != 0x0L) {//G6
            legal_Location |= left(0x400000000000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x400000000000L, opponent_Board, my_Board);
            legal_Location |= down(0x400000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x400000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x400000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x800000000000L) != 0x0L) {//H6
            legal_Location |= left(0x800000000000L, opponent_Board, my_Board);
            legal_Location |= down_Left(0x800000000000L, opponent_Board, my_Board);
            legal_Location |= down(0x800000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x800000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x800000000000L, opponent_Board, my_Board);
        }

        //-------------------------------------------------------------------

        if ((my_Board & 0x1000000000000L) != 0x0L) {//A7
            legal_Location |= right(0x1000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x1000000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x1000000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x2000000000000L) != 0x0L) {//B7
            legal_Location |= right(0x2000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x2000000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x2000000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x4000000000000L) != 0x0L) {//C7
            legal_Location |= left(0x4000000000000L, opponent_Board, my_Board);
            legal_Location |= right(0x4000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x4000000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x4000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x4000000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x8000000000000L) != 0x0L) {//D7
            legal_Location |= left(0x8000000000000L, opponent_Board, my_Board);
            legal_Location |= right(0x8000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x8000000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x8000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x8000000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x10000000000000L) != 0x0L) {//E7
            legal_Location |= left(0x10000000000000L, opponent_Board, my_Board);
            legal_Location |= right(0x10000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x10000000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x10000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x10000000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x20000000000000L) != 0x0L) {//F7
            legal_Location |= left(0x20000000000000L, opponent_Board, my_Board);
            legal_Location |= right(0x20000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x20000000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x20000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x20000000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x40000000000000L) != 0x0L) {//G7
            legal_Location |= left(0x40000000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x40000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x40000000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x80000000000000L) != 0x0L) {//H7
            legal_Location |= left(0x80000000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x80000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x80000000000000L, opponent_Board, my_Board);
        }

        //-------------------------------------------------------------------

        if ((my_Board & 0x100000000000000L) != 0x0L) {//A8
            legal_Location |= right(0x100000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x100000000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x100000000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x200000000000000L) != 0x0L) {//B8
            legal_Location |= right(0x200000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x200000000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x200000000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x400000000000000L) != 0x0L) {//C8
            legal_Location |= left(0x400000000000000L, opponent_Board, my_Board);
            legal_Location |= right(0x400000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x400000000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x400000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x400000000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x800000000000000L) != 0x0L) {//D8
            legal_Location |= left(0x800000000000000L, opponent_Board, my_Board);
            legal_Location |= right(0x800000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x800000000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x800000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x800000000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x1000000000000000L) != 0x0L) {//E8
            legal_Location |= left(0x1000000000000000L, opponent_Board, my_Board);
            legal_Location |= right(0x1000000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x1000000000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x1000000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x1000000000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x2000000000000000L) != 0x0L) {//F8
            legal_Location |= left(0x2000000000000000L, opponent_Board, my_Board);
            legal_Location |= right(0x2000000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Right(0x2000000000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x2000000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x2000000000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x4000000000000000L) != 0x0L) {//G8
            legal_Location |= left(0x4000000000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x4000000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x4000000000000000L, opponent_Board, my_Board);
        }

        if ((my_Board & 0x8000000000000000L) != 0x0L) {//H8
            legal_Location |= left(0x8000000000000000L, opponent_Board, my_Board);
            legal_Location |= up(0x8000000000000000L, opponent_Board, my_Board);
            legal_Location |= up_Left(0x8000000000000000L, opponent_Board, my_Board);
        }

        //-------------------------------------------------------------------

        return legal_Location;
    }

    public long up(long location, long opponent_Board, long my_Board) {
        if ((location & 0xffL) != 0x0L) {//Row A1 第一行 不用看，上面没位置
            return 0x0L;
        }
        if ((location & 0xff_00L) != 0x0L) {//Row A2 第二行 不用看，上面无论黑、白、空，都不能下
            return 0x0L;
        }
        if ((location & 0xff_00_00L) != 0x0L) {//Row A3 第三行，只判断第一行能不能下就行
            long mlocation = location >>> 8;
            if ((mlocation & opponent_Board) != 0x0L) {//如果正上方第一个位置有对面的棋子
                mlocation = location >>> 16;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L)//如果正上方第二个位置是空的
                {
                    return mlocation;
                }
            }
        }
        if ((location & 0xff_00_00_00L) != 0x0L) {//Row A4 第四行，判断第一二行能不能下
            long mlocation = location >>> 8;
            if ((mlocation & opponent_Board) != 0x0L) {//如果正上方第一个位置有对方的棋子
                mlocation = location >>> 16;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L)//如果正上方第二个位置是空的
                {
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果正上方第二个位置也是对方棋子
                    mlocation = location >>> 24;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果正上方第三个位置是空的
                        return mlocation;
                    }
                }
            }
        }
        if ((location & 0xff_00_00_00_00L) != 0x0L) {//Row A5 第五行，判断第一二三行能不能下
            long mlocation = location >>> 8;
            if ((mlocation & opponent_Board) != 0x0L) {//如果正上方第一个位置有对方的棋子
                mlocation = location >>> 16;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L)//如果正上方第二个位置是空的
                {
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果正上方第二个位置也是对方棋子
                    mlocation = location >>> 24;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果正上方第三个位置是空的
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果正上方第三个位置也是对方棋子
                        mlocation = location >>> 32;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果正上方第四个位置是空的
                            return mlocation;
                        }
                    }
                }
            }
        }
        if ((location & 0xff_00_00_00_00_00L) != 0x0L) {//Row A6 第六行，判断第一二三四行能不能下
            long mlocation = location >>> 8;
            if ((mlocation & opponent_Board) != 0x0L) {//如果正上方第一个位置有对方的棋子
                mlocation = location >>> 16;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L)//如果正上方第二个位置是空的
                {
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果正上方第二个位置也是对方棋子
                    mlocation = location >>> 24;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果正上方第三个位置是空的
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果正上方第三个位置也是对方棋子
                        mlocation = location >>> 32;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果正上方第四个位置是空的
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果正上方第四个位置也是对方棋子
                            mlocation = location >>> 40;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果正上方第五个位置是空的
                                return mlocation;
                            }
                        }
                    }
                }
            }
        }
        if ((location & 0xff_00_00_00_00_00_00L) != 0x0L) {//Row A7 第七行，判断第一二三四五行能不能下
            long mlocation = location >>> 8;
            if ((mlocation & opponent_Board) != 0x0L) {//如果正上方第一个位置有对方的棋子
                mlocation = location >>> 16;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L)//如果正上方第二个位置是空的
                {
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果正上方第二个位置也是对方棋子
                    mlocation = location >>> 24;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果正上方第三个位置是空的
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果正上方第三个位置也是对方棋子
                        mlocation = location >>> 32;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果正上方第四个位置是空的
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果正上方第四个位置也是对方棋子
                            mlocation = location >>> 40;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果正上方第五个位置是空的
                                return mlocation;
                            } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果正上方第五个位置也是对方棋子
                                mlocation = location >>> 48;
                                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果正上方第六个位置是空的
                                    return mlocation;
                                }
                            }
                        }
                    }
                }
            }
        }
        if ((location & 0xff_00_00_00_00_00_00_00L) != 0x0L) {//Row A8 第八行,前七个位置，判断第一二三四五六行能不能下
            long mlocation = location >>> 8;
            if ((mlocation & opponent_Board) != 0x0L) {//如果正上方第一个位置有对方的棋子
                mlocation = location >>> 16;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L)//如果正上方第二个位置是空的
                {
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果正上方第二个位置也是对方棋子
                    mlocation = location >>> 24;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果正上方第三个位置是空的
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果正上方第三个位置也是对方棋子
                        mlocation = location >>> 32;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果正上方第四个位置是空的
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果正上方第四个位置也是对方棋子
                            mlocation = location >>> 40;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果正上方第五个位置是空的
                                return mlocation;
                            } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果正上方第五个位置也是对方棋子
                                mlocation = location >>> 48;
                                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果正上方第六个位置是空的
                                    return mlocation;
                                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果正上方第六个位置也是对方棋子
                                    mlocation = location >>> 56;
                                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果正上方第七个位置是空的
                                        return mlocation;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0x0L;//返回的只要不是单个1，那结果就为无位置
    }

    public long right(long location, long opponent_Board, long my_Board) {
        if ((location & 0x101010101010101L) != 0x0L) {//Column A1 第一列
            long mlocation = location << 1;
            if ((mlocation & opponent_Board) != 0x0L) {//如果右边第一格是对方的棋子
                mlocation = location << 2;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第二个是空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右边第二个是对方的棋子
                    mlocation = location << 3;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第三个是空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右边第三个是对方的棋子
                        mlocation = location << 4;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第四个是空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右边第四个是对方的棋子
                            mlocation = location << 5;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第五个是空位置
                                return mlocation;
                            } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右边第五个是对方的棋子
                                mlocation = location << 6;
                                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第六个是空位置
                                    return mlocation;
                                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右边第六个是对方的棋子
                                    mlocation = location << 7;
                                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第七个是空位置
                                        return mlocation;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if ((location & 0x202020202020202L) != 0x0L) {//Column B1 第二列
            long mlocation = location << 1;
            if ((mlocation & opponent_Board) != 0x0L) {//如果右边第一格是对方的棋子
                mlocation = location << 2;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第二个是空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右边第二个是对方的棋子
                    mlocation = location << 3;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第三个是空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右边第三个是对方的棋子
                        mlocation = location << 4;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第四个是空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右边第四个是对方的棋子
                            mlocation = location << 5;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第五个是空位置
                                return mlocation;
                            } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右边第五个是对方的棋子
                                mlocation = location << 6;
                                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第六个是空位置
                                    return mlocation;
                                }
                            }
                        }
                    }
                }
            }
        }
        if ((location & 0x404040404040404L) != 0x0L) {//Column C1 第三列
            long mlocation = location << 1;
            if ((mlocation & opponent_Board) != 0x0L) {//如果右边第一格是对方的棋子
                mlocation = location << 2;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第二个是空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右边第二个是对方的棋子
                    mlocation = location << 3;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第三个是空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右边第三个是对方的棋子
                        mlocation = location << 4;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第四个是空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右边第四个是对方的棋子
                            mlocation = location << 5;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第五个是空位置
                                return mlocation;
                            }
                        }
                    }
                }
            }
        }
        if ((location & 0x808080808080808L) != 0x0L) {//Column D1 第四列
            long mlocation = location << 1;
            if ((mlocation & opponent_Board) != 0x0L) {//如果右边第一格是对方的棋子
                mlocation = location << 2;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第二个是空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右边第二个是对方的棋子
                    mlocation = location << 3;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第三个是空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右边第三个是对方的棋子
                        mlocation = location << 4;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第四个是空位置
                            return mlocation;
                        }
                    }
                }
            }
        }
        if ((location & 0x1010101010101010L) != 0x0L) {//Column E1 第五列
            long mlocation = location << 1;
            if ((mlocation & opponent_Board) != 0x0L) {//如果右边第一格是对方的棋子
                mlocation = location << 2;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第二个是空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右边第二个是对方的棋子
                    mlocation = location << 3;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第三个是空位置
                        return mlocation;
                    }
                }
            }
        }
        if ((location & 0x2020202020202020L) != 0x0L) {//Column F1 第六列
            long mlocation = location << 1;
            if ((mlocation & opponent_Board) != 0x0L) {//如果右边第一格是对方的棋子
                mlocation = location << 2;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右边第二个是空位置
                    return mlocation;
                }
            }
        }
        if ((location & 0x4040404040404040L) != 0x0L) {//Column G1 第七列 右边无论黑、白、空都无法落子
            return 0x0L;
        }
        if ((location & 0x8080808080808080L) != 0x0L) {//Column H1 第八列 右边没地方可落子
            return 0x0L;
        }
        return 0x0L;//返回的只要不是单个1，那结果就为无位置
    }

    public long down(long location, long opponent_Board, long my_Board) {
        if ((location & 0xffL) != 0x0L) {//Row A1 第一行
            long mlocation = location << 8;
            if ((mlocation & opponent_Board) != 0x0L) {//如果下面第一格是对方的棋子
                mlocation = location << 16;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果下面第二格为对方棋子
                    mlocation = location << 24;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果下面第三格为对方棋子
                        mlocation = location << 32;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第四格为空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果下面第四格为对方棋子
                            mlocation = location << 40;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第五格为空位置
                                return mlocation;
                            } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果下面第五格为对方棋子
                                mlocation = location << 48;
                                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第六格为空位置
                                    return mlocation;
                                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果下面第六格为对方棋子
                                    mlocation = location << 56;
                                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第七格为空位置
                                        return mlocation;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if ((location & 0xff_00L) != 0x0L) {//Row A2 第二行
            long mlocation = location << 8;
            if ((mlocation & opponent_Board) != 0x0L) {//如果下面第一格是对方的棋子
                mlocation = location << 16;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果下面第二格为对方棋子
                    mlocation = location << 24;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果下面第三格为对方棋子
                        mlocation = location << 32;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第四格为空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果下面第四格为对方棋子
                            mlocation = location << 40;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第五格为空位置
                                return mlocation;
                            } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果下面第五格为对方棋子
                                mlocation = location << 48;
                                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第六格为空位置
                                    return mlocation;
                                }
                            }
                        }
                    }
                }
            }
        }
        if ((location & 0xff_00_00L) != 0x0L) {//Row A3 第三行
            long mlocation = location << 8;
            if ((mlocation & opponent_Board) != 0x0L) {//如果下面第一格是对方的棋子
                mlocation = location << 16;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果下面第二格为对方棋子
                    mlocation = location << 24;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果下面第三格为对方棋子
                        mlocation = location << 32;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第四格为空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果下面第四格为对方棋子
                            mlocation = location << 40;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第五格为空位置
                                return mlocation;
                            }
                        }
                    }
                }
            }
        }
        if ((location & 0xff_00_00_00L) != 0x0L) {//Row A4 第四行
            long mlocation = location << 8;
            if ((mlocation & opponent_Board) != 0x0L) {//如果下面第一格是对方的棋子
                mlocation = location << 16;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果下面第二格为对方棋子
                    mlocation = location << 24;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果下面第三格为对方棋子
                        mlocation = location << 32;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第四格为空位置
                            return mlocation;
                        }
                    }
                }
            }
        }
        if ((location & 0xff_00_00_00_00L) != 0x0L) {//Row A5 第五行
            long mlocation = location << 8;
            if ((mlocation & opponent_Board) != 0x0L) {//如果下面第一格是对方的棋子
                mlocation = location << 16;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果下面第二格为对方棋子
                    mlocation = location << 24;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第三格为空位置
                        return mlocation;
                    }
                }
            }
        }
        if ((location & 0xff_00_00_00_00_00L) != 0x0L) {//Row A6 第六行
            long mlocation = location << 8;
            if ((mlocation & opponent_Board) != 0x0L) {//如果下面第二格是对方的棋子
                mlocation = location << 16;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果下面第二格为空位置
                    return mlocation;
                }
            }
        }
        if ((location & 0xff_00_00_00_00_00_00L) != 0x0L) {//Row A7 第七行 无论下面是黑、白、空都无法落子
            return 0x0L;
        }
        if ((location & 0xff_00_00_00_00_00_00_00L) != 0x0L) {//Row A8 第八行 下面没位置
            return 0x0L;
        }
        return 0x0L;//返回的只要不是单个1，那结果就为无位置
    }

    public long left(long location, long opponent_Board, long my_Board) {
        if ((location & 0x101010101010101L) != 0x0L) {//Column A1 第一列 左边没地方可落子
            return 0x0L;
        }
        if ((location & 0x202020202020202L) != 0x0L) {//Column B1 第二列 左边无论是黑、白、空都无法落子
            return 0x0L;
        }
        if ((location & 0x404040404040404L) != 0x0L) {//Column C1 第三列
            long mlocation = location >>> 1;
            if ((mlocation & opponent_Board) != 0x0L) {//如果左边第一格是对方的棋子
                mlocation = location >>> 2;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第二格为空位置
                    return mlocation;
                }
            }
        }
        if ((location & 0x808080808080808L) != 0x0L) {//Column D1 第四列
            long mlocation = location >>> 1;
            if ((mlocation & opponent_Board) != 0x0L) {//如果左边第一格是对方的棋子
                mlocation = location >>> 2;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左边第二格是对方的棋子
                    mlocation = location >>> 3;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第三格为空位置
                        return mlocation;
                    }
                }
            }
        }
        if ((location & 0x1010101010101010L) != 0x0L) {//Column E1 第五列
            long mlocation = location >>> 1;
            if ((mlocation & opponent_Board) != 0x0L) {//如果左边第一格是对方的棋子
                mlocation = location >>> 2;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左边第二格是对方的棋子
                    mlocation = location >>> 3;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左边第三格是对方的棋子
                        mlocation = location >>> 4;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第四格为空位置
                            return mlocation;
                        }
                    }
                }
            }
        }
        if ((location & 0x2020202020202020L) != 0x0L) {//Column F1 第六列
            long mlocation = location >>> 1;
            if ((mlocation & opponent_Board) != 0x0L) {//如果左边第一格是对方的棋子
                mlocation = location >>> 2;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左边第二格是对方的棋子
                    mlocation = location >>> 3;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左边第三格是对方的棋子
                        mlocation = location >>> 4;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第四格为空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左边第四格是对方的棋子
                            mlocation = location >>> 5;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第五格为空位置
                                return mlocation;
                            }
                        }
                    }
                }
            }
        }
        if ((location & 0x4040404040404040L) != 0x0L) {//Column G1 第七列
            long mlocation = location >>> 1;
            if ((mlocation & opponent_Board) != 0x0L) {//如果左边第一格是对方的棋子
                mlocation = location >>> 2;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左边第二格是对方的棋子
                    mlocation = location >>> 3;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左边第三格是对方的棋子
                        mlocation = location >>> 4;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第四格为空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左边第四格是对方的棋子
                            mlocation = location >>> 5;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第五格为空位置
                                return mlocation;
                            } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左边第五格是对方的棋子
                                mlocation = location >>> 6;
                                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第六格为空位置
                                    return mlocation;
                                }
                            }
                        }
                    }
                }
            }
        }
        if ((location & 0x8080808080808080L) != 0x0L) {//Column H1 第八列
            long mlocation = location >>> 1;
            if ((mlocation & opponent_Board) != 0x0L) {//如果左边第一格是对方的棋子
                mlocation = location >>> 2;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左边第二格是对方的棋子
                    mlocation = location >>> 3;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左边第三格是对方的棋子
                        mlocation = location >>> 4;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第四格为空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左边第四格是对方的棋子
                            mlocation = location >>> 5;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第五格为空位置
                                return mlocation;
                            } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左边第五格是对方的棋子
                                mlocation = location >>> 6;
                                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第六格为空位置
                                    return mlocation;
                                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左边第六格是对方的棋子
                                    mlocation = location >>> 7;
                                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左边第七格为空位置
                                        return mlocation;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0x0L;
    }

    public long up_Right(long location, long opponent_Board, long my_Board) {
        if ((location & 0x80808080808080ffL) != 0x0L) {//Row A1 and Column H8
            return 0x0L;
        }
        if ((location & 0x4040404040407f00L) != 0x0L) {//Row A2 and Column G8
            return 0x0L;
        }
        if ((location & 0x20202020203f0000L) != 0x0L) {//Row A3 and Column F8
            long mlocation = location >>> 7;
            if ((mlocation & opponent_Board) != 0x0L) {//如果右上第一格是对方的棋子
                mlocation = location >>> 14;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第二格为空位置
                    return mlocation;
                }
            }
        }
        if ((location & 0x101010101f000000L) != 0x0L) {//Row A4 and Column E8
            long mlocation = location >>> 7;
            if ((mlocation & opponent_Board) != 0x0L) {//如果右上第一格是对方的棋子
                mlocation = location >>> 14;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//如果右上方第二格为对方棋子
                    mlocation = location >>> 21;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第三格为空位置
                        return mlocation;
                    }
                }
            }
        }
        if ((location & 0x808080f00000000L) != 0x0L) {//Row A5 and Column D8
            long mlocation = location >>> 7;
            if ((mlocation & opponent_Board) != 0x0L) {//如果右上第一格是对方的棋子
                mlocation = location >>> 14;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//如果右上方第二格为对方棋子
                    mlocation = location >>> 21;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//如果右上方第三格为对方棋子
                        mlocation = location >>> 28;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第四格为空位置
                            return mlocation;
                        }
                    }
                }
            }
        }
        if ((location & 0x404070000000000L) != 0x0L) {//Row A6 and Column C8
            long mlocation = location >>> 7;
            if ((mlocation & opponent_Board) != 0x0L) {//如果右上第一格是对方的棋子
                mlocation = location >>> 14;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//如果右上方第二格为对方棋子
                    mlocation = location >>> 21;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//如果右上方第三格为对方棋子
                        mlocation = location >>> 28;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第四格为空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//如果右上方第四格为对方棋子
                            mlocation = location >>> 35;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第五格为空位置
                                return mlocation;
                            }
                        }
                    }
                }
            }
        }
        if ((location & 0x203000000000000L) != 0x0L) {//Row A7 and Column B8
            long mlocation = location >>> 7;
            if ((mlocation & opponent_Board) != 0x0L) {//如果右上第一格是对方的棋子
                mlocation = location >>> 14;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//如果右上方第二格为对方棋子
                    mlocation = location >>> 21;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//如果右上方第三格为对方棋子
                        mlocation = location >>> 28;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第四格为空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//如果右上方第四格为对方棋子
                            mlocation = location >>> 35;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第五格为空位置
                                return mlocation;
                            } else if ((mlocation & opponent_Board) != 0x0L) {//如果右上方第五格为对方棋子
                                mlocation = location >>> 42;
                                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第六格为空位置
                                    return mlocation;
                                }
                            }
                        }
                    }
                }
            }
        }
        if ((location & 0x100000000000000L) != 0x0L) {//Row A8 and Column A8
            long mlocation = location >>> 7;
            if ((mlocation & opponent_Board) != 0x0L) {//如果右上第一格是对方的棋子
                mlocation = location >>> 14;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//如果右上方第二格为对方棋子
                    mlocation = location >>> 21;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//如果右上方第三格为对方棋子
                        mlocation = location >>> 28;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第四格为空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//如果右上方第四格为对方棋子
                            mlocation = location >>> 35;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第五格为空位置
                                return mlocation;
                            } else if ((mlocation & opponent_Board) != 0x0L) {//如果右上方第五格为对方棋子
                                mlocation = location >>> 42;
                                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第六格为空位置
                                    return mlocation;
                                } else if ((mlocation & opponent_Board) != 0x0L) {//如果右上方第六格为对方棋子
                                    mlocation = location >>> 49;
                                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右上方第七格为空位置
                                        return mlocation;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0x0L;
    }

    public long up_Left(long location, long opponent_Board, long my_Board) {
        if ((location & 0x1010101010101ffL) != 0x0L) {//Row A1 and Column A8
            return 0x0L;
        }
        if ((location & 0x20202020202fe00L) != 0x0L) {//Row A2 and Column B8
            return 0x0L;
        }
        if ((location & 0x404040404fc0000L) != 0x0L) {//Row A3 and Column C8
            long mlocation = location >>> 9;
            if ((mlocation & opponent_Board) != 0x0L) {//如果左上角第一格为对方的棋子
                mlocation = location >>> 18;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第二格为空位置
                    return mlocation;
                }
            }
        }
        if ((location & 0x8080808f8000000L) != 0x0L) {//Row A4 and Column D8
            long mlocation = location >>> 9;
            if ((mlocation & opponent_Board) != 0x0L) {//如果左上角第一格为对方的棋子
                mlocation = location >>> 18;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左上角第二格为对方的棋子
                    mlocation = location >>> 27;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第三格为空位置
                        return mlocation;
                    }
                }
            }
        }
        if ((location & 0x101010f000000000L) != 0x0L) {//Row A5 and Column E8
            long mlocation = location >>> 9;
            if ((mlocation & opponent_Board) != 0x0L) {//如果左上角第一格为对方的棋子
                mlocation = location >>> 18;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左上角第二格为对方的棋子
                    mlocation = location >>> 27;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左上角第三格为对方的棋子
                        mlocation = location >>> 36;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第四格为空位置
                            return mlocation;
                        }
                    }
                }
            }
        }
        if ((location & 0x2020e00000000000L) != 0x0L) {//Row A6 and Column F8
            long mlocation = location >>> 9;
            if ((mlocation & opponent_Board) != 0x0L) {//如果左上角第一格为对方的棋子
                mlocation = location >>> 18;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左上角第二格为对方的棋子
                    mlocation = location >>> 27;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左上角第三格为对方的棋子
                        mlocation = location >>> 36;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第四格为空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左上角第四格为对方的棋子
                            mlocation = location >>> 45;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第五格为空位置
                                return mlocation;
                            }
                        }
                    }
                }
            }
        }
        if ((location & 0x40c0000000000000L) != 0x0L) {//Row A7 and Column G8
            long mlocation = location >>> 9;
            if ((mlocation & opponent_Board) != 0x0L) {//如果左上角第一格为对方的棋子
                mlocation = location >>> 18;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左上角第二格为对方的棋子
                    mlocation = location >>> 27;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左上角第三格为对方的棋子
                        mlocation = location >>> 36;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第四格为空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左上角第四格为对方的棋子
                            mlocation = location >>> 45;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第五格为空位置
                                return mlocation;
                            } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左上角第五格为对方的棋子
                                mlocation = location >>> 54;
                                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第六格为空位置
                                    return mlocation;
                                }
                            }
                        }
                    }
                }
            }
        }
        if ((location & 0x8000000000000000L) != 0x0L) {//Row A8 and Column H8
            long mlocation = location >>> 9;
            if ((mlocation & opponent_Board) != 0x0L) {//如果左上角第一格为对方的棋子
                mlocation = location >>> 18;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左上角第二格为对方的棋子
                    mlocation = location >>> 27;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左上角第三格为对方的棋子
                        mlocation = location >>> 36;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第四格为空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左上角第四格为对方的棋子
                            mlocation = location >>> 45;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第五格为空位置
                                return mlocation;
                            } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左上角第五格为对方的棋子
                                mlocation = location >>> 54;
                                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第六格为空位置
                                    return mlocation;
                                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左上角第六格为对方的棋子
                                    mlocation = location >>> 63;
                                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左上角第七格为空位置
                                        return mlocation;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0x0L;
    }

    public long down_Right(long location, long opponent_Board, long my_Board) {
        if ((location & 0xff80808080808080L) != 0x0L) {//Row A8 and Column H1
            return 0x0L;
        }
        if ((location & 0x7f404040404040L) != 0x0L) {//Row A7 and Column G1
            return 0x0L;
        }
        if ((location & 0x3f2020202020L) != 0x0L) {//Row A6 and Column F1
            long mlocation = location << 9;
            if ((mlocation & opponent_Board) != 0x0L) {//如果右下角第一格为对方的棋子
                mlocation = location << 18;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第二格为空位置
                    return mlocation;
                }
            }
        }
        if ((location & 0x1f10101010L) != 0x0L) {//Row A5 and Column E1
            long mlocation = location << 9;
            if ((mlocation & opponent_Board) != 0x0L) {//如果右下角第一格为对方的棋子
                mlocation = location << 18;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右下角第二格为对方的棋子
                    mlocation = location << 27;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第三格为空位置
                        return mlocation;
                    }
                }
            }
        }
        if ((location & 0xf080808L) != 0x0L) {//Row A4 and Column D1
            long mlocation = location << 9;
            if ((mlocation & opponent_Board) != 0x0L) {//如果右下角第一格为对方的棋子
                mlocation = location << 18;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右下角第二格为对方的棋子
                    mlocation = location << 27;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右下角第三格为对方的棋子
                        mlocation = location << 36;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第四格为空位置
                            return mlocation;
                        }
                    }
                }
            }
        }
        if ((location & 0x70404L) != 0x0L) {//Row A3 and Column C1
            long mlocation = location << 9;
            if ((mlocation & opponent_Board) != 0x0L) {//如果右下角第一格为对方的棋子
                mlocation = location << 18;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右下角第二格为对方的棋子
                    mlocation = location << 27;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右下角第三格为对方的棋子
                        mlocation = location << 36;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第四格为空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右下角第四格为对方的棋子
                            mlocation = location << 45;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第五格为空位置
                                return mlocation;
                            }
                        }
                    }
                }
            }
        }
        if ((location & 0x302L) != 0x0L) {//Row A2 and Column B1
            long mlocation = location << 9;
            if ((mlocation & opponent_Board) != 0x0L) {//如果右下角第一格为对方的棋子
                mlocation = location << 18;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右下角第二格为对方的棋子
                    mlocation = location << 27;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右下角第三格为对方的棋子
                        mlocation = location << 36;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第四格为空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右下角第四格为对方的棋子
                            mlocation = location << 45;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第五格为空位置
                                return mlocation;
                            } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右下角第五格为对方的棋子
                                mlocation = location << 54;
                                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第六格为空位置
                                    return mlocation;
                                }
                            }
                        }
                    }
                }
            }
        }
        if ((location & 0x1L) != 0x0L) {//Row A1 and Column A1
            long mlocation = location << 9;
            if ((mlocation & opponent_Board) != 0x0L) {//如果右下角第一格为对方的棋子
                mlocation = location << 18;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右下角第二格为对方的棋子
                    mlocation = location << 27;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右下角第三格为对方的棋子
                        mlocation = location << 36;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第四格为空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右下角第四格为对方的棋子
                            mlocation = location << 45;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第五格为空位置
                                return mlocation;
                            } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右下角第五格为对方的棋子
                                mlocation = location << 54;
                                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第六格为空位置
                                    return mlocation;
                                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果右下角第六格为对方的棋子
                                    mlocation = location << 63;
                                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果右下角第七格为空位置
                                        return mlocation;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0x0L;
    }

    public long down_Left(long location, long opponent_Board, long my_Board) {
        if ((location & 0xff01010101010101L) != 0x0L) {//Row A8 and Column A1
            return 0x0L;
        }
        if ((location & 0xfe020202020202L) != 0x0L) {//Row A7 and Column B1
            return 0x0L;
        }
        if ((location & 0xfc0404040404L) != 0x0L) {//Row A6 and Column C1
            long mlocation = location << 7;
            if ((mlocation & opponent_Board) != 0x0L) {//如果左下角第一格是对方的棋子
                mlocation = location << 14;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第二格为空位置
                    return mlocation;
                }
            }
        }
        if ((location & 0xf808080808L) != 0x0L) {//Row A5 and Column D1
            long mlocation = location << 7;
            if ((mlocation & opponent_Board) != 0x0L) {//如果左下角第一格是对方的棋子
                mlocation = location << 14;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左下角第二格是对方的棋子
                    mlocation = location << 21;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第三格为空位置
                        return mlocation;
                    }
                }
            }
        }
        if ((location & 0xf0101010L) != 0x0L) {//Row and Column
            long mlocation = location << 7;
            if ((mlocation & opponent_Board) != 0x0L) {//如果左下角第一格是对方的棋子
                mlocation = location << 14;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左下角第二格是对方的棋子
                    mlocation = location << 21;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左下角第三格是对方的棋子
                        mlocation = location << 28;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第四格为空位置
                            return mlocation;
                        }
                    }
                }
            }
        }
        if ((location & 0xe02020L) != 0x0L) {//Row and Column
            long mlocation = location << 7;
            if ((mlocation & opponent_Board) != 0x0L) {//如果左下角第一格是对方的棋子
                mlocation = location << 14;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左下角第二格是对方的棋子
                    mlocation = location << 21;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左下角第三格是对方的棋子
                        mlocation = location << 28;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第四格为空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左下角第四格是对方的棋子
                            mlocation = location << 35;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第五格为空位置
                                return mlocation;
                            }
                        }
                    }
                }
            }
        }
        if ((location & 0xc040L) != 0x0L) {//Row and Column
            long mlocation = location << 7;
            if ((mlocation & opponent_Board) != 0x0L) {//如果左下角第一格是对方的棋子
                mlocation = location << 14;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左下角第二格是对方的棋子
                    mlocation = location << 21;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左下角第三格是对方的棋子
                        mlocation = location << 28;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第四格为空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左下角第四格是对方的棋子
                            mlocation = location << 35;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第五格为空位置
                                return mlocation;
                            } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左下角第五格是对方的棋子
                                mlocation = location << 42;
                                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第六格为空位置
                                    return mlocation;
                                }
                            }
                        }
                    }
                }
            }
        }
        if ((location & 0x80L) != 0x0L) {//Row and Column
            long mlocation = location << 7;
            if ((mlocation & opponent_Board) != 0x0L) {//如果左下角第一格是对方的棋子
                mlocation = location << 14;
                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第二格为空位置
                    return mlocation;
                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左下角第二格是对方的棋子
                    mlocation = location << 21;
                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第三格为空位置
                        return mlocation;
                    } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左下角第三格是对方的棋子
                        mlocation = location << 28;
                        if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第四格为空位置
                            return mlocation;
                        } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左下角第四格是对方的棋子
                            mlocation = location << 35;
                            if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第五格为空位置
                                return mlocation;
                            } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左下角第五格是对方的棋子
                                mlocation = location << 42;
                                if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第六格为空位置
                                    return mlocation;
                                } else if ((mlocation & opponent_Board) != 0x0L) {//否则，如果左下角第六格是对方的棋子
                                    mlocation = location << 49;
                                    if ((mlocation & opponent_Board) == 0x0L && (mlocation & my_Board) == 0x0L) {//如果左下角第七格为空位置
                                        return mlocation;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0x0L;
    }

    //--------------------------------------------------------------------------------------

    public long reversal(long location, long opponent_Board, long my_Board) {

        long reversal_Result=0x0L;
        reversal_Result|=up_Reversal(location,opponent_Board,my_Board);
        reversal_Result|=right_Reversal(location,opponent_Board,my_Board);
        reversal_Result|=down_Reversal(location,opponent_Board,my_Board);
        reversal_Result|=left_Reversal(location,opponent_Board,my_Board);
        reversal_Result|=up_Right_Reversal(location,opponent_Board,my_Board);
        reversal_Result|=up_Left_Reversal(location,opponent_Board,my_Board);
        reversal_Result|=down_Right_Reversal(location,opponent_Board,my_Board);
        reversal_Result|=down_Left_Reversal(location,opponent_Board,my_Board);
        return reversal_Result;
    }

    public long up_Reversal(long location, long opponent_Board, long my_Board) {
        if ((location & 0xffL) != 0x0L) {//Row A1
            return 0x0L;
        }
        if ((location & 0xff00L) != 0x0L) {//Row A2
            return 0x0L;
        }
        if ((location & 0xff0000L) != 0x0L) {//Row A3
            if ((my_Board & (location >>> 16)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L) {
                return location >>> 8;
            }
        }
        if ((location & 0xff000000L) != 0x0L) {//Row A4
            if ((my_Board & (location >>> 16)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L) {
                return location >>> 8;
            } else if ((my_Board & (location >>> 24)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L
                    &&(opponent_Board&(location>>>16))!=0x0L) {
                return location >>> 8 | location >>> 16;
            }
        }
        if ((location & 0xff00000000L) != 0x0L) {//Row A5
            if ((my_Board & (location >>> 16)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L) {
                return location >>> 8;
            } else if ((my_Board & (location >>> 24)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L
                    &&(opponent_Board&(location>>>16))!=0x0L) {
                return location >>> 8 | location >>> 16;
            } else if ((my_Board & (location >>> 32)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L
                    &&(opponent_Board&(location>>>16))!=0x0L
                    &&(opponent_Board&(location>>>24))!=0x0L) {
                return location >>> 8 | location >>> 16 | location >>> 24;
            }
        }
        if ((location & 0xff0000000000L) != 0x0L) {//Row A6
            if ((my_Board & (location >>> 16)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L) {
                return location >>> 8;
            } else if ((my_Board & (location >>> 24)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L
                    &&(opponent_Board&(location>>>16))!=0x0L) {
                return location >>> 8 | location >>> 16;
            } else if ((my_Board & (location >>> 32)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L
                    &&(opponent_Board&(location>>>16))!=0x0L
                    &&(opponent_Board&(location>>>24))!=0x0L) {
                return location >>> 8 | location >>> 16 | location >>> 24;
            } else if ((my_Board & (location >>> 40)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L
                    &&(opponent_Board&(location>>>16))!=0x0L
                    &&(opponent_Board&(location>>>24))!=0x0L
                    &&(opponent_Board&(location>>>32))!=0x0L) {
                return location >>> 8 | location >>> 16 | location >>> 24 | location >>> 32;
            }
        }
        if ((location & 0xff000000000000L) != 0x0L) {//Row A7
            if ((my_Board & (location >>> 16)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L) {
                return location >>> 8;
            } else if ((my_Board & (location >>> 24)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L
                    &&(opponent_Board&(location>>>16))!=0x0L) {
                return location >>> 8 | location >>> 16;
            } else if ((my_Board & (location >>> 32)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L
                    &&(opponent_Board&(location>>>16))!=0x0L
                    &&(opponent_Board&(location>>>24))!=0x0L) {
                return location >>> 8 | location >>> 16 | location >>> 24;
            } else if ((my_Board & (location >>> 40)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L
                    &&(opponent_Board&(location>>>16))!=0x0L
                    &&(opponent_Board&(location>>>24))!=0x0L
                    &&(opponent_Board&(location>>>32))!=0x0L) {
                return location >>> 8 | location >>> 16 | location >>> 24 | location >>> 32;
            } else if ((my_Board & (location >>> 48)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L
                    &&(opponent_Board&(location>>>16))!=0x0L
                    &&(opponent_Board&(location>>>24))!=0x0L
                    &&(opponent_Board&(location>>>32))!=0x0L
                    &&(opponent_Board&(location>>>40))!=0x0L) {
                return location >>> 8 | location >>> 16 | location >>> 24 | location >>> 32 | location >>> 40;
            }
        }
        if ((location & 0xff00000000000000L) != 0x0L) {//Row A8
            if ((my_Board & (location >>> 16)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L) {
                return location >>> 8;
            } else if ((my_Board & (location >>> 24)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L
                    &&(opponent_Board&(location>>>16))!=0x0L) {
                return location >>> 8 | location >>> 16;
            } else if ((my_Board & (location >>> 32)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L
                    &&(opponent_Board&(location>>>16))!=0x0L
                    &&(opponent_Board&(location>>>24))!=0x0L) {
                return location >>> 8 | location >>> 16 | location >>> 24;
            } else if ((my_Board & (location >>> 40)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L
                    &&(opponent_Board&(location>>>16))!=0x0L
                    &&(opponent_Board&(location>>>24))!=0x0L
                    &&(opponent_Board&(location>>>32))!=0x0L) {
                return location >>> 8 | location >>> 16 | location >>> 24 | location >>> 32;
            } else if ((my_Board & (location >>> 48)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L
                    &&(opponent_Board&(location>>>16))!=0x0L
                    &&(opponent_Board&(location>>>24))!=0x0L
                    &&(opponent_Board&(location>>>32))!=0x0L
                    &&(opponent_Board&(location>>>40))!=0x0L) {
                return location >>> 8 | location >>> 16 | location >>> 24 | location >>> 32 | location >>> 40;
            } else if ((my_Board & (location >>> 56)) != 0x0L
                    &&(opponent_Board&(location>>>8))!=0x0L
                    &&(opponent_Board&(location>>>16))!=0x0L
                    &&(opponent_Board&(location>>>24))!=0x0L
                    &&(opponent_Board&(location>>>32))!=0x0L
                    &&(opponent_Board&(location>>>40))!=0x0L
                    &&(opponent_Board&(location>>>48))!=0x0L) {
                return location >>> 8 | location >>> 16 | location >>> 24 | location >>> 32 | location >>> 40 | location >>> 48;
            }
        }
        return 0x0L;
    }

    public long right_Reversal(long location, long opponent_Board, long my_Board) {
        if((location&0x101010101010101L)!=0x0L){//Column A1
            if((my_Board&(location<<2))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L){
                return location<<1;
            }else if((my_Board&(location<<3))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L
                    &&(opponent_Board&(location<<2))!=0x0L){
                return location<<1|location<<2;
            }else if((my_Board&(location<<4))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L
                    &&(opponent_Board&(location<<2))!=0x0L
                    &&(opponent_Board&(location<<3))!=0x0L){
                return location<<1|location<<2|location<<3;
            }else if((my_Board&(location<<5))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L
                    &&(opponent_Board&(location<<2))!=0x0L
                    &&(opponent_Board&(location<<3))!=0x0L
                    &&(opponent_Board&(location<<4))!=0x0L){
                return location<<1|location<<2|location<<3|location<<4;
            }else if((my_Board&(location<<6))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L
                    &&(opponent_Board&(location<<2))!=0x0L
                    &&(opponent_Board&(location<<3))!=0x0L
                    &&(opponent_Board&(location<<4))!=0x0L
                    &&(opponent_Board&(location<<5))!=0x0L){
                return location<<1|location<<2|location<<3|location<<4|location<<5;
            }else if((my_Board&(location<<7))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L
                    &&(opponent_Board&(location<<2))!=0x0L
                    &&(opponent_Board&(location<<3))!=0x0L
                    &&(opponent_Board&(location<<4))!=0x0L
                    &&(opponent_Board&(location<<5))!=0x0L
                    &&(opponent_Board&(location<<6))!=0x0L){
                return location<<1|location<<2|location<<3|location<<4|location<<5|location<<6;
            }
        }
        if((location&0x202020202020202L)!=0x0L){//Column B1
            if((my_Board&(location<<2))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L){
                return location<<1;
            }else if((my_Board&(location<<3))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L
                    &&(opponent_Board&(location<<2))!=0x0L){
                return location<<1|location<<2;
            }else if((my_Board&(location<<4))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L
                    &&(opponent_Board&(location<<2))!=0x0L
                    &&(opponent_Board&(location<<3))!=0x0L){
                return location<<1|location<<2|location<<3;
            }else if((my_Board&(location<<5))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L
                    &&(opponent_Board&(location<<2))!=0x0L
                    &&(opponent_Board&(location<<3))!=0x0L
                    &&(opponent_Board&(location<<4))!=0x0L){
                return location<<1|location<<2|location<<3|location<<4;
            }else if((my_Board&(location<<6))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L
                    &&(opponent_Board&(location<<2))!=0x0L
                    &&(opponent_Board&(location<<3))!=0x0L
                    &&(opponent_Board&(location<<4))!=0x0L
                    &&(opponent_Board&(location<<5))!=0x0L){
                return location<<1|location<<2|location<<3|location<<4|location<<5;
            }
        }
        if((location&0x404040404040404L)!=0x0L){//Column C1
            if((my_Board&(location<<2))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L){
                return location<<1;
            }else if((my_Board&(location<<3))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L
                    &&(opponent_Board&(location<<2))!=0x0L){
                return location<<1|location<<2;
            }else if((my_Board&(location<<4))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L
                    &&(opponent_Board&(location<<2))!=0x0L
                    &&(opponent_Board&(location<<3))!=0x0L){
                return location<<1|location<<2|location<<3;
            }else if((my_Board&(location<<5))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L
                    &&(opponent_Board&(location<<2))!=0x0L
                    &&(opponent_Board&(location<<3))!=0x0L
                    &&(opponent_Board&(location<<4))!=0x0L){
                return location<<1|location<<2|location<<3|location<<4;
            }
        }
        if((location&0x808080808080808L)!=0x0L){//Column D1
            if((my_Board&(location<<2))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L){
                return location<<1;
            }else if((my_Board&(location<<3))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L
                    &&(opponent_Board&(location<<2))!=0x0L){
                return location<<1|location<<2;
            }else if((my_Board&(location<<4))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L
                    &&(opponent_Board&(location<<2))!=0x0L
                    &&(opponent_Board&(location<<3))!=0x0L){
                return location<<1|location<<2|location<<3;
            }
        }
        if((location&0x1010101010101010L)!=0x0L){//Column E1
            if((my_Board&(location<<2))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L){
                return location<<1;
            }else if((my_Board&(location<<3))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L
                    &&(opponent_Board&(location<<2))!=0x0L){
                return location<<1|location<<2;
            }
        }
        if((location&0x2020202020202020L)!=0x0L){//Column F1
            if((my_Board&(location<<2))!=0x0L
                    &&(opponent_Board&(location<<1))!=0x0L){
                return location<<1;
            }
        }
        if((location&0x4040404040404040L)!=0x0L){//Column G1
            return 0x0L;
        }
        if((location&0x8080808080808080L)!=0x0L){//Column H1
            return 0x0L;
        }
        return 0x0L;
    }

    public long down_Reversal(long location, long opponent_Board, long my_Board) {

        if ((location & 0xffL) != 0x0L) {//Row A1

            if ((my_Board & (location << 16)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L) {
                return location << 8;
            } else if ((my_Board & (location << 24)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L
                    &&(opponent_Board&(location<<16))!=0x0L) {
                return location << 8 | location << 16;
            } else if ((my_Board & (location << 32)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L
                    &&(opponent_Board&(location<<16))!=0x0L
                    &&(opponent_Board&(location<<24))!=0x0L) {
                return location << 8 | location << 16 | location << 24;
            } else if ((my_Board & (location << 40)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L
                    &&(opponent_Board&(location<<16))!=0x0L
                    &&(opponent_Board&(location<<24))!=0x0L
                    &&(opponent_Board&(location<<32))!=0x0L) {
                return location << 8 | location << 16 | location << 24 | location << 32;
            } else if ((my_Board & (location << 48)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L
                    &&(opponent_Board&(location<<16))!=0x0L
                    &&(opponent_Board&(location<<24))!=0x0L
                    &&(opponent_Board&(location<<32))!=0x0L
                    &&(opponent_Board&(location<<40))!=0x0L) {
                return location << 8 | location << 16 | location << 24 | location << 32 | location << 40;
            } else if ((my_Board & (location << 56)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L
                    &&(opponent_Board&(location<<16))!=0x0L
                    &&(opponent_Board&(location<<24))!=0x0L
                    &&(opponent_Board&(location<<32))!=0x0L
                    &&(opponent_Board&(location<<40))!=0x0L
                    &&(opponent_Board&(location<<48))!=0x0L) {
                return location << 8 | location << 16 | location << 24 | location << 32 | location << 40 | location << 48;
            }
        }
        if ((location & 0xff00L) != 0x0L) {//Row A2
            if ((my_Board & (location << 16)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L) {
                return location << 8;
            } else if ((my_Board & (location << 24)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L
                    &&(opponent_Board&(location<<16))!=0x0L) {
                return location << 8 | location << 16;
            } else if ((my_Board & (location << 32)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L
                    &&(opponent_Board&(location<<16))!=0x0L
                    &&(opponent_Board&(location<<24))!=0x0L) {
                return location << 8 | location << 16 | location << 24;
            } else if ((my_Board & (location << 40)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L
                    &&(opponent_Board&(location<<16))!=0x0L
                    &&(opponent_Board&(location<<24))!=0x0L
                    &&(opponent_Board&(location<<32))!=0x0L) {
                return location << 8 | location << 16 | location << 24 | location << 32;
            } else if ((my_Board & (location << 48)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L
                    &&(opponent_Board&(location<<16))!=0x0L
                    &&(opponent_Board&(location<<24))!=0x0L
                    &&(opponent_Board&(location<<32))!=0x0L
                    &&(opponent_Board&(location<<40))!=0x0L) {
                return location << 8 | location << 16 | location << 24 | location << 32 | location << 40;
            }
        }
        if ((location & 0xff0000L) != 0x0L) {//Row A3
            if ((my_Board & (location << 16)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L) {
                return location << 8;
            } else if ((my_Board & (location << 24)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L
                    &&(opponent_Board&(location<<16))!=0x0L) {
                return location << 8 | location << 16;
            } else if ((my_Board & (location << 32)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L
                    &&(opponent_Board&(location<<16))!=0x0L
                    &&(opponent_Board&(location<<24))!=0x0L) {
                return location << 8 | location << 16 | location << 24;
            } else if ((my_Board & (location << 40)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L
                    &&(opponent_Board&(location<<16))!=0x0L
                    &&(opponent_Board&(location<<24))!=0x0L
                    &&(opponent_Board&(location<<32))!=0x0L) {
                return location << 8 | location << 16 | location << 24 | location << 32;
            }
        }
        if ((location & 0xff000000L) != 0x0L) {//Row A4
            if ((my_Board & (location << 16)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L) {
                return location << 8;
            } else if ((my_Board & (location << 24)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L
                    &&(opponent_Board&(location<<16))!=0x0L) {
                return location << 8 | location << 16;
            } else if ((my_Board & (location << 32)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L
                    &&(opponent_Board&(location<<16))!=0x0L
                    &&(opponent_Board&(location<<24))!=0x0L) {
                return location << 8 | location << 16 | location << 24;
            }
        }
        if ((location & 0xff00000000L) != 0x0L) {//Row A5
            if ((my_Board & (location << 16)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L) {
                return location << 8;
            } else if ((my_Board & (location << 24)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L
                    &&(opponent_Board&(location<<16))!=0x0L) {
                return location << 8 | location << 16;
            }
        }
        if ((location & 0xff0000000000L) != 0x0L) {//Row A6
            if ((my_Board & (location << 16)) != 0x0L
                    &&(opponent_Board&(location<<8))!=0x0L) {
                return location << 8;
            }
        }
        if ((location & 0xff000000000000L) != 0x0L) {//Row A7
            return 0x0L;
        }
        if ((location & 0xff00000000000000L) != 0x0L) {//Row A8
            return 0x0L;
        }

        return 0x0L;
    }

    public long left_Reversal(long location, long opponent_Board, long my_Board){
        if((location&0x101010101010101L)!=0x0L){//Column A1
            return 0x0L;
        }
        if((location&0x202020202020202L)!=0x0L){//Column B1
            return 0x0L;
        }
        if((location&0x404040404040404L)!=0x0L){//Column C1
            if((my_Board&(location>>>2))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L){
                return location>>>1;
            }
        }
        if((location&0x808080808080808L)!=0x0L){//Column D1
            if((my_Board&(location>>>2))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L){
                return location>>>1;
            }else if((my_Board&(location>>>3))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L
                    &&(opponent_Board&(location>>>2))!=0x0L){
                return location>>>1|location>>>2;
            }
        }
        if((location&0x1010101010101010L)!=0x0L){//Column E1
            if((my_Board&(location>>>2))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L){
                return location>>>1;
            }else if((my_Board&(location>>>3))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L
                    &&(opponent_Board&(location>>>2))!=0x0L){
                return location>>>1|location>>>2;
            }else if((my_Board&(location>>>4))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L
                    &&(opponent_Board&(location>>>2))!=0x0L
                    &&(opponent_Board&(location>>>3))!=0x0L){
                return location>>>1|location>>>2|location>>>3;
            }
        }
        if((location&0x2020202020202020L)!=0x0L){//Column F1
            if((my_Board&(location>>>2))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L){
                return location>>>1;
            }else if((my_Board&(location>>>3))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L
                    &&(opponent_Board&(location>>>2))!=0x0L){
                return location>>>1|location>>>2;
            }else if((my_Board&(location>>>4))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L
                    &&(opponent_Board&(location>>>2))!=0x0L
                    &&(opponent_Board&(location>>>3))!=0x0L){
                return location>>>1|location>>>2|location>>>3;
            }else if((my_Board&(location>>>5))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L
                    &&(opponent_Board&(location>>>2))!=0x0L
                    &&(opponent_Board&(location>>>3))!=0x0L
                    &&(opponent_Board&(location>>>4))!=0x0L){
                return location>>>1|location>>>2|location>>>3|location>>>4;
            }
        }
        if((location&0x4040404040404040L)!=0x0L){//Column G1
            if((my_Board&(location>>>2))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L){
                return location>>>1;
            }else if((my_Board&(location>>>3))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L
                    &&(opponent_Board&(location>>>2))!=0x0L){
                return location>>>1|location>>>2;
            }else if((my_Board&(location>>>4))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L
                    &&(opponent_Board&(location>>>2))!=0x0L
                    &&(opponent_Board&(location>>>3))!=0x0L){
                return location>>>1|location>>>2|location>>>3;
            }else if((my_Board&(location>>>5))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L
                    &&(opponent_Board&(location>>>2))!=0x0L
                    &&(opponent_Board&(location>>>3))!=0x0L
                    &&(opponent_Board&(location>>>4))!=0x0L){
                return location>>>1|location>>>2|location>>>3|location>>>4;
            }else if((my_Board&(location>>>6))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L
                    &&(opponent_Board&(location>>>2))!=0x0L
                    &&(opponent_Board&(location>>>3))!=0x0L
                    &&(opponent_Board&(location>>>4))!=0x0L
                    &&(opponent_Board&(location>>>5))!=0x0L){
                return location>>>1|location>>>2|location>>>3|location>>>4|location>>>5;
            }
        }
        if((location&0x8080808080808080L)!=0x0L){//Column H1
            if((my_Board&(location>>>2))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L){
                return location>>>1;
            }else if((my_Board&(location>>>3))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L
                    &&(opponent_Board&(location>>>2))!=0x0L){
                return location>>>1|location>>>2;
            }else if((my_Board&(location>>>4))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L
                    &&(opponent_Board&(location>>>2))!=0x0L
                    &&(opponent_Board&(location>>>3))!=0x0L){
                return location>>>1|location>>>2|location>>>3;
            }else if((my_Board&(location>>>5))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L
                    &&(opponent_Board&(location>>>2))!=0x0L
                    &&(opponent_Board&(location>>>3))!=0x0L
                    &&(opponent_Board&(location>>>4))!=0x0L){
                return location>>>1|location>>>2|location>>>3|location>>>4;
            }else if((my_Board&(location>>>6))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L
                    &&(opponent_Board&(location>>>2))!=0x0L
                    &&(opponent_Board&(location>>>3))!=0x0L
                    &&(opponent_Board&(location>>>4))!=0x0L
                    &&(opponent_Board&(location>>>5))!=0x0L){
                return location>>>1|location>>>2|location>>>3|location>>>4|location>>>5;
            }else if((my_Board&(location>>>7))!=0x0L
                    &&(opponent_Board&(location>>>1))!=0x0L
                    &&(opponent_Board&(location>>>2))!=0x0L
                    &&(opponent_Board&(location>>>3))!=0x0L
                    &&(opponent_Board&(location>>>4))!=0x0L
                    &&(opponent_Board&(location>>>5))!=0x0L
                    &&(opponent_Board&(location>>>6))!=0x0L){
                return location>>>1|location>>>2|location>>>3|location>>>4|location>>>5|location>>>6;
            }
        }
        return 0x0L;
    }

    public long up_Right_Reversal(long location, long opponent_Board, long my_Board) {
        if((location&0x80808080808080ffL)!=0x0L){//Row A1 and Column H8
            return 0x0L;
        }
        if((location&0x4040404040407f00L)!=0x0L){//Row A2 and Column G8
            return 0x0L;
        }
        if((location&0x20202020203f0000L)!=0x0L){//Row A3 and Column F8
            if((my_Board&(location>>>14))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L){
                return location>>>7;
            }
        }
        if((location&0x101010101f000000L)!=0x0L){//Row A4 and Column E8
            if((my_Board&(location>>>14))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L){
                return location>>>7;
            }else if((my_Board&(location>>>21))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L
                    &&(opponent_Board&(location>>>14))!=0x0L){
                return location>>>7|location>>>14;
            }
        }
        if((location&0x808080f00000000L)!=0x0L){//Row A5 and Column D8
            if((my_Board&(location>>>14))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L){
                return location>>>7;
            }else if((my_Board&(location>>>21))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L
                    &&(opponent_Board&(location>>>14))!=0x0L){
                return location>>>7|location>>>14;
            }else if((my_Board&(location>>>28))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L
                    &&(opponent_Board&(location>>>14))!=0x0L
                    &&(opponent_Board&(location>>>21))!=0x0L){
                return location>>>7|location>>>14|location>>>21;
            }
        }
        if((location&0x404070000000000L)!=0x0L){//Row A6 and Column C8
            if((my_Board&(location>>>14))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L){
                return location>>>7;
            }else if((my_Board&(location>>>21))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L
                    &&(opponent_Board&(location>>>14))!=0x0L){
                return location>>>7|location>>>14;
            }else if((my_Board&(location>>>28))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L
                    &&(opponent_Board&(location>>>14))!=0x0L
                    &&(opponent_Board&(location>>>21))!=0x0L){
                return location>>>7|location>>>14|location>>>21;
            }else if((my_Board&(location>>>35))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L
                    &&(opponent_Board&(location>>>14))!=0x0L
                    &&(opponent_Board&(location>>>21))!=0x0L
                    &&(opponent_Board&(location>>>28))!=0x0L){
                return location>>>7|location>>>14|location>>>21|location>>>28;
            }
        }
        if((location&0x203000000000000L)!=0x0L){//Row A7 and Column B8
            if((my_Board&(location>>>14))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L){
                return location>>>7;
            }else if((my_Board&(location>>>21))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L
                    &&(opponent_Board&(location>>>14))!=0x0L){
                return location>>>7|location>>>14;
            }else if((my_Board&(location>>>28))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L
                    &&(opponent_Board&(location>>>14))!=0x0L
                    &&(opponent_Board&(location>>>21))!=0x0L){
                return location>>>7|location>>>14|location>>>21;
            }else if((my_Board&(location>>>35))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L
                    &&(opponent_Board&(location>>>14))!=0x0L
                    &&(opponent_Board&(location>>>21))!=0x0L
                    &&(opponent_Board&(location>>>28))!=0x0L){
                return location>>>7|location>>>14|location>>>21|location>>>28;
            }else if((my_Board&(location>>>42))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L
                    &&(opponent_Board&(location>>>14))!=0x0L
                    &&(opponent_Board&(location>>>21))!=0x0L
                    &&(opponent_Board&(location>>>28))!=0x0L
                    &&(opponent_Board&(location>>>35))!=0x0L){
                return location>>>7|location>>>14|location>>>21|location>>>28|location>>>35;
            }
        }
        if((location&0x100000000000000L)!=0x0L){//Row A8 and Column A8
            if((my_Board&(location>>>14))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L){
                return location>>>7;
            }else if((my_Board&(location>>>21))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L
                    &&(opponent_Board&(location>>>14))!=0x0L){
                return location>>>7|location>>>14;
            }else if((my_Board&(location>>>28))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L
                    &&(opponent_Board&(location>>>14))!=0x0L
                    &&(opponent_Board&(location>>>21))!=0x0L){
                return location>>>7|location>>>14|location>>>21;
            }else if((my_Board&(location>>>35))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L
                    &&(opponent_Board&(location>>>14))!=0x0L
                    &&(opponent_Board&(location>>>21))!=0x0L
                    &&(opponent_Board&(location>>>28))!=0x0L){
                return location>>>7|location>>>14|location>>>21|location>>>28;
            }else if((my_Board&(location>>>42))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L
                    &&(opponent_Board&(location>>>14))!=0x0L
                    &&(opponent_Board&(location>>>21))!=0x0L
                    &&(opponent_Board&(location>>>28))!=0x0L
                    &&(opponent_Board&(location>>>35))!=0x0L){
                return location>>>7|location>>>14|location>>>21|location>>>28|location>>>35;
            }else if((my_Board&(location>>>49))!=0x0L
                    &&(opponent_Board&(location>>>7))!=0x0L
                    &&(opponent_Board&(location>>>14))!=0x0L
                    &&(opponent_Board&(location>>>21))!=0x0L
                    &&(opponent_Board&(location>>>28))!=0x0L
                    &&(opponent_Board&(location>>>35))!=0x0L
                    &&(opponent_Board&(location>>>42))!=0x0L){
                return location>>>7|location>>>14|location>>>21|location>>>28|location>>>35|location>>>42;
            }
        }
        return 0x0L;
    }

    public long up_Left_Reversal(long location, long opponent_Board, long my_Board) {
        if((location&0x1010101010101ffL)!=0x0L){//Row A1 and Column A8
            return 0x0L;
        }
        if((location&0x20202020202fe00L)!=0x0L){//Row A2 and Column B8
            return 0x0L;
        }
        if((location&0x404040404fc0000L)!=0x0L){//Row A3 and Column C8
            if((my_Board&(location>>>18))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L){
                return location>>>9;
            }
        }
        if((location&0x8080808f8000000L)!=0x0L){//Row A4 and Column D8
            if((my_Board&(location>>>18))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L){
                return location>>>9;
            }else if((my_Board&(location>>>27))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L
                    &&(opponent_Board&(location>>>18))!=0x0L){
                return location>>>9|location>>>18;
            }
        }
        if((location&0x101010f000000000L)!=0x0L){//Row A5 and Column E8
            if((my_Board&(location>>>18))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L){
                return location>>>9;
            }else if((my_Board&(location>>>27))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L
                    &&(opponent_Board&(location>>>18))!=0x0L){
                return location>>>9|location>>>18;
            }else if((my_Board&(location>>>36))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L
                    &&(opponent_Board&(location>>>18))!=0x0L
                    &&(opponent_Board&(location>>>27))!=0x0L){
                return location>>>9|location>>>18|location>>>27;
            }
        }
        if((location&0x2020e00000000000L)!=0x0L){//Row A6 and Column F8
            if((my_Board&(location>>>18))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L){
                return location>>>9;
            }else if((my_Board&(location>>>27))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L
                    &&(opponent_Board&(location>>>18))!=0x0L){
                return location>>>9|location>>>18;
            }else if((my_Board&(location>>>36))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L
                    &&(opponent_Board&(location>>>18))!=0x0L
                    &&(opponent_Board&(location>>>27))!=0x0L){
                return location>>>9|location>>>18|location>>>27;
            }else if((my_Board&(location>>>45))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L
                    &&(opponent_Board&(location>>>18))!=0x0L
                    &&(opponent_Board&(location>>>27))!=0x0L
                    &&(opponent_Board&(location>>>36))!=0x0L){
                return location>>>9|location>>>18|location>>>27|location>>>36;
            }
        }
        if((location&0x40c0000000000000L)!=0x0L){//Row A7 and Column G8
            if((my_Board&(location>>>18))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L){
                return location>>>9;
            }else if((my_Board&(location>>>27))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L
                    &&(opponent_Board&(location>>>18))!=0x0L){
                return location>>>9|location>>>18;
            }else if((my_Board&(location>>>36))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L
                    &&(opponent_Board&(location>>>18))!=0x0L
                    &&(opponent_Board&(location>>>27))!=0x0L){
                return location>>>9|location>>>18|location>>>27;
            }else if((my_Board&(location>>>45))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L
                    &&(opponent_Board&(location>>>18))!=0x0L
                    &&(opponent_Board&(location>>>27))!=0x0L
                    &&(opponent_Board&(location>>>36))!=0x0L){
                return location>>>9|location>>>18|location>>>27|location>>>36;
            }else if((my_Board&(location>>>54))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L
                    &&(opponent_Board&(location>>>18))!=0x0L
                    &&(opponent_Board&(location>>>27))!=0x0L
                    &&(opponent_Board&(location>>>36))!=0x0L
                    &&(opponent_Board&(location>>>45))!=0x0L){
                return location>>>9|location>>>18|location>>>27|location>>>36|location>>>45;
            }
        }
        if((location&0x8000000000000000L)!=0x0L){//Row A8 and Column H8
            if((my_Board&(location>>>18))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L){
                return location>>>9;
            }else if((my_Board&(location>>>27))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L
                    &&(opponent_Board&(location>>>18))!=0x0L){
                return location>>>9|location>>>18;
            }else if((my_Board&(location>>>36))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L
                    &&(opponent_Board&(location>>>18))!=0x0L
                    &&(opponent_Board&(location>>>27))!=0x0L){
                return location>>>9|location>>>18|location>>>27;
            }else if((my_Board&(location>>>45))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L
                    &&(opponent_Board&(location>>>18))!=0x0L
                    &&(opponent_Board&(location>>>27))!=0x0L
                    &&(opponent_Board&(location>>>36))!=0x0L){
                return location>>>9|location>>>18|location>>>27|location>>>36;
            }else if((my_Board&(location>>>54))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L
                    &&(opponent_Board&(location>>>18))!=0x0L
                    &&(opponent_Board&(location>>>27))!=0x0L
                    &&(opponent_Board&(location>>>36))!=0x0L
                    &&(opponent_Board&(location>>>45))!=0x0L){
                return location>>>9|location>>>18|location>>>27|location>>>36|location>>>45;
            }else if((my_Board&(location>>>63))!=0x0L
                    &&(opponent_Board&(location>>>9))!=0x0L
                    &&(opponent_Board&(location>>>18))!=0x0L
                    &&(opponent_Board&(location>>>27))!=0x0L
                    &&(opponent_Board&(location>>>36))!=0x0L
                    &&(opponent_Board&(location>>>45))!=0x0L
                    &&(opponent_Board&(location>>>54))!=0x0L){
                return location>>>9|location>>>18|location>>>27|location>>>36|location>>>45|location>>>54;
            }
        }
        return 0x0L;
    }

    public long down_Right_Reversal(long location, long opponent_Board, long my_Board) {
        if((location&0xff80808080808080L)!=0x0L){//Row A8 and Column H1
            return 0x0L;
        }
        if((location&0x7f404040404040L)!=0x0L){//Row A7 and Column G1
            return 0x0L;
        }
        if((location&0x3f2020202020L)!=0x0L){//Row A6 and Column F1
            if((my_Board&(location<<18))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L){
                return location<<9;
            }
        }
        if((location&0x1f10101010L)!=0x0L){//Row A5 and Column E1
            if((my_Board&(location<<18))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L){
                return location<<9;
            }else if((my_Board&(location<<27))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L
                    &&(opponent_Board&(location<<18))!=0x0L){
                return location<<9|location<<18;
            }
        }
        if((location&0xf080808L)!=0x0L){//Row A4 and Column D1
            if((my_Board&(location<<18))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L){
                return location<<9;
            }else if((my_Board&(location<<27))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L
                    &&(opponent_Board&(location<<18))!=0x0L){
                return location<<9|location<<18;
            }else if((my_Board&(location<<36))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L
                    &&(opponent_Board&(location<<18))!=0x0L
                    &&(opponent_Board&(location<<27))!=0x0L){
                return location<<9|location<<18|location<<27;
            }
        }
        if((location&0x70404L)!=0x0L){//Row A3 and Column C1
            if((my_Board&(location<<18))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L){
                return location<<9;
            }else if((my_Board&(location<<27))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L
                    &&(opponent_Board&(location<<18))!=0x0L){
                return location<<9|location<<18;
            }else if((my_Board&(location<<36))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L
                    &&(opponent_Board&(location<<18))!=0x0L
                    &&(opponent_Board&(location<<27))!=0x0L){
                return location<<9|location<<18|location<<27;
            }else if((my_Board&(location<<45))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L
                    &&(opponent_Board&(location<<18))!=0x0L
                    &&(opponent_Board&(location<<27))!=0x0L
                    &&(opponent_Board&(location<<36))!=0x0L){
                return location<<9|location<<18|location<<27|location<<36;
            }
        }
        if((location&0x302L)!=0x0L){//Row A2 and Column B1
            if((my_Board&(location<<18))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L){
                return location<<9;
            }else if((my_Board&(location<<27))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L
                    &&(opponent_Board&(location<<18))!=0x0L){
                return location<<9|location<<18;
            }else if((my_Board&(location<<36))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L
                    &&(opponent_Board&(location<<18))!=0x0L
                    &&(opponent_Board&(location<<27))!=0x0L){
                return location<<9|location<<18|location<<27;
            }else if((my_Board&(location<<45))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L
                    &&(opponent_Board&(location<<18))!=0x0L
                    &&(opponent_Board&(location<<27))!=0x0L
                    &&(opponent_Board&(location<<36))!=0x0L){
                return location<<9|location<<18|location<<27|location<<36;
            }else if((my_Board&(location<<54))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L
                    &&(opponent_Board&(location<<18))!=0x0L
                    &&(opponent_Board&(location<<27))!=0x0L
                    &&(opponent_Board&(location<<36))!=0x0L
                    &&(opponent_Board&(location<<45))!=0x0L){
                return location<<9|location<<18|location<<27|location<<36|location<<45;
            }
        }
        if((location&0x1L)!=0x0L){//Row A1 and Column A1
            if((my_Board&(location<<18))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L){
                return location<<9;
            }else if((my_Board&(location<<27))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L
                    &&(opponent_Board&(location<<18))!=0x0L){
                return location<<9|location<<18;
            }else if((my_Board&(location<<36))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L
                    &&(opponent_Board&(location<<18))!=0x0L
                    &&(opponent_Board&(location<<27))!=0x0L){
                return location<<9|location<<18|location<<27;
            }else if((my_Board&(location<<45))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L
                    &&(opponent_Board&(location<<18))!=0x0L
                    &&(opponent_Board&(location<<27))!=0x0L
                    &&(opponent_Board&(location<<36))!=0x0L){
                return location<<9|location<<18|location<<27|location<<36;
            }else if((my_Board&(location<<54))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L
                    &&(opponent_Board&(location<<18))!=0x0L
                    &&(opponent_Board&(location<<27))!=0x0L
                    &&(opponent_Board&(location<<36))!=0x0L
                    &&(opponent_Board&(location<<45))!=0x0L){
                return location<<9|location<<18|location<<27|location<<36|location<<45;
            }else if((my_Board&(location<<63))!=0x0L
                    &&(opponent_Board&(location<<9))!=0x0L
                    &&(opponent_Board&(location<<18))!=0x0L
                    &&(opponent_Board&(location<<27))!=0x0L
                    &&(opponent_Board&(location<<36))!=0x0L
                    &&(opponent_Board&(location<<45))!=0x0L
                    &&(opponent_Board&(location<<54))!=0x0L){
                return location<<9|location<<18|location<<27|location<<36|location<<45|location<<54;
            }
        }
        return 0x0L;
    }

    public long down_Left_Reversal(long location, long opponent_Board, long my_Board) {
        if((location&0xff01010101010101L)!=0x0L){//Row A8 and Column A1
            return 0x0L;
        }
        if((location&0xfe020202020202L)!=0x0L){//Row A7 and Column B1
            return 0x0L;
        }
        if((location&0xfc0404040404L)!=0x0L){//Row A6 and Column C1
            if((my_Board&(location<<14))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L){
                return location<<7;
            }
        }
        if((location&0xf808080808L)!=0x0L){//Row A5 and Column D1
            if((my_Board&(location<<14))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L){
                return location<<7;
            }else if((my_Board&(location<<21))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L
                    &&(opponent_Board&(location<<14))!=0x0L){
                return location<<7|location<<14;
            }
        }
        if((location&0xf0101010L)!=0x0L){//Row A4 and ColumnE1
            if((my_Board&(location<<14))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L){
                return location<<7;
            }else if((my_Board&(location<<21))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L
                    &&(opponent_Board&(location<<14))!=0x0L){
                return location<<7|location<<14;
            }else if((my_Board&(location<<28))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L
                    &&(opponent_Board&(location<<14))!=0x0L
                    &&(opponent_Board&(location<<21))!=0x0L){
                return location<<7|location<<14|location<<21;
            }
        }
        if((location&0xe02020L)!=0x0L){//Row A3 and Column F1
            if((my_Board&(location<<14))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L){
                return location<<7;
            }else if((my_Board&(location<<21))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L
                    &&(opponent_Board&(location<<14))!=0x0L){
                return location<<7|location<<14;
            }else if((my_Board&(location<<28))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L
                    &&(opponent_Board&(location<<14))!=0x0L
                    &&(opponent_Board&(location<<21))!=0x0L){
                return location<<7|location<<14|location<<21;
            }else if((my_Board&(location<<35))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L
                    &&(opponent_Board&(location<<14))!=0x0L
                    &&(opponent_Board&(location<<21))!=0x0L
                    &&(opponent_Board&(location<<28))!=0x0L){
                return location<<7|location<<14|location<<21|location<<28;
            }
        }
        if((location&0xc040L)!=0x0L){//Row A2 and Column G1
            if((my_Board&(location<<14))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L){
                return location<<7;
            }else if((my_Board&(location<<21))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L
                    &&(opponent_Board&(location<<14))!=0x0L){
                return location<<7|location<<14;
            }else if((my_Board&(location<<28))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L
                    &&(opponent_Board&(location<<14))!=0x0L
                    &&(opponent_Board&(location<<21))!=0x0L){
                return location<<7|location<<14|location<<21;
            }else if((my_Board&(location<<35))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L
                    &&(opponent_Board&(location<<14))!=0x0L
                    &&(opponent_Board&(location<<21))!=0x0L
                    &&(opponent_Board&(location<<28))!=0x0L){
                return location<<7|location<<14|location<<21|location<<28;
            }else if((my_Board&(location<<42))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L
                    &&(opponent_Board&(location<<14))!=0x0L
                    &&(opponent_Board&(location<<21))!=0x0L
                    &&(opponent_Board&(location<<28))!=0x0L
                    &&(opponent_Board&(location<<35))!=0x0L){
                return location<<7|location<<14|location<<21|location<<28|location<<35;
            }
        }
        if((location&0x80L)!=0x0L){//Row A1 and Column H1
            if((my_Board&(location<<14))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L){
                return location<<7;
            }else if((my_Board&(location<<21))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L
                    &&(opponent_Board&(location<<14))!=0x0L){
                return location<<7|location<<14;
            }else if((my_Board&(location<<28))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L
                    &&(opponent_Board&(location<<14))!=0x0L
                    &&(opponent_Board&(location<<21))!=0x0L){
                return location<<7|location<<14|location<<21;
            }else if((my_Board&(location<<35))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L
                    &&(opponent_Board&(location<<14))!=0x0L
                    &&(opponent_Board&(location<<21))!=0x0L
                    &&(opponent_Board&(location<<28))!=0x0L){
                return location<<7|location<<14|location<<21|location<<28;
            }else if((my_Board&(location<<42))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L
                    &&(opponent_Board&(location<<14))!=0x0L
                    &&(opponent_Board&(location<<21))!=0x0L
                    &&(opponent_Board&(location<<28))!=0x0L
                    &&(opponent_Board&(location<<35))!=0x0L){
                return location<<7|location<<14|location<<21|location<<28|location<<35;
            }else if((my_Board&(location<<49))!=0x0L
                    &&(opponent_Board&(location<<7))!=0x0L
                    &&(opponent_Board&(location<<14))!=0x0L
                    &&(opponent_Board&(location<<21))!=0x0L
                    &&(opponent_Board&(location<<28))!=0x0L
                    &&(opponent_Board&(location<<35))!=0x0L
                    &&(opponent_Board&(location<<42))!=0x0L){
                return location<<7|location<<14|location<<21|location<<28|location<<35|location<<42;
            }
        }
        return 0x0L;
    }


}
