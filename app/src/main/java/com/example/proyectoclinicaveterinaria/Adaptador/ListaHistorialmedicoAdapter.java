package com.example.proyectoclinicaveterinaria.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoclinicaveterinaria.Editarhistorialmedico;
import com.example.proyectoclinicaveterinaria.Entidades.HistorialMedicacionyMascotas;
import com.example.proyectoclinicaveterinaria.R;
import com.example.proyectoclinicaveterinaria.Entidades.Historialmedico;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaHistorialmedicoAdapter extends RecyclerView.Adapter<ListaHistorialmedicoAdapter.HistorialmedicoViewHolder> {
    ArrayList<HistorialMedicacionyMascotas> listahistorialmedico;
    ArrayList<HistorialMedicacionyMascotas>listaOriginal;

    public ListaHistorialmedicoAdapter(ArrayList<HistorialMedicacionyMascotas>listahistorialmedico){

        this.listahistorialmedico = listahistorialmedico;
        listaOriginal=new ArrayList<>();
        listaOriginal.addAll(listahistorialmedico);
    }

    @NonNull
    @Override
    public HistorialmedicoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listaitemhistorial,null,false);
        return new HistorialmedicoViewHolder(view);
    }


    public void filtrado(String txtbuscartratamiento){
        int longitud= txtbuscartratamiento.length();
        if(longitud==0){
            listahistorialmedico.clear();
            listahistorialmedico.addAll(listaOriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<HistorialMedicacionyMascotas> collecion=listahistorialmedico.stream().filter(i-> i.getEnfermedadpadecida().toLowerCase().contains(txtbuscartratamiento.toLowerCase())).collect(Collectors.toList());
                listahistorialmedico.clear();
                listahistorialmedico.addAll(collecion);
            }else {
                for (HistorialMedicacionyMascotas c:listaOriginal){
                    if(c.getEnfermedadpadecida().toLowerCase().contains(txtbuscartratamiento.toLowerCase())){
                        listahistorialmedico.add(c);
                    }
                }
            }

        }
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ListaHistorialmedicoAdapter.HistorialmedicoViewHolder holder, int position) {
        holder.viewenfermedadpadecida.setText(listahistorialmedico.get(position).getEnfermedadpadecida());
        holder.viewtratamiento.setText(listahistorialmedico.get(position).getTratamiento());
        holder.viewfechahistorial.setText(listahistorialmedico.get(position).getFecha());
        holder.viewidmedicacion.setText(String.valueOf(listahistorialmedico.get(position).getNombremedicacion()));
        holder.viewidmascotas.setText(String.valueOf(listahistorialmedico.get(position).getNombremascotas()));
    }

    @Override
    public int getItemCount() {
        return listahistorialmedico.size();
    }

    public class HistorialmedicoViewHolder extends RecyclerView.ViewHolder {
        TextView viewenfermedadpadecida,viewtratamiento,viewfechahistorial,viewidmedicacion,viewidmascotas;
        public HistorialmedicoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewenfermedadpadecida=itemView.findViewById(R.id.tvenfermedadpadecida);
            viewtratamiento=itemView.findViewById(R.id.tvtratamiento);
            viewfechahistorial=itemView.findViewById(R.id.tvfecha);
            viewidmedicacion=itemView.findViewById(R.id.tvidmedicacion);
            viewidmascotas=itemView.findViewById(R.id.tvidmascotas);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                    Intent intent=new Intent(context, Editarhistorialmedico.class);
                    intent.putExtra("ID",listahistorialmedico.get(getAdapterPosition()).getIdhistorial());
                    context.startActivity(intent);
                }
            });

        }
    }
}
