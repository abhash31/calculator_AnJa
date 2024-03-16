package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView expressionStr, resultStr, hiddenExpStr;
    Button buttonAC, buttonDel, buttonEqual;
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, button00;
    Button buttonPercent, buttonDivide, buttonAdd, buttonSub, buttonMulti;
    Button buttonDecimal;

    Double firstNumber = 0.0;
    Double result;
    String op;
    int flag=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        ArrayList<Button> buttonlist = new ArrayList<>();
        buttonlist.add(button0);
        buttonlist.add(button1);
        buttonlist.add(button2);
        buttonlist.add(button3);
        buttonlist.add(button4);
        buttonlist.add(button5);
        buttonlist.add(button6);
        buttonlist.add(button7);
        buttonlist.add(button8);
        buttonlist.add(button9);
        buttonlist.add(button0);
        buttonlist.add(button00);

        for(Button btn:buttonlist){
            btn.setOnClickListener(view -> {
                if(flag==1 || expressionStr.getText().toString().equals("0")) {
                    if(flag==1) resultStr.setText("");
                    if(!btn.getText().toString().equals("00")) {
                        expressionStr.setText(btn.getText().toString());
                        hiddenExpStr.setText(btn.getText().toString());
                        flag=0;
                    }
                }
                else {
                    String str = expressionStr.getText().toString() + btn.getText().toString();
                    String str2 = hiddenExpStr.getText().toString() + btn.getText().toString();
                    expressionStr.setText(str);
                    hiddenExpStr.setText(str2);
                }
            });
        }

        ArrayList<Button> opertorList = new ArrayList<>();
        opertorList.add(buttonAdd);
        opertorList.add(buttonSub);
        opertorList.add(buttonDivide);
        opertorList.add(buttonMulti);
        opertorList.add(buttonPercent);

        for(Button btn:opertorList){
            btn.setOnClickListener(view -> {
                int minusFlag=0;
                op = btn.getText().toString();
                flag=0;
                String str="";
                if(expressionStr.getText().toString().endsWith(op) || expressionStr.getText().toString().equals("0") && !op.equals("-")) return;
                switch (op){
                    case "+":
                        str = expressionStr.getText().toString()+"+";
                        break;
                    case "-":
                        if(expressionStr.getText().toString().equals("0")) {
                            expressionStr.setText("");
                            minusFlag=1;
                        }
                        str = expressionStr.getText().toString()+"-";
                        break;
                    case "x":
                        str = expressionStr.getText().toString()+"x";
                        break;
                    case "/":
                        str = expressionStr.getText().toString()+"/";
                        break;
                    case "%":
                        str = expressionStr.getText().toString()+"%";
                        break;
                }
                if(!resultStr.getText().toString().equals("")) firstNumber = Double.parseDouble((resultStr.getText().toString()));
                else firstNumber = Double.parseDouble(hiddenExpStr.getText().toString());
                expressionStr.setText(str);
                hiddenExpStr.setText("0");
                if(minusFlag==1) hiddenExpStr.setText("-");
                minusFlag=0;
            });
        }

        buttonEqual.setOnClickListener(view -> {
            if(firstNumber==0) resultStr.setText("");
            else {
                try {
                    Double secondNumber = Double.parseDouble(hiddenExpStr.getText().toString());
                    result = 0.0;
                    if (op.equals("")) return;
                    else if (op.equals("+")) result = firstNumber + secondNumber;
                    else if (op.equals("-")) result = firstNumber - secondNumber;
                    else if (op.equals("x")) result = firstNumber * secondNumber;
                    else if (op.equals("/")) {
                        if (secondNumber != 0) result = firstNumber / secondNumber;
                    }
                    else if(op.equals("%")){
                        result = (firstNumber/100)*secondNumber;
                    }
                    if (result.toString().endsWith(".0")) {
                        String intResult = result.toString().substring(0, result.toString().length() - 2);
                        resultStr.setText(intResult);
                    } else resultStr.setText(String.format(result.toString()));
                    flag = 1;
                    op = "";
                } catch (Exception ignored) {}
            }
        });

        buttonDecimal.setOnClickListener(view -> {
            if(!hiddenExpStr.getText().toString().contains(".")){
                String str = expressionStr.getText().toString() + ".";
                String str2 = hiddenExpStr.getText().toString() + ".";
                expressionStr.setText(str);
                hiddenExpStr.setText(str2);
            }
        });

        buttonAC.setOnClickListener(view -> {
            expressionStr.setText("0");
            hiddenExpStr.setText("0");
            resultStr.setText("");
        });

        //NEED SEVERAL IMPROVEMENTS
        buttonDel.setOnClickListener(view -> {
            if(hiddenExpStr.getText().length()>1){
                hiddenExpStr.setText(hiddenExpStr.getText().toString().substring(0, hiddenExpStr.getText().length()-1));
            }
            if(expressionStr.getText().length()>1){
                expressionStr.setText(expressionStr.getText().toString().substring(0, expressionStr.getText().length()-1));
            } else {
                expressionStr.setText("0");
                hiddenExpStr.setText("0");
            }
        });

    }

    void init(){
        expressionStr = findViewById(R.id.expression_text);
        hiddenExpStr = findViewById(R.id.hiddenExp_text);
        resultStr = findViewById(R.id.solution_text);

        buttonAC = findViewById(R.id.button_ac);
        buttonDel = findViewById(R.id.button_delete);
        buttonEqual = findViewById(R.id.button_equal);

        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);
        button7 = findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);
        button9 = findViewById(R.id.button_9);
        button0 = findViewById(R.id.button_0);
        button00 = findViewById(R.id.button_00);

        buttonPercent = findViewById(R.id.button_percent);
        buttonDivide = findViewById(R.id.button_divide);
        buttonAdd = findViewById(R.id.button_plus);
        buttonSub = findViewById(R.id.button_subtract);
        buttonMulti = findViewById(R.id.button_multiply);

        buttonDecimal = findViewById(R.id.button_decimal);
    }
}