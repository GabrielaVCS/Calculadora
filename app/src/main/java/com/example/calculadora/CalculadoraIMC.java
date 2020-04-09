package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CalculadoraIMC extends AppCompatActivity {

    EditText edtPeso;
    EditText edtAltura;
    EditText edtResult;

    Button btnCalcularIMC;

    Button btnLimpar;
    Button btnVoltar;

    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_imc);

        edtPeso = (EditText)findViewById(R.id.edtPeso);
        edtAltura = (EditText)findViewById(R.id.edtAltura);
        edtResult = (EditText)findViewById(R.id.edtResult);

        btnCalcularIMC = (Button)findViewById(R.id.btnCalcularIMC);

        btnLimpar = (Button)findViewById(R.id.btnLimpar);
        btnVoltar = (Button)findViewById(R.id.btnVoltar);

        txtResult = (TextView)findViewById(R.id.txtResult);

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtAltura.setText("");
                edtPeso.setText("");
                edtResult.setText("");
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        btnCalcularIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(edtPeso.getText().toString().trim().equals("")||edtAltura.getText().toString().trim().equals("")){
                Toast.makeText(getApplicationContext(), R.string.validacaoNums, Toast.LENGTH_SHORT).show();
                return;
            }else if(Double.parseDouble(edtAltura.getText().toString()) == 0){
                Toast.makeText(getApplicationContext(), R.string.validacaoNum2Igual0, Toast.LENGTH_SHORT).show();
                return;
            }else {
                DecimalFormat format = new DecimalFormat("0.00");
                Double result = (Double.parseDouble(edtPeso.getText().toString()) / Math.pow(Double.parseDouble(edtAltura.getText().toString()), 2d));
                edtResult.setText(format.format(result));
                txtResult.setText(verificaTabela(result));
            }

            }
        });
    }

    private String verificaTabela(Double pResult){
        String result = "";
        if(pResult < 18.5) result = "Abaixo do peso";
        else if(18.5 <= pResult && pResult <= 24.9) result = "Peso normal";
        else if(25 <= pResult && pResult <= 29.9) result = "Sobrepeso";
        else if(30 <= pResult && pResult <= 34.9) result = "Obesidade grau 1";
        else if(35 <= pResult && pResult <= 39.9) result = "Obesidade grau 2";
        else if(pResult > 40) result = "Obesidade grau 3";
        return result;
    }
}
