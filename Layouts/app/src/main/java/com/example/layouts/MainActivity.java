package com.example.layouts;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String apikey = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYXZpZXIub3Rlcm8ubG91emFvQGNvbGV4aW8ta2FyYm8uY29tIiwianRpIjoiZmFkZTMyMDktMWEwMS00YTdlLTk0ZGEtNmZmMzNlZjJkODJiIiwiaXNzIjoiQUVNRVQiLCJpYXQiOjE3NTg1NDYzODMsInVzZXJJZCI6ImZhZGUzMjA5LTFhMDEtNGE3ZS05NGRhLTZmZjMzZWYyZDgyYiIsInJvbGUiOiIifQ.07_l1ZVdPOrDFg42w7JPXF2BMCcWn2tHgVHcZZQlBj0 ";
    RequestQueue cola;
    TextView txtTemperatura;
    TextView txtEstadoCielo;
    TextView txtMaxima;
    TextView txtMinima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        txtTemperatura = findViewById(R.id.txtTemperatura);
        txtEstadoCielo = findViewById(R.id.txtEstadoCielo);
        txtMaxima = findViewById(R.id.txtMaxima);
        txtMinima = findViewById(R.id.txtMinima);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        cola = Volley.newRequestQueue(this);
        String url = "https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/diaria/15030?api_key="+apikey;


        JsonObjectRequest peticion = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("Moncho", jsonObject.toString());
                        try {
                            if (jsonObject.getString("descripcion").equals("exito")){
                                Log.d("Moncho",jsonObject.getString("datos"));
                                pedirDatos(jsonObject.getString("datos"));
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }
        );

        cola.add(peticion);
    }

    private void pedirDatos(String url){
        Log.d("Moncho2","URL:---- "+url);

        JsonArrayRequest peticion = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        Log.d("Moncho2",""+jsonArray.length());
                        try {
                            //-------------------------------------EstadoCielo------------------------------------------------

                            String estadoCielo = jsonArray.getJSONObject(0).getJSONObject("prediccion")
                                    .getJSONArray("dia").getJSONObject(0).getJSONArray("estadoCielo")
                                    .getJSONObject(2).getString("descripcion");
                            Log.d("Moncho3","EstadoCielo-- "+estadoCielo);
                            txtEstadoCielo.setText(estadoCielo);

                            //----------------------------------Máxima----------------------------------------------------
                            String Maxima = jsonArray.getJSONObject(0).getJSONObject("prediccion")
                                    .getJSONArray("dia").getJSONObject(0).getJSONObject("temperatura")
                                    .getString("maxima");
                            Log.d("Moncho4","Maxima-- "+Maxima);
                            txtMaxima.setText("Max.: "+Maxima+"º");


                            //----------------------------------Mínima----------------------------------------------------
                            String Minima = jsonArray.getJSONObject(0).getJSONObject("prediccion")
                                    .getJSONArray("dia").getJSONObject(0).getJSONObject("temperatura")
                                    .getString("minima");
                            Log.d("Moncho5","Minima-- "+Minima);
                            txtMinima.setText("Min.: "+Minima+"º");


                            //----------------------------------Temperatura-----------------------------------------------
                            int temperatura = (Integer.parseInt(Maxima) + Integer.parseInt(Minima))/2;
                            txtTemperatura.setText(temperatura+"º");



                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }
        );
        cola.add(peticion);
    }
}