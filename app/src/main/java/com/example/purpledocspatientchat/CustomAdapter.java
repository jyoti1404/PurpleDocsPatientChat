package com.example.purpledocspatientchat;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    Context context;
    ArrayList<UserPojo> arrayList;

    public CustomAdapter(ArrayList<UserPojo> arrayList , Context context){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        final UserPojo userPojo=arrayList.get(position);
        holder.caseno.setText(userPojo.getPassCode());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ChatRoom.class);
                intent.putExtra("passCode", userPojo.getPassCode());
                intent.putExtra("id", userPojo.getId());
                Log.d("12345",userPojo.getPassCode()+ "    "+userPojo.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView caseno;
        public LinearLayout linearLayout;

        public ViewHolder(View listItem) {
            super(listItem);
            caseno = listItem.findViewById(R.id.caseno);
            linearLayout = listItem.findViewById(R.id.linearlayout);

        }
    }
}
