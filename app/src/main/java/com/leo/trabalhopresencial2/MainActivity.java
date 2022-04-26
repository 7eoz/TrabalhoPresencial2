package com.leo.trabalhopresencial2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import API.RetrofitConfig;
import Model.Coin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RadioButton fromReal, fromDollar, fromEuro, fromBitcoin, fromEtherium, toReal, toDollar, toEuro,
            toBitcoin, toEtherium;
    EditText inputValue;
    Button convertButton;
    TextView outputValue;
    Double ask = 0.0;
    DecimalFormat format = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.input_value);
        outputValue = findViewById(R.id.output_value);
        convertButton = findViewById(R.id.convert_button);
        fromReal = findViewById(R.id.from_real);
        fromDollar = findViewById(R.id.from_dollar);
        fromEuro = findViewById(R.id.from_euro);
        fromBitcoin = findViewById(R.id.fromBitcoin);
        fromEtherium = findViewById(R.id.fromEtherium);
        toReal = findViewById(R.id.to_real);
        toDollar = findViewById(R.id.to_dollar);
        toEuro = findViewById(R.id.to_euro);
        toBitcoin = findViewById(R.id.toBitcoin);
        toEtherium = findViewById(R.id.toEtherium);
    }

    public void convert(View view) {
        if (inputValue.getText().length() == 0) {
            Toast.makeText(this, "Please input a value to be converted", Toast.LENGTH_SHORT).show();
        } else if (fromReal.isChecked() && toDollar.isChecked()) {
            getData("BRL-USD");
        } else if (fromDollar.isChecked() && toReal.isChecked()) {
            getData("USD-BRL");
        } else if (fromReal.isChecked() && toEuro.isChecked()) {
            getData("BRL-EUR");
        } else if (fromEuro.isChecked() && toReal.isChecked()) {
            getData("EUR-BRL");
        } else if (fromDollar.isChecked() && toEuro.isChecked()) {
            getData("USD-EUR");
        } else if (fromEuro.isChecked() && toDollar.isChecked()) {
            getData("EUR-USD");
        } else if (fromBitcoin.isChecked() && toDollar.isChecked()) {
            getData("EUR-BRL");
        } else if (fromBitcoin.isChecked() && toEuro.isChecked()) {
            getData("BTC-EUR");
        } else if (fromBitcoin.isChecked() && toReal.isChecked()) {
            getData("BTC-BRL");
        } else if (fromBitcoin.isChecked() && toEtherium.isChecked()) {
            getData("BTC-ETC");
        } else if (fromEtherium.isChecked() && toDollar.isChecked()) {
            getData("ETC-USD");
        } else if (fromEtherium.isChecked() && toEuro.isChecked()) {
            getData("ETC-EUR");
        } else if (fromEtherium.isChecked() && toReal.isChecked()) {
            getData("ETH-BRL");
        } else if (fromEtherium.isChecked() && toBitcoin.isChecked()) {
            getData("ETC-BTC");
        }
        else if (fromEuro.isChecked() && toEuro.isChecked() ||
                fromReal.isChecked() && toReal.isChecked() ||
                fromDollar.isChecked() && toDollar.isChecked() ||
                fromBitcoin.isChecked() && toBitcoin.isChecked() ||
                fromEtherium.isChecked() && toEtherium.isChecked()) {
            Toast.makeText(this, "Please select 2 different currencies", Toast.LENGTH_SHORT).show();
        } else if (!fromEuro.isChecked() || !toEuro.isChecked() ||
                !fromReal.isChecked() || !toReal.isChecked() ||
                !fromDollar.isChecked() || !toDollar.isChecked() ||
                !fromBitcoin.isChecked() || !toBitcoin.isChecked() ||
                !fromEtherium.isChecked() || !toEtherium.isChecked()) {
            Toast.makeText(this, "Please select a 'From' and a 'To' currency", Toast.LENGTH_SHORT).show();
        }
    }

    public void getData (String cot){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Checking exchange...");
        progressDialog.show();

        Call<Coin[]> call = new RetrofitConfig().getCoinService().getCoin(cot);
        call.enqueue(new Callback<Coin[]>() {
            @Override
            public void onResponse(Call<Coin[]> call, Response<Coin[]> response) {
                if(response.isSuccessful()){
                    Coin[] coin = response.body();
                    progressDialog.dismiss();
                    ask = Double.parseDouble(coin[0].getAsk());
                    outputValue.setText(
                            String.valueOf(
                                    format.format(
                                            Double.parseDouble(
                                                    inputValue.getText().toString()) * ask
                                    )
                            )
                    );
                }
            }

            @Override
            public void onFailure(Call<Coin[]> call, Throwable t) {
                progressDialog.dismiss();
                t.printStackTrace();
            }
        });
    }
}