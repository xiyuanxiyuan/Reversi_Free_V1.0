package com.example.reversi_free_v10;

public class CustomAlgorithm2 {

    public Board mboard=new Board();

    public CustomAlgorithm2(Board board){
        mboard=board;
    }

    public long get_Highest_Score_Location(long opponent_Board, long my_Board){

        long legal_Drop_Location=mboard.search_Legal_Location(opponent_Board,my_Board);

        long check=0x1L;

        int result=0;
        int the_Highest_Score=0;
        long the_Highest_Score_Location=0x0L;

        for(int i=0;i<64;i++){
            if((legal_Drop_Location&check<<i)!=0x0L){
                result=get_Reversal_Number(check<<i,opponent_Board,my_Board);
                result+=isEdge(check<<i);
                result+=isInnerEdge(check<<i);
                result+=isCorner(check<<i);
                result+=isInnerCorner(check<<i);

                if(result>=the_Highest_Score){
                    the_Highest_Score=result;
                    the_Highest_Score_Location=check<<i;
                }
            }
        }
        return the_Highest_Score_Location;
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

    public int isEdge(long location){
        if((location&0xff818181818181ffL)!=0x0L){
            return 10;
        }
        return 0;
    }

    public int isInnerEdge(long location){
        if((location&0x7e424242427e00L)!=0x0L){
            return 5;
        }
        return 0;
    }

    public int isCorner(long location){
        if((location&0x8100000000000081L)!=0x0L){
            return 15;
        }
        return 0;
    }

    public int isInnerCorner(long location){
        if((location&0x42000000004200L)!=0x0L){
            return -5;
        }
        return 0;
    }

}
