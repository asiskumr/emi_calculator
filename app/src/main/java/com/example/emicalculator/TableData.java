package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class TableData extends AppCompatActivity {
    public static final String TOTAL_EMI_KEY = "TOTAL_EMI_KEY";
    public static final String VARIABLE_1_KEY = "VARIABLE_1_KEY";
    public static final String PRINCIPLE_LOAN_KEY = "PRINCIPLE_LOAN_KEY";
    public static final String TOTAL_AMOUNT_KEY = "TOTAL_AMOUNT_KEY";

    LinearLayout new_row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_data);

        Intent outputIntent = getIntent();

        double total_emi = outputIntent.getDoubleExtra(TOTAL_EMI_KEY, 0);
        double var1 = outputIntent.getDoubleExtra(VARIABLE_1_KEY, 0);
        double principle_loan = outputIntent.getDoubleExtra(PRINCIPLE_LOAN_KEY, 0);
        double total_amount = outputIntent.getIntExtra(TOTAL_AMOUNT_KEY, 0);

        new_row = findViewById(R.id.new_row);
       // row_add_new = findViewById(R.id.row_add_new);





        for (int i=1; i<=20; i++){

            View calculationView = getLayoutInflater().inflate(R.layout.row_add_table,null,false);

            TextView month_noText = (TextView)calculationView.findViewById(R.id.new_col1);
            TextView total_emiText = (TextView)calculationView.findViewById(R.id.new_col2);
            TextView interest_monthlyText = (TextView)calculationView.findViewById(R.id.new_col3);
            TextView principle_monthly_Text = (TextView)calculationView.findViewById(R.id.new_col4);
            TextView loan_remainingText = (TextView)calculationView.findViewById(R.id.new_col5);

            DecimalFormat df3 = new DecimalFormat();
            df3.setMaximumFractionDigits(0);

            Double principal_monthly = total_emi-(var1*principle_loan);
            Double interest_monthly = total_emi-principal_monthly;
            Double loan_remaining_monthly = principle_loan-principal_monthly;
            //Double actual_balance = total_amount-total_emi;

            month_noText.setText("" + df3.format(i));
            total_emiText.setText("" + df3.format(total_emi));
            interest_monthlyText.setText("" + df3.format(interest_monthly));
            principle_monthly_Text.setText("" + df3.format(principal_monthly));
            loan_remainingText.setText("" + df3.format(loan_remaining_monthly));




            new_row.addView(calculationView );

        }







    }
}