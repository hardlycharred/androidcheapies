package com.hardlycharred.choicecheapies.gui;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hardlycharred.choicecheapies.R;
import com.hardlycharred.choicecheapies.domain.Deal;
import com.hardlycharred.choicecheapies.misc.PopulateSales;

import java.io.IOException;
import java.util.ArrayList;

import static com.hardlycharred.choicecheapies.R.id.dealCheapies;
import static com.hardlycharred.choicecheapies.R.id.dealName;
import static com.hardlycharred.choicecheapies.R.id.dealURL;

public class ViewIndividualDealActivity extends AppCompatActivity {

    Intent intent;
    Deal curDeal;
    TextView tvDealDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_individual_deal);
        TextView tvDealName = (TextView) findViewById(dealName);
        TextView tvDealURL = (TextView) findViewById(dealURL);
        TextView tvCheapiesURL = (TextView) findViewById(dealCheapies);
        tvDealDesc = (TextView) findViewById(R.id.dealDesc);
        intent = getIntent();
        curDeal = (Deal) intent.getSerializableExtra(ViewAllDealsActivity.DEAL);
        tvDealName.setText(curDeal.getTitle());
        tvDealURL.setText(Html.fromHtml("<u>" + curDeal.getDealURL() + "</u>"));
        tvDealURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchBrowserIntent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(curDeal.getDealURL()));
                startActivity(launchBrowserIntent);
            }
        });
        tvCheapiesURL.setText(Html.fromHtml("<u>" + curDeal.getCheapiesURL() + "</u>"));
        tvCheapiesURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchBrowserIntent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(curDeal.getCheapiesURL()));
                startActivity(launchBrowserIntent);
            }
        });
        if (curDeal.getDescription() == null) {
            tvDealDesc.setText("Getting Deal Description...");
            new FetchDescriptionsTask().execute();
        } else {
            tvDealDesc.setText(curDeal.getDescription());
        }
    }

    class FetchDescriptionsTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            Log.d("FetchDealsTask", "Entered doInBackground");
            ArrayList<Deal> retrievedDeals = new ArrayList<>();
            Log.d("FetchDealsTask", "Made arraylist");
            PopulateSales populateSales = new PopulateSales();
            String dealDesc = "";
            try {
                dealDesc = populateSales.getDealDescription(curDeal.getCheapiesURL());
            } catch (IOException e) {
                e.printStackTrace();
                dealDesc = "Not found";
            }
            return dealDesc;
        }

        @Override
        protected void onPostExecute(String dealDesc) {
            tvDealDesc.setText(dealDesc);


        }
    }


}
