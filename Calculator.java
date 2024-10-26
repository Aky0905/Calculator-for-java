import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    public static void main(String[] args) {
    

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

        panel.add(button);
    }

    // 패널을 프레임에 추가
    frame.add(panel, BorderLayout.CENTER);

    // 프레임 보이기
    frame.setVisible(true);
    }
}
    
