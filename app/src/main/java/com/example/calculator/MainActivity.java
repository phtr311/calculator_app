package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText calculation, result;
    private Button btnC;
    private Button btnCE;
    private Button btnDelete;
    private Button btnsum;
    private Button btnsub;
    private Button btnmulti;
    private Button btndiv;
    private Button btnsign;
    private Button btnResult;
    private Button[] btnNumber;

    String before_cal, ans, current_result;

    boolean operator, before_op;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculation = findViewById(R.id.calculation);
        result = findViewById(R.id.result);
        calculation.getText().clear();

        btnNumber = new Button[10];

        btnNumber[0] = findViewById(R.id.btn0);
        btnNumber[1] = findViewById(R.id.btn1);
        btnNumber[2] = findViewById(R.id.btn2);
        btnNumber[3] = findViewById(R.id.btn3);
        btnNumber[4] = findViewById(R.id.btn4);
        btnNumber[5] = findViewById(R.id.btn5);
        btnNumber[6] = findViewById(R.id.btn6);
        btnNumber[7] = findViewById(R.id.btn7);
        btnNumber[8] = findViewById(R.id.btn8);
        btnNumber[9] = findViewById(R.id.btn9);
        btnsum = findViewById(R.id.btnSum);
        btnsub = findViewById(R.id.btnSub);
        btnmulti = findViewById(R.id.btnMulti);
        btndiv = findViewById(R.id.btnDiv);
        btnC = findViewById(R.id.btnC);
        btnCE = findViewById(R.id.btnCE);
        btnDelete = findViewById(R.id.btnDelete);
        btnsign = findViewById(R.id.btnSign);
        btnResult = findViewById(R.id.btnResult);

        before_cal = "";
        ans = "";
        current_result = "0";
        operator = false;
        before_op = false;

        for (int i = 0; i < 10; i++) {
            int current_resultKey = i;
            btnNumber[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    numberButton(Integer.toString(current_resultKey));
                    show();
                }
            });
        }

        btnC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                actionC();
                show();
                showPast();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!before_op) {
                    actionDel();
                    show();
                }
            }
        });

        btnCE.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                current_result = "0";
                operator = false;
                before_op = false;
                show();
            }
        });

        btnsign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!current_result.equals("0")) {
                    if (current_result.charAt(0) == '-') current_result = current_result.substring(1, current_result.length());
                    else current_result = "-" + current_result;
                    show();
                }
                if (before_op) {
                    if (ans.charAt(0) == '-') ans = ans.substring(1, ans.length());
                    else ans = "-" + ans;
                    showRes();
                }

            }
        });

        btndiv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (current_result.charAt(current_result.length() - 1) == '.') {
                    actionDel();
                }
                if (!operator) {
                    before_cal = current_result;
                    before_cal = before_cal + "÷";
                    ans = current_result;
                    showRes();
                    current_result = "0";
                    operator = true;
                    before_op = true;
                }
                if (before_op) changeOperator("÷");
                showPast();
            }
        });

        btnmulti.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (current_result.charAt(current_result.length() - 1) == '.') {
                    actionDel();
                }
                if (!operator) {
                    before_cal = current_result;
                    before_cal = before_cal + "×";
                    ans = current_result;
                    showRes();
                    current_result = "0";
                    operator = true;
                    before_op = true;
                }
                if (before_op) changeOperator("×");
                showPast();
            }
        });

        btnsum.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (current_result.charAt(current_result.length() - 1) == '.') {
                    actionDel();
                }
                if (!operator) {
                    before_cal = current_result;
                    before_cal = before_cal + "+";
                    ans = current_result;
                    showRes();
                    current_result = "0";
                    operator = true;
                    before_op = true;
                }
                if (before_op) changeOperator("+");
                showPast();
            }
        });

        btnsub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (current_result.charAt(current_result.length() - 1) == '.') {
                    actionDel();
                }
                if (!operator) {
                    before_cal = current_result;
                    before_cal = before_cal + "-";
                    ans = current_result;
                    showRes();
                    current_result = "0";
                    operator = true;
                    before_op = true;
                }
                if (before_op) changeOperator("-");
                showPast();
            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (current_result.charAt(current_result.length() - 1) == '.') {
                    actionDel();
                }
                if (!operator) {
                    before_cal = current_result + "=";
                    ans = current_result;
                    current_result = "0";
                    showRes();
                } else {
                    String operator_text = before_cal.substring(before_cal.length() - 1, before_cal.length());
                    String first_value = before_cal.substring(0, before_cal.length() - 1);
                    switch (operator_text) {
                        case "+" : if (!before_op) {
                            before_cal = before_cal + current_result + "=";
                            current_result = Double.toString(Double.parseDouble(first_value) + Double.parseDouble(current_result));

                        }
                        else {
                            before_cal = before_cal + ans + "=";
                            current_result = Double.toString(Double.parseDouble(first_value) + Double.parseDouble(ans));
                        }
                        ans = current_result;
                        break;
                        case "-" : if (!before_op) {
                            before_cal = before_cal + current_result + "=";
                            current_result = Double.toString(Double.parseDouble(first_value) - Double.parseDouble(current_result));
                        }
                        else {
                            before_cal = before_cal + ans + "=";
                            current_result = Double.toString(Double.parseDouble(first_value) - Double.parseDouble(ans));
                        }
                        ans = current_result;
                        break;
                        case "×" : if (!before_op) {
                            before_cal = before_cal + current_result + "=";
                            current_result = Double.toString(Double.parseDouble(first_value) * Double.parseDouble(current_result));

                        }
                        else {
                            before_cal = before_cal + ans + "=";
                            current_result = Double.toString(Double.parseDouble(first_value) * Double.parseDouble(ans));
                        }
                        ans = current_result;
                        break;
                        case "÷" : if (!before_op) {
                            before_cal = before_cal + current_result + "=";
                            current_result = Double.toString(Double.parseDouble(first_value) / Double.parseDouble(current_result));

                        }
                        else {
                            before_cal = before_cal + ans + "=";
                            current_result = Double.toString(Double.parseDouble(first_value) / Double.parseDouble(ans));
                        }
                        ans = current_result;
                        break;

                    }

                    if (current_result.equals("-0")) current_result = "0";
                    if (current_result.equals("-Infinity")) current_result = "Infinity";
                    show();
                }
                showPast();
            }
        });
    }

    public void numberButton(String numberIn) {
        if (current_result.equals("0")) current_result = "";
        if (operator) before_op = false;
        current_result = current_result + numberIn;
        show();
    }

    public void showRes() {

        result.setText(ans);
    }

    public void show() {
        delLong();
        result.setText(current_result);
    }

    public void showPast() {

        calculation.setText(before_cal);
    }

    public void actionC() {
        current_result = "0";
        ans = "";
        before_cal ="";
        operator = false;
        before_op = false;
    }

    public void actionDel() {
        if (!current_result.equals("0")) {
            current_result = current_result.substring(0, current_result.length() - 1);
            if (current_result.isEmpty()) current_result = "0";
        }
    }

    public void changeOperator (String newOperator){
        before_cal = before_cal.substring(0, before_cal.length() - 1);
        before_cal = before_cal + newOperator;
    }

    public void delLong(){
        int max_length;
        if (!current_result.contains(".")) max_length = 10;
        else max_length = 11;
        while (current_result.length() > max_length) {
            actionDel();
        }
    }

}
