package jess.pam.preloveitproggress6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import jess.pam.preloveitproggress6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static final
    String FirebaseURL = "https://prelovitprogress6-default-rtdb.asia-southeast1.firebasedatabase.app/";
    private RecyclerView rvProduk, rvKategori;
    private AdapterProduk adapterProduk;
    private DatabaseReference appDb;
    private FirebaseDatabase db;
    private List<Produk> dataProduk;
    private AdapterKategori AdapterKategori;
    private List<Kategori> dataK;
    private List<Produk> keranjang;
    private List<Produk> favorit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView ivKeranjangM = findViewById(R.id.ivKeranjangM);
        ImageView ivFavM = findViewById(R.id.ivFavM);
        ImageView ivProfilM = findViewById(R.id.ivProfilM);

        ivProfilM.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
            startActivity(intent);
        });

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

        rvProduk = findViewById(R.id.rvProduk);

        // Konfigurasi Firebase
        this.db = FirebaseDatabase.getInstance(FirebaseURL);
        this.appDb = this.db.getReference("produk");

        dataProduk = new ArrayList<>();
        adapterProduk = new AdapterProduk(this, dataProduk, keranjang, favorit);
        keranjang = new ArrayList<>();

        rvProduk.setAdapter(adapterProduk);
        rvProduk.setLayoutManager(new GridLayoutManager(this, 2));
        this.appDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataProduk.clear();
                for (DataSnapshot s : snapshot.getChildren()) {
                    Produk produk = s.getValue(Produk.class);
                    if (produk != null) {
                        dataProduk.add(produk);
                    }
                }
                adapterProduk.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Gagal memuat data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        ivKeranjangM.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, KeranjangActivity.class);
            startActivity(intent);
        });

        ivFavM.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FavoritActivity.class);
            startActivity(intent);
        });

    }
}
