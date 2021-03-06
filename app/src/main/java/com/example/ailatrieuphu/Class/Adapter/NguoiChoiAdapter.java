package com.example.ailatrieuphu.Class.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ailatrieuphu.NguoiChoi;
import com.example.ailatrieuphu.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NguoiChoiAdapter extends RecyclerView.Adapter<NguoiChoiAdapter.ViewHolder> {
    private Context context;
    private ArrayList<NguoiChoi> Listnguoichoi;

    public NguoiChoiAdapter(Context context, ArrayList<NguoiChoi> listnguoichoi) {
        this.context = context;
        Listnguoichoi = listnguoichoi;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tentaikhoan,sodiem;
        private ImageView anhdaidien,stt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tentaikhoan=itemView.findViewById(R.id.txttaikhoan_xephang);
            sodiem=itemView.findViewById(R.id.txtdiem_xephang);
            anhdaidien=itemView.findViewById(R.id.imganhdaidien_xephang);
            stt=itemView.findViewById(R.id.img_stt);
        }
    }
    @NonNull
    @Override
    public NguoiChoiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.bangxephang_viewholder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NguoiChoiAdapter.ViewHolder holder, int position) {
        holder.tentaikhoan.setText(Listnguoichoi.get(position).getTen_dang_nhap());
      //  Toast.makeText(context, Listnguoichoi.get(position).getId(), Toast.LENGTH_SHORT).show();
        holder.sodiem.setText(Listnguoichoi.get(position).getDiem_cao_nhat());
    //  holder.anhdaidien.setImageResource(Integer.parseInt(Listnguoichoi.get(position).getHinh_dai_dien().toString()));
        String hinh= Listnguoichoi.get(position).getHinh_dai_dien();
        holder.stt.setImageResource(R.drawable.ironman);
        if(!hinh.equals("")){
            Picasso.with(context).load("http://10.0.3.2:8080/GameLaravel/public/img/"+hinh).into(holder.anhdaidien);
        }else{
            holder.anhdaidien.setImageResource(R.drawable.ironman);
        }



    }

    @Override
    public int getItemCount() {
        return Listnguoichoi.size();
    }


}
