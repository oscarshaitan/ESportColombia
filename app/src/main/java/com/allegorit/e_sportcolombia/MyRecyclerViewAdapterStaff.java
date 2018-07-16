package com.allegorit.e_sportcolombia;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyRecyclerViewAdapterStaff extends RecyclerView
        .Adapter<MyRecyclerViewAdapterStaff
        .DataObjectHolder> {
    private ArrayList<StaffObj> mDataset;
    private static Activity activity;

    public MyRecyclerViewAdapterStaff(ArrayList<StaffObj> mDataset, Activity activity) {
        this.mDataset = mDataset;
        this.activity = activity;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.staff_card, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.name.setText(mDataset.get(position).getName());
        holder.name2.setText(mDataset.get(position).getName2());
        holder.rol.setText(mDataset.get(position).getRol());
        holder.age.setText(mDataset.get(position).getAge());
        holder.since.setText(mDataset.get(position).getSince());
        holder.p1 = mDataset.get(position).getPic();
        holder.g1 = mDataset.get(position).getGame1();
        holder.g2 = mDataset.get(position).getGame2();
        holder.g3 = mDataset.get(position).getGame3();
        holder.g4 = mDataset.get(position).getGame4();

        Picasso.get()
                .load(holder.p1)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.pic);
        Picasso.get()
                .load(holder.g1)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.game1);
        Picasso.get()
                .load(holder.g2)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.game2);
        Picasso.get()
                .load(holder.g3)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.game3);
        Picasso.get()
                .load(holder.g4)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.game4);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder{
        TextView name, name2, rol, age, since;
        ImageView pic, game1, game2, game3, game4;
        int g1,g2,g3,g4,p1;
        public DataObjectHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            name2 = (TextView)itemView.findViewById(R.id.name2);
            rol = (TextView)itemView.findViewById(R.id.rol);
            age = (TextView)itemView.findViewById(R.id.age);
            since = (TextView)itemView.findViewById(R.id.since);

            pic = (ImageView)itemView.findViewById(R.id.pic);
            game1 = (ImageView)itemView.findViewById(R.id.game1);
            game2 = (ImageView)itemView.findViewById(R.id.game2);
            game3 = (ImageView)itemView.findViewById(R.id.game3);
            game4 = (ImageView)itemView.findViewById(R.id.game4);

        }
    }
}
