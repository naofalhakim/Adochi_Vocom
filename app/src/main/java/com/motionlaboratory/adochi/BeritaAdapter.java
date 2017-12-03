package com.motionlaboratory.adochi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by naofal on 3/11/2017.
 */
public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.MyViewHolder> {

    private Context mContext;
    private List<Berita> beritaList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView judul, narasi;
        public ImageView gambar;

        public MyViewHolder(View view) {
            super(view);
            judul = (TextView) view.findViewById(R.id.judul);
            narasi = (TextView) view.findViewById(R.id.narasi);
            gambar = (ImageView) view.findViewById(R.id.gambar);
        }
    }


    public BeritaAdapter(Context mContext, List<Berita> beritaList) {
        this.mContext = mContext;
        this.beritaList = beritaList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_beranda, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Berita berita = beritaList.get(position);
        holder.judul.setText(berita.getJudul());
        holder.narasi.setText(berita.getNarasi());
        Glide.with(mContext).load(berita.getGambar()).into(holder.gambar);

    }

    @Override
    public int getItemCount() {
        return beritaList.size();
    }
}
