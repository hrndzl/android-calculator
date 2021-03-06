package com.hrndzl.mobilecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fathzer.soft.javaluator.DoubleEvaluator;

public class MainActivity extends AppCompatActivity {
    TextView display;
    StringBuffer sb;
    boolean equalsClicked, operatorClicked, dotClicked, numberClicked = false;
    boolean dotClickable = true;
    String[] operators = {"+", "-", "*", "/"};
    int dotCounter, operatorCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.textView);
    }

    public String getDisplayText() {
        return display.getText().toString();
    }

    public void printNumber(String number) {
        if (equalsClicked && !dotClicked) {
            display.setText(number);
            equalsClicked = false;
        }

        else {
            display.setText(display.getText() + number);
        }
        dotClicked = false;
        operatorClicked = false;
        numberClicked = true;
    }

    public String lastChar(String lastCharInput) {
        return (lastCharInput.substring(lastCharInput.length() - 1));
    }

    public void printOperator(String operator) {
        try {
            if (getDisplayText().equals("") && operator.equals("-")) {
                    display.setText(operator);
                    operatorClicked = true;
            }
            else {
                for (int i = 0; i < operators.length - 1; i++) {
                    if (!lastChar(getDisplayText()).equals(".") || !lastChar(getDisplayText()).equals(operators[i])) {
                        if (operatorClicked) {}
                        else if (dotClicked) {}
                        else {
                            display.setText(display.getText() + operator);
                            operatorClicked = true;
                            dotClicked = false;
                            equalsClicked = false;
                            numberClicked = false;
                            dotClickable = true;
                            operatorCounter++;
                        }
                        break;
                    }
                }
            }
        }
        catch (Exception e) {}
    }

    public void clear(View view) {
        display.setText("");
        dotClicked = false;
        dotClickable = true;
        operatorClicked = false;
        equalsClicked = false;
        numberClicked = false;
        operatorCounter = 0;
        dotCounter = 0;
    }

    public void delete(View view) {
        try {
            if (lastChar(getDisplayText()).equals(".")) {
                dotCounter--;
                dotClickable = true;
                dotClicked = false;
            }

            for (int i = 0; i <= operators.length - 1; i++) {
                if (lastChar(getDisplayText()).equals(operators[i])) {
                    operatorCounter--;
                    operatorClicked = false;
                }
            }
            sb = new StringBuffer(getDisplayText());
            sb.deleteCharAt(sb.length() - 1);
            display.setText(sb);
        }
        catch (Exception e) {}
    }

    public void clickZero(View view) {
        printNumber("0");
    }

    public void clickOne(View view) {
        printNumber("1");
    }

    public void clickTwo(View view) {
        printNumber("2");
    }

    public void clickThree(View view) {
        printNumber("3");
    }

    public void clickFour(View view) {
        printNumber("4");
    }

    public void clickFive(View view) {
        printNumber("5");
    }

    public void clickSix(View view) {
        printNumber("6");
    }

    public void clickSeven(View view) {
        printNumber("7");
    }

    public void clickEight(View view) {
        printNumber("8");
    }

    public void clickNine(View view) {
        printNumber("9");
    }

    public void clickDot(View view) {
        if (dotClickable) {
            for (int i = 0; i <= operators.length - 1; i++) {

                if (dotClicked) {
                    break;
                }

                else if (getDisplayText().equals("")) {
                    display.setText("0.");
                    dotClicked = true;
                    dotClickable = false;
                    dotCounter++;
                    break;
                }

                else if (lastChar(getDisplayText()).equals(operators[i])) {
                    display.setText(display.getText() + "0.");
                    dotClicked = true;
                    dotClickable = false;
                    dotCounter++;
                    break;
                }

                else if (numberClicked) {
                    display.setText(display.getText() + ".");
                    dotClicked = true;
                    dotClickable = false;
                    dotCounter++;
                    break;
                }
            }
        }
    }

    public void clickAddition(View view) {
        printOperator("+");
    }

    public void clickSubstraction(View view) {
        printOperator("-");
    }

    public void clickMultiplication(View view) {
        printOperator("*");
    }

    public void divide(View view) {
        printOperator("/");
    }

    public void clickEquals(View view) {
        try {
            DoubleEvaluator evaluator = new DoubleEvaluator();
            String finalProduct = (String) display.getText();
            Double result = evaluator.evaluate(finalProduct);
            String doubleTo = Double.toString(result);

            if (lastChar(doubleTo).equals("0")) {
                sb = new StringBuffer(doubleTo);
                sb.deleteCharAt(sb.length() - 1);
                sb.deleteCharAt(sb.length() - 1);
                display.setText(sb);
                dotClicked = false;
            }

            else {
                display.setText(doubleTo);
            }

            equalsClicked = true;
            operatorClicked = false;
            numberClicked = true;
        }
        catch (Exception e) {}
    }
}