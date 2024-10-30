import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private static String currentInput = "";
    private static String operator = "";
    private static double firstOperand = 0;
    private static boolean resultDisplayed = false; // 결과가 표시되었는지 여부

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
        frame.getContentPane().setBackground(Color.LIGHT_GRAY); // 배경색 변경

        // 디스플레이 텍스트 필드
        JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.BOLD, 36));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 여백 추가
        frame.add(textField, BorderLayout.NORTH);

        // 버튼 패널
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 5, 5, 5)); // 간격 추가
        panel.setBackground(Color.GRAY); // 패널 배경색 변경

        String[] buttonLabels = {
            "AC", "CE", "", "", "🔙",
            "7", "8", "9", "/", "sqrt",
            "4", "5", "6", "*", "%",
            "1", "2", "3", "-", "i/x",
            "0", "+/-", ".", "+", "="
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 24)); // 버튼 폰트
            button.setBackground(Color.WHITE); // 버튼 배경 색상
            button.setForeground(Color.BLUE); // 기본 글자 색상
            button.setFocusPainted(false); // 포커스 시 테두리 없애기
            button.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1)); // 버튼 테두리 추가
            button.setPreferredSize(new Dimension(80, 80)); // 버튼 크기 조정

            // 기호 버튼은 빨간색으로 설정
            if ("i/xACCE/+-*%/x=sqrt".contains(label)) {
                button.setForeground(Color.RED);
            }

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
            case "AC": // 모든 기록 지우기
                currentInput = "";
                operator = "";
                firstOperand = 0;
                textField.setText("");
                resultDisplayed = false; // 결과 표시 초기화
                break;
            case "CE": // 마지막 입력 지우기
                if (!currentInput.isEmpty() && !resultDisplayed) { // 결과가 표시되지 않았을 때만
                    currentInput = "";
                    textField.setText("");
                }
                break;
            case "🔙": // Backspace 버튼 처리
                if (currentInput.length() > 0 && !resultDisplayed) { // 결과가 표시되지 않았을 때만
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                    textField.setText(currentInput);
                }
                break;
            case "+/-": // 음수/양수 전환
                if (!currentInput.isEmpty()) {
                    double value = Double.parseDouble(currentInput);
                    value = -value; // 부호 변경
                    currentInput = String.valueOf(value);
                    textField.setText(currentInput);
                }
                break;
            case "sqrt": // 제곱근 계산
                if (!currentInput.isEmpty()) {
                    double value = Double.parseDouble(currentInput);
                    if (value < 0) {
                        textField.setText("오류"); // 음수 제곱근에 대한 오류 처리
                    } else {
                        double sqrtValue = Math.sqrt(value);
                        currentInput = String.valueOf(sqrtValue);
                        textField.setText(currentInput);
                        resultDisplayed = true; // 결과가 표시됨
                    }
                }
                break;
            case "%": // 백분율 계산
                if (!currentInput.isEmpty()) {
                    double value = Double.parseDouble(currentInput);
                    double percentValue = value / 100; // 백분율 계산
                    currentInput = String.valueOf(percentValue);
                    textField.setText(currentInput);
                    resultDisplayed = true; // 결과가 표시됨
                }
                break;
            case "i/x": // 역수 계산
                if (!currentInput.isEmpty()) {
                    double value = Double.parseDouble(currentInput);
                    if (value == 0) {
                        textField.setText("오류"); // 0의 역수에 대한 오류 처리
                    } else {
                        double reciprocalValue = 1 / value;
                        currentInput = String.valueOf(reciprocalValue);
                        textField.setText(currentInput);
                        resultDisplayed = true; // 결과가 표시됨
                    }
                }
                break;
            case "=":
                if (!currentInput.isEmpty() && !operator.isEmpty()) {
                    double secondOperand = Double.parseDouble(currentInput);
                    double result = performCalculation(firstOperand, secondOperand, operator);
                    textField.setText(String.valueOf(result));
                    currentInput = String.valueOf(result); // 결과를 currentInput에 저장
                    operator = ""; // 연산자 초기화
                    resultDisplayed = true; // 결과가 표시됨
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
                    resultDisplayed = false; // 새로운 계산을 위한 초기화
                }
                break;
            default:
                currentInput += command;
                textField.setText(currentInput);
                resultDisplayed = false; // 결과가 아닌 경우
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
