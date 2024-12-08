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

public class AdapterKeranjang extends RecyclerView.Adapter<AdapterKeranjang.KeranjangVH> {
    private Context ctx;
    private List<Produk> data;
    private OnItemRemovedListener listener;

    public AdapterKeranjang(Context ctx, List<Produk> data, OnItemRemovedListener listener) {
        this.ctx = ctx;
        this.data = data;
        this.listener = listener;
    }

    public static class KeranjangVH extends RecyclerView.ViewHolder {
        ImageView ivGambarK, ivHapus;
        TextView tvProdukK, tvHargaK, tvDeskripsiK;

        public KeranjangVH(@NonNull View itemView) {
            super(itemView);
            ivGambarK = itemView.findViewById(R.id.ivGambarK);
            ivHapus = itemView.findViewById(R.id.ivHapus);
            tvProdukK = itemView.findViewById(R.id.tvProdukK);
            tvHargaK = itemView.findViewById(R.id.tvHargaK);
            tvDeskripsiK = itemView.findViewById(R.id.tvDeskripsiK);
        }
    }

    @NonNull
    @Override
    public KeranjangVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(ctx).inflate(R.layout.viewholder_keranjang, parent, false);
        return new KeranjangVH(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull KeranjangVH holder, int position) {
        Produk item = data.get(position);

        holder.tvProdukK.setText(item.getJudul());
        holder.tvHargaK.setText(item.getHarga());
        holder.tvDeskripsiK.setText(item.getDeskripsi());
        holder.ivGambarK.setImageResource(item.getImageResource(ctx));

        holder.ivHapus.setOnClickListener(v -> {
            FirebaseDatabase database = FirebaseDatabase.getInstance("https://prelovitprogress6-default-rtdb.asia-southeast1.firebasedatabase.app/");
            DatabaseReference keranjangRef = database.getReference("keranjang");

            keranjangRef.child(item.getIdKeranjang()).removeValue()
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(ctx, "Produk dihapus dari keranjang!", Toast.LENGTH_SHORT).show();
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

