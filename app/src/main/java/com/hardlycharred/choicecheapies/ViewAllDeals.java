package com.hardlycharred.choicecheapies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class ViewAllDeals extends AppCompatActivity {

    public static final String MESSAGE = "com.hardlycharred.choicecheapies.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_deals);

        ListView lv = (ListView) findViewById(R.id.allDealsView);

        //Declaration part
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                ViewAllDeals.this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>());
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String thisDeal = (String) parent.getItemAtPosition(position);
                Log.d("ViewAllDeals CLick", thisDeal);
                Intent intent = new Intent(ViewAllDeals.this, ViewIndividualDeal.class);
                intent.putExtra(MESSAGE, thisDeal);
                startActivity(intent);
            }
        });

        Log.d("Before thread called ", "We made it this far");
        new FetchDealsTask().execute();
        Log.d("After thread is called ", "We made it this far");


    }

    class FetchDealsTask extends AsyncTask<Void, Void, ArrayList> {

        @Override
        protected ArrayList doInBackground(Void... params) {
            Log.d("FetchDealsTask", "Entered doInBackground");
            ArrayList<String> dealNames = new ArrayList<>();
            Log.d("FetchDealsTask", "Made arraylist");
            PopulateSales populateSales = new PopulateSales();
            DealDAO dealDAO = new DealDAO();
            try {
                Log.d("FetchDealsTask", "Before sales retrieval");
                populateSales.getDeals();
                for (Deal d : dealDAO.getCurrentDeals()) {
                    dealNames.add(d.getTitle());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("FetchDealsTask", "Finished doInBackground");
            return dealNames;
        }

        @Override
        protected void onPostExecute(ArrayList retrievedDealNames) {
            Log.d("onPostExecute", "Deal Names Retrieved");
            ListView lv = (ListView) findViewById(R.id.allDealsView);
            ArrayAdapter lvAdapter = (ArrayAdapter) lv.getAdapter();
            lvAdapter.clear();
            lvAdapter.addAll(retrievedDealNames);

        }
    }

}
