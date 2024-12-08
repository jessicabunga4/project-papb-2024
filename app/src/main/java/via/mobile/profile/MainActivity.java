<<<<<<< HEAD
package via.mobile.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvProductList;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    private FirebaseDatabase db;
    private DatabaseReference appDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tombol navigasi ke Edit Profile
        Button btnGoToEditProfile = findViewById(R.id.btnEditProfil);
        btnGoToEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditProfile.class);
            startActivity(intent);
        });

        // Tombol navigasi ke Add Product
        ImageButton btnGoToProduk = findViewById(R.id.btnAddPlus);
        btnGoToProduk.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddProduct.class);
            startActivity(intent);
        });

        // Inisialisasi RecyclerView
        rvProductList = findViewById(R.id.rvProductList);
        rvProductList.setLayoutManager(new GridLayoutManager(this, 2));

        // Inisialisasi Firebase
        db = FirebaseDatabase.getInstance();
        appDb = db.getReference("products");

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
                Toast.makeText(MainActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
=======
package via.mobile.profile;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.fm = getSupportFragmentManager();

        // Mengecek apakah activity sedang dijalankan pertama kali atau tidak
        if (savedInstanceState == null) {
            // Jika belum ada fragment, tambahkan MainProfileFragment
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.container_main, new MainProfileFragment());
            ft.commit();
        }
    }
}
>>>>>>> 35dac96 (Sesudah Konversi Fragment)
