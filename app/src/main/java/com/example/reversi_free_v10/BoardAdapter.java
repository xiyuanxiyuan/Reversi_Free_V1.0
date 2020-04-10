package com.example.reversi_free_v10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private List<BoardGrid> mBoardGridList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        Button boardGrid;

        public ViewHolder(View view) {
            super(view);
            boardGrid = (Button) view.findViewById(R.id.board_grid);
        }
    }

    public BoardAdapter(List<BoardGrid> boardGridList) {
        mBoardGridList = boardGridList;
    }

    @Override
    public BoardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_grid_layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.boardGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                BoardGrid boardGrid=mBoardGridList.get(position);
                Toast.makeText(view.getContext(),"you clicked: "+boardGrid.getButton_id(),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//        BoardGrid boardGrid = mBoardGridList.get(position);
//        holder.boardGrid.setId(boardGrid.getButton_id());

    }

    public int getItemCount() {
        return mBoardGridList.size();
    }
}
