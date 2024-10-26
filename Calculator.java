import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private static String currentInput = "";
    private static String operator = "";
    private static double firstOperand = 0;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("김재영의 계산기");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // 디스플레이 텍스트 필드
        JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.PLAIN, 36));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(textField, BorderLayout.NORTH);

        // 버튼 패널
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 5, 5, 5)); // 간격 추가

        String[] buttonLabels = {
            "℀", "CE", "", "", "🔙",
            "7", "8", "9", "/", "sqrt",
            "4", "5", "6", "*", "%",
            "1", "2", "3", "-", "1/x",
            "0", "+/-", ".", "+", "="
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 24)); // 버튼 폰트
            button.setBackground(Color.WHITE); // 버튼 배경 색상
            button.setForeground(Color.BLUE); // 기본 글자 색상
            button.setFocusPainted(false); // 포커스 시 테두리 없애기

            // 버튼 클릭 이벤트 처리
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleButtonPress(label, textField);
                }
            });

            panel.add(button);
        }

        // 패널을 프레임에 추가
        frame.add(panel, BorderLayout.CENTER);

        // 프레임 보이기
        frame.setVisible(true);
    }

    private static void handleButtonPress(String command, JTextField textField) {
        switch (command) {
            case "C":
                currentInput = "";
                operator = "";
                firstOperand = 0;
                textField.setText("");
                break;
            case "CE":
                currentInput = "";
                textField.setText("");
                break;
            case "🔙": // Backspace 버튼 처리
                if (currentInput.length() > 0) {
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                    textField.setText(currentInput);
                }
                break;
            case "=":
                if (!currentInput.isEmpty() && !operator.isEmpty()) {
                    double secondOperand = Double.parseDouble(currentInput);
                    double result = performCalculation(firstOperand, secondOperand, operator);
                    textField.setText(String.valueOf(result));
                    currentInput = "";
                    operator = "";
                }
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                if (!currentInput.isEmpty()) {
                    firstOperand = Double.parseDouble(currentInput);
                    operator = command;
                    currentInput = "";
                }
                break;
            default:
                currentInput += command;
                textField.setText(currentInput);
                break;
        }
    }

    private static double performCalculation(double first, double second, String operator) {
        switch (operator) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "*":
                return first * second;
            case "/":
                return first / second;
            default:
                return second;
        }
    }
}
