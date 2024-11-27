package via.mobile.profile;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.VH> {

    private Context ctx;
    private final List<Product> dataset;
    private DatabaseReference appDb;

    public ProductAdapter(Context ctx, List<Product> dataset) {
        this.ctx = ctx;
        this.dataset = dataset;
    }

    // Set Database Reference
    public void setAppDb(DatabaseReference appDb) {
        this.appDb = appDb;
    }

    public class VH extends RecyclerView.ViewHolder {
        private final ImageView ivGambar;
        private final TextView tvNama;
        private final TextView tvHarga;
        private final TextView tvJenis;
        private Product product;

        public VH(@NonNull View itemView) {
            super(itemView);
            this.ivGambar = itemView.findViewById(R.id.ivGambar);
            this.tvNama = itemView.findViewById(R.id.tvNama);
            this.tvHarga = itemView.findViewById(R.id.tvHarga);
            this.tvJenis = itemView.findViewById(R.id.tvJenis);
        }

        public void bind(Product product) {
            this.product = product;

            // Load gambar menggunakan Glide
            if (product.getGambar() != null && !product.getGambar().isEmpty()) {
                Glide.with(ctx)
                        .load(Uri.parse(product.getGambar()))
                        .into(ivGambar);
            }
            this.tvNama.setText(product.getNama());
            DecimalFormat formatter = new DecimalFormat("#,###");
            this.tvHarga.setText(String.format("Rp %s", formatter.format(product.getHarga())));
            this.tvJenis.setText(product.getJenis());
        }

    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Menginflate layout item produk
        View v = LayoutInflater.from(this.ctx)
                .inflate(R.layout.item_produk, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(this.dataset.get(position)); // Menghubungkan data produk ke view holder
    }

    @Override
    public int getItemCount() {
        return this.dataset.size(); // Mengembalikan jumlah produk dalam list
    }
}
