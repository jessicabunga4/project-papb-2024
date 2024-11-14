package via.mobile.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Mengambil tombol dari layout
        Button btnGoToEditProfile = findViewById(R.id.button);

        // Menangani klik tombol
        btnGoToEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat Intent untuk berpindah ke Activity Edit Profile
                Intent intent = new Intent(MainActivity.this, EditProfile.class);
                startActivity(intent);
            }
        });
    }
}