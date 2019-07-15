package com.example.todolast.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolast.Model.Todo;
import com.example.todolast.R;
import com.example.todolast.ViewDetail;

import java.util.List;
import java.util.zip.Inflater;


public class CourceAdapter extends RecyclerView.Adapter<CourceAdapter.CourceViewHolder> {
    List<Todo> noteslist;
    Context context;
    public CourceAdapter(List<Todo> noteslist, Context context){
        this.noteslist=noteslist;
        this.context=context;
    }

    @NonNull
    @Override
    public CourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from((parent.getContext()));
        View view= inflater.inflate(R.layout.item,parent,false);
        return new CourceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourceViewHolder holder, int position) {
        Todo data=noteslist.get(position);
        holder.titlerecycler.setText(data.getTitle());
        holder.descrecycler.setText(data.getDesc());


    }

    @Override
    public int getItemCount() {
        return noteslist.size();
    }

    public class CourceViewHolder extends RecyclerView.ViewHolder{
        TextView titlerecycler,descrecycler;

        public CourceViewHolder(@NonNull View itemView) {
            super(itemView);
            titlerecycler=itemView.findViewById(R.id.itemTitle);
            descrecycler=itemView.findViewById(R.id.itemDesc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Todo todo=noteslist.get(getAdapterPosition());
                    Intent i = new Intent(context, ViewDetail.class);
                    i.putExtra("id",todo.getId());
                    i.putExtra("title",todo.getTitle());
                    i.putExtra("desc",todo.getDesc());
                    context.startActivity(i);
                }
            });
        }
    }
}
