package operation;

import java.util.List;
import model.ShippingCost;

public interface ShippingDAO {
    boolean insertShippingCost(ShippingCost shipping);
    boolean deleteShippingCost(int shippingId);
    boolean updateOceanFreight(int shippingId, float value);
    boolean updateRentPerDay(int shippingId, float value);
    boolean updateNumDays(int shippingId, int days);
    boolean updatePortHandling(int shippingId, float value);
    boolean updateInlandTransport(int shippingId, float value);
    boolean updateInsurance(int shippingId, float value);
    List<ShippingCost> getShippingBySeller(int sellerId);
}
