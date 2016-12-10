package com.gogoflyapp.gogofly.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gogoflyapp.gogofly.R;

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
        tvTo.setText(flight.getArrival_loc());
        tvDepartureTime.setText(flight.getDeparture_time());
        tvPrice.setText(flight.getPrice());

        // Return the completed view to render on screen
        return convertView;
        }
}
