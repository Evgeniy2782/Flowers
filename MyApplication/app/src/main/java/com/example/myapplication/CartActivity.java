package com.example.myapplication;

import android.os.Bundle;

public class CartActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

    }
    public int getViewId() {
        return R.layout.test;
    }

}
