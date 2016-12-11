package com.gogoflyapp.gogofly.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gogoflyapp.gogofly.R;
import com.gogoflyapp.gogofly.tools.Flight;
import com.gogoflyapp.gogofly.tools.Suitcase;

import org.w3c.dom.Text;

import java.util.Random;

public class FlightViewActivity extends GoGoFlyActivity {

    private Flight current_flight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_view);

        // Header Stuff
        settleIn();

        // Get the current Flight
        int num = Integer.valueOf(getIntent().getExtras().getString("flight_position_in_Array"));
        if (Suitcase.getInstance().getSelectedFlights().size() == 0) {
            current_flight = Suitcase.getInstance().getTotalFlights().get(num);
        } else {
            current_flight = Suitcase.getInstance().getSelectedFlights().get(num);
        }

        // System.out.println(current_flight.getDestination_name());
        //addFAB();

        setFromData();
        setToData();

        ImageView imageViewGo = (ImageView) findViewById(R.id.imageViewGoGoFly);
        imageViewGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to new Activity
                Intent intent = new Intent(getApplicationContext(), BuyFlightActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setFromData() {
        // From Header
        ((TextView) findViewById(R.id.textView_from_line)).setText(current_flight.getOrigin_code() + " - " + current_flight.getDestination_code());
        ((TextView) findViewById(R.id.textView_from_header_price)).setText("Free");

        // From // To
        ((RelativeLayout) findViewById(R.id.rel_lay_from_from_to)).setBackgroundColor(getResources().getColor(R.color.colorAlmostGrey));
        ((TextView) findViewById(R.id.textView_from_f_country)).setText(current_flight.getOrigin_name());
        ((TextView) findViewById(R.id.textView_from_t_country)).setText(current_flight.getDestination_name());

        // Departure stuff
        ((TextView) findViewById(R.id.textView_from_departure)).setText(getString(R.string.fo_departure));
        ((TextView) findViewById(R.id.textView_from_date)).setText(current_flight.getDeparture_date().substring(5));
        ((TextView) findViewById(R.id.textView_from_time)).setText(current_flight.getDeparture_time());

        // Flight Duration // rel_lay_from_duration
        ((RelativeLayout) findViewById(R.id.rel_lay_from_duration)).setBackgroundColor(getResources().getColor(R.color.colorAlmostGrey));
        ((TextView) findViewById(R.id.textView_from_duration)).setText(getString(R.string.fo_duration));
        String tymes = current_flight.getFlight_time();
        if (tymes != null) {
            Double times = Double.parseDouble(tymes);
            String hours = Integer.toString((int) (times / (60 * 60 * 1000)));
            if (hours.length() < 2)
                hours = "0" + hours;
            String minutes = Integer.toString( (int) (times / (60 * 1000) % 60));
            if (minutes.length() < 2)
                minutes = "0" + minutes;

            ((TextView) findViewById(R.id.textView_from_duration_time)).setText(hours + "." + minutes + " HR");
        }
    }

    private void setToData() {
        // From Header
        ((TextView) findViewById(R.id.textView_to_line)).setText(current_flight.getDestination_code() + " - " + current_flight.getOrigin_code());
        ((TextView) findViewById(R.id.textView_to_header_price)).setText(String.format(getResources().getString(R.string.flight_price), current_flight.getPrice()));

        // From // To
        ((RelativeLayout) findViewById(R.id.rel_lay_from_fo_from)).setBackgroundColor(getResources().getColor(R.color.colorAlmostGrey));
        ((TextView) findViewById(R.id.textView_to_f_country)).setText(current_flight.getDestination_name());
        ((TextView) findViewById(R.id.textView_to_t_country)).setText(current_flight.getOrigin_name());

        // Departure stuff
        ((TextView) findViewById(R.id.textView_to_departure)).setText(getString(R.string.fo_departure));
        ((TextView) findViewById(R.id.textView_to_date)).setText(current_flight.getReturn_date().substring(5));
        ((TextView) findViewById(R.id.textView_to_time)).setText(getFakeTime());

        // Flight Duration // rel_lay_from_duration
        ((RelativeLayout) findViewById(R.id.rel_lay_to_duration)).setBackgroundColor(getResources().getColor(R.color.colorAlmostGrey));
        ((TextView) findViewById(R.id.textView_to_duration)).setText(getString(R.string.fo_duration));
        String tymes = current_flight.getFlight_time();
        if (tymes != null) {
            Double times = Double.parseDouble(tymes);
            String hours = Integer.toString((int) (times / (60 * 60 * 1000)));
            if (hours.length() < 2)
                hours = "0" + hours;
            String minutes = Integer.toString( (int) (times / (60 * 1000) % 60));
            if (minutes.length() < 2)
                minutes = "0" + minutes;

            ((TextView) findViewById(R.id.textView_to_duration_time)).setText(hours + "." + minutes + " HR");
        }
    }

    private String getFakeTime() {
        String time;
        Random rand = new Random();
        int hours = rand.nextInt(24) + 1;
        int minuts = rand.nextInt(55) + 1;

        if (hours < 10) {
            time = "0" + hours;
        } else if (hours > 99) {
            time = "" + hours;
        } else {
            time = " " + hours;
        }

        time += ":";

        if (minuts < 10) {
            time += "0" + minuts;
        } else {
            time += minuts;
        }

        return time;
    }
}

