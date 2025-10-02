package com.example.amigos;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorAmigos extends RecyclerView.Adapter<AdaptadorAmigos.ViewHolder> {

    private List<Amigo> itemList;
    RecyclerView rvAmigos;

    public AdaptadorAmigos(List<Amigo> itemList, RecyclerView rvAmigos) {
        this.itemList = itemList;
        this.rvAmigos=rvAmigos;
    }


    @NonNull
    @Override
    public AdaptadorAmigos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.amigos_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorAmigos.ViewHolder holder, int position) {
        Amigo item = itemList.get(position);
        holder.itemNombre.setText(item.getNombre().toString());
        holder.itemTelefono.setText(item.getTelefono().toString());
        holder.itemBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=new AmigosHelper(view.getContext()).getWritableDatabase();
                db.execSQL("DELETE FROM amigos WHERE id="+item.getId());
                db.close();
                itemList.remove(position);
                rvAmigos.getAdapter().notifyDataSetChanged();
            }
        });
        holder.itemEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),AmigosEditar.class);
                intent.putExtra("itemID",item.getId());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemNombre;
        TextView itemTelefono;
        ImageView itemBorrar;
        ImageView itemEditar;

        public ViewHolder(View itemView){
            super(itemView);
            itemNombre = itemView.findViewById(R.id.itemNombre);
            itemTelefono=itemView.findViewById(R.id.itemTelefono);
            itemBorrar=itemView.findViewById(R.id.itemBorrar);
            itemEditar=itemView.findViewById(R.id.itemEditar);
        }
    }
}
