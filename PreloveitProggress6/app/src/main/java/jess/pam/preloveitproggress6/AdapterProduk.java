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
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.ProdukVH> {
    private Context ctx;
    private List<Produk> data;
    private List<Produk> keranjang;
    private List<Produk> favorit;

    public AdapterProduk(Context ctx, List<Produk> data, List<Produk> keranjang, List<Produk> favorit) {
        this.ctx = ctx;
        this.data = data;
        this.keranjang = keranjang != null ? keranjang : new ArrayList<>();
        this.favorit = favorit != null ? favorit : new ArrayList<>();
    }

    public static class ProdukVH extends RecyclerView.ViewHolder {
        ImageView tvImage, ivfavorit;
        TextView tvTitle, tvFavnum, tvPrice;

        public ProdukVH(@NonNull View itemView) {
            super(itemView);
            this.tvImage = itemView.findViewById(R.id.tvImage);
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.tvFavnum = itemView.findViewById(R.id.tvFavnum);
            this.tvPrice = itemView.findViewById(R.id.tvPrice);
            this.ivfavorit = itemView.findViewById(R.id.ivfavorit);  // ImageView untuk menambahkan ke favorit
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

        // Menambahkan listener untuk menambahkan produk ke favorit
        holder.ivfavorit.setOnClickListener(v -> {
            if (!favorit.contains(item)) {
                favorit.add(item); // Menambahkan produk ke daftar favorit
                updateFavoriteInDatabase(item); // Update ke database Firebase
                notifyItemChanged(position); // Memberitahu adapter untuk memperbarui tampilan
                Toast.makeText(ctx, "Produk ditambahkan ke favorit", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ctx, "Produk sudah ada di favorit", Toast.LENGTH_SHORT).show();
            }
        });

        // Menambahkan listener untuk membuka halaman detail produk
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

            // Kirimkan data keranjang jika diperlukan (misalnya, ID keranjang)
            if (keranjang != null && !keranjang.isEmpty()) {
                ArrayList<String> keranjangIds = new ArrayList<>();
                for (Produk p : keranjang) {
                    keranjangIds.add(p.getIdKeranjang());  // Gunakan ID keranjang atau data lain yang relevan
                }
                intent.putStringArrayListExtra("KERANJANG_IDS", keranjangIds);
            }

            ctx.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    // Fungsi untuk update data favorit ke Firebase
    private void updateFavoriteInDatabase(Produk item) {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://prelovitprogress6-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference favoritRef = database.getReference("favorit");

        // Buat key unik untuk setiap item favorit
        String favoritId = favoritRef.push().getKey();
        if (favoritId != null) {
            item.setIdFavorit(favoritId); // Menyimpan ID favorit di produk
            favoritRef.child(favoritId).setValue(item)
                    .addOnSuccessListener(aVoid -> {
                        // Favorit berhasil ditambahkan ke database
                    })
                    .addOnFailureListener(e -> {
                        // Gagal menambahkan favorit ke database
                    });
        }
    }
}