package operation;
import model.Seller;

public interface SellerOperations {
    boolean registerSeller(Seller s);
    Seller loginSeller(String email, String password);
    boolean updateSellerName(int sellerId, String name);
    boolean updateSellerEmail(int sellerId, String email);
    boolean updateSellerPassword(int sellerId, String password);
    boolean deleteSeller(int sellerId);
    
}


