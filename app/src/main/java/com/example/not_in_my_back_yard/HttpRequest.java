package com.example.not_in_my_back_yard;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpRequest {

    final static String ngrok = "https://b8ed-62-219-65-138.ngrok-free.app"; //

    public interface CallBack_JsonArrayRequest{
        void dataReady(ArrayList<Stop> value);
    }

    public static void FindBusStops(RequestQueue requestQueue, CallBack_JsonArrayRequest callBack_jsonArrayRequest, double Lat, double Lng, double Radius){
        String url = ngrok + "/api/Stops/" + Lat + "/" + Lng + "/" + Radius;
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Stop> value = new ArrayList<>();
        JsonArrayRequest
                jsonArrayRequest
                = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                (Response.Listener<JSONArray>) response -> {
                    for (int i = 0; i < response.length(); i++) {
                        // creating a new json object and
                        // getting each object from our json array.
                        try {
                            // we are getting each json object.
                            JSONObject responseObj = response.getJSONObject(i);
                            // now we get our response from API in json object format.
                            // in below line we are extracting a string with
                            // its key value from our json object.
                            // similarly we are extracting all the strings from our json object.
                            int StopId = responseObj.getInt("stopId");
                            int StopCode = responseObj.getInt("stopCode");
                            String StopName = responseObj.getString("stopName");
                            String StopDesc = responseObj.getString("stopDesc");
                            double StopLat = responseObj.getDouble("stopLat");
                            double StopLon = responseObj.getDouble("stopLon");


                            value.add(new Stop(StopId,StopCode, StopName, StopDesc, StopLat, StopLon));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    if(callBack_jsonArrayRequest != null)
                        callBack_jsonArrayRequest.dataReady(value);
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {

                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    public static void PostReport(RequestQueue requestQueue, PostReport postReport){
        String url = ngrok + "/api/Reports";
        // Make new json object and put params in it
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("reportSource", postReport.getReportSource());
            jsonParams.put("openDate", postReport.getDate());
            jsonParams.put("status", postReport.getStatus());
            jsonParams.put("stopId", postReport.getStopId());
            jsonParams.put("hazardType", postReport.getHazardType());
            jsonParams.put("description", postReport.getDescription());
            jsonParams.put("picPath", postReport.getPicPath());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Building a request
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                // Using a variable for the domain is great for testing
                url,
                jsonParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        // Handle the response

                    }
                },

                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        // Handle the error

                    }
                });
        requestQueue.add(request);
    }
}
