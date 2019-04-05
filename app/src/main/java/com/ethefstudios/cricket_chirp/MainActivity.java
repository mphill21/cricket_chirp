package com.ethefstudios.cricket_chirp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvResults;
    EditText etChirps;
    Button btnCalculate;
    RadioButton rbCelsius;
    RadioButton rbFahrenheit;
    TextView tvInstructions;

    String selectedScale;

    Double F_CHIRPS = 40.0;
    Double C_CHIRPS_DIVIDE = 3.0;
    Double C_CHIRPS_ADD = 4.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResults = findViewById(R.id.tvResults);
        etChirps = findViewById(R.id.etChirps);
        btnCalculate = findViewById(R.id.btnCalculate);
        rbCelsius = findViewById(R.id.rbCelsius);
        rbFahrenheit = findViewById(R.id.rbFahrenheit);
        tvInstructions = findViewById(R.id.tvInstructions);

        tvResults.setVisibility(View.GONE);
        rbFahrenheit.toggle();

        selectedScale  = getApplicationContext().getResources().getString(R.string.Fahrenheit);

        rbCelsius.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean bool){
                if(bool)
                {
                    etChirps.setHint(R.string.hint_chirps_Celsius);
                    tvInstructions.setText(R.string.instructions_Celsius);
                    selectedScale = getApplicationContext().getResources().getString(R.string.Celsius);
                }
            }
        });

        rbFahrenheit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean bool){
                if(bool)
                {
                    etChirps.setHint(R.string.hint_chirps_Fahrenheit);
                    tvInstructions.setText(R.string.instructions_Fahrenheit);
                    selectedScale = getApplicationContext().getResources().getString(R.string.Fahrenheit);
                }
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sChirps = etChirps.getText().toString().trim();
                Integer chirps = Integer.parseInt(sChirps);

                Double degrees;

                if(rbFahrenheit.isChecked()) {
                    degrees = calculateFahrenheit(chirps);
                }
                else
                {
                    degrees = calculateCelsius(chirps);
                }
                String output = String.format("The approximate temperature outside is %.1f degrees %s.", degrees, selectedScale);

                tvResults.setText(output);
                tvResults.setVisibility(View.VISIBLE);
            }
        });

    }

    protected Double calculateFahrenheit(Integer chirps)
    {
        return chirps + F_CHIRPS;
    }

    protected Double calculateCelsius(Integer chirps)
    {
        return (chirps / C_CHIRPS_DIVIDE) + C_CHIRPS_ADD;
    }
}
