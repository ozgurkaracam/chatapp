package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.chatapp.Adapter.MessageAdapter;
import com.example.chatapp.Data.Message;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    String username,othername;
    EditText msgText;
    ImageView sendBtn;
    DatabaseReference dbRef;
    Message message;
    ArrayList<Message> messages;
    RecyclerView recyclerMessages;
    MessageAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        username=getIntent().getStringExtra("username");
        othername=getIntent().getStringExtra("othername");

        msgText=findViewById(R.id.msgText);
        sendBtn=findViewById(R.id.sendBtn);
        dbRef= FirebaseDatabase.getInstance().getReference("messages");
        messages=new ArrayList<Message>();
        recyclerMessages=findViewById(R.id.recyclerMessages);




        messages.add(new Message(username,othername,"asdasdasd"));
        messages.add(new Message(othername,username,"qweqwe"));
        messages.add(new Message(username,othername,"xzczxc"));



        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message=new Message(username,othername,msgText.getText().toString());
                dbRef.child(username).child(othername).push().setValue(message);
                dbRef.child(othername).child(username).push().setValue(message);

                msgText.setText("");
            }
        });




        dbRef.child(username).child(othername).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    ArrayList<Message> messages=new ArrayList<>();
                    for(DataSnapshot dsnp: dataSnapshot.getChildren()){
                        Log.e("txt",dsnp.getValue(Message.class).getFrom());
                        messages.add(dsnp.getValue(Message.class));

                    }
                    messageAdapter=new MessageAdapter(messages,getApplicationContext(),ChatActivity.this,username,othername);
                    recyclerMessages.setAdapter(messageAdapter);
                    recyclerMessages.setLayoutManager(new LinearLayoutManager(ChatActivity.this));



                }
                catch (Exception e){}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
