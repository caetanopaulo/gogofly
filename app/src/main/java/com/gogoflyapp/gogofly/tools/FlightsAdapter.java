package com.gogoflyapp.gogofly.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gogoflyapp.gogofly.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Kees Marijs on 12/10/2016.
 *
 * ArrayAdapter adapted to show a list of flights.
 */
public class FlightsAdapter extends ArrayAdapter<Flight> {
    Context context;
    public FlightsAdapter(Context context, ArrayList<Flight> flights) {
        super(context, 0, flights);
        this.context = context;
    }


    /**
     * Here we get the view, and fill it with the data.
     * @param position                  Number in the list.
     * @param convertView               ???
     * @param parent                    Where's my daddy?
     * @return                          All filled out!
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        Flight flight = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_flight, parent, false);
        }

        // ListItem Background color.
        if (position % 2 == 0) {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.colorAlmostGrey));
        } else {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.colorAlmostWhite));
        }

        // Lookup view for data population
        TextView tvTo = (TextView) convertView.findViewById(R.id.listItem_to);
        TextView tvDepartureTime = (TextView) convertView.findViewById(R.id.listItem_departure);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.listItem_price);

        // Populate the data into the template view using the data object
        tvTo.setText(flight.getDestination_name());
        tvDepartureTime.setText(flight.getDeparture_time());

        String currency = flight.getCurrency();
        tvPrice.setText(String.format(context.getResources().getString(R.string.flight_price), flight.getPrice()));

        if (flight.getDestination_weather() != null) {
            convertView.findViewById(R.id.ll_weather).setBackgroundColor(context.getResources().getColor(R.color.colorAccent));

            // Temperature
            TextView tvWeather = (TextView) convertView.findViewById(R.id.textView_weather);
            DecimalFormat df = new DecimalFormat("#.0");
            Double temp = flight.getDestination_weather().getTemperature();
            String temp_string = "";
            if (temp.intValue() == 0) {
                if (temp > 0d) {
                    temp_string = "0" + String.format(context.getResources().getString(R.string.weather_temp_c), df.format(temp));
                } else {
                    temp_string = "-0" + String.format(context.getResources().getString(R.string.weather_temp_c), df.format(temp)).substring(1, 3);
                }
            } else {
                temp_string = String.format(context.getResources().getString(R.string.weather_temp_c), df.format(temp));
            }
            tvWeather.setText(temp_string);
            tvWeather.setTextColor(context.getResources().getColor(R.color.colorAlmostWhite));
            tvWeather.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));

            // Icon
            ImageView ivWeather = (ImageView) convertView.findViewById(R.id.imageView_weather);
            ivWeather.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            switch (flight.getDestination_weather().getSky_state()) {
                case OVERCAST:
                    ivWeather.setImageResource(R.drawable.ic_weather_overcast);
                    break;

                case SNOW:
                    ivWeather.setImageResource(R.drawable.ic_weather_snow);
                    break;

                case MIST:
                    ivWeather.setImageResource(R.drawable.ic_weather_mist);
                    break;

                case RAIN:
                    ivWeather.setImageResource(R.drawable.ic_weather_rain);
                    break;

                case SUN:
                    ivWeather.setImageResource(R.drawable.ic_weather_sun);
                default:
            }
        }

        // Return the completed view to render on screen
        return convertView;
        }
}
