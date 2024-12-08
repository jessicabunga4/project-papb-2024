package jess.pam.preloveitproggress6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
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

public class FavoritActivity extends AppCompatActivity {
    private RecyclerView rvFavorit;
    private AdapterFavorit adapterFavorit;
    private List<Produk> favoritList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);

        rvFavorit = findViewById(R.id.rvFavorit);
        favoritList = new ArrayList<>();
        adapterFavorit = new AdapterFavorit(this, favoritList, this::onItemRemoved);
        rvFavorit.setAdapter(adapterFavorit);
        rvFavorit.setLayoutManager(new LinearLayoutManager(this));

        muatDataFavorit();  // Fungsi untuk memuat data produk favorit dari Firebase

        ImageView btHome2 = findViewById(R.id.btHome2);
        btHome2.setOnClickListener(v -> {
            Intent intent = new Intent(FavoritActivity.this, MainActivity.class);
            startActivity(intent);
        });

        ImageView ivKeranjangM = findViewById(R.id.ivKeranjangM);
        ivKeranjangM.setOnClickListener(v -> {
            Intent intent = new Intent(FavoritActivity.this, KeranjangActivity.class);
            startActivity(intent);
        });
    }

    private void muatDataFavorit() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://prelovitprogress6-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference favoritRef = database.getReference("favorit");

        favoritRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                favoritList.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    Produk produk = itemSnapshot.getValue(Produk.class);
                    if (produk != null) {
                        favoritList.add(produk);
                    }
                }

                adapterFavorit.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FavoritActivity.this, "Gagal memuat data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onItemRemoved() {
        adapterFavorit.notifyDataSetChanged();
    }
}

