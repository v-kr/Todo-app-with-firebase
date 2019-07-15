package com.example.sports.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sports.Model.User;
import com.example.sports.R;

import java.util.List;


public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourceViewHolder> {
    List<User> data;
    Context context;
    ImageView profilepic;
    TextView username,type;
    public CourseAdapter(List<User> noteslist, Context context){
        this.data=noteslist;
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
        final User geterl =data.get(position);
        String login,image,usertype;
        login=geterl.getTitle();
        usertype=geterl.getDescription();
        type.setText(usertype);
        username.setText(login);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CourceViewHolder extends RecyclerView.ViewHolder{
//        TextView titlerecycler,descrecycler;

        public CourceViewHolder(@NonNull final View itemView) {
            super(itemView);
            profilepic=itemView.findViewById(R.id.image1);
            username=itemView.findViewById(R.id.title);
            type=itemView.findViewById(R.id.description);


        }
    }
}
