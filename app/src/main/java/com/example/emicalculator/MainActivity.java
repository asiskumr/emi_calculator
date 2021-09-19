package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText_carPrice, editText_downPayment, editText_interestRate, editText_loanAmount, editText_loanTerms;
    Button calc_btn,clear_btn1;
    //int int_car,int_down,int_amount,int_terms;
    // float int_interest;
    RadioButton months_radio, years_radio;
    private RadioGroup radioGroup;
    ImageButton aboutMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       getSupportActionBar().setTitle("       \t\t\t\t\t\t\t\t\t\tEnter Values Below");



        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
        //radio button paasing
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    rb.getText();
                }

            }
        });
        aboutMe = findViewById(R.id.aboutMe);
        months_radio = findViewById(R.id.months_id);
        years_radio = findViewById(R.id.years_id);
        editText_carPrice = findViewById(R.id.car_price_id);
        editText_downPayment = findViewById(R.id.down_payment_id);
        editText_interestRate = findViewById(R.id.interest_rate_id);
        //editText_loanAmount= findViewById(R.id.loan_amount_id);
        editText_loanTerms = findViewById(R.id.loan_terms_id);
        clear_btn1 = findViewById(R.id.clear_btn1);
        calc_btn = findViewById(R.id.calculate_btn_id);


        calc_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {



                String a = editText_carPrice.getText().toString();
                double int_car = Double.parseDouble(a);
                String b = editText_downPayment.getText().toString();
                double int_down = Double.parseDouble(b);
                Double int_interest=Double.parseDouble(String.valueOf(editText_interestRate.getText()));
               // String c = editText_interestRate.getText().toString();
               // double int_interest = Double.parseDouble(c);
                String d;
                d = editText_loanTerms.getText().toString();
                int int_terms = Integer.parseInt(d);

                RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                if(rb==years_radio){
                    int_terms = int_terms*12;

                }

                if(int_car<=0 || int_interest<=0 || int_terms<=0  ){
                    Toast.makeText(MainActivity.this, "Enter Appropriate Value", Toast.LENGTH_SHORT).show();

                }
                else{
                    Intent startIntent = new Intent(MainActivity.this, Output_EMI.class);

                    startIntent.putExtra(Output_EMI.CAR_PRICE, int_car);
                    startIntent.putExtra(Output_EMI.DOWN_PAYMENT, int_down);
                    //startIntent.putExtra(Output_EMI.LOAN_AMOUNT,int_amount);
                    startIntent.putExtra(Output_EMI.INTEREST_RATE, int_interest);
                    startIntent.putExtra(Output_EMI.LOAN_TERMS, int_terms);


                    startActivity(startIntent);

                }
            }
        });

    }


    public void clear_all_vals(View view) {
                  editText_carPrice.setText("");
                    editText_downPayment.setText("");
                    editText_interestRate.setText("");
                  //editText_loanAmount.setText("");
                    editText_loanTerms.setText("");
        radioGroup.clearCheck();
    }
    public void knowMe(View view){
        Intent aboutIntent = new Intent(MainActivity.this, AboutMyself.class);
        startActivity(aboutIntent);

    }

}