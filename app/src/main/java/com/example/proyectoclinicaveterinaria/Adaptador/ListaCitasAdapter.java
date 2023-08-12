package com.example.proyectoclinicaveterinaria.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoclinicaveterinaria.EditarCitasActivity;
import com.example.proyectoclinicaveterinaria.R;
import com.example.proyectoclinicaveterinaria.Entidades.Citas;



import java.util.ArrayList;

public class ListaCitasAdapter extends RecyclerView.Adapter<ListaCitasAdapter.CitaViewHolder> {

    ArrayList<Citas>listacitas;
    public ListaCitasAdapter(ArrayList<Citas>listacitas){
        this.listacitas=listacitas;
    }


    @NonNull

    @Override
    public CitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_citas,null,false);
        return new CitaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  CitaViewHolder holder, int position) {
        holder.viewtipocita.setText(listacitas.get(position).getTIPODECITA());
        holder.viewnombremascota.setText(listacitas.get(position).getNOMBREMASCOTA());
        holder.viewveterinario.setText(listacitas.get(position).getVETERINARIO());
        holder.viewfechacita.setText(listacitas.get(position).getFECHACITA());
        holder.viewhoracita.setText(listacitas.get(position).getHORACITA());
        holder.viewdescripcion.setText(listacitas.get(position).getDESCRIPCION());
        holder.viewsede.setText(listacitas.get(position).getCLINICA());
    }

    @Override
    public int getItemCount() {
        return listacitas.size();
    }

    public class CitaViewHolder extends RecyclerView.ViewHolder {
        TextView viewtipocita,viewnombremascota,viewveterinario,viewfechacita,viewhoracita,viewdescripcion,viewsede;


        public CitaViewHolder(@NonNull  View itemView) {
            super(itemView);
            viewtipocita=itemView.findViewById(R.id.tvtipocitarv);
            viewnombremascota=itemView.findViewById(R.id.tvnombremascotarv);
            viewveterinario=itemView.findViewById(R.id.tvveterinariorv);
            viewfechacita=itemView.findViewById(R.id.tvfechacitarv);
            viewhoracita=itemView.findViewById(R.id.tvhoracitarv);
            viewdescripcion=itemView.findViewById(R.id.tvdescripcionrv);
            viewsede=itemView.findViewById(R.id.tvsederv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                    Intent intent=new Intent(context, EditarCitasActivity.class);
                    intent.putExtra("ID",listacitas.get(getAdapterPosition()).getNrocitas());
                    context.startActivity(intent);
                }
            });
        }
    }
}
