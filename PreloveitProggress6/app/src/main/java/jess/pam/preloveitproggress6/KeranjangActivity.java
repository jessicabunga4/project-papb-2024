package jess.pam.preloveitproggress6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class KeranjangActivity extends AppCompatActivity {
    private RecyclerView rvKeranjang;
    private TextView tvSubTotal, tvTotal;
    private AdapterKeranjang adapterKeranjang;
    private List<Produk> keranjang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);

        rvKeranjang = findViewById(R.id.rvKeranjang);
        tvSubTotal = findViewById(R.id.tvSubTotal);
        tvTotal = findViewById(R.id.tvTotal);

        keranjang = new ArrayList<>();
        adapterKeranjang = new AdapterKeranjang(this, keranjang, this::updateSubtotal);
        rvKeranjang.setAdapter(adapterKeranjang);
        rvKeranjang.setLayoutManager(new LinearLayoutManager(this));

        muatDataKeranjang();

        ImageView btHome = findViewById(R.id.btHome);
        btHome.setOnClickListener(v -> {
            Intent intent = new Intent(KeranjangActivity.this, MainActivity.class);
            startActivity(intent);
        });

        ImageView ivFavM = findViewById(R.id.ivFavM);
        ivFavM.setOnClickListener(v -> {
            Intent intent = new Intent(KeranjangActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void muatDataKeranjang() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://prelovitprogress6-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference keranjangRef = database.getReference("keranjang");

        keranjangRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                keranjang.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    Produk produk = itemSnapshot.getValue(Produk.class);
                    if (produk != null) {
                        keranjang.add(produk);
                    }
                }

                adapterKeranjang.notifyDataSetChanged();
                updateSubtotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(KeranjangActivity.this, "Gagal memuat data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateSubtotal() {
        int subtotal = 0;

        for (Produk produk : keranjang) {
            String hargaString = produk.getHarga();
            String cleanedHarga = hargaString.replace("Rp.", "").replace(".", "");

            try {
                int harga = Integer.parseInt(cleanedHarga);
                subtotal += harga;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        tvSubTotal.setText("Rp " + subtotal);

        int total = subtotal + 1000;
        tvTotal.setText("Rp " + total);
    }

    private void onItemRemoved() {
        updateSubtotal();
        adapterKeranjang.notifyDataSetChanged();
    }
}
