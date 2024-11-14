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