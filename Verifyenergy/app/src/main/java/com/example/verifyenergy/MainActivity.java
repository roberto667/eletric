package com.example.verifyenergy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText Potencia, Horas, Tarifa, Dias;
    private AutoCompleteTextView opcoes;
    private ScrollView scrollView;
    private View decorview;

    private Button Calcular;
    private List<EditText> camposDuplicados = new ArrayList<>();
    private Button ConfTarifa;
    private TextView Valor, Usodiaval, Custodiaval, Usomesval, Customesval, Usosemval, Custosemval, Usoanoval, Custoanoval, Custototalval, Consumototalval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //esconder a status e a activity bar
        decorview = getWindow().getDecorView();
        decorview.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        //Pegar os buttons, etc...
        Custototalval = findViewById(R.id.Custototalval);
        Consumototalval = findViewById(R.id.Consumototalval);
        //Valor = findViewById(R.id.Resposta);
        Tarifa = findViewById(R.id.Tarifa);
        Horas = findViewById(R.id.Horas);
        Dias = findViewById(R.id.Dias);
        Potencia = findViewById(R.id.Potencia);
        scrollView = findViewById(R.id.scroll);
        Usodiaval = findViewById(R.id.Usodiaval);
        Custodiaval = findViewById(R.id.CustoDiaval);
        Usosemval = findViewById(R.id.Usosemanaval);
        Custosemval = findViewById(R.id.Custosemvalu);
        Usomesval = findViewById(R.id.Usomesval);
        Customesval = findViewById(R.id.Customesval);
        Usoanoval = findViewById(R.id.Usoanoval);
        Custoanoval = findViewById(R.id.Custoanoval);
        Button adciona = findViewById(R.id.Adcionar);
        final LinearLayout linearLayout = findViewById(R.id.Constraintmain2); // Substitua "seuLinearLayout" pelo ID do seu LinearLayout

        adciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout constraintLayout3 = findViewById(R.id.Constraintmain3); // Encontre o ConstraintLayout existente
                ConstraintLayout newConstraintLayout = new ConstraintLayout(getApplicationContext());

                // Copie as configurações do ConstraintLayout original
                newConstraintLayout.setLayoutParams(constraintLayout3.getLayoutParams());
                newConstraintLayout.setBackgroundResource(R.drawable.tvbord); // Defina o mesmo plano de fundo

                // Adicione o conteúdo do ConstraintLayout original ao novo ConstraintLayout
                View.inflate(getApplicationContext(), R.layout.itenscauculo, newConstraintLayout); // Substitua "seu_layout" pelo layout do conteúdo

                // Adicione o novo ConstraintLayout ao LinearLayout
                // Adicione o novo ConstraintLayout ao LinearLayout
                linearLayout.addView(newConstraintLayout, linearLayout.indexOfChild(adciona));

                // Acesse os campos dos elementos copiados
                EditText potenciaCopiada = newConstraintLayout.findViewById(R.id.Potencia);
                EditText tarifaCopiada = newConstraintLayout.findViewById(R.id.Tarifa);
                EditText horasCopiadas = newConstraintLayout.findViewById(R.id.Horas);
                EditText diasCopiados = newConstraintLayout.findViewById(R.id.Dias);

                // Adicione ouvintes de texto aos campos copiados
                potenciaCopiada.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        calcularValores();
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {}
                });

                tarifaCopiada.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        calcularValores();
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {}
                });

                horasCopiadas.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        calcularValores();
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {}
                });

                diasCopiados.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        calcularValores();
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {}
                });
            }
        });


        Potencia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calcularValores();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Horas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calcularValores();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Tarifa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calcularValores();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Dias.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calcularValores();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //Os atributos automatico
        opcoes = findViewById(R.id.Opcoes);
        String aparelhos[] = getResources().getStringArray(R.array.aparelhos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, aparelhos);
        opcoes.setAdapter(adapter);
    }


    public void usodiaval() {
        try {
            String potenciaStr = Potencia.getText().toString();
            String horasStr = Horas.getText().toString();

            if (!TextUtils.isEmpty(potenciaStr) && !TextUtils.isEmpty(horasStr)) {
                double potencia = Double.parseDouble(potenciaStr);
                double horas = Double.parseDouble(horasStr);
                double usodia = (potencia * horas) / 1000;

                // Formate o resultado com duas casas decimais
                DecimalFormat df = new DecimalFormat("0.00");
                String usodiaval = df.format(usodia);
                Usodiaval.setText(usodiaval);
            }
        } catch (NumberFormatException e) {
            Usodiaval.setText("Valores inválidos");
        }
    }

    public void custodia() {
        try {
            String potenciaStr = Potencia.getText().toString();
            String horasStr = Horas.getText().toString();
            String tarifaStr = Tarifa.getText().toString();

            if (!TextUtils.isEmpty(potenciaStr) && !TextUtils.isEmpty(horasStr) && !TextUtils.isEmpty(tarifaStr)) {
                double potencia = Double.parseDouble(potenciaStr);
                double horas = Double.parseDouble(horasStr);
                double tarifa = Double.parseDouble(tarifaStr);
                double usodia = (potencia * horas * tarifa) / 1000;

                // Formate o resultado com duas casas decimais
                DecimalFormat df = new DecimalFormat("0.00");
                String usodiaval = df.format(usodia);
                Custodiaval.setText(usodiaval);
            }
        } catch (NumberFormatException e) {
            Custodiaval.setText("Valores inválidos");
        }
    }

    public void usomesval() {
        try {
            String potenciaStr = Potencia.getText().toString();
            String horasStr = Horas.getText().toString();

            if (!TextUtils.isEmpty(potenciaStr) && !TextUtils.isEmpty(horasStr)) {
                double potencia = Double.parseDouble(potenciaStr);
                double horas = Double.parseDouble(horasStr);
                double usomes = (30 * potencia * horas) / 1000;

                // Formate o resultado com duas casas decimais
                DecimalFormat df = new DecimalFormat("0.00");
                String usomesval = df.format(usomes);
                Usomesval.setText(usomesval);
            }
        } catch (NumberFormatException e) {
            Usomesval.setText("Valores inválidos");
        }
    }

    public void customes() {
        try {
            String potenciaStr = Potencia.getText().toString();
            String horasStr = Horas.getText().toString();
            String tarifaStr = Tarifa.getText().toString();

            if (!TextUtils.isEmpty(potenciaStr) && !TextUtils.isEmpty(horasStr) && !TextUtils.isEmpty(tarifaStr)) {
                double potencia = Double.parseDouble(potenciaStr);
                double horas = Double.parseDouble(horasStr);
                double tarifa = Double.parseDouble(tarifaStr);
                double usodia = (potencia * horas * tarifa * 30) / 1000;

                // Formate o resultado com duas casas decimais
                DecimalFormat df = new DecimalFormat("0.00");
                String usodiaval = df.format(usodia);
                Customesval.setText(usodiaval);
            }
        } catch (NumberFormatException e) {
            Customesval.setText("Valores inválidos");
        }
    }

    public void usosemanaval() {
        try {
            String potenciaStr = Potencia.getText().toString();
            String horasStr = Horas.getText().toString();

            if (!TextUtils.isEmpty(potenciaStr) && !TextUtils.isEmpty(horasStr)) {
                double potencia = Double.parseDouble(potenciaStr);
                double horas = Double.parseDouble(horasStr);
                double usosem = (potencia * horas * 7) / 1000;

                // Formate o resultado com duas casas decimais
                DecimalFormat df = new DecimalFormat("0.00");
                String usosemaval = df.format(usosem);
                Usosemval.setText(usosemaval);
            }
        } catch (NumberFormatException e) {
            Usosemval.setText("Valores inválidos");
        }
    }

    public void custosemana() {
        try {
            String potenciaStr = Potencia.getText().toString();
            String horasStr = Horas.getText().toString();
            String tarifaStr = Tarifa.getText().toString();

            if (!TextUtils.isEmpty(potenciaStr) && !TextUtils.isEmpty(horasStr) && !TextUtils.isEmpty(tarifaStr)) {
                double potencia = Double.parseDouble(potenciaStr);
                double horas = Double.parseDouble(horasStr);
                double tarifa = Double.parseDouble(tarifaStr);
                double usosem = (potencia * horas * tarifa * 7) / 1000;

                // Formate o resultado com duas casas decimais
                DecimalFormat df = new DecimalFormat("0.00");
                String usosemval = df.format(usosem);
                Custosemval.setText(usosemval);
            }
        } catch (NumberFormatException e) {
            Custosemval.setText("Valores inválidos");
        }
    }

    public void usoanoval() {
        try {
            String potenciaStr = Potencia.getText().toString();
            String horasStr = Horas.getText().toString();

            if (!TextUtils.isEmpty(potenciaStr) && !TextUtils.isEmpty(horasStr)) {
                double potencia = Double.parseDouble(potenciaStr);
                double horas = Double.parseDouble(horasStr);
                double usoano = (potencia * horas * 365) / 1000;

                // Formate o resultado com duas casas decimais
                DecimalFormat df = new DecimalFormat("0.00");
                String usoanoval = df.format(usoano);
                Usoanoval.setText(usoanoval);
            }
        } catch (NumberFormatException e) {
            Usoanoval.setText("Valores inválidos");
        }
    }

    public void custosano() {
        try {
            String potenciaStr = Potencia.getText().toString();
            String horasStr = Horas.getText().toString();
            String tarifaStr = Tarifa.getText().toString();

            if (!TextUtils.isEmpty(potenciaStr) && !TextUtils.isEmpty(horasStr) && !TextUtils.isEmpty(tarifaStr)) {
                double potencia = Double.parseDouble(potenciaStr);
                double horas = Double.parseDouble(horasStr);
                double tarifa = Double.parseDouble(tarifaStr);
                double usosem = (potencia * horas * tarifa * 365) / 1000;

                // Formate o resultado com duas casas decimais
                DecimalFormat df = new DecimalFormat("0.00");
                String usosemval = df.format(usosem);
                Custoanoval.setText(usosemval);
            }
        } catch (NumberFormatException e) {
            Custoanoval.setText("Valores inválidos");
        }
    }

    public void Consumototal() {
        String potenciaStr = Potencia.getText().toString();
        String horasStr = Horas.getText().toString();
        String tarifaStr = Tarifa.getText().toString();
        String diasstr = Dias.getText().toString();
        try {
            if (!TextUtils.isEmpty(potenciaStr) && !TextUtils.isEmpty(horasStr) && !TextUtils.isEmpty(tarifaStr)) {
                double potencia = Double.parseDouble(potenciaStr);
                double horas = Double.parseDouble(horasStr);
                double tarifa = Double.parseDouble(tarifaStr);
                int dias = Integer.parseInt(diasstr);
                double consumo = (potencia * horas * tarifa * dias) / 1000;

                // Formate o resultado com duas casas decimais
                DecimalFormat df = new DecimalFormat("0.00");
                String consumototal = df.format(consumo);
                Consumototalval.setText(consumototal+"kWh");
            }
        } catch (NumberFormatException e) {
            Consumototalval.setText("Valores inválidos");

        }
    }

    public void Custototal() {
        String potenciaStr = Potencia.getText().toString();
        String horasStr = Horas.getText().toString();
        String tarifaStr = Tarifa.getText().toString();
        try {
            if (!TextUtils.isEmpty(potenciaStr) && !TextUtils.isEmpty(horasStr) && !TextUtils.isEmpty(tarifaStr)) {
                double potencia = Double.parseDouble(potenciaStr);
                double horas = Double.parseDouble(horasStr);
                double tarifa = Double.parseDouble(tarifaStr);
                double custo = (potencia * horas * tarifa) / 1000;

                // Formate o resultado com duas casas decimais
                DecimalFormat df = new DecimalFormat("0.00");
                String custototal = df.format(custo);
                Custototalval.setText(custototal+"R$");
            }
        } catch (NumberFormatException e) {
            Custototalval.setText("Valores inválidos");

        }

    }
    private void calcularValores() {
        usodiaval();
        custodia();
        usomesval();
        customes();
        usosemanaval();
        custosemana();
        usoanoval();
        custosano();
        Consumototal();
        Custototal();
    }

}
