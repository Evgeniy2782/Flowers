package com.example.evgen.flowersstories.Cart.DB;

public class CartModel {
    private int id;
    private String nameItem;
    private int position;
    private int quantity;
    private String image;
    private String in_Stock;
    private String invNumber;

    public CartModel(){

    }

//    public CartModel(String image, String nameItem, int price, int quantity, String in_Stock, String invNumber) {

    public CartModel(String invNumber, int quantity, int position) {

        this.nameItem = nameItem;
        this.position = position;
        this.quantity = quantity;
        this.image = image;
        this.invNumber = invNumber;
        this.in_Stock = in_Stock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setIn_Stock(String in_Stock) {
        this.in_Stock = in_Stock;
    }

    public void setInv_number(String inv_number) {
        this.invNumber = inv_number;
    }

    public String getNameItem() {
        return nameItem;
    }

    public int getPosition() {
        return position;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getIn_Stock() {
        return in_Stock;
    }

    public String getInv_number() {
        return invNumber;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

