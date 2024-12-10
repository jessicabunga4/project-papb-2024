package ace.mobile.favoriteproductlast4;

public class FavoriteProduct {
    private int imageResId;
    private String productName;
    private String description;
    private String price;

    public FavoriteProduct(int imageResId, String productName, String description, String price) {
        this.imageResId = imageResId;
        this.productName = productName;
        this.description = description;
        this.price = price;
    }

    // Getter and Setter methods
    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
