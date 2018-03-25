package com.example.android.yos_1202154119_modul5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Yosafat Dhimas on 25/03/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.holder> {
    Context mContext;
    List<Todo> mList;
    int mColor;

    public Adapter(Context context, List<Todo> list, int color){
        this.mContext = context;
        this.mList = list;
        this.mColor = color;
    }

    //method viewholder untuk recyclerview
    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //membuat view baru
        View view = LayoutInflater.from(mContext).inflate(R.layout.cardview, parent, false);
        holder hld = new holder(view);
        return hld;
    }

    //method untuk mengatur isi data dalam viewholder
    @Override
    public void onBindViewHolder(holder holder, int position) {
        Todo data = mList.get(position);

        holder.Todo.setText(data.getTodo());
        holder.Descrpt.setText(data.getDesc());
        holder.Priority.setText(data.getPrior());
        holder.CV.setCardBackgroundColor(mContext.getResources().getColor(this.mColor));
    }

    //hitung jumlah mList yang ada
    @Override
    public int getItemCount() {
        return mList.size();
    }

    //get the data
    public Todo getData(int position){
        return mList.get(position);
    }

    //hapus data dari mList item
    public void deleteData(int i){
        //data yg dipilih dihapus dan menampilkan notifikasi bahwa data telah dihapus
        mList.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, mList.size());
    }

    class holder extends RecyclerView.ViewHolder{
        TextView Todo;
        TextView Descrpt;
        TextView Priority;
        CardView CV;

        //mengambil id textview layout cardview
        holder(View itemView){
            super(itemView);

            Todo = itemView.findViewById(R.id.todo);
            Descrpt = itemView.findViewById(R.id.description);
            Priority = itemView.findViewById(R.id.number);
            CV = itemView.findViewById(R.id.cardview);
        }
    }
}
