package com.example.proyectoclinicaveterinaria.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoclinicaveterinaria.EditarmedicacionActivity;
import com.example.proyectoclinicaveterinaria.R;
import com.example.proyectoclinicaveterinaria.Entidades.Medicacion;
import com.google.android.gms.common.server.converter.StringToIntConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaMedicacionAdapter extends RecyclerView.Adapter<ListaMedicacionAdapter.MedicacionViewHolder> {

    ArrayList<Medicacion> listamedicacion;
    ArrayList<Medicacion>listaOriginal;



    public ListaMedicacionAdapter(ArrayList<Medicacion>listamedicacion){

        this.listamedicacion = listamedicacion;
        listaOriginal=new ArrayList<>();
        listaOriginal.addAll(listamedicacion);
    }

    @NonNull
    @Override
    public MedicacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listaitemmedicacion,null,false);
        return new MedicacionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicacionViewHolder holder, int position) {
        holder.viewnombredemedicacion.setText(listamedicacion.get(position).getNombre());
        holder.viewpreciomedicacion.setText(String.valueOf(listamedicacion.get(position).getPrecio()));
    }


    public void filtrado(String txtbuscarmedicacion){
        int longitud= txtbuscarmedicacion.length();
        if(longitud==0){
            listamedicacion.clear();
            listamedicacion.addAll(listaOriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Medicacion> collecion=listamedicacion.stream().filter(i-> i.getNombre().toLowerCase().contains(txtbuscarmedicacion.toLowerCase())).collect(Collectors.toList());
                listamedicacion.clear();
                listamedicacion.addAll(collecion);
            }else {
                for (Medicacion c:listaOriginal){
                    if(c.getNombre().toLowerCase().contains(txtbuscarmedicacion.toLowerCase())){
                        listamedicacion.add(c);
                    }
                }
            }

        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listamedicacion.size();
    }

    public class MedicacionViewHolder extends RecyclerView.ViewHolder {
        TextView viewnombredemedicacion,viewpreciomedicacion,viewfotomedicacion;
        ImageView imgMedicamento;
        public MedicacionViewHolder(@NonNull View itemView) {
            super(itemView);
            viewnombredemedicacion=itemView.findViewById(R.id.tvnombredemedicacion);
            viewpreciomedicacion=itemView.findViewById(R.id.tvpreciodemedicacion);
            imgMedicamento= itemView.findViewById(R.id.imgMedicamento);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                    Intent intent=new Intent(context, EditarmedicacionActivity.class);
                    intent.putExtra("ID",listamedicacion.get(getAdapterPosition()).getIdmedicacion());
                    context.startActivity(intent);
                }
            });
        }
    }
}
