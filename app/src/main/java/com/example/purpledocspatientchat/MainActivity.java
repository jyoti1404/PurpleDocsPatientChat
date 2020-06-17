package com.example.purpledocspatientchat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button button_join_chat;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_join_chat = findViewById(R.id.button_join_chat);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("userinfo");
        sharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);

        button_join_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                final View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog, viewGroup, false);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                final EditText passcode = alertDialog.findViewById(R.id.edit_chat_passcode);
                Button ok = alertDialog.findViewById(R.id.button_ok);
                Button cancel = alertDialog.findViewById(R.id.button_cancel);

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String id = databaseReference.push().getKey();
                        String pass = passcode.getText().toString();

                        if (!(passcode.getText().toString().equals(""))){

                            UserPojo userPojo = new UserPojo();
                            userPojo.setId(id);
                            userPojo.setPassCode(pass);
                            databaseReference.child(id).setValue(userPojo);

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("id", userPojo.getId());
                            editor.putString("passCode", userPojo.getPassCode());;
                            editor.commit();

                            Intent intent = new Intent(MainActivity.this, list_of_doctors.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(MainActivity.this, "Chat Started", Toast.LENGTH_SHORT).show();
                        }
                        else if (passcode.getText().toString().equals("")){
                            Toast.makeText(MainActivity.this, "Invalid PassCode", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Invalid PassCode", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
            }
        });
    }
}
