package com.example.proyectoclinicaveterinaria.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoclinicaveterinaria.EditarespecialidadActivity;
import com.example.proyectoclinicaveterinaria.R;
import com.example.proyectoclinicaveterinaria.Entidades.Especialidades;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaEspecialidadAdapter extends RecyclerView.Adapter<ListaEspecialidadAdapter.EspecialidadViewHolder> {
    ArrayList<Especialidades> listaespecialidades;
    ArrayList<Especialidades>listaOriginal;

    public ListaEspecialidadAdapter(ArrayList<Especialidades>listaespecialidades){

        this.listaespecialidades = listaespecialidades;
        listaOriginal=new ArrayList<>();
        listaOriginal.addAll(listaespecialidades);
    }

    @NonNull
    @Override
    public EspecialidadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listaitemespecialidad,null,false);
        return new EspecialidadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  EspecialidadViewHolder holder, int position) {
        holder.viewnombredeespecialidad.setText(listaespecialidades.get(position).getNombredeespecialidad());
        holder.viewdescripcionespecialidad.setText(listaespecialidades.get(position).getDescripcion());
    }

    public void filtrado(String txtbuscarespecialidad){
        int longitud= txtbuscarespecialidad.length();
        if(longitud==0){
            listaespecialidades.clear();
            listaespecialidades.addAll(listaOriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Especialidades> collecion=listaespecialidades.stream().filter(i-> i.getNombredeespecialidad().toLowerCase().contains(txtbuscarespecialidad.toLowerCase())).collect(Collectors.toList());
                listaespecialidades.clear();
                listaespecialidades.addAll(collecion);
            }else {
                for (Especialidades c:listaOriginal){
                    if(c.getNombredeespecialidad().toLowerCase().contains(txtbuscarespecialidad.toLowerCase())){
                        listaespecialidades.add(c);
                    }
                }
            }

        }
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return listaespecialidades.size();
    }

    public class EspecialidadViewHolder extends RecyclerView.ViewHolder {
        TextView viewnombredeespecialidad,viewdescripcionespecialidad;
        public EspecialidadViewHolder(@NonNull View itemView) {
            super(itemView);
            viewnombredeespecialidad=itemView.findViewById(R.id.tvnombredeespecialidad);
            viewdescripcionespecialidad=itemView.findViewById(R.id.tvdescripcionespecialidad);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                    Intent intent=new Intent(context, EditarespecialidadActivity.class);
                    intent.putExtra("ID",listaespecialidades.get(getAdapterPosition()).getIdespecialidades());
                    context.startActivity(intent);
                }
            });

        }
    }
}
