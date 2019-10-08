import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame{

    public static void main (String[] args) {
        new Calculator();
    }

    public static JTextField displayField;
    public static JButton[] numButtons = new JButton[20];

    public Calculator() {
        super ("Калькулятор");
        setSize(350, 500);
        setLocation(new Point(450, 250));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contents = new JPanel(new BorderLayout());

        // Создание табло
        displayField = new JFormattedTextField();
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setPreferredSize (new Dimension(350, 100));
        displayField.setBackground(Color.lightGray);
        displayField.setFont(new Font("Some-Font-Name", Font.BOLD, 35));
        contents.add("North", displayField);

        // Генерация кнопок
        String[] button = {"C", "<=", "(^)", "/", "1", "2", "3", "*", "4", "5", "6", "-", "7", "8", "9", "+", "", "0", ".", "="};
        for (int i = 0 ; i < button.length ; i ++) {
            for (int j = 0; j < numButtons.length ; j++) {
                if (i == j)
                    numButtons[j] = new JButton(button[i]);
            }
        }

        // Размещение кнопок на панели
        JPanel Buttons;
        Buttons = new JPanel();
        GridLayout layout = new GridLayout(5,4, 5,5);
        Buttons.setLayout(layout);
        for (int i = 0; i < numButtons.length; i++) {
            JButton jButton = numButtons[i];
            if (i >= 4 && i <= 6 || i >= 8 && i <= 10 || i >= 12 && i <= 14 || i == 17) {
                jButton.setBackground(Color.white);
            }
            Buttons.add(jButton);
        }
        Buttons.setBackground(Color.lightGray);
        contents.add("Center", Buttons);


        // Слушаем кнопки
        CalculatorEngine calcEngine = new CalculatorEngine(this);
        for (JButton numButton : numButtons) {
            numButton.addActionListener(calcEngine);
        }

        // Размещение панели в окне
        setContentPane(contents);
        setVisible (true);

    }
}
