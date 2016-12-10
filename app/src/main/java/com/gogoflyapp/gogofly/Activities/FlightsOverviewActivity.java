package com.gogoflyapp.gogofly.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gogoflyapp.gogofly.R;
import com.gogoflyapp.gogofly.tools.Flight;
import com.gogoflyapp.gogofly.tools.FlightsAdapter;
import com.gogoflyapp.gogofly.tools.Suitcase;

import java.util.ArrayList;

import static android.R.attr.value;

/**
 * Activity to show flights to user.
 */
public class FlightsOverviewActivity extends AppCompatActivity {

    private ArrayList<Flight> flights;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_overview);

        // Make sure we have data to work with.
        flights = Suitcase.getInstance().getFlights();
        showFlights();
    }

    /**
     * Simple method to show the flights to the user.
     */
    private void showFlights() {
        // Create the adapter to convert the array to views
        FlightsAdapter adapter = new FlightsAdapter(this, flights);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listView_flights);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                Intent i = new Intent(getApplicationContext(), FlightViewActivity.class);
                //If you wanna send any data to nextActicity.class you can use
                String key = "flight_position_in_Array";
                //i.putExtra(key, value.get(position));

                startActivity(i);
            }
        });
    }

}
