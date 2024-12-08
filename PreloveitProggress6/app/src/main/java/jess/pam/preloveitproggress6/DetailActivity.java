package jess.pam.preloveitproggress6;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    TextView deskripsi;
    TextView nama;
    TextView brand;
    TextView minus;
    ImageView gambar;
    TextView judul;
    TextView harga;
    TextView fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView backArrow = findViewById(R.id.imageView3);
        Button btKeranjang = findViewById(R.id.btKeranjang);

        gambar = findViewById(R.id.ivImageDetail);
        judul = findViewById(R.id.tvDetailJudul);
        harga = findViewById(R.id.tvDetailPrice);
        fav = findViewById(R.id.tvDetailFav);
        deskripsi = findViewById(R.id.tvDetailDesc);
        nama = findViewById(R.id.tvDetailName);
        brand = findViewById(R.id.tvDetailBrand);
        minus = findViewById(R.id.tvDetailMinus);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String imageName = bundle.getString("IMAGE");
            int imageResId = getResources().getIdentifier(imageName, "drawable", getPackageName());
            gambar.setImageResource(imageResId);

            judul.setText(bundle.getString("JUDUL"));
            harga.setText(bundle.getString("HARGA"));
            fav.setText(bundle.getString("FAVORIT"));
            deskripsi.setText(bundle.getString("DESC"));
            nama.setText(bundle.getString("NAMA"));
            brand.setText(bundle.getString("BRAND"));
            minus.setText(bundle.getString("MINUS"));
        }

        // Tombol Tambah ke Keranjang
        btKeranjang.setOnClickListener(v -> {
            Produk produk = new Produk(
                    getIntent().getStringExtra("IMAGE"),
                    getIntent().getStringExtra("JUDUL"),
                    getIntent().getStringExtra("HARGA"),
                    getIntent().getStringExtra("FAVORIT"),
                    getIntent().getStringExtra("DESC"),
                    getIntent().getStringExtra("NAMA"),
                    getIntent().getStringExtra("BRAND"),
                    getIntent().getStringExtra("MINUS")
            );
            tambahkanKeKeranjang(produk);
        });

        // Tombol Kembali (Back)
        backArrow.setOnClickListener(v -> finish());
    }

    // Pindahkan metode ini keluar dari onCreate
    private void tambahkanKeKeranjang(Produk produk) {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://prelovitprogress6-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference keranjangRef = database.getReference("keranjang");

        // Buat key unik untuk setiap item keranjang
        String keranjangId = keranjangRef.push().getKey();
        if (keranjangId != null) {
            produk.setIdKeranjang(keranjangId); // Menyimpan ID keranjang di produk
            keranjangRef.child(keranjangId).setValue(produk)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Produk ditambahkan ke keranjang!", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Gagal menambahkan ke keranjang: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        }
    }
}
