package com.example.verificarenergia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText Potencia;
    private TextView  Valorpotencia;
    private EditText Tarifa;
    private EditText Horas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Horas = findViewById(R.id.Horas);
        Tarifa = findViewById(R.id.Tarifa);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.Opcoes);
        String aparelhos[] = getResources().getStringArray(R.array.aparelhos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, aparelhos);
        autoCompleteTextView.setAdapter(adapter);


    }
    public void  caucular(View view){
        int horas = Integer.parseInt(Horas.getText().toString());
        double tarifa = Double.parseDouble(Tarifa.getText().toString());
        double potencia = Double.parseDouble(Potencia.getText().toString());
        double energi_hour = horas*potencia;
        double valor = energi_hour*tarifa;
        Valorpotencia.setText("O valor é R$"+valor);
    }
}