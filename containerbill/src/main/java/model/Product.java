package model;

public class Product {
    private int productId;
    private int sellerId;
    private float length;
    private float width;
    private float height;
    private float unitPrice;

    // Derived fields
    private float volume;
    private int unitsFit;

    // Constructors
    public Product() {}

    public Product(int productId, int sellerId, float length, float width, float height, float unitPrice) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.length = length;
        this.width = width;
        this.height = height;
        this.unitPrice = unitPrice;
        this.volume = length * width * height;
    }

    // Getters and Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getSellerId() { return sellerId; }
    public void setSellerId(int sellerId) { this.sellerId = sellerId; }

    public float getLength() { return length; }
    public void setLength(float length) {
        this.length = length;
        computeVolume();
    }

    public float getWidth() { return width; }
    public void setWidth(float width) {
        this.width = width;
        computeVolume();
    }

    public float getHeight() { return height; }
    public void setHeight(float height) {
        this.height = height;
        computeVolume();
    }

    public float getUnitPrice() { return unitPrice; }
    public void setUnitPrice(float unitPrice) { this.unitPrice = unitPrice; }

    public float getVolume() { return volume; }
    public void setVolume(float volume) { this.volume = volume; }

    public int getUnitsFit() { return unitsFit; }
    public void setUnitsFit(int unitsFit) { this.unitsFit = unitsFit; }

    private void computeVolume() {
        this.volume = this.length * this.width * this.height;
    }
}
