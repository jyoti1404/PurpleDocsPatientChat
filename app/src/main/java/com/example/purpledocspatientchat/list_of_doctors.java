package com.example.purpledocspatientchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class list_of_doctors extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<UserPojo> arrayList = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_doctors);

        database= FirebaseDatabase.getInstance();
        reference = database.getReference("userinfo");
        final SharedPreferences sharedPreferences = getSharedPreferences("PREFERENCE",MODE_PRIVATE);

        recyclerView = findViewById(R.id.list_of_doctors);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    UserPojo userPojo = dataSnapshot1.getValue(UserPojo.class);

                    if (!userPojo.getPassCode().equals(sharedPreferences.getString("passCode", null))) {
                        arrayList.add(userPojo);

                    }
                }
                CustomAdapter customAdapter = new CustomAdapter(arrayList, list_of_doctors.this);
                recyclerView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(list_of_doctors.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
