package com.gogoflyapp.gogofly.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gogoflyapp.gogofly.R;
import com.gogoflyapp.gogofly.tools.Flight;
import com.gogoflyapp.gogofly.tools.Suitcase;
import com.gogoflyapp.gogofly.tools.Theme;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SetupActivity extends AppCompatActivity {

    String accessToken = null;
    String expires_in = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        // get data from server
        phoneHome();

        ImageView imageViewGo = (ImageView) findViewById(R.id.imageViewGoGoFly);
        imageViewGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println(createBase64("5dx9xyxnkrpbxx5bj4dmq7rd:26yA3kRsyQ"));
                phoneHome();
                //createFakeFlights();
                // go to new Activity
                Intent intent = new Intent(getApplicationContext(), FlightsOverviewActivity.class);
//                EditText editText = (EditText) findViewById(R.id.edit_message);
//                String message = editText.getText().toString();
//                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void phoneHome() {
        String url = "https://www.klm.com/oauthcust/oauth/token?grant_type=client_credentials";
        String auth = "Basic NWR4OXh5eG5rcnBieHg1Ymo0ZG1xN3JkOjI2eUEza1JzeVE=";

        requestWithSomeHttpHeaders(url, auth);
    }

    /**
     * Method to create token for app to use to talk to the KLM Server.
     */
    private void createToken() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.ite1.klm.com/oauthcust/oauth/token";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println(response.substring(0, 500));
                        // mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Go Go Fly!");
                // mTextView.setText("That didn't work!");
            }
        });
    }

    private String createBase64(String key_secrit) {
        try {
            byte[] data = key_secrit.getBytes("UTF-8");
            return Base64.encodeToString(data, Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return key_secrit;
    }

    public void requestWithSomeHttpHeaders(String url, String value_start) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response succes!", response);

                        JSONObject reader = null;
                        try {
                            reader = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            accessToken = reader.getString("access_token");
                            expires_in = reader.getString("expires_in");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        /*
                        Log.d("access token: ", accessToken);
                        Log.d("expires in: ", expires_in);
                        */

                        if (accessToken != null) {
                            String urlTravelLocations = "https://api.klm.com/travel/locations/cities?expand=lowest-fare&pageSize=100000&country=NL&origins=AMS&minDepartureDate=2016-02-23&maxDepartureDate=2017-01-22&minDuration=P3D&maxDuration=P20D&minBudget=0&maxBudget=5000000";
                            String authTravelLocations = "Bearer " + accessToken;

                            // System.out.println("Using Travel Location Bearer: " + authTravelLocations);

                            requestTravelLocationsWithSomeHttpHeaders(urlTravelLocations, authTravelLocations);
                        } else
                            System.out.println("NULL Travel Location Bearer!");

                        }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("ERROR", "error => " + error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                // String creds = String.format("%s:%s", "k.flummox@gmail.com", "CEzfw5tXKLM");
                //String auth = "Basic NWR4OXh5eG5rcnBieHg1Ymo0ZG1xN3JkOjI2eUEza1JzeVE="; // + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);

                String auth = "Basic OHVzem1janl1YWI1OHF6ZGU1bXJoNTdlOmdhaFRleWo5R0g=";
                params.put("Authorization", auth);
                return params;
            }
        };
        queue.add(postRequest);
    }

    /**
     * The real magic, calling real data.
     * @param url
     * @param value_start
     */
    public void requestTravelLocationsWithSomeHttpHeaders(String url, String value_start) {
        final String value = value_start;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);

                        JSONObject reader = null;
                        try {
                            reader = new JSONObject(response);
                            parseData(reader);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("ERROR", "error => " + error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String authTravelLocations = "Bearer " + accessToken;
                params.put("Authorization", authTravelLocations);
                return params;
            }
        };
        queue.add(postRequest);
    }

    /**
     * Faking it.
     *
    private void createFakeFlights() {
        ArrayList<Flight> fake_flights = new ArrayList<>();
        String amsterdam = "Schiphol";
        String[] destinations = {"London", "Paris", "Berlin", "Rome", "Barcelona"};
        for (String city : destinations) {
            Flight new_flight = new Flight(city, city, amsterdam, getFakeTime(), city, getFakePrice());
            fake_flights.add(new_flight);
        }

        Suitcase.getInstance().setFlights(fake_flights);
    }
     */

    private void parseData(JSONObject dataObj) {
        ArrayList<Flight> new_flights = new ArrayList<>();
        String amsterdam = "Schiphol";

        JSONArray _embeddedArrJSON = null;
        try {
            _embeddedArrJSON = dataObj.getJSONArray("_embedded");

            for (int i=0;i< _embeddedArrJSON.length(); i++) {
                JSONObject json = _embeddedArrJSON.getJSONObject(i);

                Flight new_flight = new Flight(json.getString("name"));
                new_flight.setDestination_code(json.getString("code"));

                JSONObject json_fare = json.getJSONObject("fare");

                JSONObject json_fare_origin = json_fare.getJSONObject("origin");
                new_flight.setOrigin_name(json_fare_origin.getString("name"));
                new_flight.setOrigin_code(json_fare_origin.getString("code"));

                new_flight.setDeparture_date(json_fare.getString("departureDate"));
                new_flight.setReturn_date(json_fare.getString("returnDate"));

                // Cash
                JSONObject json_amount = json_fare.getJSONObject("amount");
                new_flight.setCurrency(json_amount.getString("currency"));
                new_flight.setPrice(fixPrice(Double.parseDouble(json_amount.getString("price")))+"");

                // Travel time
                String json_amount_possibleTravelTime = null;
                try {
                    json_amount_possibleTravelTime = json_fare.getString("possibleTravelTime");
                    // System.out.println(json_fare.getString("possibleTravelTime"));
                    // json_amount_possibleTravelTime = json_fare.getString("possibleTravelTime");
                } catch (JSONException jse) {
                    // jse.printStackTrace();
                }

                if (json_amount_possibleTravelTime != null) {
                    new_flight.setFlight_time(json_amount_possibleTravelTime);
                } else {
                    new_flight.setFlight_time(null);
                }

                // Popularity
                String popularity = null;
                try {
                    popularity = json.getString("popularity");
                } catch (JSONException jse) {
                    // jse.printStackTrace();
                }

                if (popularity != null) {
                    new_flight.setPopularity(popularity);
                } else {
                    new_flight.setPopularity(null);
                }

                new_flight.setDeparture_time(getFakeTime());

                // System.out.println(json.getString("popularity"));
                new_flights.add(new_flight);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Suitcase.getInstance().setFlights(new_flights);
    }

    private long fixPrice(double price) {
        return Math.round(price / 2);
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

    private String getFakePrice() {
        return String.format(getString(R.string.money_euro), Integer.toString(new Random().nextInt(125) + 25)) + ",00";
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Setup Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

}
