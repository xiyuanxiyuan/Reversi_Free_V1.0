package com.example.reversi_free_v10;

import java.util.ArrayList;
import java.util.List;

public class MiniMax {

    public Board board=new Board();
    public CustomAlgorithm customAlgorithm=new CustomAlgorithm();
    public CustomAlgorithm2 customAlgorithm2=new CustomAlgorithm2();

    public MiniMax() {
    }

    public long getMinMax(int flag, long opponent_Board, long my_Board) {

        if (flag == 1) {
            int mostScore=0;
            long bestLocation=0x0L;
            Board mboard = new Board();
            mboard.white = opponent_Board;
            mboard.black = my_Board;

            List<Result> resultList1 = get_Current_Layer_Result(mboard.white, mboard.black);
            for (int i = 0; i < resultList1.size(); i++) {
                Board mmboard = mboard;
                int score1=resultList1.get(i).score;
                mmboard.drop_Black(resultList1.get(i).location);
                mmboard.drop_White(customAlgorithm2.get_Highest_Score_Location(mmboard.black, mmboard.white));

                List<Result> resultList2 = get_Current_Layer_Result(mmboard.white, mmboard.black);
                for (int j = 0; j < resultList2.size(); j++) {
                    Board mmmboard = mmboard;
                    int score2=resultList2.get(j).score;
                    mmmboard.drop_Black(resultList2.get(j).location);
                    mmmboard.drop_White(customAlgorithm2.get_Highest_Score_Location(mmmboard.black, mmmboard.white));

                    List<Result> resultList3 = get_Current_Layer_Result(mmmboard.white, mmmboard.black);
                    for (int k = 0; k < resultList3.size(); k++) {
                       // Board mmmmboard = mmmboard;
                        int score3=resultList3.get(k).score;
                        if(mostScore<=score1+score2+score3){
                            bestLocation=resultList1.get(i).location;
                        }
                    }
                }
            }
            if(bestLocation!=0x0L){
                return bestLocation;
            }else{
                return customAlgorithm2.get_Highest_Score_Location(opponent_Board,my_Board);
            }

        }

        if (flag == -1) {
            int mostScore=0;
            long bestLocation=0x0L;
            Board mboard = new Board();
            mboard.black = opponent_Board;
            mboard.white = my_Board;


            List<Result> resultList1 = get_Current_Layer_Result(mboard.black, mboard.white);
            for (int i = 0; i < resultList1.size(); i++) {

                Board mmboard = mboard;
                int score1=resultList1.get(i).score;
                mmboard.drop_White(resultList1.get(i).location) ;
                mmboard.drop_Black(customAlgorithm2.get_Highest_Score_Location(mmboard.white, mmboard.black)) ;


                List<Result> resultList2 = get_Current_Layer_Result(mmboard.black, mmboard.white );
                 for (int j = 0; j < resultList2.size(); j++) {

                    Board mmmboard = mmboard;
                    int score2=resultList2.get(j).score;
                    mmmboard.drop_White(resultList2.get(j).location) ;
                    mmmboard.drop_Black(customAlgorithm2.get_Highest_Score_Location(mmmboard.white , mmmboard.black )) ;

                    List<Result> resultList3 = get_Current_Layer_Result(mmmboard.black , mmmboard.white);
                    for (int k = 0; k < resultList3.size(); k++) {

                        //Board mmmmboard = mmmboard;
                        int score3=resultList3.get(k).score;
                        if(mostScore<=score1+score2+score3){
                            bestLocation=resultList1.get(i).location;
                        }
//                        mmmmboard.drop_White(resultList3.get(i).location) ;
//                        mmmmboard.drop_Black(customAlgorithm2.get_Highest_Score_Location(mmmmboard.black, mmmmboard.white)) ;
//                        List<Result> resultList4 = get_Current_Layer_Result(mmmmboard.black , mmmmboard.white);
//                        for(int l=0;i<resultList4.size();l++){
//                            int score4=resultList4.get(l).score;
//                            if(mostScore<=score1+score2+score3+score4)
//                                bestLocation=resultList1.get(i).location;
//                            }
                        }
                    }
                }
            if(bestLocation!=0x0L){
                return bestLocation;
            }else{
                return customAlgorithm2.get_Highest_Score_Location(opponent_Board,my_Board);
            }
        }
        return 0x0L;
    }

    public int get_Current_Score(long location, long opponent_Board, long my_Board) {
        int result = 0;
        result = customAlgorithm.get_Reversal_Number(location, opponent_Board, my_Board);
        result += customAlgorithm2.isEdge(location);
        result += customAlgorithm2.isInnerEdge(location);
        result += customAlgorithm2.isCorner(location);
        result += customAlgorithm2.isInnerCorner(location);
        return result;
    }

    public List<Result> get_Current_Layer_Result(long opponent_Board, long my_Board) {
        long legal_Drop_Location = board.search_Legal_Location(opponent_Board, my_Board);
        List<Result> saveResult = new ArrayList<>();
        long check = 0x1L;
        for (int i = 0; i < 64; i++) {
            if ((legal_Drop_Location & check << i) != 0x0L) {
                Result result = new Result();
                result.location = check << i;
                result.score = get_Current_Score(check << i, opponent_Board, my_Board);
                saveResult.add(result);
            }
        }
        return saveResult;
    }

}
