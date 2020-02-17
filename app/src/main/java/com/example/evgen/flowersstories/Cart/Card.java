package com.example.evgen.flowersstories.Cart;

public class Card {

    private String invNumberCard;
    private String cartImage;
    private String cartItem;
    private String cartPrice;
    private String cartQuantity;
    private String cartPriceStart;
    private boolean isSelectedcheckBox;

    public Card(String invNumberCard, String cartImage, String cartItem, String cartPrice, String cartQuantity, String cartPriceStart, boolean isSelectedcheckBox) {

        this.invNumberCard = invNumberCard;
        this.cartImage = cartImage;
        this.cartItem = cartItem;
        this.cartPrice = cartPrice;
        this.cartQuantity = cartQuantity;
        this.cartPriceStart = cartPriceStart;
        this.isSelectedcheckBox = isSelectedcheckBox;
    }

    public void setSelectedcheckBox(boolean selectedcheckBox) {
        isSelectedcheckBox = selectedcheckBox;
    }

    public boolean isSelectedcheckBox() {
        return isSelectedcheckBox;
    }

    public String getInvNumberCard() {
        return invNumberCard;
    }

    public String getCartPriceStart() {
        return cartPriceStart;
    }

    public String getInvNumber() {
        return invNumberCard;
    }

    public String getCartItem() {
        return cartItem;
    }

    public String getCartPrice() {
        return cartPrice;
    }

    public String getCartImage() {
        return cartImage;
    }
    public String getCartQuantity() {
        return cartQuantity;
    }
}
