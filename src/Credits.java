import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Credits {
    int windowwidth = 860;
    int windowheight = 1040;

    JFrame frame = new JFrame("Credits - Kelompok 8");

    public Credits() {
        frame.setSize(windowwidth, windowheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        Font fontCredits = new Font("Arial", Font.BOLD, 40);

        
        JLabel jLabel1 = new JLabel("Jakharia Jefferson Girsang(10124611)");
        jLabel1.setFont(fontCredits);
        jLabel1.setForeground(Color.BLACK);
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER); 

        JLabel jLabel2 = new JLabel("Rio Stanley()");
        jLabel2.setFont(fontCredits);
        jLabel2.setForeground(Color.BLACK);
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel jLabel3 = new JLabel("Razaq Ahmad()");
        jLabel3.setFont(fontCredits);
        jLabel3.setForeground(Color.BLACK);
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(3, 1, 0, 30)); 
        textPanel.add(jLabel1);
        textPanel.add(jLabel2);
        textPanel.add(jLabel3);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.add(textPanel);

        frame.add(centerPanel, BorderLayout.CENTER);


        
        JButton backButton = new JButton("Kembali ke Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setPreferredSize(new Dimension(860, 80)); 
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Menu();
                frame.dispose();
            }
        });
        
        frame.add(backButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}