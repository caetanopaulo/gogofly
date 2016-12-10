package com.gogoflyapp.gogofly.Activities;

import android.content.Intent;
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


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static android.R.attr.country;

public class SetupActivity extends AppCompatActivity {

    String accessToken  = null;
    String expires_in = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        ImageView imageViewGo = (ImageView) findViewById(R.id.imageViewGoGoFly);
        imageViewGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println(createBase64("5dx9xyxnkrpbxx5bj4dmq7rd:26yA3kRsyQ"));
                fireFolley();
                //createFakeFlights();
                // go to new Activity
                Intent intent = new Intent(getApplicationContext(), FlightsOverviewActivity.class);
//                EditText editText = (EditText) findViewById(R.id.edit_message);
//                String message = editText.getText().toString();
//                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);

            }
        });
    }

    private void fireFolley() {
        RequestQueue queue = Volley.newRequestQueue(this);
        // String url ="https://api.klm.com/travel/flightoffers/reference-data";
        // https://www.klm.com/oauthcust/oauth/token
        //String url ="https://www.klm.com/oauthcust/oauth/token?grand_type=client_credentials";
        String url ="https://www.klm.com/oauthcust/oauth/token?grant_type=client_credentials";
        // Authorization
        String auth = "Basic NWR4OXh5eG5rcnBieHg1Ymo0ZG1xN3JkOjI2eUEza1JzeVE=";

        requestWithSomeHttpHeaders(url, auth);

        /*
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println(response); // .substring(0,500)
                        // mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Go Go Fly, but not today!");
                // mTextView.setText("That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        */
    }

    /**
     * Method to create token for app to use to talk to the KLM Server.
     */
    private void createToken() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://www.ite1.klm.com/oauthcust/oauth/token";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println(response.substring(0,500));
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
        final String value = value_start;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);


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

                        Log.d("access token: ", accessToken);
                        Log.d("expires in: ", expires_in);

                        if(accessToken != null){
                            String urlTravelLocations ="https://api.klm.com/travel/locations/cities?expand=lowest-fare&pageSize=100000&country=NL&origins=AMS&minDepartureDate=2016-02-23&maxDepartureDate=2017-01-22&minDuration=P3D&maxDuration=P20D&minBudget=0&maxBudget=5000000";
                            String authTravelLocations = "Bearer "+accessToken;

                            System.out.println("Using Travel Location Bearer: "+authTravelLocations);

                            requestTravelLocationsWithSomeHttpHeaders(urlTravelLocations, authTravelLocations);
                        }else
                            System.out.println("NULL Travel Location Bearer!");


                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("ERROR","error => "+error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s","k.flummox@gmail.com","CEzfw5tXKLM");
                //String auth = "Basic NWR4OXh5eG5rcnBieHg1Ymo0ZG1xN3JkOjI2eUEza1JzeVE="; // + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);

                String auth = "Basic OHVzem1janl1YWI1OHF6ZGU1bXJoNTdlOmdhaFRleWo5R0g=";
                params.put("Authorization", auth);
                return params;
            }
        };
        queue.add(postRequest);

    }

    public void requestTravelLocationsWithSomeHttpHeaders(String url, String value_start) {
        final String value = value_start;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);




                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("ERROR","error => "+error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                //String creds = String.format("%s:%s","k.flummox@gmail.com","CEzfw5tXKLM");
                //String auth = "Basic NWR4OXh5eG5rcnBieHg1Ymo0ZG1xN3JkOjI2eUEza1JzeVE="; // + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);

                String authTravelLocations = "Bearer "+accessToken;
                //String auth = "Basic OHVzem1janl1YWI1OHF6ZGU1bXJoNTdlOmdhaFRleWo5R0g=";
                params.put("Authorization", authTravelLocations);
                return params;
            }
        };
        queue.add(postRequest);

    }

    private void createFakeFlights() {
        ArrayList<Flight> fake_flights = new ArrayList<>();
        String amsterdam = "Schiphol";
        String [] destinations = {"London", "Paris", "Berlin", "Rome", "Barcelona"};
        for (String city : destinations) {
            Flight new_flight = new Flight(city, city, amsterdam, getFakeTime(), city, getFakePrice());
            fake_flights.add(new_flight);
        }

        Suitcase.getInstance().setFlights(fake_flights);
    }

    private String getFakeTime() {
        String time;
        Random rand = new Random();
        int hours = rand.nextInt(24) + 1;
        int minuts = rand.nextInt(55) + 1;

        if (hours < 10) {
            time = "0" + hours;
        } else if (hours > 99){
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
        return String.format(getString(R.string.money_euro) , Integer.toString(new Random().nextInt(125) + 25)) + ",00";
    }


}
