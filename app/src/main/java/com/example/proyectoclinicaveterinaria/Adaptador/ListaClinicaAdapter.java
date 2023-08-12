package com.example.proyectoclinicaveterinaria.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoclinicaveterinaria.EditarclinicaActivity;
import com.example.proyectoclinicaveterinaria.R;
import com.example.proyectoclinicaveterinaria.Entidades.Clinica;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaClinicaAdapter extends RecyclerView.Adapter<ListaClinicaAdapter.ClinicaViewHolder>{


    ArrayList<Clinica> listaclinica;
    ArrayList<Clinica>listaOriginal;

    public ListaClinicaAdapter(ArrayList<Clinica>listaclinica){

        this.listaclinica =listaclinica;
        listaOriginal=new ArrayList<>();
        listaOriginal.addAll(listaclinica);
    }

    @NonNull

    @Override
    public ClinicaViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listaitemclinicas,null,false);
        return new ClinicaViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ClinicaViewHolder holder, int position) {
        holder.viewsedeclinica.setText(listaclinica.get(position).getSede());
        holder.viewdireccionclinica.setText(listaclinica.get(position).getDireccion());
        holder.viewtelefonoclinica.setText(String.valueOf(listaclinica.get(position).getTelefono()));

    }

    public void filtrado(String txtbuscarclinica){
        int longitud= txtbuscarclinica.length();
        if(longitud==0){
            listaclinica.clear();
            listaclinica.addAll(listaOriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Clinica> collecion=listaclinica.stream().filter(i-> i.getSede().toLowerCase().contains(txtbuscarclinica.toLowerCase())).collect(Collectors.toList());
                listaclinica.clear();
                listaclinica.addAll(collecion);
            }else {
                for (Clinica c:listaOriginal){
                    if(c.getSede().toLowerCase().contains(txtbuscarclinica.toLowerCase())){
                        listaclinica.add(c);
                    }
                }
            }

        }
        notifyDataSetChanged();
    }
    
    @Override
    public int getItemCount() {
        return listaclinica.size();
    }

    public class ClinicaViewHolder extends RecyclerView.ViewHolder {

        TextView viewsedeclinica,viewdireccionclinica,viewtelefonoclinica;
        public ClinicaViewHolder(@NonNull View itemView) {
            super(itemView);
            viewsedeclinica=itemView.findViewById(R.id.tvsedeclinica);
            viewdireccionclinica=itemView.findViewById(R.id.tvdireccionclinica);
            viewtelefonoclinica=itemView.findViewById(R.id.tvtelefonoclinica);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                    Intent intent=new Intent(context, EditarclinicaActivity.class);
                    intent.putExtra("ID",listaclinica.get(getAdapterPosition()).getIdclinica());
                    context.startActivity(intent);
                }
            });

        }
    }
}
