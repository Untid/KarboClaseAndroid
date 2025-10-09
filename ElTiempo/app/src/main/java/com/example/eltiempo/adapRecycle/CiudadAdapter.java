package com.example.eltiempo.adapRecycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eltiempo.R;
import com.example.eltiempo.model.CiudadModel;

import java.util.List;

public class CiudadAdapter extends RecyclerView.Adapter<CiudadAdapter.ViewHolder> {
    private List<CiudadModel> ciudades;
    private OnCiudadClickListener listener;

    public interface OnCiudadClickListener {
        void onCiudadClick(CiudadModel ciudadModel);
    }

    public CiudadAdapter(List<CiudadModel> ciudades, OnCiudadClickListener listener) {
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
        CiudadModel ciudadModel = ciudades.get(position);
        holder.tvNombre.setText(ciudadModel.getNombre());
        holder.itemView.setOnClickListener(v -> listener.onCiudadClick(ciudadModel));
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
