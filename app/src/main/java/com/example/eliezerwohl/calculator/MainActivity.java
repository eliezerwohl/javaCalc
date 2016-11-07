package com.example.eliezerwohl.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.button;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    //var to hold the operands and type of calculations
    private Double operand1 = null;
    private Double operand2 = null;
    private String pendingOperation = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (EditText) findViewById(R.id.result);
        newNumber = (EditText) findViewById(R.id.newNumber);
        displayOperation = (TextView) findViewById(R.id.operation);
        Button button0 = (Button)findViewById(R.id.button0);
        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
        Button button5 = (Button)findViewById(R.id.button5);
        Button button6 = (Button)findViewById(R.id.button6);
        Button button7 = (Button)findViewById(R.id.button7);
        Button button8 = (Button)findViewById(R.id.button8);
        Button button9 = (Button)findViewById(R.id.button9);

        Button buttonDot = (Button)findViewById(R.id.buttonDot);
        Button buttonEquals = (Button)findViewById(R.id.buttonEquals);
        Button buttonDivide = (Button)findViewById(R.id.buttonDivide);
        Button buttonMultiply = (Button)findViewById(R.id.buttonMultiply);
        Button buttonMinus = (Button)findViewById(R.id.buttonMinus);
        Button buttonPlus = (Button)findViewById(R.id.buttonPlus);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                newNumber.append(b.getText().toString());
           }
        };
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);

        View.OnClickListener opListner = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                if (value.length() !=0){
                    preformOperation(value, op);
                }
                pendingOperation = op;
                displayOperation.setText(pendingOperation);
            }
        };
        buttonEquals.setOnClickListener(opListner);
        buttonDivide.setOnClickListener(opListner);
        buttonMultiply.setOnClickListener(opListner);
        buttonMinus.setOnClickListener(opListner);
        buttonPlus.setOnClickListener(opListner);
    }
    private void preformOperation(String value, String operation){
//        displayOperation.setText(operation);
        if (operand1 == null){
            operand1 = Double.valueOf(value);

        }
        else {
            operand2 = Double.valueOf(value);
            if (pendingOperation.equals("=")){
                pendingOperation = operation;
            }
            switch (pendingOperation){
                case "=":
                    operand1 = operand2;
                    break;
                case "/":
                    if (operand2 == 0){
                        //dividing by zero crashes the computer
                        operand1 = 0.0;
                    }else{
                        operand1 /= operand2;
                    }
                    break;
                case "*":
                    operand1*=operand2;
                    break;
                case "-":
                    operand1-=operand2;
                    break;
                case "+":
                    operand1+=operand2;
                    break;

            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
    }
}
