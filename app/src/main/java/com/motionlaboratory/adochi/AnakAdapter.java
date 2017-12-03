package com.motionlaboratory.adochi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by naofal on 3/11/2017.
 */

public class AnakAdapter extends RecyclerView.Adapter<AnakAdapter.MyViewHolder> {

    private Context mContext;
    private List<Anak> anakList;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nama_a, asal_panti, daerah, umur;
        public ImageView gambar_anak;
        List<Anak> anakList2 = new ArrayList<Anak>();
        Context ctx;

        public MyViewHolder(View view,Context ctx, List<Anak> anakList) {
            super(view);
            this.ctx = ctx;
            this.anakList2 = anakList;
            view.setOnClickListener(this);
            nama_a = (TextView) view.findViewById(R.id.nama_anak);
            umur = (TextView) view.findViewById(R.id.umur_anak);
            gambar_anak = (ImageView) view.findViewById(R.id.gambar_anak);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Anak anak = this.anakList2.get(position);
            Intent intent = new Intent(ctx,AnakDetailActivity.class);
            intent.putExtra("nama",anak.getNama());
            intent.putExtra("Umur",anak.getUmur());
            intent.putExtra("gambar",anak.getGambar());
            intent.putExtra("daerah",anak.getDaerah());
            this.ctx.startActivity(intent);
        }
    }

    public AnakAdapter(Context mContext, List<Anak> anakList) {
        this.mContext = mContext;
        this.anakList = anakList;
    }

    @Override
    public AnakAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_anak, parent, false);

        return new AnakAdapter.MyViewHolder(itemView,mContext,anakList);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Anak anak = anakList.get(position);
        holder.nama_a.setText(anak.getNama());
        holder.umur.setText(anak.getUmur());
        Glide.with(mContext).load(anak.getGambar()).into(holder.gambar_anak);

    }

    @Override
    public int getItemCount() {
        return anakList.size();
    }


}
