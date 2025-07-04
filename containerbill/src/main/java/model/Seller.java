package model;

import imp.sellerOperationImp;

public class Seller {
    private int sellerId;
    private String name;
    private String email;
    private String password;

    
    public Seller() {}

    public Seller(int sellerId, String name, String email, String password) {
        this.sellerId = sellerId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

  

    public int getSellerId() {
		return sellerId;
	}
    
    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
	public void insertSeller(Seller Seller) {
	    new sellerOperationImp().insertSeller(Seller);
	    System.out.println("Returned from operationsimp.insert()");
	}
	
}


