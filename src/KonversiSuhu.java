import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KonversiSuhu {
    int windowwidth = 860;
    int windowheight = 1040;
    private JTextField inputCelcius, outputFahrenheit, outputKelvin, outputReamur;
    private JButton btnKonversi;

    JFrame frame = new JFrame("Konversi Suhu");

    public KonversiSuhu() {
        frame.setSize(windowwidth, windowheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Layout utama frame

        // 1. Buat Panel baru dengan GridLayout untuk menampung form input
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(5, 2, 10, 20));
        // Beri margin/padding agar form tidak menempel ke ujung layar
        panelForm.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); 

        Font fontCredits = new Font("Arial", Font.BOLD, 80);

        // 2. Tambahkan komponen ke dalam 'panelForm' (BUKAN langsung add)
        panelForm.add(new JLabel(" Suhu Celcius (°C):"));
        inputCelcius = new JTextField();
        panelForm.add(inputCelcius);

        panelForm.add(new JLabel(" Fahrenheit (°F):"));
        outputFahrenheit = new JTextField();
        outputFahrenheit.setEditable(false);
        panelForm.add(outputFahrenheit);

        panelForm.add(new JLabel(" Kelvin (K):"));
        outputKelvin = new JTextField();
        outputKelvin.setEditable(false);
        panelForm.add(outputKelvin);

        panelForm.add(new JLabel(" Reamur (°R):"));
        outputReamur = new JTextField();
        outputReamur.setEditable(false);
        panelForm.add(outputReamur);

        // 3. Menambahkan Tombol Eksekusi
        panelForm.add(new JLabel("")); // Kosongkan sel kiri bawah
        btnKonversi = new JButton("Konversi");
        panelForm.add(btnKonversi);

        // 4. Masukkan panelForm ke dalam Frame di posisi Atas (NORTH)
        frame.add(panelForm, BorderLayout.NORTH);

        // 5. Logika saat tombol "Konversi" diklik
        btnKonversi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double c = Double.parseDouble(inputCelcius.getText());
                    outputFahrenheit.setText(String.format("%.2f", (c * 9 / 5) + 32));
                    outputKelvin.setText(String.format("%.2f", c + 273.15));
                    outputReamur.setText(String.format("%.2f", c * 4 / 5));
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Masukkan angka yang valid!", "Error Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 6. WAJIB DITAMBAHKAN: Tampilkan Frame
        frame.setVisible(true);
    }
}