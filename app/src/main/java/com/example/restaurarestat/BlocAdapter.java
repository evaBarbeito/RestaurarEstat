package com.example.restaurarestat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BlocAdapter extends RecyclerView.Adapter<BlocAdapter.BlocViewHolder> {

    private ArrayList<Bloc> mBlocList;

    public static class BlocViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewLine1;
        public TextView mTextViewLine2;

        public BlocViewHolder(View itemView) {
            super(itemView);
            mTextViewLine1 = itemView.findViewById(R.id.textview_line1);
            mTextViewLine2 = itemView.findViewById(R.id.textview_line_2);
        }
    }

    public BlocAdapter(ArrayList<Bloc> exampleList) {
        mBlocList = exampleList;
    }


    @Override
    public BlocViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        BlocViewHolder evh = new BlocViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(BlocViewHolder holder, int position) {
        Bloc currentItem = mBlocList.get(position);

        holder.mTextViewLine1.setText(currentItem.getHoraInici());
        holder.mTextViewLine2.setText(currentItem.getTemperatura());
    }

    @Override
    public int getItemCount() {
        return mBlocList.size();
    }
}
