//package via.mobile.profile;
//
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.Query;
//
//import java.util.List;
//
//@Dao
//public interface FrenDao {
//
//    @Query("SELECT * FROM fren")
//    List<Product> getAllFren(); // Mendapatkan semua entri dalam database
//
//    @Query("SELECT * FROM fren WHERE id = :id")
//    Product getFrenById(int id); // Mendapatkan produk berdasarkan id
//
//    @Query("SELECT * FROM fren WHERE gambar LIKE :gambar LIMIT 1")
//    Product getFrenByPict(String gambar); // Mendapatkan produk berdasarkan gambar
//
//    @Query("SELECT * FROM fren WHERE nama LIKE :nama LIMIT 1")
//    Product getFrenByName(String nama); // Mendapatkan produk berdasarkan nama
//
//    @Query("SELECT * FROM fren WHERE harga LIKE :harga LIMIT 1")
//    Product getFrenByPrice(String harga); // Mendapatkan produk berdasarkan harga
//
//    @Query("SELECT * FROM fren WHERE jenis LIKE :jenis LIMIT 1")
//    Product getFrenByCategory(String jenis); // Mendapatkan produk berdasarkan jenis
//
//    @Insert
//    void insertAll(Product... frens); // Menambahkan produk ke dalam database
//
//    @Delete
//    void delete(Product fren); // Menghapus produk yang dipilih
//}
