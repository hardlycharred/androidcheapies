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

    public static final String DEAL = "com.hardlycharred.choicecheapies.DEAL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_deals);

        ListView lv = (ListView) findViewById(R.id.allDealsView);

        //Declaration part
        ArrayAdapter<Deal> arrayAdapter = new ArrayAdapter<>(
                ViewAllDeals.this,
                android.R.layout.simple_list_item_1,
                new ArrayList<Deal>());
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Deal curDeal = (Deal) parent.getItemAtPosition(position);
                Log.d("onItemClick", "We got the clicked deal");
                Intent intent = new Intent(ViewAllDeals.this, ViewIndividualDeal.class);
                intent.putExtra(DEAL, curDeal);
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
            ArrayList<Deal> retrievedDeals = new ArrayList<>();
            Log.d("FetchDealsTask", "Made arraylist");
            PopulateSales populateSales = new PopulateSales();
            DealDAO dealDAO = new DealDAO();
            try {
                Log.d("FetchDealsTask", "Before sales retrieval");
                populateSales.getDeals();
                for (Deal d : dealDAO.getCurrentDeals()) {
                    retrievedDeals.add(d);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("FetchDealsTask", "Finished doInBackground");
            return retrievedDeals;
        }

        @Override
        protected void onPostExecute(ArrayList retrievedDeals) {
            Log.d("onPostExecute", "Deal Names Retrieved");
            ListView lv = (ListView) findViewById(R.id.allDealsView);
            ArrayAdapter lvAdapter = (ArrayAdapter) lv.getAdapter();
            lvAdapter.clear();
            lvAdapter.addAll(retrievedDeals);

        }
    }

}
