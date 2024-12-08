package via.mobile.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EditProfileFragment extends Fragment {

    private RecyclerView rvProfile;
    private List<Profile> data;
    private ProfileAdapter profileAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Menginflate layout untuk fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        // Menginisialisasi RecyclerView
        rvProfile = view.findViewById(R.id.rvProfile);

        // Mengisi data list
        data = new ArrayList<>();
        data.add(new Profile("Gender", "Perempuan"));
        data.add(new Profile("Nomor Telepon", "****11"));
        data.add(new Profile("Email", "j******3@gmail.com"));
        data.add(new Profile("Status", "Kalau bisa dipakai kembali, kenapa tidak?"));

        // Mengatur RecyclerView dan Adapter
        profileAdapter = new ProfileAdapter(requireContext(), data);
        rvProfile.setAdapter(profileAdapter);
        rvProfile.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Menginisialisasi tombol simpan perubahan (anggap ini adalah tombol yang sama untuk menyimpan perubahan)
        Button btnSaveChanges = view.findViewById(R.id.button);
        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menampilkan pesan Toast setelah perubahan disimpan
                Toast.makeText(getActivity(), "Your Profile was Successfully Saved", Toast.LENGTH_SHORT).show();

                // Beralih ke MainProfileFragment setelah Toast
                // Membuat instance dari MainProfileFragment
                MainProfileFragment mainProfileFragment = new MainProfileFragment();

                // Memulai transaksi Fragment
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container_main, mainProfileFragment);  // Ganti dengan MainProfileFragment
                transaction.addToBackStack(null);  // Menambahkan ke back stack agar pengguna bisa menekan tombol back
                transaction.commit();
            }
        });

        return view;
    }
}
