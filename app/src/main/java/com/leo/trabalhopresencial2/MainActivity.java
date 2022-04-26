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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RadioButton fromReal, fromDollar, fromEuro, toReal, toDollar, toEuro;
    EditText inputValue;
    Button convertButton;
    TextView outputValue;

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
        toReal = findViewById(R.id.to_real);
        toDollar = findViewById(R.id.to_dollar);
        toEuro = findViewById(R.id.to_euro);
    }

    public void convert(View view) {
        if (inputValue.getText().length() == 0) {
            Toast.makeText(this, "Please set a value to be converted", Toast.LENGTH_SHORT).show();
        } else if (fromReal.isChecked() && toDollar.isChecked()) {
            outputValue.setText(
                    String.valueOf(
                            Double.parseDouble(inputValue.getText().toString()) / 5.5
                    )
            );
        } else if (fromDollar.isChecked() && toReal.isChecked()) {
            outputValue.setText(
                    String.valueOf(
                            Double.parseDouble(inputValue.getText().toString()) * 5.5
                    )
            );
        } else if (fromReal.isChecked() && toEuro.isChecked()) {
            outputValue.setText(
                    String.valueOf(
                            Double.parseDouble(inputValue.getText().toString()) / 5.74
                    )
            );
        } else if (fromEuro.isChecked() && toReal.isChecked()) {
            outputValue.setText(
                    String.valueOf(
                            Double.parseDouble(inputValue.getText().toString()) * 5.74
                    )
            );
        } else if (fromDollar.isChecked() && toEuro.isChecked()) {
            outputValue.setText(
                    String.valueOf(
                            Double.parseDouble(inputValue.getText().toString()) / 1.11
                    )
            );
        } else if (fromEuro.isChecked() && toDollar.isChecked()) {
            outputValue.setText(
                    String.valueOf(
                            Double.parseDouble(inputValue.getText().toString()) * 1.11
                    )
            );
        } else if (fromEuro.isChecked() && toEuro.isChecked() ||
                fromReal.isChecked() && toReal.isChecked() ||
                fromDollar.isChecked() && toDollar.isChecked()) {
            Toast.makeText(this, "Please select 2 different currencies", Toast.LENGTH_SHORT).show();
        }
    }

    public void getData (View view){

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Consultando endereço...");
        progressDialog.show();

        Call<> call = new RetrofitConfig().getCEPService().getFullAddress(input.getText().toString());
        call.enqueue(new Callback<Address>() {

            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {
                if(response.isSuccessful()){
                    Address address = response.body();
                    progressDialog.dismiss();
                    output.setText(address.getLogradouro() +" - "+ address.getComplemento()+ "\n"
                            + address.getBairro() + "\n" + address.getLocalidade()+" - "+address.getUf());
                }
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {

            }
        });
    }
}