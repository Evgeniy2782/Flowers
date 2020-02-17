package com.example.evgen.flowersstories.ViewPagerItem;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.evgen.flowersstories.Cart.CartController;
import com.example.evgen.flowersstories.Cart.DB.CartModel;
import com.example.evgen.flowersstories.Cart.DB.DBHelper;
import com.example.evgen.flowersstories.MainActivity;
import com.example.evgen.flowersstories.R;
import com.example.evgen.flowersstories.RetrofitClient.InternetConnection;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.evgen.flowersstories.RetrofitClient.RetrofitClient.getApiService;

public class ItemViewPagerRose extends MainActivity {

    ViewPager viewPager;
    ViewPagerAdapter adapter;
    ArrayList<ProductModel> flowerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (InternetConnection.checkConnection(getApplicationContext())) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.item_viewpager_rose);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            if (toolbar != null) {

                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                navigationDrawer(toolbar);
            }

            viewPager = (ViewPager) findViewById(R.id.viewPager);
            flowerList = new ArrayList<>();

            Call<ProductArray> call = getApiService().getRose();

            call.enqueue(new Callback<ProductArray>() {

                @Override
                public void onResponse(Call<ProductArray> call, Response<ProductArray> response) {
                    if (response.isSuccessful()) {

                        flowerList = response.body().getProductArray();
                        adapter = new ViewPagerAdapter(ItemViewPagerRose.this, flowerList);
                        viewPager.setAdapter(adapter);
                    } else {

                    }
                }

                @Override
                public void onFailure(Call<ProductArray> call, Throwable t) {
//                    Log.d(TAG, "onFailure: " + flowerList.size());
//                    Log.d(TAG, "failure " + t);
                }
            });
        }
    }

    @Override
    protected void onResume() {
        CartController cartController = new CartController();
        super.onResume();
        setupBadge();
        cartController.buyDelete();
    }

    public void addCart(View view) {
        Toast.makeText(getApplicationContext(), "Товар добавлен в корзину " + flowerList.get(viewPager.getCurrentItem()).getName(),
                Toast.LENGTH_SHORT).show();

        String invNumber = ((flowerList.get(viewPager.getCurrentItem()).getInvNumberFlowers()));
        int quantity = flowerList.get(viewPager.getCurrentItem()).getQuantity();
        int position = viewPager.getCurrentItem();

        DBHelper dbHelper = new DBHelper(this);
        dbHelper.addCart(new CartModel(invNumber, quantity, position),invNumber);

        setupBadge();

        }
    }

