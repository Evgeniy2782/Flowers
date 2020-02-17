package com.example.evgen.flowersstories.Cart;

public class CartModelBuy {

    private String invNumber;
    private String cartItem;
    private String cartQuantity;
    private String cartPrice;
    private boolean cartCheckBox;

    public CartModelBuy(String invNumber, String cartItem, String cartQuantity, String cartPrice, boolean cartCheckBox) {
        this.invNumber = invNumber;
        this.cartItem = cartItem;
        this.cartQuantity = cartQuantity;
        this.cartPrice = cartPrice;
        this.cartCheckBox = cartCheckBox;
    }

    public String getInvNumber() {
        return invNumber;
    }

    public String getCartItem() {
        return cartItem;
    }

    public String getCartQuantity() {
        return cartQuantity;
    }

    public String getCartPrice() {
        return cartPrice;
    }

    public boolean isCartCheckBox() {
        return cartCheckBox;
    }

    public void setCartQuantity(String cartQuantity) {
        this.cartQuantity = cartQuantity;
    }
}
