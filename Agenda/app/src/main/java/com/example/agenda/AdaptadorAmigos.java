package com.example.agenda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda.model.Amigo;

import java.util.List;

public class AdaptadorAmigos extends RecyclerView.Adapter<AdaptadorAmigos.ViewHolder> {

    private List<Amigo> amigoList;

    RecyclerView rvAmigos;

    public AdaptadorAmigos(List<Amigo> amigoList, RecyclerView rvAmigos) {
        this.amigoList = amigoList;
        this.rvAmigos = rvAmigos;
    }


    @NonNull
    @Override
    public AdaptadorAmigos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.amigos_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorAmigos.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return amigoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemNombre;
        TextView itemTelefono;
        ImageView itemBorrar;
        ImageView itemEditar;

        public ViewHolder(View itemView){
            super(itemView);

            itemNombre = itemView.findViewById(R.id.itemNombre);
            itemTelefono= itemView.findViewById(R.id.itemTelefono);
            itemBorrar = itemView.findViewById(R.id.itemBorrar);
            itemEditar = itemView.findViewById(R.id.itemEditar);
        }
    }
}

