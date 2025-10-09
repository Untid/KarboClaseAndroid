package com.example.eltiempo.model;

public class CiudadModel {
    private String nombre;
    private double latitud;
    private double longitud;

    public CiudadModel(String nombre, double latitud, double longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }
}
