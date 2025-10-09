package com.example.tiempoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CiudadAdapter extends RecyclerView.Adapter<CiudadAdapter.ViewHolder> {
    private List<Ubicacion> ciudades;
    private OnCiudadClickListener listener;

    public interface OnCiudadClickListener {
        void onCiudadClick(Ubicacion ciudad);
    }

    public CiudadAdapter(List<Ubicacion> ciudades, OnCiudadClickListener listener) {
        this.ciudades = ciudades;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ciudad, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ubicacion ciudad = ciudades.get(position);
        holder.tvNombre.setText(ciudad.getNombre());
        holder.itemView.setOnClickListener(v -> listener.onCiudadClick(ciudad));
    }

    @Override
    public int getItemCount() {
        return ciudades.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        ViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreItem);
        }
    }
}
