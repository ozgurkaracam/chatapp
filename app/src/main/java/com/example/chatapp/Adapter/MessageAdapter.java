package com.example.chatapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.print.PrintAttributes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.Data.Message;
import com.example.chatapp.R;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    ArrayList<Message> messages;
    Context context;
    Activity activity;
    String username,othername;

    public MessageAdapter() {
    }

    public MessageAdapter(ArrayList<Message> messages, Context context, Activity activity,String username, String othername) {
        this.messages = messages;
        this.context = context;
        this.activity = activity;
        this.username=username;
        this.othername=othername;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.recycler_messages,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtMessage.setText(messages.get(position).getText());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(5,5,5,5);
        params.gravity = Gravity.RIGHT;


        if(!messages.get(position).getFrom().equals(username)){
            holder.txtMessage.setLayoutParams(params);
            holder.txtMessage.setBackgroundColor(Color.WHITE);
        }
        else{}


    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMessage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
                txtMessage=itemView.findViewById(R.id.txtMessage);
        }
    }
}