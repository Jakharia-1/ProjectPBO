import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu {
    int windowwidth = 860;
    int windowheight = 1040;

    JFrame frame = new JFrame("Menu Utama");

    Menu () {
        frame.setSize(windowwidth, windowheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JButton button1 = new JButton("Kalkulator");
        button1.setPreferredSize(new Dimension(250, 60)); 
        button1.setFont(new Font("Arial", Font.BOLD, 20));

        JButton button2 = new JButton("Credits");
        button2.setPreferredSize(new Dimension(250, 60)); 
        button2.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new GridLayout(2, 1, 0, 20));
        buttonContainer.add(button1);
        buttonContainer.add(button2);

        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0)); 
        topPanel.add(buttonContainer);

        frame.add(topPanel, BorderLayout.NORTH);


        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Calculator_kel8();
                frame.dispose(); 
            }
        });
        
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Credits(); 
                frame.dispose(); 
            }
        });

        frame.setVisible(true);
    }
}