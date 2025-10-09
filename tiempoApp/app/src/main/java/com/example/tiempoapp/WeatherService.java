package com.example.tiempoapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherService {
    public interface WeatherCallback {
        void onSuccess(double temperatura);
        void onError(String error);
    }

    // ✅ Recibe un Context como parámetro
    public static void obtenerClima(Context context, double lat, double lon, WeatherCallback callback) {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=" + lat +
                "&longitude=" + lon + "&current_weather=true";

        // ✅ Usa el contexto que te pasan
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONObject current = response.getJSONObject("current_weather");
                        double temp = current.getDouble("temperature");
                        callback.onSuccess(temp);
                    } catch (JSONException e) {
                        callback.onError("Error al parsear JSON: " + e.getMessage());
                    }
                },
                error -> {
                    String errorMsg = "Error de red";
                    if (error.networkResponse != null) {
                        errorMsg += " (" + error.networkResponse.statusCode + ")";
                    }
                    callback.onError(errorMsg);
                }
        );
        queue.add(request);
    }
}