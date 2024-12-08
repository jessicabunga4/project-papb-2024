package jess.pam.sebelumkonversifragment;

import static android.content.Intent.getIntent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView backArrow = findViewById(R.id.imageView3);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String namaProduk = getIntent().getStringExtra("NAMA_PRODUK");
        String harga = getIntent().getStringExtra("HARGA");
        String favorit = getIntent().getStringExtra("FAVORIT");
        int imageResource = getIntent().getIntExtra("IMAGE", 0);
        TextView namaProdukTextView = findViewById(R.id.textView9);
        TextView hargaTextView = findViewById(R.id.textView12);
        ImageView produkImageView = findViewById(R.id.imageView3);
        namaProdukTextView.setText(namaProduk);
        hargaTextView.setText(harga);
        produkImageView.setImageResource(imageResource);
    }
}
