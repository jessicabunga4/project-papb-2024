package via.mobile.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter {

    private final Context ctx;
    private final List<Profile> data;

    public ProfileAdapter(Context ctx, List<Profile> data) {
        this.ctx = ctx;
        this.data = data;
    }

    public class ProfileVH extends ViewHolder {

        private final TextView tvJudul;
        private final TextView tvIsi;

        public ProfileVH(@NonNull View itemView) {
            super(itemView);
            this.tvJudul = itemView.findViewById(R.id.tvJudul);
            this.tvIsi = itemView.findViewById(R.id.tvIsi);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(this.ctx)
                .inflate(R.layout.activity_rowview, parent, false);
        ViewHolder vh = new ProfileVH(rowView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Profile p = this.data.get(position);
        ProfileVH vh = (ProfileVH) holder;
        vh.tvJudul.setText(p.judul);
        vh.tvIsi.setText(p.isi);
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}

