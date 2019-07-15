package com.example.githubapi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubapi.Model.User;
import com.example.githubapi.Model.User;
import com.example.githubapi.R;
import com.squareup.picasso.Picasso;
//import com.example.githubapi.ViewDetail;

import java.util.List;
import java.util.zip.Inflater;


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
      login=geterl.getType();
      usertype=geterl.getLogin();
      image=geterl.getAvatarUrl();
      type.setText(usertype);
      username.setText(login);
      Picasso.get().load(image).into(profilepic);
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
            username=itemView.findViewById(R.id.text1);
            type=itemView.findViewById(R.id.text2);


        }
    }
}
