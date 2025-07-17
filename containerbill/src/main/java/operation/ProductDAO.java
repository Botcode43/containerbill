package operation;
import java.util.List;
import model.Product;

public interface ProductDAO {
    boolean insertProduct(Product product);
    List<Product> getProductsBySeller(int sellerId);
    boolean updateProductLength(int productId, float newLength);
    boolean updateProductWidth(int productId, float newWidth);
    boolean updateProductHeight(int productId, float newHeight);
    boolean updateProductPrice(int productId, float newPrice);
    boolean deleteProduct(int productId);
}