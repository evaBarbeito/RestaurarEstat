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

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class BlocViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewLine1;
        public TextView mTextViewLine2;

        public BlocViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextViewLine1 = itemView.findViewById(R.id.textview_line1) ;
            mTextViewLine2 = itemView.findViewById(R.id.textview_line_2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public BlocAdapter(ArrayList<Bloc> exampleList) {
        mBlocList = exampleList;
    }


    @Override
    public BlocViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        BlocViewHolder evh = new BlocViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(BlocViewHolder holder, int position) {
        Bloc currentItem = mBlocList.get(position);

        holder.mTextViewLine1.setText(currentItem.getHoraInici() +  ":00 h");
        holder.mTextViewLine2.setText(currentItem.getTemperatura()+ "ºC");
    }

    @Override
    public int getItemCount() {
        return mBlocList.size();
    }
}
