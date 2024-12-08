package jess.pam.preloveitproggress6;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

public class Produk {
    private String image;
    private String judul, harga, favorit, deskripsi, nama, brand, minus, idKeranjang, idFavorit;

    public Produk() {
    }

    public Produk(String image, String judul, String harga, String favorit, String deskripsi, String nama, String brand, String minus) {
        this.image = image;
        this.judul = judul;
        this.harga = harga;
        this.favorit = favorit;
        this.deskripsi = deskripsi;
        this.nama = nama;
        this.brand = brand;
        this.minus = minus;
    }


    public String getImage() {
        return image;
    }


    public String getJudul() {
        return judul;
    }

    public String getHarga() {
        return harga;
    }

    public String getFavorit() {
        return favorit;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getNama() {
        return nama;
    }

    public String getBrand() {
        return brand;
    }

    public String getMinus() {
        return minus;
    }

    public int getImageResource(Context context) {
        return context.getResources().getIdentifier(image, "drawable", context.getPackageName());
    }

    public String getIdKeranjang() {
        return idKeranjang;
    }

    public void setIdKeranjang(String idKeranjang) {
        this.idKeranjang = idKeranjang;
    }

    public String getIdFavorit() {
        return idFavorit;
    }

    public void setIdFavorit(String idFavorit) {
        this.idFavorit = idFavorit;
    }
}
