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
     */
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

    private void parseData(JSONObject dataObj) {
        ArrayList<Flight> fake_flights = new ArrayList<>();
        String amsterdam = "Schiphol";

        JSONArray _embeddedArrJSON = null;
        try {
            _embeddedArrJSON = dataObj.getJSONArray("_embedded");

            for (int i=0;i< _embeddedArrJSON.length(); i++) {
                JSONObject json = _embeddedArrJSON.getJSONObject(i);

                //System.out.println(json.getString("Pref1"));
                // System.out.println(json);

                // ,,,"children":[{"code":"AAL","name":"Aalborg","description":"Aalborg - Aalborg (AAL), Denmark","coordinates":{"latitude":57.09306,"longitude":9.85}}],"fare":{"origin":{"code":"AMS","name":"Schiphol","description":"Amsterdam - Schiphol (AMS), Netherlands","coordinates":{"latitude":52.30833,"longitude":4.76806}},"departureDate":"2017-01-04","returnDate":"2017-01-07","amount":{"currency":"EUR","price":178.59},"possibleTravelTime":4500000},"parent":{"code":"DK","name":"Denmark","description":"Denmark (DK)","coordinates":{"latitude":56,"longitude":10},"parent":{"code":"EUR","name":"Europe","description":"Europe (EUR)","coordinates":{"latitude":51.179,"longitude":11.25}}},"popularity":5,"themes":[{"icon":"&#xe617;","name":"Arts & Culture","category":"ART"},{"icon":"&#xe614;","name":"Food & Drinks","category":"CUL"},{"icon":"&#xe60a;","name":"Romance","category":"ROM"}]
                String code = json.getString("code"); // "code":"AAL",
                String name = json.getString("name"); // "name":"Aalborg"
                // "description":"Aalborg (AAL)"
                // "coordinates":{"latitude":57.08944,"longitude":9.84889}

                // parent => parent => code = EUR

                JSONObject fareJson = json.getJSONObject("fare");
                //fareJson.get("");

                //Flight new_flight = new Flight(city, city, amsterdam, getFakeTime(), city, getFakePrice());
                //fake_flights.add(new_flight);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*
        for (String city : destinations) {

        }
        */

        Suitcase.getInstance().setFlights(fake_flights);
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
