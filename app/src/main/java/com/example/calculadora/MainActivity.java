package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnCalculadora;
    Button btnCalculadoraIMC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalculadora = (Button)findViewById(R.id.btnCalculadora);
        btnCalculadoraIMC = (Button)findViewById(R.id.btnCalculadoraIMC);

        btnCalculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Calculadora.class));
            }
        });

        btnCalculadoraIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CalculadoraIMC.class));
            }
        });
    }
}
