package com.example.vloussyaddmessage.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vloussyaddmessage.DataCallBack;
import com.example.vloussyaddmessage.Model.MessageModel;
import com.example.vloussyaddmessage.R;

import java.util.List;

public class fragment1Adapter extends RecyclerView.Adapter<fragment1Adapter.Fragment1ViewHolder> {

    public Activity context;
    public List<MessageModel> messageModelList;
    DataCallBack dataCallBack;

    public fragment1Adapter(Activity context, List<MessageModel> messageModelList, DataCallBack dataCallBack) {

        this.context = context;
        this.messageModelList = messageModelList;
        this.dataCallBack = dataCallBack;

    }

    @NonNull
    @Override
    public fragment1Adapter.Fragment1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Fragment1ViewHolder viewHolder = new Fragment1ViewHolder(inflater.inflate(R.layout.message_item, parent, false));
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull fragment1Adapter.Fragment1ViewHolder holder, int position) {

        MessageModel messageModel = messageModelList.get(position);

        holder.destinationItem.setText(messageModel.destination);
        holder.sourceItem.setText(messageModel.source);
        holder.amount1Item.setText(messageModel.amount1);
        holder.amount2Item.setText(messageModel.amount2);
        holder.senderNameItem.setText(messageModel.senderName);
        holder.senderMobileNumberItem.setText(messageModel.senderMobileNumber);
        holder.priceItem.setText(messageModel.price);
        holder.dateItem.setText(messageModel.messageDate);

    }

    @Override
    public int getItemCount() {
        return messageModelList.size();
    }

    public class Fragment1ViewHolder extends RecyclerView.ViewHolder {

        TextView destinationItem;
        TextView sourceItem;
        TextView amount1Item;
        TextView amount2Item;
        TextView senderNameItem;
        TextView senderMobileNumberItem;
        TextView priceItem;
        TextView dateItem;

        public Fragment1ViewHolder(@NonNull View itemView) {
            super(itemView);

            destinationItem = itemView.findViewById(R.id.destinationItem);
            sourceItem = itemView.findViewById(R.id.sourceItem);
            amount1Item = itemView.findViewById(R.id.amount1Item);
            amount2Item = itemView.findViewById(R.id.amount2Item);
            senderNameItem = itemView.findViewById(R.id.senderNameItem);
            senderMobileNumberItem = itemView.findViewById(R.id.senderMobileNumberItem);
            priceItem = itemView.findViewById(R.id.priceItem);
            dateItem = itemView.findViewById(R.id.dateItem);

            itemView.setOnClickListener(view -> {
                MessageModel messageModel = messageModelList.get(getAdapterPosition());
                dataCallBack.Result(messageModel, "", getAdapterPosition());

            });

        }
    }
}
