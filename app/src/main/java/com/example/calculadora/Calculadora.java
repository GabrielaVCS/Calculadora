package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculadora extends AppCompatActivity {

    EditText edtNum1;
    EditText edtNum2;
    EditText edtResult;

    Button btnDiv;
    Button btnMult;
    Button btnSub;
    Button btnSoma;
    Button btnResult;

    Button btnLimpar;
    Button btnVoltar;

    ListView lwHistorico;
    List<String> listHistorico;
    ArrayAdapter<String> arrayAdapter;

    int operacao = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        edtNum1   = (EditText)findViewById(R.id.edtNum1);
        edtNum2   = (EditText)findViewById(R.id.edtNum2);
        edtResult = (EditText)findViewById(R.id.edtResult);

        btnDiv    = (Button)findViewById(R.id.btnDiv);
        btnMult   = (Button)findViewById(R.id.btnMult);
        btnSub    = (Button)findViewById(R.id.btnSub);
        btnSoma   = (Button)findViewById(R.id.btnSoma);
        btnResult = (Button)findViewById(R.id.btnResult);

        btnLimpar = (Button)findViewById(R.id.btnLimpar);
        btnVoltar = (Button)findViewById(R.id.btnVoltar);

        lwHistorico = (ListView)findViewById(R.id.lwHistorico);

        listHistorico = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listHistorico);
        lwHistorico.setAdapter(arrayAdapter);

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpar();
                edtNum1.setText("");
                edtNum2.setText("");
                edtResult.setText("");
                edtNum1.requestFocus();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNum1.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), R.string.validacaoNum1, Toast.LENGTH_SHORT).show();
                    return;
                }
                limpar();
                operacao = R.string.div;
                btnDiv.setBackgroundResource(R.color.colorPrimary);
                btnDiv.setTextColor(Color.WHITE);
                edtNum2.requestFocus();
            }
        });
        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNum1.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), R.string.validacaoNum1, Toast.LENGTH_SHORT).show();
                    return;
                }
                limpar();
                operacao = R.string.mul;
                btnMult.setBackgroundResource(R.color.colorPrimary);
                btnMult.setTextColor(Color.WHITE);
                edtNum2.requestFocus();
            }
        });
        btnSoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNum1.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), R.string.validacaoNum1, Toast.LENGTH_SHORT).show();
                    return;
                }
                limpar();
                operacao = R.string.sum;
                btnSoma.setBackgroundResource(R.color.colorPrimary);
                btnSoma.setTextColor(Color.WHITE);
                edtNum2.requestFocus();
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNum1.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), R.string.validacaoNum1, Toast.LENGTH_SHORT).show();
                    return;
                }
                limpar();
                operacao = R.string.sub;
                btnSub.setBackgroundResource(R.color.colorPrimary);
                btnSub.setTextColor(Color.WHITE);
                edtNum2.requestFocus();
            }
        });
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNum1.getText().toString().trim().equals("")||edtNum2.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), R.string.validacaoNums, Toast.LENGTH_SHORT).show();
                    return;
                }else if(Integer.parseInt(edtNum2.getText().toString()) == 0 && operacao == R.string.div){
                    Toast.makeText(getApplicationContext(), R.string.validacaoNum2Igual0, Toast.LENGTH_SHORT).show();
                    edtResult.setText("");
                    return;
                }else{
                    String result = getResult(Double.parseDouble(edtNum1.getText().toString()),
                                                Double.parseDouble(edtNum2.getText().toString()),
                                                operacao).toString();
                    edtResult.setText(result);
                    if(!listHistorico.contains(result)){
                        listHistorico.add(result);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        edtNum2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    if(operacao == -1){
                        edtNum1.requestFocus();
                        Toast.makeText(getApplicationContext(), R.string.validacaoOperacao, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        lwHistorico.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem = (String)lwHistorico.getItemAtPosition(position);
                edtNum1.setText(clickedItem);
                edtNum2.setText("");
                edtResult.setText("");
            }
        });
    }

    private Double getResult(Double pNum1, Double pNum2, int pOperacao){
        Double result = 0d;
        if(pOperacao == R.string.div){
            result = pNum1/pNum2;
        }else if(pOperacao == R.string.mul){
            result = pNum1*pNum2;
        }else if(pOperacao == R.string.sum){
            result = pNum1+pNum2;
        }else if(pOperacao == R.string.sub){
            result = pNum1-pNum2;
        }
        return result;
    }

    private void limpar(){
        Button btnDefault = new Button(this);
        btnDiv.setBackground(btnDefault.getBackground());
        btnDiv.setTextColor(Color.BLACK);
        btnMult.setBackground(btnDefault.getBackground());
        btnMult.setTextColor(Color.BLACK);
        btnSub.setBackground(btnDefault.getBackground());
        btnSub.setTextColor(Color.BLACK);
        btnSoma.setBackground(btnDefault.getBackground());
        btnSoma.setTextColor(Color.BLACK);
    }
}
