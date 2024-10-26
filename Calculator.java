import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
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

        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.setFont(new Font("Arial", Font.BOLD, 24)); // 버튼 폰트
            button.setBackground(Color.WHITE); // 버튼 배경 색상
            button.setForeground(Color.BLUE); // 기본 글자 색상
            button.setFocusPainted(false); // 포커스 시 테두리 없애기

            // 버튼 색상 조정
            if (i % 5 == 4) { // 오른쪽 열
                button.setForeground(Color.RED);
            } else if (i % 5 == 3) { // 오른쪽 1열
                button.setForeground(Color.RED);
            }

            // 버튼 클릭 이벤트 처리
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (button.getText().equals("=")) {
                        // 결과 계산 후 새로운 창에 결과 표시
                        showResult(textField.getText());
                    } else {
                        // 디스플레이에 버튼 텍스트 추가
                        textField.setText(textField.getText() + button.getText());
                    }
                }
            });

            panel.add(button);
        }

        // 패널을 프레임에 추가
        frame.add(panel, BorderLayout.CENTER);

        // 프레임 보이기
        frame.setVisible(true);
    }

    private static void showResult(String expression) {
        // 계산 로직을 여기에 추가
        // 간단한 예: 입력된 수식을 그대로 결과 창에 표시
        JFrame resultFrame = new JFrame("결과");
        resultFrame.setSize(300, 200);
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultFrame.setLayout(new FlowLayout());

        JLabel resultLabel = new JLabel("결과: " + expression);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        resultFrame.add(resultLabel);

        resultFrame.setVisible(true);
    }
}
