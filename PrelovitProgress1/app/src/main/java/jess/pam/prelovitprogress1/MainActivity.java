package jess.pam.prelovitprogress1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvProduk, rvKategori;
    private AdapterProduk AdapterProduk;
    private AdapterKategori AdapterKategori;
    private List<Kategori> dataK;
    private List<Produk> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.rvProduk = this.findViewById(R.id.rvProduk);

        List<Produk> data = new ArrayList<>();
        data.add(new Produk(R.drawable.item1, "Kemeja Flanel", "Rp. 30.000", "13"));
        data.add(new Produk(R.drawable.item2, "Topi Oldschool", "Rp. 28.000", "8"));
        data.add(new Produk(R.drawable.item3, "Gaun Floral", "Rp. 55.000", "19"));
        data.add(new Produk(R.drawable.item4, "Gaun Formal", "Rp. 65.000", "11"));
        data.add(new Produk(R.drawable.item5, "Jaket Kulit", "Rp. 100.000", "23"));
        data.add(new Produk(R.drawable.item6, "Baggy Star Jeans", "Rp. 85.000", "12"));
        this.data = data;

        this.AdapterProduk = new AdapterProduk(this, data, getSupportFragmentManager());
        this.rvProduk.setAdapter(this.AdapterProduk);
        this.rvProduk.setLayoutManager(new GridLayoutManager(this, 2));

        this.rvKategori = findViewById(R.id.rvKategori);
        dataK = new ArrayList<>();
        dataK.add(new Kategori(R.drawable.catt1, "Rok"));
        dataK.add(new Kategori(R.drawable.catt2, "Kaos"));
        dataK.add(new Kategori(R.drawable.catt3, "Dress"));
        dataK.add(new Kategori(R.drawable.catt4, "Jaket"));
        dataK.add(new Kategori(R.drawable.catt5, "Celana"));

        AdapterKategori = new AdapterKategori(this, dataK);
        rvKategori.setAdapter(AdapterKategori);
        rvKategori.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    };

    private void openDetailFragment() {
        DetailFragment fragment = new DetailFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detailFragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}