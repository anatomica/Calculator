import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.*;

public class CalculatorEngine implements ActionListener {

    Calculator parent;
    private char action; // Арифметический метод
    private char sex;
    private double result = 0; // Результат выражения или значения
    private double displayValue = 0; // Значение на экране
    private int mark = 0; // Метка

    CalculatorEngine(Calculator parent) {
        this.parent = parent; // Отсылка на ActionListener в Calculator
    }

    public void actionPerformed(ActionEvent e) {

        // Получить источник действия
        JButton clickedButton = (JButton) e.getSource();
        String dispFieldText = Calculator.displayField.getText();

        // Получить число из дисплея калькулятора
        if (!"".equals(dispFieldText)) {
            displayValue = Double.parseDouble(dispFieldText);
        }

        Object screen = e.getSource();

        // Отображение вводимых чисел на экране
        String ButtonLabel = clickedButton.getText();
        if (mark == 0 && (ButtonLabel.equals("1") || ButtonLabel.equals("2") ||
                ButtonLabel.equals("3") || ButtonLabel.equals("4") ||
                ButtonLabel.equals("5") || ButtonLabel.equals("6") ||
                ButtonLabel.equals("7") || ButtonLabel.equals("8") ||
                ButtonLabel.equals("9") || ButtonLabel.equals("0"))) {
            Calculator.displayField.setText(dispFieldText + ButtonLabel);
        }

        if (mark == 1 && (ButtonLabel.equals("1") || ButtonLabel.equals("2") ||
                ButtonLabel.equals("3") || ButtonLabel.equals("4") ||
                ButtonLabel.equals("5") || ButtonLabel.equals("6") ||
                ButtonLabel.equals("7") || ButtonLabel.equals("8") ||
                ButtonLabel.equals("9") || ButtonLabel.equals("0"))) {
            Calculator.displayField.setText("" + ButtonLabel);
            mark = 0;
        }

        // Выбор арифметического действия
        if (screen == Calculator.numButtons[4]) {
            result = 0;
            displayValue = 0;
            Calculator.displayField.setText("");
        } else if (screen == Calculator.numButtons[5]) {
            String str = Calculator.displayField.getText();
            if (str != null && str.length() > 0) {
                str = str.substring(0, str.length() - 1);
                Calculator.displayField.setText(str);
            }
            //Calculator.displayField.setText("");
        } else if (screen == Calculator.numButtons[19]) {
            action = '+';
            result = displayValue;
            mark = 1;
            // Calculator.displayField.setText("");
        } else if (screen == Calculator.numButtons[15]) {
            if (displayValue == 0 && Calculator.displayField.getText().equals(""))
                Calculator.displayField.setText(ButtonLabel + "0");
            else {
                action = '-';
                result = displayValue;
                mark = 1;
            }
        } else if (screen == Calculator.numButtons[7]) {
            action = '/';
            result = displayValue;
            mark = 1;
        } else if (screen == Calculator.numButtons[11]) {
            action = '*';
            result = displayValue;
            mark = 1;
        } else if (screen == Calculator.numButtons[22]) {
            if (dispFieldText.indexOf(".") > 0) {
                Calculator.displayField.setText(dispFieldText + "");
            } else {
                Calculator.displayField.setText(dispFieldText + ButtonLabel);
            }
        } else if (screen == Calculator.numButtons[6]) {
            action = '^';
            result = displayValue;
            mark = 1;
        } else if (screen == Calculator.numButtons[1]) {
            action = 'S';
            result = displayValue;
            mark = 1;
        } else if (screen == Calculator.numButtons[0]) {
            action = 'I';
            result = displayValue;
            mark = 1;
        } else if (screen == Calculator.numButtons[2]) {
            sex = 'M';
        } else if (screen == Calculator.numButtons[3]) {
            sex = 'W';
        } else if (screen == Calculator.numButtons[23]) {

            //  Арифметическое действие
            if (action == '+') {
                result = result + displayValue;
                Calculator.displayField.setText("" + result);
            } else if (action == '-') {
                result = result - displayValue;
                Calculator.displayField.setText("" + result);
            } else if (action == '/') {
                if (displayValue == 0) {
                    Calculator.displayField.setText("");
                } else {
                    result = result / displayValue;
                    Calculator.displayField.setText("" + result);
                }
            } else if (action == '*') {
                result = result * displayValue;
                Calculator.displayField.setText("" + result);
            } else if (action == '^') {
                double oldResult = result;
                for (int i = 1; i < displayValue; i++) {
                    result = result * oldResult;
                }
                Calculator.displayField.setText("" + result);
            } else if (action == 'S') {
                double age = Math.pow (0.993, result);
                double mg = displayValue/88.4;
                double skf;
                double GFR = 1;
                if (sex == 'W' && mg <= 0.7) {
                    skf = Math.pow ((mg/0.7), -0.329);
                    GFR = 144 * skf * age;
                }
                if (sex == 'W' && mg > 0.7) {
                    skf = Math.pow ((mg/0.7), -1.209);
                    GFR = 144 * skf * age;
                }
                if (sex == 'M' && mg <= 0.9) {
                    skf = Math.pow ((mg/0.9), -0.411);
                    GFR = 141 * skf * age;
                }
                if (sex == 'M' && mg > 0.9) {
                    skf = Math.pow ((mg/0.9), -1.209);
                    GFR = 141 * skf * age;
                }
                BigDecimal aroundGFR = new BigDecimal(GFR).setScale(0, RoundingMode.HALF_EVEN);
                Calculator.displayField.setText("" + aroundGFR);
            } else if (action == 'I') {
                result = result / ((displayValue /100) * (displayValue / 100));
                BigDecimal aroundIMT = new BigDecimal(result).setScale(1, RoundingMode.HALF_EVEN);
                Calculator.displayField.setText("" + aroundIMT);
            }
        }
    }
}
