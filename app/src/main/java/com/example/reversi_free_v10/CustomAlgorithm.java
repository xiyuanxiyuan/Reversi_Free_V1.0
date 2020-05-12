package com.example.reversi_free_v10;

public class CustomAlgorithm {

    public Board mboard=new Board();

    public CustomAlgorithm(Board board){
        mboard=board;
    }

    public long get_Reversal_Number_Most_Location(long opponent_Board, long my_Board){

        long legal_Drop_Location=mboard.search_Legal_Location(opponent_Board,my_Board);

        long check=0x1L;

        int result=0;
        int the_Most_Reversal_Number=0;
        long the_Most_Reversal_Location=0x0L;

        for(int i=0;i<64;i++){
            if((legal_Drop_Location&check<<i)!=0x0L){
                result=get_Reversal_Number(check,opponent_Board,my_Board);
                if(result>=the_Most_Reversal_Number){
                    the_Most_Reversal_Number=result;
                    the_Most_Reversal_Location=check<<i;
                }
            }
        }
        return the_Most_Reversal_Location;
    }

    public int get_Reversal_Number(long location, long opponent_Board, long my_Board){
        long reversal_Result=mboard.reversal(location,opponent_Board,my_Board);
        long check=0x1L;
        int reversal_Number=0;
        for(int i=0;i<64;i++){
            if((reversal_Result&check<<i)!=0x0L){
                reversal_Number++;
            }
        }
        return reversal_Number;
    }

}
