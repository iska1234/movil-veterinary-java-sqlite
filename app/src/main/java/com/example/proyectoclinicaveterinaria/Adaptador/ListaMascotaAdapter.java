package com.example.proyectoclinicaveterinaria.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoclinicaveterinaria.DAO.MascotaDAO;
import com.example.proyectoclinicaveterinaria.Entidades.Mascota;
import com.example.proyectoclinicaveterinaria.R;
import com.example.proyectoclinicaveterinaria.RegistrarCitasActivity;
import com.example.proyectoclinicaveterinaria.db.DbMascotas;

import java.util.ArrayList;

public class ListaMascotaAdapter extends RecyclerView.Adapter<ListaMascotaAdapter.MascotaViewHolder> {

    ListaMascotaAdapter listaMascotaAdapter;
    ArrayList<Mascota> listaMascota;
    MascotaDAO mascotaDAO;
    public ListaMascotaAdapter(ArrayList<Mascota> listaMascota, MascotaDAO mascotaDAO){
        this.listaMascota = listaMascota;
        this.mascotaDAO = mascotaDAO;
    }
    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mascota,null,false);
       return new MascotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder holder, int position) {

        holder.bindData(mascotaDAO.MascotaBuscarPorPosicion(position),position);
        holder.viewnombre.setText(mascotaDAO.MascotaBuscarPorPosicion(position).getNommas());
            //holder.viewnombre.setText(listaMascota.get(position).getNommas());
            holder.viewpeso.setText("Peso: "+mascotaDAO.MascotaBuscarPorPosicion(position).getPesomas());
            holder.viewraza.setText("Raza: "+mascotaDAO.MascotaBuscarPorPosicion(position).getRazamas());
            holder.viewespecie.setText("Especie: "+mascotaDAO.MascotaBuscarPorPosicion(position).getEspecie());
            holder.viewedad.setText("Edad: "+mascotaDAO.MascotaBuscarPorPosicion(position).getEdad());
            holder.viewsexo.setText("Sexo: "+mascotaDAO.MascotaBuscarPorPosicion(position).getSexomas());
            //holder.viewfoto.setText(listaMascota.get(position).getFotomas());
            //
            holder.setOnClickListener();
    }


    @Override
    public int getItemCount() {
        return mascotaDAO.tamanio();
    }

    public class MascotaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Context context;
        TextView viewnombre,viewpeso,viewraza,viewespecie,viewedad,viewsexo,viewfoto;
        Button btnEmpezarcita;
        LinearLayout llClick;
        int codigoMascota,posici;
        Mascota mascota;

        public MascotaViewHolder(@NonNull View itemView){
            super(itemView);
            context = itemView.getContext();
            viewnombre=itemView.findViewById(R.id.tvNombreMascota);
            viewpeso=itemView.findViewById(R.id.tvPesoMascota);
            viewraza=itemView.findViewById(R.id.tvRazaMascota);
            viewespecie=itemView.findViewById(R.id.tvEspecieMascota);
            viewedad=itemView.findViewById(R.id.tvEdadMascota);
            viewsexo=itemView.findViewById(R.id.tvSexoMascota);
            //viewfoto=itemView.findViewById(R.id.tvFotoMascota);
            btnEmpezarcita=itemView.findViewById(R.id.btnEmpezarCita);
            llClick=itemView.findViewById(R.id.llClick);
            llClick.setOnClickListener(this);
        }
        void setOnClickListener(){
            btnEmpezarcita.setOnClickListener(this);
        }
        public void bindData(Mascota item,int codig) {
            codigoMascota=item.getCodmas();
            posici=codig;
             mascota=new Mascota(codigoMascota,item.getNommas(),item.getPesomas(), item.getRazamas(), item.getEspecie(), item.getEdad(), item.getSexomas(),item.getFotomas());
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnEmpezarCita:
                    Intent intent = new Intent(context, RegistrarCitasActivity.class);
                    intent.putExtra("NOMMAS",viewnombre.getText());
                    context.startActivity(intent);
                    break;
                case R.id.llClick:
                    //Toast.makeText(context, "click codigo mascota: "+codigoMascota, Toast.LENGTH_SHORT).show();
                    ModificarMascota(context,codigoMascota);
                    break;
            }
        }
        void ModificarMascota(Context context,int cod){
            EditText xnombremas , xpesomas , xraza , xespecie , xedad ;
            Button btnUpdate,btnEliminar ;
            RadioButton xrbmacho , xrrbhembra;
            String sexo="";
            LayoutInflater mInflater;
            mInflater=LayoutInflater.from(context);
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            View view=mInflater.inflate(R.layout.dialogo_modificar_mascota,null);
            builder.setView(view);
            AlertDialog dialog=builder.create();
            dialog.show();

            btnUpdate=view.findViewById(R.id.btnActualizar);
            btnEliminar=view.findViewById(R.id.outlinedEliminar);

            xnombremas = view.findViewById(R.id.edtnombremascotaE);
            xpesomas = view.findViewById(R.id.edtpesomascotaE);
            xraza = view.findViewById(R.id.edtrazamascotaE);
            xespecie = view.findViewById(R.id.edtespeciemascotaE);
            xedad= view.findViewById(R.id.edtedadmascotaE);
            xrbmacho= view.findViewById(R.id.rbmachoE);
            xrrbhembra = view.findViewById(R.id.rbhembraE);

            xnombremas.setText(mascota.getNommas());
            xpesomas.setText(String.valueOf(mascota.getPesomas()));
            xraza.setText(mascota.getRazamas());
            xespecie.setText(mascota.getEspecie());
            xedad.setText(String.valueOf(mascota.getEdad()));
            sexo=mascota.getSexomas();
            if (sexo.equals("Macho")) {
                xrbmacho.setChecked(true);
                xrrbhembra.setChecked(false);
            }
            if (sexo.equals("Hembra")){
                xrrbhembra.setChecked(true);
                xrbmacho.setChecked(false);
            }
            DbMascotas dbMascotas = new DbMascotas(context);
            //////
            btnUpdate.setOnClickListener(view12 -> {
                String selecionado="";
                if (xrrbhembra.isChecked()) selecionado="Hembra";
                if (xrbmacho.isChecked()) selecionado="Macho";

                if (!xnombremas.getText().toString().isEmpty() && !xpesomas.getText().toString().isEmpty() && !xraza.getText().toString().isEmpty() && !xespecie.getText().toString().isEmpty() && !xedad.getText().toString().isEmpty()){
                    Mascota mascota1=new Mascota(cod,xnombremas.getText().toString().trim(),xpesomas.getText().toString(),xraza.getText().toString().trim(),xespecie.getText().toString().trim(),xedad.getText().toString().trim(),selecionado, mascota.getFotomas());
                    boolean id = dbMascotas.updateMascota(mascota1);
                    //Log.d("ID: ","RETORNA DE REGISTRAR: .."+id);
                    if (id){
                        int posicionn=mascotaDAO.BuscarPosicionMascota(cod);
                        mascotaDAO.ActualizarEMascota(posicionn,mascota1);
                        notifyDataSetChanged();
                        Toast.makeText(context, "LA MASCOTA HA SIDO ACTUALIZADA", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    else{
                        Toast.makeText(context, "ERROR!!..NO SE PUDO ACTUA LIZAR", Toast.LENGTH_SHORT).show();
                    }
                }else Toast.makeText(context, "FALTAN INGRESAR DATOS DE LA MASCOTA", Toast.LENGTH_SHORT).show();
            });

            btnEliminar.setOnClickListener(view1 -> {
                boolean respuesta=dbMascotas.deleteOneMascota(cod);//si elimina retorna false
                if (!respuesta) {
                    Toast.makeText(context, "LA MASCOTA SE A ELIMINADO CON EXITO", Toast.LENGTH_SHORT).show();
                    int posicion=mascotaDAO.BuscarPosicionMascota(cod);
                    mascotaDAO.EliminarMascota(posicion);
                    //listaMascotaAdapter=new ListaMascotaAdapter(listaMascota,mascotaDAO);
                    //listaMascotaAdapter.notifyItemRemoved(posici);
                    //new ListadoMascotasActivity().pintar(posici);
                    notifyItemRemoved(posici);
                    dialog.dismiss();
                }else Toast.makeText(context, "PROBLEMAS PARA ELIMINAR MASCOTA, COMUNIQUESE CON EL ADMIN", Toast.LENGTH_SHORT).show();

            });




        }



    }
}
