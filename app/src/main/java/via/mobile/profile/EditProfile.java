package via.mobile.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.widget.Button; // Tambahkan import Button
import android.widget.Toast; // Tambahkan import Toast

public class EditProfile extends AppCompatActivity {

    private RecyclerView rvProfile;
    private List<Profile> data;
    private ProfileAdapter profileAdapter;
    private Button btnSave; // Deklarasi tombol

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile1);

        this.rvProfile = this.findViewById(R.id.rvProfile);

        List<Profile> data = new ArrayList<>();
        data.add(new Profile("Gender", "Perempuan"));
        data.add(new Profile("Nomor Telepon", "****11"));
        data.add(new Profile("Email", "j******3@gmail.com"));
        data.add(new Profile("Status", "Kalau bisa dipakai kembali, kenapa tidak?"));
        this.data = data;

        this.profileAdapter = new ProfileAdapter(this, this.data);
        this.rvProfile.setAdapter(this.profileAdapter);
        this.rvProfile.setLayoutManager(
                new LinearLayoutManager(this)
        );

        // Inisialisasi tombol
        this.btnSave = findViewById(R.id.btnSave);
        this.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menampilkan toast
                Toast.makeText(EditProfile.this, "Your Profile was Successfully Saved", Toast.LENGTH_SHORT).show();

                // Pindah ke MainActivity
                Intent intent = new Intent(EditProfile.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
