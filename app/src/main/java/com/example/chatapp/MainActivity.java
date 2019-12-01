package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import info.hoang8f.widget.FButton;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    EditText userName;
    FButton fButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepare();
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String> username=new HashMap<>();
                username.put("username",userName.getText().toString());
                myRef.child("users").child(userName.getText().toString()).child("username").setValue(userName.getText().toString());
                Intent intent=new Intent(MainActivity.this,UsersActivity.class);
                intent.putExtra("userName",userName.getText().toString());
                startActivity(intent);

            }
        });

        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                            if(editable.toString().contains("."))
                                fButton.setEnabled(false);
                            else
                                fButton.setEnabled(true);
            }
        });
    }

    private void prepare() {
        userName=findViewById(R.id.edtUsername);
        fButton=findViewById(R.id.btnGiris);
    }
}
