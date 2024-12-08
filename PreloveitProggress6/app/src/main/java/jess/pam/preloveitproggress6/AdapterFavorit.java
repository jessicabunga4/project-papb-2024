package jess.pam.preloveitproggress6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterFavorit extends RecyclerView.Adapter<AdapterFavorit.FavoritVH> {
    private Context ctx;
    private List<Produk> data;
    private OnItemRemovedListener listener;

    public AdapterFavorit(Context ctx, List<Produk> data, OnItemRemovedListener listener) {
        this.ctx = ctx;
        this.data = data;
        this.listener = listener;
    }

    public static class FavoritVH extends RecyclerView.ViewHolder {
        ImageView ivGambarF, ivFav;
        TextView tvProdukF, tvHargaF, tvDeskripsiF;

        public FavoritVH(@NonNull View itemView) {
            super(itemView);
            ivGambarF = itemView.findViewById(R.id.ivGambarF);  // ImageView untuk gambar produk
            ivFav = itemView.findViewById(R.id.ivfav);      // ImageView untuk tombol hapus
            tvProdukF = itemView.findViewById(R.id.tvProdukF);  // TextView untuk judul produk
            tvHargaF = itemView.findViewById(R.id.tvHargaF);    // TextView untuk harga produk
            tvDeskripsiF = itemView.findViewById(R.id.tvDeskripsiF); // TextView untuk deskripsi produk
        }
    }

    @NonNull
    @Override
    public FavoritVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(ctx).inflate(R.layout.viewholder_favorit, parent, false);  // Ganti dengan layout favorit yang sesuai
        return new FavoritVH(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritVH holder, int position) {
        Produk item = data.get(position);

        holder.tvProdukF.setText(item.getJudul());
        holder.tvHargaF.setText(item.getHarga());
        holder.tvDeskripsiF.setText(item.getDeskripsi());
        holder.ivGambarF.setImageResource(item.getImageResource(ctx));  // Pastikan method ini cocok untuk mendapatkan gambar produk

        // Event untuk menghapus produk dari daftar favorit
        holder.ivFav.setOnClickListener(v -> {
            FirebaseDatabase database = FirebaseDatabase.getInstance("https://prelovitprogress6-default-rtdb.asia-southeast1.firebasedatabase.app/");
            DatabaseReference favoritRef = database.getReference("favorit");

            // Hapus data berdasarkan ID favorit
            favoritRef.child(item.getIdFavorit()).removeValue()
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(ctx, "Produk dihapus dari favorit!", Toast.LENGTH_SHORT).show();
                        data.remove(position);
                        notifyItemRemoved(position);
                        if (listener != null) {
                            listener.onItemRemoved();
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(ctx, "Gagal menghapus: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemRemovedListener {
        void onItemRemoved();
    }
}
