package ace.mobile.favoriteproductlast4;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Memulai FavoriteFragment
        if (savedInstanceState == null) {
            FavoriteFragment favoriteFragment = new FavoriteFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, favoriteFragment);
            transaction.commit();
        }
    }
}
