package com.example.evgen.flowersstories.Cart;

import android.content.Context;

import com.example.evgen.flowersstories.Cart.DB.DBHelper;

import java.util.ArrayList;
import java.util.List;
public class CartController extends CartActivity {

    static List<CartModelBuy> buy = new ArrayList<>();

    public void addItemPlusCheckBoxBuy(CartModelBuy cartModelBuy, Context context) {
        DBHelper db = new DBHelper(context);
        int quantity = Integer.parseInt(db.getQuantityCount(cartModelBuy.getInvNumber()));

        CartModelBuy cartModelBuyDB = new CartModelBuy(cartModelBuy.getInvNumber(), cartModelBuy.getCartItem(), String.valueOf(quantity),
                cartModelBuy.getCartPrice(), true);

        buy.add(cartModelBuyDB);
        textBuyPrice.setText(String.valueOf(summPriceBuy())+ " р.");
    }

    public void buyDelete() {

        buy.clear();
        System.out.println("buyClear: ");
    }

    public void removeItemMinusCheckBoxBuy(CartModelBuy cartModelBuy) {

        int priceSummMinusCHK = 0;

        int summPrice = summPriceBuy();

        for (int i = 0; i < buy.size(); i++) {

            if (buy.get(i).getInvNumber().equals(cartModelBuy.getInvNumber())) {
                priceSummMinusCHK += Integer.parseInt(buy.get(i).getCartPrice()) * Integer.parseInt(buy.get(i).getCartQuantity());
                System.out.println("buyDelete " + buy.get(i).getInvNumber() + " " + buy.get(i).getCartItem() + " quantity "
                        + buy.get(i).getCartQuantity() + " price " + buy.get(i).getCartPrice());

                buy.remove(i);
            }
        }
        textBuyPrice.setText(String.valueOf(summPrice - priceSummMinusCHK) + " р.");
    }

    public int summPriceBuy() {
        int priceSummBuy = 0;

        for (int w = 0; w < buy.size(); w++) {
            priceSummBuy += Integer.parseInt(buy.get(w).getCartPrice()) * Integer.parseInt(buy.get(w).getCartQuantity());

            System.out.println(" priceSummBuy " + priceSummBuy);
        }
        return priceSummBuy;
    }

    public void buyItemQuantityPlus(String invNumber) {
        int plusOneQuantity = 0;

        for (int i = 0; i < buy.size(); i++) {
            if (buy.get(i).getInvNumber().equals(invNumber)) {

                plusOneQuantity = Integer.parseInt(buy.get(i).getCartQuantity()) + 1;
                buy.get(i).setCartQuantity(String.valueOf(plusOneQuantity));
            }
        }
        textBuyPrice.setText(String.valueOf(summPriceBuy()) + " р.");
    }

    public void buyItemQuantityMinus(String invNumber) {

        int plusOneQuantity = 0;

        for (int i = 0; i < buy.size(); i++) {
            if (buy.get(i).getInvNumber().equals(invNumber)) {

                plusOneQuantity = Integer.parseInt(buy.get(i).getCartQuantity()) - 1;
                buy.get(i).setCartQuantity(String.valueOf(plusOneQuantity));
            }
            textBuyPrice.setText(String.valueOf(summPriceBuy())+ " р.");
        }
    }

    public void buyDeleteOfBD(String invNumber) {

        for (int i = 0; i < buy.size(); i++) {
            if (buy.get(i).getInvNumber().equals(invNumber)) {
                buy.remove(i);
            }
            textBuyPrice.setText(String.valueOf(summPriceBuy()));
        }
    }
}