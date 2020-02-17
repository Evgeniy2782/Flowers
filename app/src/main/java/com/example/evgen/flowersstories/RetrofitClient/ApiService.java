package com.example.evgen.flowersstories.RetrofitClient;

import com.example.evgen.flowersstories.ViewPagerItem.ProductArray;
import com.example.evgen.flowersstories.ViewPagerItem.ProductModel;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

//    @GET("productRose.json")
//        // Пример как пишется запрос к файлу .json
//    Call<ProductArray> getJson();

//    @GET("/cartTEST/productTulpan.json")
//    Call<ProductArray>getTulpan();



    @POST("idRose")
    Call<ProductArray>getRose();

//    @GET("group/{id}/users")
//    Call<List<User>> groupList(@Path("id") int groupId, @QueryMap Map<String, String> options);
//    @GET(":3001/idRose/RWh307P4")
//    Call<List<ProductArray>>getInvFlower(
//        //    @Query("priceID") int priceID
//    );

    @POST("idRose/{id}")
    Call<ProductModel>getInvFlower(
            @Path("id")  String patch
//       //     @Query("name") String name
    );
}
