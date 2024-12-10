package jess.pam.sebelumkonversifragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterKategori extends RecyclerView.Adapter {
    private Context ctx;
    private List<Kategori> data;

    public AdapterKategori(Context ctx, List<Kategori> data) {
        this.ctx = ctx;
        this.data = data;
    }

    public class KategoriVH extends RecyclerView.ViewHolder {
        ImageView imgKategori;
        TextView tvKategori;

        public KategoriVH(@NonNull View itemView) {
            super(itemView);
            this.imgKategori = itemView.findViewById(R.id.Imgkategori);
            this.tvKategori = itemView.findViewById(R.id.Tvkategori);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(ctx).inflate(R.layout.viewholder_kategori, parent, false);
        RecyclerView.ViewHolder vh = new KategoriVH(rowView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Kategori item = this.data.get(position);
        KategoriVH vh = (KategoriVH) holder;

        vh.imgKategori.setImageResource(item.image);
        vh.tvKategori.setText(item.judul);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx, "Anda mengklik: " + item.judul, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}
