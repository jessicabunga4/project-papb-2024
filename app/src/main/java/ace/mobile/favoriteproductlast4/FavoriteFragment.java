package ace.mobile.favoriteproductlast4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements FavoriteAdapter.OnLikeClickListener {

    private RecyclerView recyclerView;
    private FavoriteAdapter favoriteAdapter;
    private List<FavoriteProduct> productList;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);

        // Inisialisasi RecyclerView
        recyclerView = rootView.findViewById(R.id.rvListProduct);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inisialisasi Adapter
        productList = new ArrayList<>();
        favoriteAdapter = new FavoriteAdapter(productList, this);
        recyclerView.setAdapter(favoriteAdapter);

        // Memuat data produk
        loadProducts();

        return rootView;
    }

    private void loadProducts() {
        productList.add(new FavoriteProduct(R.drawable.nike_air_putih, "Nike Air White Original",
                "Produk masih dalam kondisi bagus. Hanya 1 kali pemakaian. Dijual karena ukurannya tidak sesuai", "Rp 1.200.000"));

        productList.add(new FavoriteProduct(R.drawable.jaket_kulit_hitam, "Jaket Kulit Hitam",
                "Jaket kulit hitam prelove, masih dalam kondisi sangat baik. Kulit masih mulus dan nyaman dipakai.", "Rp 150.000"));
        productList.add(new FavoriteProduct(R.drawable.blouse, "Blouse Casual",
                "Blouse polos dengan bahas chiffon yang ringan, hanya beberapa kali dipakai", "Rp 250.000"));
        productList.add(new FavoriteProduct(R.drawable.celana, "Celana Kulot",
                "Celana kulot preloved berwarna hitam, masih dalam kondisi sangat bagus. Hanya dipakai beberapa kali, bahannya tetap nyaman dan elastis.", "Rp 250.000"));

        favoriteAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLikeClick(int position) {
        // Hapus produk dari daftar
        productList.remove(position);
        favoriteAdapter.notifyItemRemoved(position);

        // Menampilkan pesan notifikasi
        Toast.makeText(getContext(), "Produk dihapus dari favorit", Toast.LENGTH_SHORT).show();
    }
}
