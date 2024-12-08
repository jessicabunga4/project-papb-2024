package via.mobile.profile;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;  // Impor FragmentTransaction untuk mengganti fragment

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Mengambil tombol dari layout
        Button btnGoToEditProfile = view.findViewById(R.id.button);

        // Menangani klik tombol untuk membuka EditProfileFragment
        btnGoToEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuka EditProfileFragment dalam MainProfileFragment
                EditProfileFragment editProfileFragment = new EditProfileFragment();

                // Memulai transaksi fragment untuk mengganti tampilan
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.container_main, editProfileFragment);  // Mengganti fragment yang ada di container_main
                transaction.addToBackStack(null);  // Agar pengguna bisa kembali ke MainProfileFragment
                transaction.commit();  // Melakukan transaksi
            }
        });

        return view;
    }
}
