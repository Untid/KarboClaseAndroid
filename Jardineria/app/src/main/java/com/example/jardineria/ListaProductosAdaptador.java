package com.example.jardineria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ListaProductosAdaptador extends BaseExpandableListAdapter {

    private Context contexto;
    private List<String> productosCategoria;
    private HashMap<String, List<Producto>> productosLista;


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return productosLista.get(productosCategoria.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return productosLista.get(productosCategoria.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return productosCategoria.get(groupPosition);

    }

    @Override
    public int getGroupCount() {

        return productosCategoria.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String nomreCategoria = getGroup(groupPosition).toString();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.categoria_item, null);
        }
        TextView categoriaItem = convertView.findViewById(R.id.categoriaItem);
        categoriaItem.setText(nomreCategoria);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
