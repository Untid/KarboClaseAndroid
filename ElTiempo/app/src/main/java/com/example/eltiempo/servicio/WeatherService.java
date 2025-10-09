package com.example.eltiempo.servicio;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.eltiempo.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherService {
    public interface WeatherCallback {
        void onSuccess(double temperatura);
        void onError(String error);
    }

    public static void obtenerClima(double lat, double lon, WeatherCallback callback) {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=" + lat +
                "&longitude=" + lon + "&current_weather=true";

        RequestQueue queue = Volley.newRequestQueue(MyApplication.getAppContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONObject current = response.getJSONObject("current_weather");
                        double temp = current.getDouble("temperature");
                        callback.onSuccess(temp);
                    } catch (JSONException e) {
                        callback.onError("Error al parsear JSON");
                    }
                },
                error -> callback.onError("Error de red: " + error.getMessage())
        );
        queue.add(request);
    }
}
