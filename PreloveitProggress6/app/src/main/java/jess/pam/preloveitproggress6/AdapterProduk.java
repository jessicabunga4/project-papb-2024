package jess.pam.preloveitproggress6;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.ProdukVH> {
    private Context ctx;
    private List<Produk> data;

    public AdapterProduk(Context ctx, List<Produk> data) {
        this.ctx = ctx;
        this.data = data;
    }

    public static class ProdukVH extends RecyclerView.ViewHolder {
        ImageView tvImage;
        TextView tvTitle, tvFavnum, tvPrice;

        public ProdukVH(@NonNull View itemView) {
            super(itemView);
            this.tvImage = itemView.findViewById(R.id.tvImage);
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.tvFavnum = itemView.findViewById(R.id.tvFavnum);
            this.tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }

    @NonNull
    @Override
    public ProdukVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(ctx).inflate(R.layout.viewholder_pop_list, parent, false);
        return new ProdukVH(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdukVH holder, int position) {
        Produk item = this.data.get(position);

        holder.tvTitle.setText(item.getJudul());
        holder.tvFavnum.setText(item.getFavorit());
        holder.tvPrice.setText(item.getHarga());

        holder.tvImage.setImageResource(item.getImageResource(ctx));


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(ctx, DetailActivity.class);
            intent.putExtra("JUDUL", item.getJudul());
            intent.putExtra("HARGA", item.getHarga());
            intent.putExtra("FAVORIT", item.getFavorit());
            intent.putExtra("DESC", item.getDeskripsi());
            intent.putExtra("NAMA", item.getNama());
            intent.putExtra("IMAGE", item.getImage());
            intent.putExtra("BRAND", item.getBrand());
            intent.putExtra("MINUS", item.getMinus());



            ctx.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}
