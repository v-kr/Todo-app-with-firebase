package com.example.recyclerview;

import android.icu.text.CaseMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class CourceAdapter extends RecyclerView.Adapter<CourceAdapter.CourceViewHolder> {

    private  String[] data;
    public CourceAdapter(String[] data){
        this.data=data;

    }
    @NonNull
    @Override
    public CourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item,parent,false);

        return new CourceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourceViewHolder holder, int position) {
        String title=data[position];
        holder.txt1.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class CourceViewHolder extends RecyclerView.ViewHolder{
        TextView txt1;
        ImageView img1;


        public CourceViewHolder(@NonNull View itemView) {


            super(itemView);

            img1=itemView.findViewById(R.id.imageView);
            txt1=itemView.findViewById(R.id.textView);

        }
    }

}