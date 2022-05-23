package com.example.vloussyaddmessage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.vloussyaddmessage.Adapter.fragment1Adapter;
import com.example.vloussyaddmessage.DataCallBack;
import com.example.vloussyaddmessage.MessageDetails;
import com.example.vloussyaddmessage.Model.MessageModel;
import com.example.vloussyaddmessage.R;
import com.example.vloussyaddmessage.databinding.Fragment1LayoutBinding;
import com.example.vloussyaddmessage.databinding.Fragment5LayoutBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Fragment5 extends Fragment {

    Fragment5LayoutBinding binding;
    FirebaseFirestore fireStoreDB;
    MessageModel messageModel;
    ArrayList<MessageModel> messageArrayList;
    fragment1Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = Fragment5LayoutBinding.inflate(inflater, container, false);

        fireStoreDB = FirebaseFirestore.getInstance();
        messageArrayList = new ArrayList<>();

//        messageModel = new MessageModel();

        binding.fragment5RV.setLayoutManager(new LinearLayoutManager(getActivity()));
//        adapter = new fragment1Adapter(getActivity(), messageArrayList, new DataCallBack() {
//            @Override
//            public void Result(Object obj, String type, Object otherData) {
//
//                messageModel = (MessageModel) obj;
//                int position = (int) otherData;
////                Intent intent = new Intent(getActivity(), MessageDetails.class);
////                startActivity(intent);
//
//            }
//        });
//
//        binding.fragment1RV.setAdapter(adapter);

        getMessageData();
        return binding.getRoot();
    }

    private void initAdapter() {

        adapter = new fragment1Adapter(getActivity(), messageArrayList, new DataCallBack() {
            @Override
            public void Result(Object obj, String type, Object otherData) {

                messageModel = (MessageModel) obj;
                int position = (int) otherData;
//                Intent intent = new Intent(getActivity(), MessageDetails.class);
//                startActivity(intent);
                Intent intent = new Intent(getActivity(), MessageDetails.class);
                intent.putExtra("messageModel", messageModel);
                startActivity(intent);

            }
        });

        binding.fragment5RV.setAdapter(adapter);

    }

    public void getMessageData() {

        binding.loadingLy.setVisibility(View.VISIBLE);
        fireStoreDB.collection("Messages")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    binding.loadingLy.setVisibility(View.GONE);

                    for (DocumentSnapshot document : task.getResult().getDocuments()) {
                        MessageModel messageModel = document.toObject(MessageModel.class);
                        messageArrayList.add(messageModel);
                    }
                    initAdapter();
                } else {
                    binding.loadingLy.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), getString(R.string.fail_get_data), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}