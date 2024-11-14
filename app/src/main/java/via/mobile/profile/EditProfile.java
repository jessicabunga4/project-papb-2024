package via.mobile.profile;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EditProfile extends AppCompatActivity {

    private RecyclerView rvProfile;
    private List<Profile> data;
    private ProfileAdapter profileAdapter;

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
    }
}