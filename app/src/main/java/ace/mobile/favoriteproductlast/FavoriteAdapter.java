package ace.mobile.favoriteproductlast;


import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private List<FavoriteProduct> productList;
    private OnLikeClickListener onLikeClickListener;

    public FavoriteAdapter(List<FavoriteProduct> productList, OnLikeClickListener onLikeClickListener) {
        this.productList = productList;
        this.onLikeClickListener = onLikeClickListener;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        FavoriteProduct favoriteProduct = productList.get(position);

        holder.tvProductName.setText(favoriteProduct.getProductName());
        holder.tvDescription.setText(favoriteProduct.getDescription());
        holder.tvPrice.setText(favoriteProduct.getPrice());

        // Load image from drawable resources
        holder.ivProduct.setImageResource(favoriteProduct.getImageResId());

        // Set the "like" button click listener
        holder.btnLike.setOnClickListener(v -> {
            onLikeClickListener.onLikeClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProduct;
        TextView tvProductName, tvDescription, tvPrice;
        ImageButton btnLike;

        public FavoriteViewHolder(View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.ivProduct);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            btnLike = itemView.findViewById(R.id.btnLike);
        }
    }

    public interface OnLikeClickListener {
        void onLikeClick(int position);
    }
}
