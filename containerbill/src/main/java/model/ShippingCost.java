package model;

public class ShippingCost {
    private int shippingId;
    private int sellerId;
    private float oceanFreight;
    private float rentPerDay;
    private int numDays;
    private float portHandling;
    private float inlandTransport;
    private float insurance;
    private float totalShippingCost;

    // Constructors
    public ShippingCost() {}

    public ShippingCost(int shippingId, int sellerId, float oceanFreight, float rentPerDay, int numDays,
                        float portHandling, float inlandTransport, float insurance, float totalShippingCost) {
        this.shippingId = shippingId;
        this.sellerId = sellerId;
        this.oceanFreight = oceanFreight;
        this.rentPerDay = rentPerDay;
        this.numDays = numDays;
        this.portHandling = portHandling;
        this.inlandTransport = inlandTransport;
        this.insurance = insurance;
        this.totalShippingCost = totalShippingCost;
    }

    // Getters and Setters
    public int getShippingId() { return shippingId; }
    public void setShippingId(int shippingId) { this.shippingId = shippingId; }

    public int getSellerId() { return sellerId; }
    public void setSellerId(int sellerId) { this.sellerId = sellerId; }

    public float getOceanFreight() { return oceanFreight; }
    public void setOceanFreight(float oceanFreight) { this.oceanFreight = oceanFreight; }

    public float getRentPerDay() { return rentPerDay; }
    public void setRentPerDay(float rentPerDay) { this.rentPerDay = rentPerDay; }

    public int getNumDays() { return numDays; }
    public void setNumDays(int numDays) { this.numDays = numDays; }

    public float getPortHandling() { return portHandling; }
    public void setPortHandling(float portHandling) { this.portHandling = portHandling; }

    public float getInlandTransport() { return inlandTransport; }
    public void setInlandTransport(float inlandTransport) { this.inlandTransport = inlandTransport; }

    public float getInsurance() { return insurance; }
    public void setInsurance(float insurance) { this.insurance = insurance; }

    public float getTotalShippingCost() { return totalShippingCost; }
    public void setTotalShippingCost(float totalShippingCost) { this.totalShippingCost = totalShippingCost; }
}
