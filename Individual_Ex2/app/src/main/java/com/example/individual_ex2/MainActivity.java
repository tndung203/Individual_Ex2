package com.example.individual_ex2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText display;
    private double value1 = 0;
    private double value2 = 0;
    private String operator = "";
    private boolean newValue = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Number buttons
        setNumberButtonListener(R.id.btn_0, "0");
        setNumberButtonListener(R.id.btn_1, "1");
        setNumberButtonListener(R.id.btn_2, "2");
        setNumberButtonListener(R.id.btn_3, "3");
        setNumberButtonListener(R.id.btn_4, "4");
        setNumberButtonListener(R.id.btn_5, "5");
        setNumberButtonListener(R.id.btn_6, "6");
        setNumberButtonListener(R.id.btn_7, "7");
        setNumberButtonListener(R.id.btn_8, "8");
        setNumberButtonListener(R.id.btn_9, "9");

        // Operator buttons
        setOperatorButtonListener(R.id.btn_plus, "+");
        setOperatorButtonListener(R.id.btn_minus, "-");
        setOperatorButtonListener(R.id.btn_multiply, "*");
        setOperatorButtonListener(R.id.btn_divide, "/");

        // Percentage button
        findViewById(R.id.btn_percent).setOnClickListener(v -> {
            double currentValue = Double.parseDouble(display.getText().toString());
            double result = currentValue / 100;
            display.setText(String.valueOf(result));
            newValue = true;
        });

        // Delete button
        findViewById(R.id.btn_delete).setOnClickListener(v -> {
            String currentText = display.getText().toString();
            if (currentText.length() > 1) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            } else {
                display.setText("0");
                value1 = 0;
                value2 = 0;
                operator = "";
                newValue = true;
            }
        });

        // Equals button
        findViewById(R.id.btn_equals).setOnClickListener(v -> {
            if (!operator.isEmpty()) {
                value2 = Double.parseDouble(display.getText().toString());
                double result = calculate();
                display.setText(String.valueOf(result));
                value1 = result;
                operator = "";
                newValue = true;
            }
        });
    }

    private void setNumberButtonListener(int buttonId, final String number) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            String currentText = display.getText().toString();
            if (newValue || currentText.equals("0")) {
                display.setText(number);
                newValue = false;
            } else {
                display.setText(currentText + number);
            }
        });
    }

    private void setOperatorButtonListener(int buttonId, final String op) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            if (!operator.isEmpty()) {
                value2 = Double.parseDouble(display.getText().toString());
                value1 = calculate();
                display.setText(String.valueOf(value1));
            } else {
                value1 = Double.parseDouble(display.getText().toString());
            }
            operator = op;
            newValue = true;
        });
    }

    private double calculate() {
        switch (operator) {
            case "+":
                return value1 + value2;
            case "-":
                return value1 - value2;
            case "*":
                return value1 * value2;
            case "/":
                if (value2 != 0) {
                    return value1 / value2;
                } else {
                    display.setText("Error");
                    return 0;
                }
            default:
                return value1;
        }
    }
}