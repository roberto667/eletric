package com.example.verifyenergy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText  Horas, Tarifa, Dias;
    private AutoCompleteTextView opcoes, Potencia;
    private ScrollView scrollView;
    private View decorview;

    private Button adciona, remover;
    private Spinner Typeenergy;
    private double typeenergy;
    private String typeenergy2;
    private List<EditText> potenciasCopiadas = new ArrayList<>();
    private List<EditText> horassCopiadas = new ArrayList<>();
    private List<EditText> diassCopiadas = new ArrayList<>();
    private List<Spinner> typeEnergyCopiadas = new ArrayList<>();


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
        adciona = findViewById(R.id.Adcionar);
        final ConstraintLayout constraintLayout5 = findViewById(R.id.Constraintmain5);

        final LinearLayout linearLayout = findViewById(R.id.Constraintmain2); // Substitua "seuLinearLayout" pelo ID do seu LinearLayout

        AdapterView.OnItemSelectedListener escolha = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String typenergy = Typeenergy.getSelectedItem().toString();
                if (typenergy.equals("W")) {
                    typeenergy = 1;
                } else if (typenergy.equals("kW")) {
                    typeenergy = 1000;
                } else {
                    typeenergy = 0.30;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };

        adciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) constraintLayout5.getLayoutParams();
                params.setMargins(0, 10, 0, 150);
                constraintLayout5.setLayoutParams(params);


                ConstraintLayout constraintLayout3 = findViewById(R.id.Constraintmain3); // Encontre o ConstraintLayout existente
                ConstraintLayout newConstraintLayout = new ConstraintLayout(getApplicationContext());

                // Copie as configurações do ConstraintLayout original
                newConstraintLayout.setLayoutParams(constraintLayout3.getLayoutParams());
                newConstraintLayout.setBackgroundResource(R.drawable.tvbord); // Defina o mesmo plano de fundo

                // Adicione o conteúdo do ConstraintLayout original ao novo ConstraintLayout
                View.inflate(getApplicationContext(), R.layout.activity_itenscauculo, newConstraintLayout); // Substitua "seu_layout" pelo layout do conteúdo

                // Adicione o novo ConstraintLayout ao LinearLayout
                // Adicione o novo ConstraintLayout ao LinearLayout
                linearLayout.addView(newConstraintLayout, linearLayout.indexOfChild(adciona));

                // Acesse os campos dos elementos copiados
                AutoCompleteTextView potenciaCopiada = newConstraintLayout.findViewById(R.id.Potencia);
                EditText horasCopiadas = newConstraintLayout.findViewById(R.id.Horas);
                EditText diasCopiados = newConstraintLayout.findViewById(R.id.Dias);
                opcoes = newConstraintLayout.findViewById(R.id.Opcoes);
                Typeenergy = newConstraintLayout.findViewById(R.id.Typenegy);


                String aparelhos[] = ParseName();
                String potencias[] = ParseNumber();
                ArrayAdapter<String> adaptername = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, aparelhos);
                opcoes.setAdapter(adaptername);
                String[] typeEnergy = getResources().getStringArray(R.array.typeEnergy);
                ArrayAdapter<String> adaptertypeenergy = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, typeEnergy);
                opcoes.setAdapter(adaptertypeenergy);
                Typeenergy.setAdapter(adaptertypeenergy);

                opcoes.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        for (int a = 0; a < xmlsize(); a++) {
                            if (opcoes.getText().toString().equals(aparelhos[a])) {
                                potenciaCopiada.setText(potencias[a]);
                            }
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                Typeenergy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        typeenergy2 = Typeenergy.getSelectedItem().toString();
                        calcularValores(); // Recalcule os valores ao selecionar uma unidade de energia
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });



                // Adicione ouvintes de texto aos campos copiados
                potenciaCopiada.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        calcularValores();
                    }
                });


                horasCopiadas.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        calcularValores();
                    }
                });

                diasCopiados.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        calcularValores();
                    }
                });
                // Adicione os campos copiados às listas
                potenciasCopiadas.add(potenciaCopiada);
                horassCopiadas.add(horasCopiadas);
                diassCopiadas.add(diasCopiados);
                typeEnergyCopiadas.add(Typeenergy);


                calcularValores();
            }

        });





    }

    public int xmlsize(){
        String xmlContent[] = getResources().getStringArray(R.array.aparelhos);
        int numberOfItems = xmlContent.length;
        return numberOfItems;
    }
    public String[] ParseName(){
        String[] itemName = new String[268];
        String xmlContent[] = getResources().getStringArray(R.array.aparelhos);
        int numberOfItems = xmlContent.length;
        for (int i = 0; i < numberOfItems; i++){
            String itemContent = xmlContent[i].replace("<item>", "").replace("</item>", "").trim();
            String[] parts = itemContent.split(",");
            if (parts.length == 2) {
                itemName[i] = parts[0].trim();
            } else {
                System.err.println("Invalid XML-like format.");
            }
        }
        return itemName;
    }

    public String[] ParseNumber(){
        String[] itemNumber = new String[268];
        String xmlContent[] = getResources().getStringArray(R.array.aparelhos);
        int numberOfItems = xmlContent.length;
        for (int i = 0; i < numberOfItems; i++){
            String itemContent = xmlContent[i].replace("<item>", "").replace("</item>", "").trim();
            String[] parts = itemContent.split(",");
            if (parts.length == 2) {
                itemNumber[i] = parts[1].trim();

            } else {
                System.err.println("Invalid XML-like format.");
            }
        }
        return itemNumber;
    }





    public void calcularValores() {
        // Limpe os valores antes de calcular
        double totalUsodia = 0;
        double totalUsodiacusto = 0;
        double totalUsomes = 0;
        double totalUsomescusto = 0;
        double totalUsosem = 0;
        double totalUsosemcusto = 0;
        double totalUsoano = 0;
        double totalUsoanocusto = 0;
        double totalConsumototal = 0;
        double totalCustototal = 0;




        // Iterar pelos campos copiados e adicioná-los aos cálculos existentes
        for (int i = 0; i < potenciasCopiadas.size(); i++) {
            EditText potenciaCopiada = potenciasCopiadas.get(i);
            EditText horasCopiadas = horassCopiadas.get(i);
            EditText diasCopiados = diassCopiadas.get(i);
            Spinner typeenergiacopiada = typeEnergyCopiadas.get(i);
            //tipo de energia
            typeenergy2 = typeenergiacopiada.getSelectedItem().toString();

            if (Objects.equals(typeenergy2, "W")){
                typeenergy = 1000;
            } else if (Objects.equals(typeenergy2, "kW")) {
                typeenergy = 1;
            }


            try {
                String tarifaStr = Tarifa.getText().toString();
                String potenciaStr = potenciaCopiada.getText().toString();
                String horasStr = horasCopiadas.getText().toString();
                String diasStr = diasCopiados.getText().toString();

                if (!TextUtils.isEmpty(potenciaStr) && !TextUtils.isEmpty(horasStr) && !TextUtils.isEmpty(diasStr)) {
                    double potencia = Double.parseDouble(potenciaStr);
                    if (Objects.equals(typeenergy2, "BTU")){
                        potencia = potencia/ 0.30;
                        typeenergy = 1000;
                    }
                    double horas = Double.parseDouble(horasStr);
                    double tarifa = Double.parseDouble(tarifaStr);
                    int dias = Integer.parseInt(diasStr);

                    // Adicione esses valores copiados aos cálculos existentes
                    double usodia = ((potencia * horas) / typeenergy);
                    double usodiacusto = ((potencia * horas * tarifa) / typeenergy);
                    double usomes = ((30 * potencia * horas) / typeenergy);
                    double usomescusto = ((30 * potencia * horas * tarifa) / typeenergy);
                    double usosem = ((potencia * horas * 7) / typeenergy);
                    double usosemcusto = ((potencia * horas * tarifa * 7 ) / typeenergy);
                    double usoano = ((potencia * horas * 365 ) / typeenergy);
                    double usoanocusto = ((potencia * horas * tarifa * 365) / typeenergy);



                    double custototal = ((potencia * horas * tarifa * dias) / typeenergy);
                    double consumo = ((potencia * horas * tarifa) / typeenergy);


                    // Adicione os valores calculados aos totais
                    totalUsodia += usodia;
                    totalUsodiacusto += usodiacusto;
                    totalUsomes += usomes;
                    totalUsomescusto += usomescusto;
                    totalUsosem += usosem;
                    totalUsosemcusto += usosemcusto;
                    totalUsoano += usoano;
                    totalUsoanocusto += usoanocusto;
                    totalConsumototal += consumo;
                    totalCustototal += custototal;



                }else{

                   Usodiaval.setText(0.00+"R$");
                    Custodiaval.setText(0.00+"R$");
                    Usosemval.setText(0.00+"R$");
                    Custosemval.setText(0.00+"R$");
                    Customesval.setText(0.00+"R$");
                    Usomesval.setText(0.00+"R$");
                    Custoanoval.setText(0.00+"R$");
                    Usoanoval.setText(0.00+"R$");
                    Consumototalval.setText(0.00+"kWh");
                    Custototalval.setText(0.00+"R$");


                }
            } catch (NumberFormatException e) {
                // Lida com valores inválidos, se necessário
            }

        }
        DecimalFormat df = new DecimalFormat("0.00");
        String totalUsodiaStr = df.format(totalUsodia);
        String totalUsodiacustoStr = df.format(totalUsodiacusto);
        String totalUsomesStr = df.format(totalUsomes);
        String totalUsomescustoStr = df.format(totalUsomescusto);
        String totalUsosemStr = df.format(totalUsosem);
        String totalUsosemcustoStr = df.format(totalUsosemcusto);
        String totalUsoanoStr = df.format(totalUsoano);
        String totalUsoanocustoStr = df.format(totalUsoanocusto);
        String totalConsumototalStr = df.format(totalConsumototal);
        String totalCustototalStr = df.format(totalCustototal);

// Atualize as TextViews com os totais
        Usodiaval.setText(totalUsodiaStr + "R$");
        Custodiaval.setText(totalUsodiacustoStr + "R$");
        Usosemval.setText(totalUsosemStr + "R$");
        Custosemval.setText(totalUsosemcustoStr + "R$");
        Customesval.setText(totalUsomescustoStr + "R$");
        Usomesval.setText(totalUsomesStr + "R$");
        Custoanoval.setText(totalUsoanocustoStr + "R$");
        Usoanoval.setText(totalUsoanoStr + "R$");
        Consumototalval.setText(totalConsumototalStr + "R$");
        Custototalval.setText(totalCustototalStr + "R$");


    }

}