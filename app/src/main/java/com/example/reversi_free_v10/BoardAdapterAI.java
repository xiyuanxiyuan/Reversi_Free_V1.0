package com.example.reversi_free_v10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class BoardAdapterAI extends RecyclerView.Adapter<BoardAdapterAI.ViewHolder> {

    private List<BoardGrid> mBoardGridList;
    private int mplayMode=2;
    private int mdifficulty=0;
    private long touchedLocation;
    private Board board = new Board();
    private int flag = 1;
    private CustomAlgorithm customAlgorithm=new CustomAlgorithm(board);
    private int mpieceSort;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        Button boardGrid;
        ImageView boardGridImage;

        public ViewHolder(View view) {
            super(view);
            boardGrid = (Button) view.findViewById(R.id.board_grid);
            boardGridImage = (ImageView) view.findViewById(R.id.board_grid_image);
        }
    }

    public BoardAdapterAI(List<BoardGrid> boardGridList, int playMode, int difficulty,int pieceSort) {
        mBoardGridList = boardGridList;
        mplayMode=playMode;
        mdifficulty=difficulty;
        mpieceSort=pieceSort;
    }

    @Override
    public BoardAdapterAI.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_grid_layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final BoardGrid boardGrid = mBoardGridList.get(position);

        if(mplayMode==3) {

            if ((customAlgorithm.get_Reversal_Number_Most_Location(board.white, board.black) & board.search_Legal_Location(board.white, board.black)) != 0x0L && flag == 1) {//黑棋落子
                board.drop_Black(customAlgorithm.get_Reversal_Number_Most_Location(board.white, board.black));
                //notifyDataSetChanged();
                flag = -1;
            } else if ((board.search_Legal_Location(board.white, board.black)) == 0x0L && board.search_Legal_Location(board.black, board.white) != 0x0L && flag == 1) {
                flag = -1;
            }
        }

        int boardgrid_piece = boardGrid.getBoardgrid_piece();

        if (boardgrid_piece == 1) {
            switch (mpieceSort) {
                case 1:
                    holder.boardGridImage.setImageResource(R.drawable.p_w);
                    break;
                case 2:
                    holder.boardGridImage.setImageResource(R.drawable.p_03_w);
                    break;
                default:
                    holder.boardGridImage.setImageResource(R.drawable.p_w);
                    break;
            }
        } else if (boardgrid_piece == -1) {
            switch (mpieceSort) {
                case 1:
                    holder.boardGridImage.setImageResource(R.drawable.p_b);
                    break;
                case 2:
                    holder.boardGridImage.setImageResource(R.drawable.p_03_b);
                    break;
                default:
                    holder.boardGridImage.setImageResource(R.drawable.p_b);
                    break;
            }
        }

        changeBoardGrids(board.white, board.black);


        holder.boardGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                BoardGrid boardGrid = mBoardGridList.get(position);
                touchedLocation = boardGrid.getButton_id();

                Toast.makeText(v.getContext(), "you clicked: " + touchedLocation, Toast.LENGTH_SHORT).show();


//-------------------------------------------------------------------------------------------------
                if(mplayMode==1) {
                    if((touchedLocation&(board.search_Legal_Location(board.white,board.black) |board.search_Legal_Location(board.black,board.white)))==0x0L){
                        return;
                    }

                    if ((touchedLocation & board.search_Legal_Location(board.white, board.black)) != 0x0L && flag == 1) {//黑棋落子
                        board.drop_Black(touchedLocation);
                        notifyDataSetChanged();
                        flag = -1;
                    } else if ((board.search_Legal_Location(board.white, board.black)) == 0x0L && board.search_Legal_Location(board.black, board.white) != 0x0L && flag == 1) {
                        flag = -1;
                    }

                    if ((customAlgorithm.get_Reversal_Number_Most_Location(board.black, board.white)& board.search_Legal_Location(board.black, board.white)) != 0x0L && flag == -1) {//白棋落子
                        board.drop_White(customAlgorithm.get_Reversal_Number_Most_Location(board.black, board.white));
                        notifyDataSetChanged();
                        flag = 1;
                    } else if ((board.search_Legal_Location(board.black, board.white)) == 0x0L && (board.search_Legal_Location(board.white, board.black)) != 0x0L && flag == -1) {
                        flag = 1;
                    }

                    if(board.search_Legal_Location(board.white,board.black)==0x0L&&board.search_Legal_Location(board.black,board.white)==0x0L){
                        Toast.makeText(v.getContext(),"game over",Toast.LENGTH_LONG).show();
                    }
                }
//-------------------------------------------------------------------------------------------------
                if(mplayMode==2) {
                    if((touchedLocation&(board.search_Legal_Location(board.white,board.black) |board.search_Legal_Location(board.black,board.white)))==0x0L){
                        return;
                    }

                    if ((touchedLocation & board.search_Legal_Location(board.white, board.black)) != 0x0L && flag == 1) {//黑棋落子
                        board.drop_Black(touchedLocation);
                        notifyDataSetChanged();
                        flag = -1;
                    } else if ((board.search_Legal_Location(board.white, board.black)) == 0x0L && board.search_Legal_Location(board.black, board.white) != 0x0L && flag == 1) {
                        flag = -1;
                    }

                    if ((customAlgorithm.get_Reversal_Number_Most_Location(board.black, board.white)& board.search_Legal_Location(board.black, board.white)) != 0x0L && flag == -1) {//白棋落子
                        board.drop_White(customAlgorithm.get_Reversal_Number_Most_Location(board.black, board.white));
                        notifyDataSetChanged();
                        flag = 1;
                    } else if ((board.search_Legal_Location(board.black, board.white)) == 0x0L && (board.search_Legal_Location(board.white, board.black)) != 0x0L && flag == -1) {
                        flag = 1;
                    }

                    if(board.search_Legal_Location(board.white,board.black)==0x0L&&board.search_Legal_Location(board.black,board.white)==0x0L){
                        Toast.makeText(v.getContext(),"game over",Toast.LENGTH_LONG).show();
                    }
                }
//-------------------------------------------------------------------------------------------------
                if(mplayMode==3) {
                    if((touchedLocation&(board.search_Legal_Location(board.white,board.black) |board.search_Legal_Location(board.black,board.white)))==0x0L){
                        return;
                    }

                    if ((touchedLocation & board.search_Legal_Location(board.black, board.white)) != 0x0L && flag == -1) {//白棋落子
                        board.drop_White(touchedLocation );
                        notifyDataSetChanged();
                        flag = 1;
                    } else if ((board.search_Legal_Location(board.black, board.white)) == 0x0L && (board.search_Legal_Location(board.white, board.black)) != 0x0L && flag == -1) {
                        flag = 1;
                    }

                    if(board.search_Legal_Location(board.white,board.black)==0x0L&&board.search_Legal_Location(board.black,board.white)==0x0L){
                        Toast.makeText(v.getContext(),"game over",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }

    public int getItemViewType(int position) {
        return position;
    }

    public int getItemCount() {
        return mBoardGridList.size();
    }

    private void changeBoardGrids(long white, long black) {

        if ((white & 0x1L) != 0x0L) {
            mBoardGridList.get(0).setBoardgrid_piece(1);
        } else if ((black & 0x1L) != 0x0L) {
            mBoardGridList.get(0).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(0).setBoardgrid_piece(0);
        }

        if ((white & 0x2L) != 0x0L) {
            mBoardGridList.get(1).setBoardgrid_piece(1);
        } else if ((black & 0x2L) != 0x0L) {
            mBoardGridList.get(1).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(1).setBoardgrid_piece(0);
        }

        if ((white & 0x4L) != 0x0L) {
            mBoardGridList.get(2).setBoardgrid_piece(1);
        } else if ((black & 0x4L) != 0x0L) {
            mBoardGridList.get(2).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(2).setBoardgrid_piece(0);
        }

        if ((white & 0x8L) != 0x0L) {
            mBoardGridList.get(3).setBoardgrid_piece(1);
        } else if ((black & 0x8L) != 0x0L) {
            mBoardGridList.get(3).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(3).setBoardgrid_piece(0);
        }

        if ((white & 0x10L) != 0x0L) {
            mBoardGridList.get(4).setBoardgrid_piece(1);
        } else if ((black & 0x10L) != 0x0L) {
            mBoardGridList.get(4).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(4).setBoardgrid_piece(0);
        }

        if ((white & 0x20L) != 0x0L) {
            mBoardGridList.get(5).setBoardgrid_piece(1);
        } else if ((black & 0x20L) != 0x0L) {
            mBoardGridList.get(5).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(5).setBoardgrid_piece(0);
        }

        if ((white & 0x40L) != 0x0L) {
            mBoardGridList.get(6).setBoardgrid_piece(1);
        } else if ((black & 0x40L) != 0x0L) {
            mBoardGridList.get(6).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(6).setBoardgrid_piece(0);
        }

        if ((white & 0x80L) != 0x0L) {
            mBoardGridList.get(7).setBoardgrid_piece(1);
        } else if ((black & 0x80L) != 0x0L) {
            mBoardGridList.get(7).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(7).setBoardgrid_piece(0);
        }

        //---------------------------------------------------------------------------

        if ((white & 0x100L) != 0x0L) {
            mBoardGridList.get(8).setBoardgrid_piece(1);
        } else if ((black & 0x100L) != 0x0L) {
            mBoardGridList.get(8).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(8).setBoardgrid_piece(0);
        }

        if ((white & 0x200L) != 0x0L) {
            mBoardGridList.get(9).setBoardgrid_piece(1);
        } else if ((black & 0x200L) != 0x0L) {
            mBoardGridList.get(9).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(9).setBoardgrid_piece(0);
        }

        if ((white & 0x400L) != 0x0L) {
            mBoardGridList.get(10).setBoardgrid_piece(1);
        } else if ((black & 0x400L) != 0x0L) {
            mBoardGridList.get(10).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(10).setBoardgrid_piece(0);
        }

        if ((white & 0x800L) != 0x0L) {
            mBoardGridList.get(11).setBoardgrid_piece(1);
        } else if ((black & 0x800L) != 0x0L) {
            mBoardGridList.get(11).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(11).setBoardgrid_piece(0);
        }

        if ((white & 0x1000L) != 0x0L) {
            mBoardGridList.get(12).setBoardgrid_piece(1);
        } else if ((black & 0x1000L) != 0x0L) {
            mBoardGridList.get(12).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(12).setBoardgrid_piece(0);
        }

        if ((white & 0x2000L) != 0x0L) {
            mBoardGridList.get(13).setBoardgrid_piece(1);
        } else if ((black & 0x2000L) != 0x0L) {
            mBoardGridList.get(13).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(13).setBoardgrid_piece(0);
        }

        if ((white & 0x4000L) != 0x0L) {
            mBoardGridList.get(14).setBoardgrid_piece(1);
        } else if ((black & 0x4000L) != 0x0L) {
            mBoardGridList.get(14).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(14).setBoardgrid_piece(0);
        }

        if ((white & 0x8000L) != 0x0L) {
            mBoardGridList.get(15).setBoardgrid_piece(1);
        } else if ((black & 0x8000L) != 0x0L) {
            mBoardGridList.get(15).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(15).setBoardgrid_piece(0);
        }

        //---------------------------------------------------------------------

        if ((white & 0x10000L) != 0x0L) {
            mBoardGridList.get(16).setBoardgrid_piece(1);
        } else if ((black & 0x10000L) != 0x0L) {
            mBoardGridList.get(16).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(16).setBoardgrid_piece(0);
        }

        if ((white & 0x20000L) != 0x0L) {
            mBoardGridList.get(17).setBoardgrid_piece(1);
        } else if ((black & 0x20000L) != 0x0L) {
            mBoardGridList.get(17).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(17).setBoardgrid_piece(0);
        }

        if ((white & 0x40000L) != 0x0L) {
            mBoardGridList.get(18).setBoardgrid_piece(1);
        } else if ((black & 0x40000L) != 0x0L) {
            mBoardGridList.get(18).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(18).setBoardgrid_piece(0);
        }

        if ((white & 0x80000L) != 0x0L) {
            mBoardGridList.get(19).setBoardgrid_piece(1);
        } else if ((black & 0x80000L) != 0x0L) {
            mBoardGridList.get(19).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(19).setBoardgrid_piece(0);
        }

        if ((white & 0x100000L) != 0x0L) {
            mBoardGridList.get(20).setBoardgrid_piece(1);
        } else if ((black & 0x100000L) != 0x0L) {
            mBoardGridList.get(20).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(20).setBoardgrid_piece(0);
        }

        if ((white & 0x200000L) != 0x0L) {
            mBoardGridList.get(21).setBoardgrid_piece(1);
        } else if ((black & 0x200000L) != 0x0L) {
            mBoardGridList.get(21).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(21).setBoardgrid_piece(0);
        }

        if ((white & 0x400000L) != 0x0L) {
            mBoardGridList.get(22).setBoardgrid_piece(1);
        } else if ((black & 0x400000L) != 0x0L) {
            mBoardGridList.get(22).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(22).setBoardgrid_piece(0);
        }

        if ((white & 0x800000L) != 0x0L) {
            mBoardGridList.get(23).setBoardgrid_piece(1);
        } else if ((black & 0x800000L) != 0x0L) {
            mBoardGridList.get(23).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(23).setBoardgrid_piece(0);
        }

        //------------------------------------------------------------------------

        if ((white & 0x1000000L) != 0x0L) {
            mBoardGridList.get(24).setBoardgrid_piece(1);
        } else if ((black & 0x1000000L) != 0x0L) {
            mBoardGridList.get(24).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(24).setBoardgrid_piece(0);
        }


        if ((white & 0x2000000L) != 0x0L) {
            mBoardGridList.get(25).setBoardgrid_piece(1);
        } else if ((black & 0x2000000L) != 0x0L) {
            mBoardGridList.get(25).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(25).setBoardgrid_piece(0);
        }


        if ((white & 0x4000000L) != 0x0L) {
            mBoardGridList.get(26).setBoardgrid_piece(1);
        } else if ((black & 0x4000000L) != 0x0L) {
            mBoardGridList.get(26).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(26).setBoardgrid_piece(0);
        }


        if ((white & 0x8000000L) != 0x0L) {
            mBoardGridList.get(27).setBoardgrid_piece(1);
        } else if ((black & 0x8000000L) != 0x0L) {
            mBoardGridList.get(27).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(27).setBoardgrid_piece(0);
        }


        if ((white & 0x10000000L) != 0x0L) {
            mBoardGridList.get(28).setBoardgrid_piece(1);
        } else if ((black & 0x10000000L) != 0x0L) {
            mBoardGridList.get(28).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(28).setBoardgrid_piece(0);
        }


        if ((white & 0x20000000L) != 0x0L) {
            mBoardGridList.get(29).setBoardgrid_piece(1);
        } else if ((black & 0x20000000L) != 0x0L) {
            mBoardGridList.get(29).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(29).setBoardgrid_piece(0);
        }


        if ((white & 0x40000000L) != 0x0L) {
            mBoardGridList.get(30).setBoardgrid_piece(1);
        } else if ((black & 0x40000000L) != 0x0L) {
            mBoardGridList.get(30).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(30).setBoardgrid_piece(0);
        }


        if ((white & 0x80000000L) != 0x0L) {
            mBoardGridList.get(31).setBoardgrid_piece(1);
        } else if ((black & 0x80000000L) != 0x0L) {
            mBoardGridList.get(31).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(31).setBoardgrid_piece(0);
        }

        //---------------------------------------------------------------------------

        if ((white & 0x100000000L) != 0x0L) {
            mBoardGridList.get(32).setBoardgrid_piece(1);
        } else if ((black & 0x100000000L) != 0x0L) {
            mBoardGridList.get(32).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(32).setBoardgrid_piece(0);
        }


        if ((white & 0x200000000L) != 0x0L) {
            mBoardGridList.get(33).setBoardgrid_piece(1);
        } else if ((black & 0x200000000L) != 0x0L) {
            mBoardGridList.get(33).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(33).setBoardgrid_piece(0);
        }


        if ((white & 0x400000000L) != 0x0L) {
            mBoardGridList.get(34).setBoardgrid_piece(1);
        } else if ((black & 0x400000000L) != 0x0L) {
            mBoardGridList.get(34).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(34).setBoardgrid_piece(0);
        }


        if ((white & 0x800000000L) != 0x0L) {
            mBoardGridList.get(35).setBoardgrid_piece(1);
        } else if ((black & 0x800000000L) != 0x0L) {
            mBoardGridList.get(35).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(35).setBoardgrid_piece(0);
        }


        if ((white & 0x1000000000L) != 0x0L) {
            mBoardGridList.get(36).setBoardgrid_piece(1);
        } else if ((black & 0x1000000000L) != 0x0L) {
            mBoardGridList.get(36).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(36).setBoardgrid_piece(0);
        }


        if ((white & 0x2000000000L) != 0x0L) {
            mBoardGridList.get(37).setBoardgrid_piece(1);
        } else if ((black & 0x2000000000L) != 0x0L) {
            mBoardGridList.get(37).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(37).setBoardgrid_piece(0);
        }


        if ((white & 0x4000000000L) != 0x0L) {
            mBoardGridList.get(38).setBoardgrid_piece(1);
        } else if ((black & 0x4000000000L) != 0x0L) {
            mBoardGridList.get(38).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(38).setBoardgrid_piece(0);
        }


        if ((white & 0x8000000000L) != 0x0L) {
            mBoardGridList.get(39).setBoardgrid_piece(1);
        } else if ((black & 0x8000000000L) != 0x0L) {
            mBoardGridList.get(39).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(39).setBoardgrid_piece(0);
        }

        //-------------------------------------------------------------------------

        if ((white & 0x10000000000L) != 0x0L) {
            mBoardGridList.get(40).setBoardgrid_piece(1);
        } else if ((black & 0x10000000000L) != 0x0L) {
            mBoardGridList.get(40).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(40).setBoardgrid_piece(0);
        }


        if ((white & 0x20000000000L) != 0x0L) {
            mBoardGridList.get(41).setBoardgrid_piece(1);
        } else if ((black & 0x20000000000L) != 0x0L) {
            mBoardGridList.get(41).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(41).setBoardgrid_piece(0);
        }


        if ((white & 0x40000000000L) != 0x0L) {
            mBoardGridList.get(42).setBoardgrid_piece(1);
        } else if ((black & 0x40000000000L) != 0x0L) {
            mBoardGridList.get(42).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(42).setBoardgrid_piece(0);
        }


        if ((white & 0x80000000000L) != 0x0L) {
            mBoardGridList.get(43).setBoardgrid_piece(1);
        } else if ((black & 0x80000000000L) != 0x0L) {
            mBoardGridList.get(43).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(43).setBoardgrid_piece(0);
        }


        if ((white & 0x100000000000L) != 0x0L) {
            mBoardGridList.get(44).setBoardgrid_piece(1);
        } else if ((black & 0x100000000000L) != 0x0L) {
            mBoardGridList.get(44).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(44).setBoardgrid_piece(0);
        }


        if ((white & 0x200000000000L) != 0x0L) {
            mBoardGridList.get(45).setBoardgrid_piece(1);
        } else if ((black & 0x200000000000L) != 0x0L) {
            mBoardGridList.get(45).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(45).setBoardgrid_piece(0);
        }


        if ((white & 0x400000000000L) != 0x0L) {
            mBoardGridList.get(46).setBoardgrid_piece(1);
        } else if ((black & 0x400000000000L) != 0x0L) {
            mBoardGridList.get(46).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(46).setBoardgrid_piece(0);
        }


        if ((white & 0x800000000000L) != 0x0L) {
            mBoardGridList.get(47).setBoardgrid_piece(1);
        } else if ((black & 0x800000000000L) != 0x0L) {
            mBoardGridList.get(47).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(47).setBoardgrid_piece(0);
        }

        //-----------------------------------------------------------------------

        if ((white & 0x1000000000000L) != 0x0L) {
            mBoardGridList.get(48).setBoardgrid_piece(1);
        } else if ((black & 0x1000000000000L) != 0x0L) {
            mBoardGridList.get(48).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(48).setBoardgrid_piece(0);
        }

        if ((white & 0x2000000000000L) != 0x0L) {
            mBoardGridList.get(49).setBoardgrid_piece(1);
        } else if ((black & 0x2000000000000L) != 0x0L) {
            mBoardGridList.get(49).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(49).setBoardgrid_piece(0);
        }

        if ((white & 0x4000000000000L) != 0x0L) {
            mBoardGridList.get(50).setBoardgrid_piece(1);
        } else if ((black & 0x4000000000000L) != 0x0L) {
            mBoardGridList.get(50).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(50).setBoardgrid_piece(0);
        }


        if ((white & 0x8000000000000L) != 0x0L) {
            mBoardGridList.get(51).setBoardgrid_piece(1);
        } else if ((black & 0x8000000000000L) != 0x0L) {
            mBoardGridList.get(51).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(51).setBoardgrid_piece(0);
        }


        if ((white & 0x10000000000000L) != 0x0L) {
            mBoardGridList.get(52).setBoardgrid_piece(1);
        } else if ((black & 0x10000000000000L) != 0x0L) {
            mBoardGridList.get(52).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(52).setBoardgrid_piece(0);
        }


        if ((white & 0x20000000000000L) != 0x0L) {
            mBoardGridList.get(53).setBoardgrid_piece(1);
        } else if ((black & 0x20000000000000L) != 0x0L) {
            mBoardGridList.get(53).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(53).setBoardgrid_piece(0);
        }


        if ((white & 0x40000000000000L) != 0x0L) {
            mBoardGridList.get(54).setBoardgrid_piece(1);
        } else if ((black & 0x40000000000000L) != 0x0L) {
            mBoardGridList.get(54).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(54).setBoardgrid_piece(0);
        }


        if ((white & 0x80000000000000L) != 0x0L) {
            mBoardGridList.get(55).setBoardgrid_piece(1);
        } else if ((black & 0x80000000000000L) != 0x0L) {
            mBoardGridList.get(55).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(55).setBoardgrid_piece(0);
        }

        //----------------------------------------------------------------------

        if ((white & 0x100000000000000L) != 0x0L) {
            mBoardGridList.get(56).setBoardgrid_piece(1);
        } else if ((black & 0x100000000000000L) != 0x0L) {
            mBoardGridList.get(56).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(56).setBoardgrid_piece(0);
        }


        if ((white & 0x200000000000000L) != 0x0L) {
            mBoardGridList.get(57).setBoardgrid_piece(1);
        } else if ((black & 0x200000000000000L) != 0x0L) {
            mBoardGridList.get(57).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(57).setBoardgrid_piece(0);
        }


        if ((white & 0x400000000000000L) != 0x0L) {
            mBoardGridList.get(58).setBoardgrid_piece(1);
        } else if ((black & 0x400000000000000L) != 0x0L) {
            mBoardGridList.get(58).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(58).setBoardgrid_piece(0);
        }


        if ((white & 0x800000000000000L) != 0x0L) {
            mBoardGridList.get(59).setBoardgrid_piece(1);
        } else if ((black & 0x800000000000000L) != 0x0L) {
            mBoardGridList.get(59).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(59).setBoardgrid_piece(0);
        }


        if ((white & 0x1000000000000000L) != 0x0L) {
            mBoardGridList.get(60).setBoardgrid_piece(1);
        } else if ((black & 0x1000000000000000L) != 0x0L) {
            mBoardGridList.get(60).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(60).setBoardgrid_piece(0);
        }


        if ((white & 0x2000000000000000L) != 0x0L) {
            mBoardGridList.get(61).setBoardgrid_piece(1);
        } else if ((black & 0x2000000000000000L) != 0x0L) {
            mBoardGridList.get(61).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(61).setBoardgrid_piece(0);
        }


        if ((white & 0x4000000000000000L) != 0x0L) {
            mBoardGridList.get(62).setBoardgrid_piece(1);
        } else if ((black & 0x4000000000000000L) != 0x0L) {
            mBoardGridList.get(62).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(62).setBoardgrid_piece(0);
        }


        if ((white & 0x8000000000000000L) != 0x0L) {
            mBoardGridList.get(63).setBoardgrid_piece(1);
        } else if ((black & 0x8000000000000000L) != 0x0L) {
            mBoardGridList.get(63).setBoardgrid_piece(-1);
        } else {
            mBoardGridList.get(63).setBoardgrid_piece(0);
        }

    }

}