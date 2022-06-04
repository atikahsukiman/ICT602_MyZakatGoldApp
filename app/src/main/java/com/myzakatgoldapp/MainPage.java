package com.myzakatgoldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainPage extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText goldWeight, goldValue;
    Button Calc, Reset;
    TextView totgold, zakatpay, totzakat;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    float g_weight;
    float g_value;

    SharedPreferences sharedPref;
    SharedPreferences sharedPref2;
    private Menu menu;


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate (R.menu.menu, menu);

        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        goldWeight = (EditText) findViewById(R.id.gold_weight);
        goldValue = (EditText) findViewById(R.id.gold_value);
        Calc = (Button) findViewById(R.id.Calcbtn);
        Reset = (Button) findViewById(R.id.Resetbtn);
        totgold = (TextView) findViewById(R.id.totgold);
        zakatpay = (TextView) findViewById(R.id.zakatpay);
        totzakat = (TextView) findViewById(R.id.totzakat);

        Calc.setOnClickListener(this);
        Reset.setOnClickListener(this);
        spinner.setOnItemSelectedListener(this);

        sharedPref = this.getSharedPreferences("weight", Context.MODE_PRIVATE);
        g_weight = sharedPref.getFloat("weight", 0.0F);

        sharedPref2 = this.getSharedPreferences("value", Context.MODE_PRIVATE);
        g_value = sharedPref2.getFloat("value", 0.0F);

        goldWeight.setText(""+g_weight);
        goldValue.setText(""+g_value);
    }


    public void ZakatCalc(){
        DecimalFormat df = new DecimalFormat("##.00");
        float g_weight = Float.parseFloat(goldWeight.getText().toString());
        float g_value = Float.parseFloat(goldValue.getText().toString());
        String stat = spinner.getSelectedItem().toString();
        double totGold;
        double uruf;
        double zakatPay;
        double totZakat;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("weight", g_weight);
        editor.apply();
        SharedPreferences.Editor editor2 = sharedPref2.edit();
        editor2.putFloat("value", g_value);
        editor2.apply();


        if (stat.equals("To Keep")){
            totGold= g_weight * g_value;
            uruf= g_weight - 85;

            if(uruf>=0.0) {
                zakatPay = uruf * g_value;
                totZakat = zakatPay * 0.025;
            }

            else{
                zakatPay = 0.0;
                totZakat = zakatPay * 0.025;

            }

            totgold.setText("Total Gold Value: RM"+ df.format(totGold));
            zakatpay.setText("Zakat Payable: RM"+ df.format(zakatPay));
            totzakat.setText("Total Zakat: RM"+ df.format(totZakat));
        }

        else{
            totGold= g_weight * g_value;
            uruf= g_weight - 200;

            if(uruf>=0.0) {
                zakatPay = uruf * g_value;
                totZakat = zakatPay * 0.025;
            }

            else{
                zakatPay = 0.0;
                totZakat = zakatPay * 0.025;

            }

            totgold.setText("Total Gold Value: RM"+ df.format(totGold));
            zakatpay.setText("Zakat Payable: RM"+ df.format(zakatPay));
            totzakat.setText("Total Zakat: RM"+ df.format(totZakat));

        }

    }

    @Override
    public void onClick(View v) {

        try {
            switch (v.getId()) {

                case R.id.Calcbtn:
                    ZakatCalc();
                    break;

                case R.id.Resetbtn:
                    goldWeight.setText("");
                    goldValue.setText("");
                    totgold.setText("");
                    zakatpay.setText("");
                    totzakat.setText("");

                    break;

            }
        } catch (java.lang.NumberFormatException nfe) {
            Toast.makeText(this, "Please enter a valid number!", Toast.LENGTH_SHORT).show();

        } catch (Exception exp) {
            Toast.makeText(this,"Unknown Exception" + exp.getMessage(),Toast.LENGTH_SHORT).show();

            Log.d("Exception",exp.getMessage());

        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.aboutapp:
                //Toast.makeText(this,"About Zakat Gold",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, AboutApp.class);
                startActivity(intent);
                break;


        }
        return super.onOptionsItemSelected(item);
    }

}