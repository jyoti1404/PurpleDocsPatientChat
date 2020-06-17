package com.example.purpledocspatientchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    Context context;
    ArrayList<MessagePojo> arrayList;

    public MessageAdapter(ArrayList<MessagePojo> arrayList, Context context){
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View list_item = layoutInflater.inflate(R.layout.recycler_item, parent, false);
        MessageAdapter.ViewHolder viewHolder = new MessageAdapter.ViewHolder(list_item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        MessagePojo messagePojo = arrayList.get(position);
        holder.textViewMessage.setText(messagePojo.getMessage());
        holder.textViewTime.setText(messagePojo.getTime());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewMessage;
        TextView textViewTime;

        public ViewHolder(View list_item) {
            super(list_item);

            textViewTime = list_item.findViewById(R.id.textViewTime);
            textViewMessage = list_item.findViewById(R.id.textViewMessage);
        }
    }
}
