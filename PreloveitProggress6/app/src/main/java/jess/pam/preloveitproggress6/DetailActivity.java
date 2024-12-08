package jess.pam.preloveitproggress6;

import static android.content.Intent.getIntent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
