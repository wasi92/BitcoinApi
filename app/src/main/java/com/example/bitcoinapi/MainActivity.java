package com.example.bitcoinapi;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bitcoinapi.api.ApiClient;
import com.example.bitcoinapi.api.ApiService;
import com.example.bitcoinapi.model.BitcoinPrice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView bitcoinPriceText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        bitcoinPriceText = findViewById(R.id.bitcoinPriceText);

        // Fetch Bitcoin price
        fetchBitcoinPrice();
    }

    private void fetchBitcoinPrice() {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<BitcoinPrice> call = apiService.getCurrentPrice();

        call.enqueue(new Callback<BitcoinPrice>() {
            @Override
            public void onResponse(Call<BitcoinPrice> call, Response<BitcoinPrice> response) {
                if (response.isSuccessful() && response.body() != null) {
                    BitcoinPrice bitcoinPrice = response.body();
                bitcoinPriceText.setText("BTC Price (USD): " + bitcoinPrice.getBpi().getUSD().getRate());
                } else {
                    Toast.makeText(MainActivity.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BitcoinPrice> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}