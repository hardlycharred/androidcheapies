package com.hardlycharred.choicecheapies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.hardlycharred.choicecheapies.R.id.dealName;

public class ViewIndividualDeal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_individual_deal);
        TextView tvDealName = (TextView) findViewById(dealName);
        TextView tvDealDesc = (TextView) findViewById(R.id.dealDesc);
        Intent intent = getIntent();
        Deal curDeal = (Deal) intent.getSerializableExtra(ViewAllDeals.DEAL);
        tvDealName.setText("Deal Title: " + curDeal.getTitle());
        tvDealDesc.setText("Deal Description: " + curDeal.getDescription());


    }
}
