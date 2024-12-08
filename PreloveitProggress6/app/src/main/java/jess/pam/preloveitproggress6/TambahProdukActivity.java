package jess.pam.preloveitproggress6;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TambahProdukActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FirebaseURL = "https://prelovitprogress6-default-rtdb.asia-southeast1.firebasedatabase.app/";
    private static final int PICK_IMAGE_REQUEST = 1; // Kode request untuk memilih gambar

    private Button btnAddProduk;
    private Button btnPickImage;
    private Button btnBack;
    private EditText etNama;
    private EditText etHarga;
    private EditText etJenis;
    private ImageView ivGambar;
    private Uri selectedImageUri;

    private FirebaseDatabase db;
    private DatabaseReference appDb;

    private RecyclerView rvProductList;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_produk); // Layout XML untuk AddProduct

        // Inisialisasi View
        this.btnAddProduk = findViewById(R.id.btAddProduk);
        this.btnPickImage = findViewById(R.id.btnPickImage);
        this.btnBack = findViewById(R.id.btnBack);  // Inisialisasi tombol Back
        this.ivGambar = findViewById(R.id.ivGambar);
        this.etNama = findViewById(R.id.etNama);
        this.etHarga = findViewById(R.id.etHarga);
        this.etJenis = findViewById(R.id.etJenis);

        // Inisialisasi Firebase
        this.db = FirebaseDatabase.getInstance(FirebaseURL);
        this.appDb = this.db.getReference("produkku"); // Reference untuk produk

        // Inisialisasi RecyclerView
        this.rvProductList = findViewById(R.id.rvProductList);
        rvProductList.setLayoutManager(new GridLayoutManager(this, 2));
        this.productList = new ArrayList<>();
        this.productAdapter = new ProductAdapter(this, productList);
        this.rvProductList.setAdapter(productAdapter);

        // Event Listener
        this.btnPickImage.setOnClickListener(v -> openGallery());
        this.btnAddProduk.setOnClickListener(this);
        this.btnBack.setOnClickListener(v -> {
            // Aksi klik tombol Back untuk kembali ke MainActivity
            Intent intent = new Intent(TambahProdukActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Muat data produk dari Firebase
        loadProductsFromFirebase();
    }

    // Fungsi untuk membuka galeri
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Menangani hasil Intent untuk memilih gambar
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData(); // Simpan URI gambar yang dipilih
            ivGambar.setImageURI(selectedImageUri); // Tampilkan gambar di ImageView
        }
    }

    // Validasi input dan tambah produk ke Firebase
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btAddProduk) {
            // Validasi input
            String nama = etNama.getText().toString().trim();
            String harga = etHarga.getText().toString().trim();
            String jenis = etJenis.getText().toString().trim();

            if (nama.isEmpty() || harga.isEmpty() || jenis.isEmpty() || selectedImageUri == null) {
                Toast.makeText(this, "Harap lengkapi semua data dan pilih gambar!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Tambah produk ke database
            String id = this.appDb.push().getKey();
            Product product = new Product();
            product.setId(id); // Set ID unik produk
            product.setNama(nama);
            product.setHarga(Double.parseDouble(harga));
            product.setJenis(jenis);
            product.setGambar(selectedImageUri.toString()); // Simpan URI gambar

            // Menambahkan produk ke Firebase
            this.appDb.child(id).setValue(product).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(TambahProdukActivity.this, "Produk berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
                        resetInputFields(); // Reset form input setelah produk ditambahkan
                        loadProductsFromFirebase(); // Refresh daftar produk
                    } else {
                        Toast.makeText(TambahProdukActivity.this, "Gagal menambahkan produk. Coba lagi.", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(TambahProdukActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    // Fungsi untuk mereset input field setelah produk ditambahkan
    private void resetInputFields() {
        etNama.setText("");
        etHarga.setText("");
        etJenis.setText("");
        ivGambar.setImageResource(0); // Hapus gambar yang ada
        selectedImageUri = null; // Reset URI gambar
    }

    // Fungsi untuk memuat data produk dari Firebase
    private void loadProductsFromFirebase() {
        appDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Product product = dataSnapshot.getValue(Product.class);
                    if (product != null) {
                        productList.add(product); // Tambahkan produk ke dalam list
                    }
                }
                productAdapter.notifyDataSetChanged(); // Beritahu adapter untuk memperbarui data
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TambahProdukActivity.this, "Gagal memuat data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}