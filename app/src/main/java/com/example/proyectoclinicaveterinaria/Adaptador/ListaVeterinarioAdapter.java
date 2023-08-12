package com.example.proyectoclinicaveterinaria.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoclinicaveterinaria.EditarveterinarioActivity;
import com.example.proyectoclinicaveterinaria.Entidades.VeterinarioyEspecialidad;
import com.example.proyectoclinicaveterinaria.R;
import com.example.proyectoclinicaveterinaria.Entidades.Veterinarios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaVeterinarioAdapter extends RecyclerView.Adapter<ListaVeterinarioAdapter.VeterinarioViewHolder> {

    ArrayList<VeterinarioyEspecialidad> listaveterinarios;
    ArrayList<VeterinarioyEspecialidad> listaOriginal;

    public ListaVeterinarioAdapter(ArrayList<VeterinarioyEspecialidad> listaveterinarios) {

        this.listaveterinarios = listaveterinarios;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaveterinarios);
    }

    @NonNull
    @Override
    public VeterinarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listaitemveterinarios, null, false);
        return new VeterinarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaVeterinarioAdapter.VeterinarioViewHolder holder, int position) {
        holder.viewnombreveterinario.setText(listaveterinarios.get(position).getNombre());
        holder.viewapellidoveterinario.setText(listaveterinarios.get(position).getApellidos());
        holder.viewdniveterinario.setText(String.valueOf(listaveterinarios.get(position).getDni()));
        holder.viewcelularveterinario.setText(String.valueOf(listaveterinarios.get(position).getCelular()));
        holder.viewdireccionveterinario.setText(listaveterinarios.get(position).getDireccion());
        holder.viewgeneroveterinario.setText(listaveterinarios.get(position).getGenero());
        holder.viewcorreoveterinario.setText(listaveterinarios.get(position).getCorreo());
        holder.viewidespecialidades.setText(String.valueOf(listaveterinarios.get(position).getNombreespecialidad()));
    }


    public void filtrado(String txtbuscarveterinario) {
        int longitud = txtbuscarveterinario.length();
        if (longitud == 0) {
            listaveterinarios.clear();
            listaveterinarios.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<VeterinarioyEspecialidad> collecion = listaveterinarios.stream().filter(i -> i.getNombre().toLowerCase().contains(txtbuscarveterinario.toLowerCase())).collect(Collectors.toList());
                listaveterinarios.clear();
                listaveterinarios.addAll(collecion);
            } else {
                for (VeterinarioyEspecialidad c : listaOriginal) {
                    if (c.getNombre().toLowerCase().contains(txtbuscarveterinario.toLowerCase())) {
                        listaveterinarios.add(c);
                    }
                }
            }

        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return listaveterinarios.size();
    }

    public class VeterinarioViewHolder extends RecyclerView.ViewHolder {
        TextView viewnombreveterinario, viewapellidoveterinario, viewdniveterinario, viewcelularveterinario, viewdireccionveterinario, viewgeneroveterinario, viewcorreoveterinario,viewidespecialidades;

        public VeterinarioViewHolder(@NonNull View itemView) {
            super(itemView);
            viewnombreveterinario = itemView.findViewById(R.id.tvnombreveterinario);
            viewapellidoveterinario = itemView.findViewById(R.id.tvapellidoveterinario);
            viewdniveterinario = itemView.findViewById(R.id.tvdniveterinario);
            viewcelularveterinario = itemView.findViewById(R.id.tvcelularveterinario);
            viewdireccionveterinario = itemView.findViewById(R.id.tvdireccionveterinario);
            viewgeneroveterinario = itemView.findViewById(R.id.tvgeneroveterinario);
            viewcorreoveterinario = itemView.findViewById(R.id.tvcorreoveterinario);
            viewidespecialidades = itemView.findViewById(R.id.tvidespecialidades);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, EditarveterinarioActivity.class);
                    intent.putExtra("ID", listaveterinarios.get(getAdapterPosition()).getIdveterinario());
                    context.startActivity(intent);
                }
            });
        }
    }
}
