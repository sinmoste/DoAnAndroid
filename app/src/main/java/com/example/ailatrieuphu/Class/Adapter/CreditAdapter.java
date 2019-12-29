package com.example.ailatrieuphu.Class.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ailatrieuphu.CreDit;
import com.example.ailatrieuphu.R;

import java.util.ArrayList;

public class CreditAdapter extends RecyclerView.Adapter<CreditAdapter.ViewHolder> {
    Context context;
    private ArrayList<CreDit> listcredit;

    public CreditAdapter(Context context, ArrayList<CreDit> listcredit) {
        this.context = context;
        this.listcredit = listcredit;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tengoicredit,giacredit,giatien;
        private ImageView imgkimcuongshop;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tengoicredit=itemView.findViewById(R.id.txt_tencredit);
            giacredit=itemView.findViewById(R.id.txt_giacredit);
            giatien=itemView.findViewById(R.id.txt_giatiencredit);
            imgkimcuongshop=itemView.findViewById(R.id.img_kimcuong_shop);
        }
    }
    @NonNull
    @Override
    public CreditAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.goicredit_viewholder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CreditAdapter.ViewHolder holder, int position) {

        String giatien=String.valueOf(listcredit.get(position).getGia_tien_credit());
        String giacredit=String.valueOf(listcredit.get(position).getGia_credit());

        //
        holder.tengoicredit.setText("Tên Gói: "+listcredit.get(position).getTen_goi_credit());
        holder.giacredit.setText(giatien);
        holder.giatien.setText(giacredit);
        holder.imgkimcuongshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listcredit.size();
    }


}
