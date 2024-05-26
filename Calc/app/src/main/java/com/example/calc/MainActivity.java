package com.example.calc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView resultTextView;
    String operand = "";
    String operation = "";
    Double firstNumber = null;
    Double secondNumber = null;
    boolean isNewOperation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        if (isNewOperation) {
            resultTextView.setText("");
            operand = "";
            isNewOperation = false;
        }
        operand += button.getText().toString();
        resultTextView.setText(resultTextView.getText().toString() + button.getText().toString());
    }

    public void onOperationClick(View view) {
        Button button = (Button) view;
        operation = button.getText().toString();

        if (operation.equals("log") || operation.equals("sqrt")) {
            resultTextView.setText(operation + "(");
        } else if (!operand.isEmpty() && firstNumber == null) {
            firstNumber = Double.parseDouble(operand);
            operand = "";
            resultTextView.setText(resultTextView.getText().toString() + " " + operation + " ");
        }
    }

    public void onCalculateClick(View view) {
        if (!operand.equals("") || operation.equals("log") || operation.equals("sqrt")) {
            if (operation.equals("log") || operation.equals("sqrt")) {
                firstNumber = Double.parseDouble(operand);
            } else {
                secondNumber = Double.parseDouble(operand);
            }

            Double result = null;

            switch (operation) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    result = firstNumber / secondNumber;
                    break;
                case "log":
                    result = Math.log(firstNumber);
                    break;
                case "^":
                    result = Math.pow(firstNumber, secondNumber);
                    break;
                case "sqrt":
                    result = Math.sqrt(firstNumber);
                    break;
                case "%":
                    result = firstNumber * (secondNumber / 100);
                    break;
            }

            if (result != null) {
                resultTextView.setText(result.toString());
                firstNumber = result;
                operand = "";
                operation = "";
                isNewOperation = true;
            }
        }
    }

    public void onClearClick(View view) {
        operand = "";
        operation = "";
        firstNumber = null;
        secondNumber = null;
        resultTextView.setText("");
    }
}
