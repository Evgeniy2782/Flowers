package com.example.evgen.flowersstories.ViewPagerItem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductArray {


    @SerializedName("FlowersRose") // Прописываеи массив в JSON файле  "FlowersTulpan":[]
    @Expose

    private ArrayList<ProductModel> productArrayList = new ArrayList<>();

    public ArrayList<ProductModel> getProductArray() {
        return productArrayList;
    }

    public void setProductArrayList(ArrayList<ProductModel>productArrayList) {
        this.productArrayList = productArrayList;

    }
//
//    @SerializedName("invNumberFlowers")
//    @Expose
//
//    private ArrayList<ProductModel> invNumberFlower = new ArrayList<>();
//
//    public List<ProductModel> getInvNumberFlower() {
//        return invNumberFlower;
//    }
}
