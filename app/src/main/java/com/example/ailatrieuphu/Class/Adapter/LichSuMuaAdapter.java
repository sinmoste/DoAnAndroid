package com.example.ailatrieuphu.Class.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ailatrieuphu.LichSuMua;
import com.example.ailatrieuphu.R;

import java.util.ArrayList;

public class LichSuMuaAdapter extends RecyclerView.Adapter<LichSuMuaAdapter.ViewHolder> {

    Context context;
    public ArrayList<LichSuMua> listls;

    public LichSuMuaAdapter(Context context, ArrayList<LichSuMua> listls) {
        this.context = context;
        this.listls = listls;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tencredit,credit,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tencredit=itemView.findViewById(R.id.txt_lichsu_tencredit);
            credit=itemView.findViewById(R.id.txt_lichsu_credit);
            time=itemView.findViewById(R.id.txt_thoigian);
        }
    }
    @NonNull
    @Override
    public LichSuMuaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.lichsumua_viewholder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuMuaAdapter.ViewHolder holder, int position) {
       // holder.tencredit.setText(listls.get(position).get);
        //String url= ReadAPI.GetJSON(URLl.url_lay_ten_credit+listls.get(position).getGoi_credit_id());
        //Toast.makeText(context,url,Toast.LENGTH_SHORT).show();
        //holder.tencredit.setText(url);
        holder.tencredit.setText(listls.get(position).getTen_goi());
        holder.credit.setText(String.valueOf(listls.get(position).getCredit()));
        holder.time.setText(listls.get(position).getCreate_at());
    }

    @Override
    public int getItemCount() {
        return listls.size();
    }


}
