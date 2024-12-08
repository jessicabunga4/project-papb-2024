package ace.mobile.favoriteproductlast;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FavoriteAdapter.OnLikeClickListener {

    private RecyclerView recyclerView;
    private FavoriteAdapter favoriteAdapter;
    private List<FavoriteProduct> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.rvListProduct);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inisialisasi Adapter
        productList = new ArrayList<>();
        favoriteAdapter = new FavoriteAdapter(productList, this);
        recyclerView.setAdapter(favoriteAdapter);

        // Memuat data produk
        loadProducts();
    }

    private void loadProducts() {
        productList.add(new FavoriteProduct(R.drawable.dress_pink, "Dress Pink",
                "Produk masih dalam kondisi bagus. Hanya 1 kali pemakaian. Dijual karena ukurannya tidak sesuai", "Rp 1.200.000"));
        productList.add(new FavoriteProduct(R.drawable.rok_midi, "Rok Midi",
                "Rok midi preloved dengan polos, dalam kondisi bagus. Hanya dipakai beberapa kali, tanpa cacat.", "Rp 70.000"));
        productList.add(new FavoriteProduct(R.drawable.blouse, "Blouse Casual",
                "Blouse polos dengan bahas chiffon yang ringan, hanya beberapa kali dipakai", "Rp 250.000"));
        productList.add(new FavoriteProduct(R.drawable.celana_kulot, "Celana Kulot",
                "Celana kulot preloved berwarna hitam, masih dalam kondisi sangat bagus. Hanya dipakai beberapa kali, bahannya tetap nyaman dan elastis.", "Rp 250.000"));

        favoriteAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLikeClick(int position) {
        // Hapus produk dari daftar
        productList.remove(position);
        favoriteAdapter.notifyItemRemoved(position);

        // Menampilkan pesan notifikasi
        Toast.makeText(this, "Produk dihapus dari favorit", Toast.LENGTH_SHORT).show();
    }
}
