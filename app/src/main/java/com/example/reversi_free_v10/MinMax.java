package com.example.reversi_free_v10;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class MinMax extends AppCompatActivity {

    public Board mboard=new Board();
    public CustomAlgorithm customAlgorithm;
    public CustomAlgorithm2 customAlgorithm2;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    public MinMax(Board board){
        mboard=board;
        customAlgorithm=new CustomAlgorithm(board);
        customAlgorithm2=new CustomAlgorithm2(board);
        editor=getSharedPreferences("minmax",MODE_PRIVATE).edit();
    }

    public long getMinMax(long opponent_Board,long my_Board){
        long legal_Drop_Location=mboard.search_Legal_Location(opponent_Board,my_Board);

        int result=0;
        return 0x0L;
    }

    public int get_Current_Score(long location,long opponent_Board,long my_Board){
        int result=0;
        result+=customAlgorithm.get_Reversal_Number(location,opponent_Board,my_Board);
        result+=customAlgorithm2.isEdge(location);
        result+=customAlgorithm2.isInnerEdge(location);
        result+=customAlgorithm2.isCorner(location);
        result+=customAlgorithm2.isInnerCorner(location);
        return result;
    }

    public int get_Current_Layer_Result(long opponent_Board,long my_Board){
        long legal_Drop_Location=mboard.search_Legal_Location(opponent_Board,my_Board);
        long check=0x1L;
        int result=0;
        for(int i=0;i<64;i++){
            if((legal_Drop_Location&check<<i)!=0x0L){
                result=get_Current_Score(check<<i,opponent_Board,my_Board);
                //editor.putInt(toString(check<<i),result);
            }
        }

        return 0;
    }

}
