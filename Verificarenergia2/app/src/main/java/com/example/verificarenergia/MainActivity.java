package com.example.verificarenergia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

        Potencia = findViewById(R.id.Potencia);
        Valorpotencia = findViewById(R.id.Valor);
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