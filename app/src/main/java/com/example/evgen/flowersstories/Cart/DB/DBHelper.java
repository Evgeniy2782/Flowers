package com.example.evgen.flowersstories.Cart.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static List<CartModel> cartList;

    private static final String cart = "cart.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_CART = "cart";

    private static final String KEY_ID = "_id";
    private static final String KEY_POSITION = "position";
    private static final String QUANTITY = "quantity";
    private static final String INV_NUMBER = "invNumber";

    // private static final String NAME_ITEM = "nameItem";
    // private static final String IMAGE = "image";
    // private static final String IN_STOCK = "inStock";

    public DBHelper(Context context) {
        super(context, cart, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + INV_NUMBER + " TEXT,"
                + QUANTITY + " INTEGER,"
                + KEY_POSITION + " INTEGER"
           //     + IMAGE +" TEXT,"
           //     + NAME_ITEM + " TEXT,
            //    + IN_STOCK +" TEXT,"

                +")";
        db.execSQL(CREATE_CART_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    public void addCart(CartModel cart, String invNumber) {

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM " + TABLE_CART;
        Cursor cursor = db.rawQuery(sql, null);
        String sqlAddCart = "";

        cartList = new ArrayList<>();

        ContentValues newValues = new ContentValues();

        if ((cursor != null)&&(cursor.getCount() > 0)) {
            cursor.moveToFirst();
            do {
                CartModel cart1 = new CartModel();
                cart1.setInv_number(cursor.getString(1));
                cart1.setQuantity(Integer.parseInt(cursor.getString(2)));
                cart1.setPosition(3);
                cartList.add(cart1);
            }
            while (cursor.moveToNext());
            cursor.close();

            for (CartModel cart1 : cartList) {

                sqlAddCart = cart1.getInv_number();

                if (sqlAddCart.equals(invNumber)) {

                    int quantityInvNumber = cart1.getQuantity() + 1;

                    System.out.println("QUANTITY " + quantityInvNumber);

                    ContentValues updatedQuantity = new ContentValues();
                    updatedQuantity.put(QUANTITY, quantityInvNumber);

                    db.update(TABLE_CART, updatedQuantity, INV_NUMBER + "=?", new String[]{sqlAddCart});
                    db.close();

                    return;
                  }
                } if (!sqlAddCart.equals(invNumber)){

                    newValues.put(KEY_POSITION, cart.getPosition());
                    newValues.put(QUANTITY, cart.getQuantity());
                    newValues.put(INV_NUMBER, cart.getInv_number());

                    db.insert(TABLE_CART, null, newValues);
                    db.close();
                    }
        }else{

                newValues.put(INV_NUMBER, cart.getInv_number());
                newValues.put(QUANTITY, cart.getQuantity());
                newValues.put(KEY_POSITION, cart.getPosition());

                db.insert(TABLE_CART, null, newValues);
                db.close();
        }
    }

    public List<CartModel> getAllCart() {
        cartList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_CART;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                CartModel cart = new CartModel();

                cart.setId(Integer.parseInt(cursor.getString(0)));
                cart.setInv_number(cursor.getString(1));
                cart.setQuantity(Integer.parseInt(cursor.getString(2)));
                cart.setPosition(Integer.parseInt(cursor.getString(3)));

                cartList.add(cart);
             //   Arrays.sort(new List[]{cartList});

                cursor.moveToNext();
    }
        } finally {
            cursor.close();
        }
        return cartList;
    }
    public String getQuantityCount(String invNumber) {

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM " + TABLE_CART;
        Cursor cursor = db.rawQuery(sql, null);
        String sqlAddCart = "";
        int quantityInvNumber = 0;
        String i = null;

        cartList = new ArrayList<>();

        if ((cursor != null)&&(cursor.getCount() > 0)) {
            cursor.moveToFirst();
            do {
                CartModel cart1 = new CartModel();
                cart1.setInv_number(cursor.getString(1));
                cart1.setQuantity(Integer.parseInt(cursor.getString(2)));
                cart1.setPosition(3);
                cartList.add(cart1);
            }
            while (cursor.moveToNext());
            cursor.close();

            for (CartModel cart1 : cartList) {

                sqlAddCart = cart1.getInv_number();

                if (sqlAddCart.equals(invNumber)) {

                    quantityInvNumber = cart1.getQuantity();

                  i = Integer.toString(quantityInvNumber);

                    db.close();
                }
            }
        }
        return i;
    }

    public void deleteCart(String invNumber) {

        SQLiteDatabase db = this.getReadableDatabase();

        for (int i = 0; i < db.getMaximumSize() ; i++) {
            db.delete(TABLE_CART, INV_NUMBER + "=?", new String[]{String.valueOf(invNumber)});
            return;
        }
        db.close();
    }

    public void minusCart(String invNumber){

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM " + TABLE_CART;
        Cursor cursor = db.rawQuery(sql, null);
        String sqlAddCart = "";

        cartList = new ArrayList<>();

        if ((cursor != null)&&(cursor.getCount() > 0)) {
            cursor.moveToFirst();
            do {
                CartModel cart1 = new CartModel();
                cart1.setInv_number(cursor.getString(1));
                cart1.setQuantity(Integer.parseInt(cursor.getString(2)));
                cart1.setPosition(3);
                cartList.add(cart1);
            }
            while (cursor.moveToNext());
            cursor.close();

            for (CartModel cart1 : cartList) {

                sqlAddCart = cart1.getInv_number();

                if (sqlAddCart.equals(invNumber)) {

                    int quantityInvNumber = cart1.getQuantity() - 1;

                    if(quantityInvNumber >= 0) {
                        System.out.println("Minus " + quantityInvNumber);

                        ContentValues updatedQuantity = new ContentValues();
                        updatedQuantity.put(QUANTITY, quantityInvNumber);

                        db.update(TABLE_CART, updatedQuantity, INV_NUMBER + "=?", new String[]{sqlAddCart});
                        db.close();

                        return;
                    }
                }
            }
        }
    }

    public void plusCart(String invNumber) {

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM " + TABLE_CART;
        Cursor cursor = db.rawQuery(sql, null);
        String sqlAddCart = "";

        cartList = new ArrayList<>();

        if ((cursor != null)&&(cursor.getCount() > 0)) {
            cursor.moveToFirst();
            do {
                CartModel cart1 = new CartModel();
                cart1.setInv_number(cursor.getString(1));
                cart1.setQuantity(Integer.parseInt(cursor.getString(2)));
                cart1.setPosition(3);
                cartList.add(cart1);
            }
            while (cursor.moveToNext());
            cursor.close();

            for (CartModel cart1 : cartList) {

                sqlAddCart = cart1.getInv_number();

                if (sqlAddCart.equals(invNumber)) {

                    int quantityInvNumber = cart1.getQuantity() + 1;

                    System.out.println("Plus " + quantityInvNumber);

                    ContentValues updatedQuantity = new ContentValues();
                    updatedQuantity.put(QUANTITY, quantityInvNumber);

                    db.update(TABLE_CART, updatedQuantity, INV_NUMBER + "=?", new String[]{sqlAddCart});
                    db.close();

                    return;
                }
            }
        }
    }
}
