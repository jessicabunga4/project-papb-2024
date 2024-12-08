package jess.pam.preloveitproggress6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfilActivity extends AppCompatActivity {
    private RecyclerView rvProductList;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    private FirebaseDatabase db;
    private DatabaseReference appDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        // Tombol navigasi ke Edit Profile
        Button btnGoToEditProfile = findViewById(R.id.btnEditProfil);
        btnGoToEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilActivity.this, EditProfileActivity.class);
            startActivity(intent);
        });

        // Tombol navigasi ke Add Product
        ImageButton btnGoToProduk = findViewById(R.id.btnAddPlus);
        btnGoToProduk.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilActivity.this, TambahProdukActivity.class);
            startActivity(intent);
        });

        // Inisialisasi RecyclerView
        rvProductList = findViewById(R.id.rvProductList);
        rvProductList.setLayoutManager(new GridLayoutManager(this, 2));

        // Inisialisasi Firebase
        db = FirebaseDatabase.getInstance();
        appDb = db.getReference("produkku");

        // Inisialisasi list produk dan adapter
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productList);
        productAdapter.setAppDb(appDb); // Set database reference untuk adapter
        rvProductList.setAdapter(productAdapter);

        // Ambil data produk dari Firebase dan tampilkan di RecyclerView
        loadProductsFromFirebase();
    }

    private void loadProductsFromFirebase() {
        // Mengambil data produk dari Firebase
        appDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Kosongkan daftar produk sebelum menambahkan data baru
                productList.clear();

                // Loop untuk mengambil semua data produk dari Firebase
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Product product = snapshot.getValue(Product.class);
                    if (product != null) {
                        productList.add(product); // Menambahkan produk ke dalam list
                    }
                }

                // Menginformasikan adapter bahwa data telah berubah
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Menangani error jika terjadi
                Toast.makeText(ProfilActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}