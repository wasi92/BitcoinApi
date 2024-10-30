package com.example.bitcoinapi.api;

// ApiService.java
import com.example.bitcoinapi.model.BitcoinPrice;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("v1/bpi/currentprice.json")
    Call<BitcoinPrice> getCurrentPrice();
}
