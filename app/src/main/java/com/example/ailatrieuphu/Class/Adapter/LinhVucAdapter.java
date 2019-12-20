package com.example.ailatrieuphu.Class.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ailatrieuphu.Answerquestion;
import com.example.ailatrieuphu.Class.CauHoiAsyncTask;
import com.example.ailatrieuphu.Class.LinhVuc;
import com.example.ailatrieuphu.R;

import java.util.ArrayList;

public class LinhVucAdapter extends RecyclerView.Adapter<LinhVucAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LinhVuc> listLinhVuc;

    public LinhVucAdapter(Context context, ArrayList<LinhVuc> m){
        this.context = context;
        listLinhVuc = m;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.linhvuc_viewholder,parent,false);
        return new ViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.btnLinhVuc.setText(listLinhVuc.get(position).getTen());
        holder.btnLinhVuc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new CauHoiAsyncTask(context).execute("http://192.168.1.253/GameLaravel/public/api/cau-hoi?linh_vuc_id="
                        + listLinhVuc.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listLinhVuc.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private Button btnLinhVuc;
        private LinhVucAdapter mAdapter;
        public ViewHolder(@NonNull View itemView, LinhVucAdapter linhVucAdapter) {
            super(itemView);
            btnLinhVuc = itemView.findViewById(R.id.btnLinhVuc);
            mAdapter = linhVucAdapter;
        }
    }
}
