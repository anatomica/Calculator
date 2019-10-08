import java.awt.event.*;
import javax.swing.*;

public class CalculatorEngine implements ActionListener {

    Calculator parent;
    private char action; // Арифметический метод
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
        if (screen == Calculator.numButtons[0]) {
            result = 0;
            displayValue = 0;
            Calculator.displayField.setText("");
        } else if (screen == Calculator.numButtons[1]) {
            Calculator.displayField.setText("");
        } else if (screen == Calculator.numButtons[15]) {
            action = '+';
            result = displayValue;
            mark = 1;
            // Calculator.displayField.setText("");
        } else if (screen == Calculator.numButtons[11]) {
            action = '-';
            result = displayValue;
            mark = 1;
        } else if (screen == Calculator.numButtons[3]) {
            action = '/';
            result = displayValue;
            mark = 1;
        } else if (screen == Calculator.numButtons[7]) {
            action = '*';
            result = displayValue;
            mark = 1;
        } else if (screen == Calculator.numButtons[18]) {
            if (dispFieldText.indexOf(".") > 0) {
                Calculator.displayField.setText(dispFieldText + "");
            } else {
                Calculator.displayField.setText(dispFieldText + ButtonLabel);
            }
        } else if (screen == Calculator.numButtons[2]) {
            action = '^';
            result = displayValue;
            mark = 1;
        } else if (screen == Calculator.numButtons[19]) {

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
            }
        }
    }
}
