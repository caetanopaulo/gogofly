package com.gogoflyapp.gogofly.tools;

import java.util.Comparator;

/**
 * Created by Kees on 12/10/2016.
 */

public class FlightPriceComparator implements Comparator<Flight> {
    @Override
    public int compare(Flight o1, Flight o2) {
        // x.intValue() == y.intValue())
        return (int) (Double.parseDouble(o1.getPrice()) - Double.parseDouble(o2.getPrice()));
    }
}