package jess.pam.sebelumkonversifragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterProduk extends RecyclerView.Adapter{
    private Context ctx;
    private List<Produk> data;
    public AdapterProduk (Context ctx, List<Produk> data) {
        this.data = data;
        this.ctx = ctx;
    }
    public class ProdukVH extends RecyclerView.ViewHolder {
        ImageView tvImage;
        TextView tvTitle;
        TextView tvFavnum;
        TextView tvPrice;
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(ctx).inflate(R.layout.viewholder_pop_list, parent, false);
        RecyclerView.ViewHolder vh = new ProdukVH(rowView);
        return vh;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Produk item = this.data.get(position);
        ProdukVH vh = (ProdukVH) holder;
        vh.tvImage.setImageResource(item.image);
        vh.tvTitle.setText(item.judul);
        vh.tvFavnum.setText(item.favorit);
        vh.tvPrice.setText(item.harga);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent untuk berpindah ke DetailActivity dan mengirim data produk
                Intent intent = new Intent(ctx, DetailActivity.class);
                intent.putExtra("NAMA_PRODUK", item.judul);
                intent.putExtra("HARGA", item.harga);
                intent.putExtra("FAVORIT", item.favorit);
                intent.putExtra("IMAGE", item.image);
                ctx.startActivity(intent);
                Toast.makeText(ctx, "Anda mengklik: " + item.judul, Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return this.data.size();
    }
}
