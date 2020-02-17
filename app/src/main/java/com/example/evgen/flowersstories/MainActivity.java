package com.example.evgen.flowersstories;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.evgen.flowersstories.Cart.CartActivity;
import com.example.evgen.flowersstories.Cart.CartController;
import com.example.evgen.flowersstories.Cart.DB.DBHelper;
import com.example.evgen.flowersstories.Draweble.NavigationDrawer;
import com.example.evgen.flowersstories.ViewPagerItem.ItemViewPagerRose;

public class MainActivity extends NavigationDrawer {

    DBHelper db = new DBHelper(this);
    private TextView textCartItemCount;

    static final String TAG = " MyMain ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {

            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            navigationDrawer(toolbar);

            Log.v(TAG, "MyMain");
        }
    }

    public void oneRose(View view) {
        Intent intent = new Intent(MainActivity.this, ItemViewPagerRose.class);
        startActivity(intent);

    }

    public void setupBadge() {
        if (textCartItemCount != null) {
            if (db.getAllCart().size() == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(db.getAllCart().size(), 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);

        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem menuItem = menu.findItem(R.id.cart);

        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = actionView.findViewById(R.id.cart_badge);

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }

        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.home:
//                onBackPressed();
//                return true;
            case R.id.cart:
                startActivity(new Intent(this, CartActivity.class));

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        CartController cartController = new CartController();
        super.onResume();
        setupBadge();
        cartController.buyDelete();
    }
}



