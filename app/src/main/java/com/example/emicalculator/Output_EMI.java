package com.example.emicalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DecimalFormat;

public class Output_EMI extends AppCompatActivity {
    public static final String CAR_PRICE = "CAR_PRICE";
    public static final String DOWN_PAYMENT = "DOWN_PAYMENT";
    // public static final String LOAN_AMOUNT = "LOAN_AMOUNT";
    public static final String INTEREST_RATE = "INTEREST_RATE";
    public static final String LOAN_TERMS = "LOAN_TERMS";
    TextView carText, downText, interestText, principalText, termsText, emi_amount, total_interest, total_sum,loan_chart_id,emi_chart_id;
    double carInt;
    double downInt;
    double interestInt;
    double amountInt;
    double termsInt;
    double principal_loan;
    double interest_loan;
    double months_loan;
    double result_emi, var1, var2, var3, numerator1, denominator1, total_amount, total_emi;
    Button button_details;
    PieChart pieChart;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_emi);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        Intent receiveIntent = getIntent();

        carInt = receiveIntent.getDoubleExtra(CAR_PRICE, 0);
        downInt = receiveIntent.getDoubleExtra(DOWN_PAYMENT, 0);
        // amountInt = receiveIntent.getIntExtra(LOAN_AMOUNT,0);
        interestInt = receiveIntent.getDoubleExtra(INTEREST_RATE, 0);
        termsInt = receiveIntent.getIntExtra(LOAN_TERMS, 0);

        carText = findViewById(R.id.carTextView_id);
        downText = findViewById(R.id.downTextView_id);
        principalText = findViewById(R.id.loanTextView_id);
        interestText = findViewById(R.id.interestTextView_id);
        termsText = findViewById(R.id.termsTextView_id);
        // Toast.makeText(this, "calculated", Toast.LENGTH_SHORT).show();
        emi_amount = findViewById(R.id.emi_main_id12);
        total_interest = findViewById(R.id.total_interest_id12);
        total_sum = findViewById(R.id.total_amount_id12);
        emi_chart_id = findViewById(R.id.emi_chart_id);
        loan_chart_id = findViewById(R.id.loan_chart_id);
        pieChart = findViewById(R.id.piechart);
        button_details= findViewById(R.id.button_details);



        carText.setText("Vehicle price:     " + carInt);
        downText.setText(String.valueOf("Down payment: " + downInt));
        principal_loan = carInt - downInt;
        principalText.setText(String.valueOf("Loan amount:    " + principal_loan));
        interestText.setText(String.valueOf("Interest rate:      " + interestInt + " %"));
        termsText.setText("Loan Tenure:      " + termsInt+" months");

        //calculation of monthly EMI
        var1 = (interestInt / (12 * 100));
        var2 = var1 + 1;
        var3 = Math.pow(var2, termsInt);
        numerator1 = (principal_loan * var1 * var3);
        denominator1 = (var3 - 1);
        result_emi = numerator1 / denominator1;

        //calculation of Total Amount (Total EMI interest + Principal amount) to be paid
        total_amount = result_emi * termsInt;

        //calculation of Total EMI interest
        total_emi = total_amount - principal_loan;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(0);
        emi_amount.setText("₹ " + df.format(result_emi));
        total_interest.setText("Total interest:   ₹ " + df.format(total_emi));
        total_sum.setText("Total Payment (Principal + Interest):   ₹ " + df.format(total_amount));


        button_details.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Output_EMI.this, "Something went wrong!\nWill resolve soon", Toast.LENGTH_SHORT).show();
                Intent startIntentTable = new Intent(Output_EMI.this, TableData.class);
                startIntentTable.putExtra(TableData.TOTAL_EMI_KEY, total_emi);
                startIntentTable.putExtra(TableData.VARIABLE_1_KEY, var1);
                //startIntent.putExtra(Output_EMI.LOAN_AMOUNT,int_amount);
                startIntentTable.putExtra(TableData.PRINCIPLE_LOAN_KEY, principal_loan);
                startIntentTable.putExtra(TableData.TOTAL_AMOUNT_KEY, total_amount);


                startActivity(startIntentTable);
            }
        });


        // Creating a method setData()
        // to set the text in text view and pie chart
        setData();




    }



    private void setData() {

        //calculation of % of total EMI and % of Loan taken for Pie Chart
        double emi_interest_chart = (total_emi*100)/total_amount;
        double loan_chart = 100-emi_interest_chart;
        DecimalFormat df1 = new DecimalFormat();
        df1.setMaximumFractionDigits(2);
        // Set the percentage of language used
        emi_chart_id.setText("Total interest :  "+df1.format(emi_interest_chart)+" %");
        loan_chart_id.setText("Principal Loan Amount :  "+df1.format(loan_chart)+" %");

        // Set the data and color to the pie chart

        pieChart.addPieSlice(
                new PieModel(

                        (float) emi_interest_chart,
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(

                        (float) loan_chart,
                        Color.parseColor("#66BB6A")));
        // To animate the pie chart
        pieChart.startAnimation();

    }

}