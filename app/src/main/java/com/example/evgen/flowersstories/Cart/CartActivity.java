package com.example.evgen.flowersstories.Cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evgen.flowersstories.Cart.DB.DBHelper;
import com.example.evgen.flowersstories.Draweble.NavigationDrawer;
import com.example.evgen.flowersstories.MainActivity;
import com.example.evgen.flowersstories.R;
import com.example.evgen.flowersstories.ViewPagerItem.ProductModel;
import com.example.evgen.flowersstories.checkout.Checkout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.evgen.flowersstories.RetrofitClient.RetrofitClient.getApiService;

public class CartActivity extends NavigationDrawer {

    DBHelper db = new DBHelper(this);
    private static ProductModel list;

    String numberDelete;
    static LinearLayout linearLayout;
    public RecyclerView recyclerView;
    public Button buttonBuy;
    static TextView textBuyPrice;

    List<Card> mNameText;
    static CartAdapter cartAdapter;

    private static final String TAG = "MyRecycler";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_recyclerview_item);
        linearLayout = findViewById(R.id.cartlinerlayout);
        buttonBuy = findViewById(R.id.checkout);
        textBuyPrice = findViewById(R.id.onTextPrice);

        CartAdapter.countLinerLayoutOffandON();
//        button = findViewById(R.id.onBuy);
        //       button.setOnClickListener(new CartAdapter.ViewHolderCart().);

        adap();
        initializeData();
        db.getAllCart();

        //    System.out.println("cartadapter: "+cartAdapter.linerLayoutOnInCart);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        if (toolbar != null) {
//
//            setSupportActionBar(toolbar);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//            navigationDrawer(toolbar);
        // }
    }

    private void initializeData() {

        mNameText = new ArrayList<>();

        for (int i = 0; i < db.getAllCart().size(); i++) {

            Call<ProductModel> call = getApiService().getInvFlower(db.getAllCart().get(i).getInv_number());
            final Integer quantity = db.getAllCart().get(i).getQuantity();

            call.enqueue(new Callback<ProductModel>() {
                @Override
                public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {

                    list = response.body();
                    int suma = list.getPriceID() * quantity;

                    mNameText.add(new Card(list.getInvNumberFlowers(), list.getImages(), list.getName(), String.valueOf(suma),
                            quantity.toString(), list.getPriceID().toString(), list.isSelectCheckBox()));

                    CartAdapter adapter = new CartAdapter(mNameText);
                    //  adapter.setHasStableIds(false);
                    adapter.notifyDataSetChanged();

                    recyclerView.setAdapter(adapter);
                    numberDelete = list.getInvNumberFlowers();
                }

                @Override
                public void onFailure(Call<ProductModel> call, Throwable t) {
                    System.out.println("ArrayEroor ");
                }
            });
        }
    }

    private void adap() {
        recyclerView = (RecyclerView) findViewById(R.id.cart_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onResume() {
        MainActivity mainActivity = new MainActivity();
        super.onResume();
        mainActivity.setupBadge();
    }

    static public void onBuy(View v) {
        if (cartAdapter.countLinerLayoutOFFandOn == 0) {
            linearLayout.setVisibility(View.GONE);
            System.out.println("gone " + CartAdapter.countLinerLayoutOFFandOn);
        } else {
            linearLayout.setVisibility(View.VISIBLE);
            System.out.println("Visible " + CartAdapter.countLinerLayoutOFFandOn);
        }
    }
    public void checkout(View view) {
        Intent intent = new Intent(CartActivity.this, Checkout.class);
        startActivity(intent);
    }
}