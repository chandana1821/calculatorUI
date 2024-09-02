import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorUI {
    private JFrame frame;
    private JTextField TextField;
    private JPanel panel;

    private String operator;
    private double num1, num2, result;

    public CalculatorUI() {
        frame = new JFrame("Calculator");
        TextField = new JTextField();
        panel = new JPanel();
        operator = "";
        num1 = num2 = result = 0;

        TextField.setEditable(false);
        TextField.setHorizontalAlignment(SwingConstants.RIGHT);
        TextField.setFont(new Font("Arial", Font.PLAIN, 22));

        String[] buttons = {
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", "AC", "=", "+"
        };

        panel.setLayout(new GridLayout(4, 4, 5, 5));

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        frame.setLayout(new BorderLayout());
        frame.add(TextField, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.charAt(0) >= '0' && command.charAt(0) <= '9' ) {
                TextField.setText(TextField.getText() + command);
            } else if (command.equals("=")) {
                num2 = Double.parseDouble(TextField.getText());
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/": result = num1 / num2; break;
                    
                    
                }
                TextField.setText(String.valueOf(result));
                operator = "";
            }
                else if(command.equals("AC")){
                    TextField.setText("");
                    operator="";
                    num1=num2=result=0;
                }
             else {
                if (!TextField.getText().isEmpty()) {
                    num1 = Double.parseDouble(TextField.getText());
                    operator = command;
                    TextField.setText("");
                }
            }
        }
    }

    public static void main(String[] args) {
        new CalculatorUI();
    }
}