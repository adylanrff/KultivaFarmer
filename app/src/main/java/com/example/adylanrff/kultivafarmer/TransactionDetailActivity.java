package com.example.adylanrff.kultivafarmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TransactionDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);

        Intent i = getIntent();
        Transaction transaction = (Transaction) i.getSerializableExtra("transaction");
        int number = i.getIntExtra("number",0);



    }
}
