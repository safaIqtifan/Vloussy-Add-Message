package com.example.vloussyaddmessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vloussyaddmessage.Model.CoinsModel;
import com.example.vloussyaddmessage.Model.MessageModel;
import com.example.vloussyaddmessage.Model.PartnersModel;
import com.example.vloussyaddmessage.databinding.ActivityAddNewMessageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddNewMessage extends AppCompatActivity {

    ActivityAddNewMessageBinding binding;
    MessageModel messageModel;
    FirebaseFirestore fireStoreDB;
    //    ProgressBar loadingLY;
    Calendar calendar;
    //    PartnersModel partnersModel;
    CoinsModel coinsModel;
    String partnersNameCoin;
    ArrayList<PartnersModel> partnerArrayList;
    ArrayList<String> namesList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_new_message);
        binding = ActivityAddNewMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText d = findViewById(R.id.amount1);
        TextInputEditText dx = findViewById(R.id.amount1);
        fireStoreDB = FirebaseFirestore.getInstance();
        messageModel = new MessageModel();
        partnerArrayList = new ArrayList<>();
        String[] namesList = new String[]{};

        binding.loadingLY.setVisibility(View.VISIBLE);
        getPartnerData();

        calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
        String date = simpleDateFormat.format(calendar.getTime());
        binding.dateTV.setText(date);

        SimpleDateFormat simpletimeFormat = new SimpleDateFormat("h:mm a");
        String time = simpletimeFormat.format(calendar.getTime());
        binding.timeTV.setText(time);

        binding.destinationTV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                for (PartnersModel partnersModel : partnerArrayList) {
                    if (editable.toString().equals(partnersModel.namePartners)) {
                        partnersNameCoin = partnersModel.partnersCoin;
                        getCoin();
                        break;
                    }
                }
            }
        });

        binding.amount1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String amount1Str = binding.amount1.getText().toString();
//                String amount2Str = binding.amount2.getText().toString();
                String priceStr = binding.price.getText().toString();
                if (!amount1Str.isEmpty()) {
                    double priceDouble = Double.parseDouble(priceStr);
                    double amount1Double = Double.parseDouble(binding.amount1.getText().toString());

                    binding.amount2.setText(String.valueOf(amount1Double * priceDouble));
                }
            }
        });

        binding.price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String amount1Str = binding.amount1.getText().toString();
                String amount2Str = binding.amount2.getText().toString();
                String priceStr = binding.price.getText().toString();
                if (!amount1Str.isEmpty() && !amount2Str.isEmpty()) {
                    double priceDouble = Double.parseDouble(priceStr);
                    double amount1Double = Double.parseDouble(binding.amount1.getText().toString());

                    binding.amount2.setText(String.valueOf(amount1Double * priceDouble));
                }
            }
        });

        binding.addBtn.setOnClickListener(view -> {
            checkData();
        });

    }

    private void checkData() {

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm a");
//        String time = simpleDateFormat.format(calendar.getTime());

        String dateStr = binding.dateTV.getText().toString();
        String timeStr = binding.timeTV.getText().toString();
        String destinationStr = binding.destinationTV.getText().toString();
        String sourceStr = binding.sourceTV.getText().toString();
        String amount1Str = binding.amount1.getText().toString();
        String amount2Str = binding.amount2.getText().toString();
//        double amount1Dou = Double.parseDouble(amount1Str);
//        double amount2Dou = Double.parseDouble(amount2Str);
        String priceStr = binding.price.getText().toString();
        String senderNameStr = binding.senderNameTV.getText().toString();
        String senderMobileNumberStr = binding.senderMobileNumber.getText().toString();
        String receiverNameStr = binding.receiverNameTV.getText().toString();
        String receiverMobileNumberStr = binding.receiverMobileNumber.getText().toString();
        String notesStr = binding.notesTV.getText().toString();

        binding.loadingLY.setVisibility(View.VISIBLE);
        boolean hasError = false;

        if (destinationStr.isEmpty()) {
            binding.destinationTV.setError(this.getString(R.string.invalid_input));
            hasError = true;
        }
        if (sourceStr.isEmpty()) {
            binding.sourceTV.setError(this.getString(R.string.invalid_input));
            hasError = true;
        }
        if (amount1Str.isEmpty()) {
            binding.amount1.setError(this.getString(R.string.invalid_input));
            hasError = true;
        }
        if (amount2Str.isEmpty()) {
            binding.amount2.setError(this.getString(R.string.invalid_input));
            hasError = true;
        }
        if (Double.parseDouble(amount1Str) == 0) {
            binding.amount1.setError("يجب ان يكون الحقل أكبر من 0");
            hasError = true;
        }
        if (Double.parseDouble(amount2Str) == 0) {
            binding.amount2.setError("يجب ان يكون الحقل أكبر من 0");
            hasError = true;
        }
        if (priceStr.isEmpty()) {
            binding.price.setError(this.getString(R.string.invalid_input));
            hasError = true;
        }
        if (senderNameStr.isEmpty()) {
            binding.senderNameTV.setError(this.getString(R.string.invalid_input));
            hasError = true;
        }
        if (senderMobileNumberStr.isEmpty()) {
            binding.senderMobileNumber.setError(this.getString(R.string.invalid_input));
            hasError = true;
        }
        if (receiverNameStr.isEmpty()) {
            binding.receiverNameTV.setError(this.getString(R.string.invalid_input));
            hasError = true;
        }
        if (receiverMobileNumberStr.isEmpty()) {
            binding.receiverMobileNumber.setError(this.getString(R.string.invalid_input));
            hasError = true;
        }
        if (notesStr.isEmpty()) {
            binding.notesTV.setError(this.getString(R.string.invalid_input));
            hasError = true;
        }
        if (hasError)
            return;

        messageModel.messageDate = dateStr;
        messageModel.messagetime = timeStr;
        messageModel.destination = destinationStr;
        messageModel.source = sourceStr;
        messageModel.amount1 = amount1Str;
        messageModel.amount2 = amount2Str;
        messageModel.price = priceStr;
        messageModel.senderName = senderNameStr;
        messageModel.senderMobileNumber = senderMobileNumberStr;
        messageModel.receiverName = receiverNameStr;
        messageModel.receiverMobileNumber = receiverMobileNumberStr;
        messageModel.notes = notesStr;

        saveMessageData();

    }

    private void saveMessageData() {

        binding.loadingLY.setVisibility(View.VISIBLE);
        String messageId = fireStoreDB.collection("Messages").document().getId(); // this is auto genrat

        Map<String, Object> postModelMap = new HashMap<>();
        postModelMap.put("post_id", messageId);
        postModelMap.put("messageDate", messageModel.messageDate);
        postModelMap.put("messagetime", messageModel.messagetime);
        postModelMap.put("destination", messageModel.destination);
        postModelMap.put("source", messageModel.source);
        postModelMap.put("amount1", messageModel.amount1);
        postModelMap.put("amount2", messageModel.amount2);
        postModelMap.put("price", messageModel.price);
        postModelMap.put("senderName", messageModel.senderName);
        postModelMap.put("senderMobileNumber", messageModel.senderMobileNumber);
        postModelMap.put("receiverName", messageModel.receiverName);
        postModelMap.put("receiverMobileNumber", messageModel.receiverMobileNumber);
        postModelMap.put("messageisDrawn", messageModel.messageisDrawn);
        postModelMap.put("drawnDate", messageModel.drawnDate);
        postModelMap.put("notes", messageModel.notes);
//        postModelMap.put("created_at", FieldValue.serverTimestamp());

        fireStoreDB.collection("Messages").document(messageId).set(postModelMap, SetOptions.merge())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            binding.loadingLY.setVisibility(View.GONE);

                            Toast.makeText(getApplicationContext(), getString(R.string.success_add_post), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddNewMessage.this, MainActivity.class);
                            startActivity(intent);
                            finish();
//                            onBackPressed();

                        } else {
                            binding.loadingLY.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), getString(R.string.fail_add_post), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void getPartnerData() {

        fireStoreDB.collection("Partners")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {

                    binding.loadingLY.setVisibility(View.GONE);

                    for (DocumentSnapshot document : task.getResult().getDocuments()) {
                        PartnersModel partnersModel = document.toObject(PartnersModel.class);
                        partnerArrayList.add(partnersModel);
                        String names = partnersModel.namePartners;
                        namesList.add(names);
                    }
//                    partnersNameCoin = partnersModel.partnersCoin;
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddNewMessage.this,
                            android.R.layout.simple_list_item_1, namesList);
                    binding.destinationTV.setAdapter(adapter);
                    binding.sourceTV.setAdapter(adapter);

                } else {
                    binding.loadingLY.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), getString(R.string.fail_get_data), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getCoin() {

        fireStoreDB.collection("Coins")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult().getDocuments()) {
                        coinsModel = document.toObject(CoinsModel.class);
//                        Log.e("a", partnersNameCoin +"");
                        if (partnersNameCoin.equals(coinsModel.coinName)) {
                            binding.price.setText(String.valueOf(coinsModel.coinSale));
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.fail_get_data), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}