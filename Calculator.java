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
        frame.setSize(520, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        
        JTextField textField = new JTextField();
        textField.setEditable(false); 
        frame.add(textField, BorderLayout.NORTH);

      
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,5,2,2));

       
        String[] buttonLabels = {
            "Backspace","","","CE","C",
        	"7", "8", "9", "/","sqrt",
            "4", "5", "6", "*","%",
            "1", "2", "3", "-","1/x",
            "0", "+/-", ".", "+","="
        };
                for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            panel.add(button);
            button.setBackground(Color.YELLOW);
        
        if (i % 5 == 4) {             button.setForeground(Color.RED); // 오른쪽 2열
        } else if (i % 5 == 3) { // 5로 나누었을 때 나머지가 0인 경우 (오른쪽 1열)
            button.setForeground(Color.RED); // 오른쪽 1열
        } else {
            button.setForeground(Color.BLUE);
        }
    }

        // 패널을 프레임에 추가
        frame.add(panel, BorderLayout.CENTER);

        // 프레임 보이기
        frame.setVisible(true);
    }
}
