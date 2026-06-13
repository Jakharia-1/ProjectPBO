import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

class Calculator_kel8 {
    int windowwidth = 860;
    int windowheight = 1040;

    Color colorFlint = new Color(95, 99, 104);
    Color colorColdGray = new Color(128, 134, 139);
    Color colorVividGamboge = new Color(255, 149, 0);
    Color colorAlexandra = new Color(66, 133, 244);

    String[] buttonValues = {
        "AC", "+/-", "%", "÷", 
        "7", "8", "9", "×", 
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "MN", "="
    };
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};

    JFrame frame = new JFrame("calculator_kel8");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    // Sekarang kita hanya butuh satu variabel memori untuk seluruh layar
    String expression = "0";
    // Variabel penanda jika user baru saja menekan "="
    boolean isCalculated = false; 

    Calculator_kel8() {
        frame.setSize(windowwidth, windowheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        displayLabel.setBackground(colorVividGamboge);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 100));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);

        buttonsPanel.setLayout(new GridLayout(5, 4));
        buttonsPanel.setBackground(colorColdGray);
        frame.add(buttonsPanel);

        for (int i = 0; i < buttonValues.length; i++) {
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(colorColdGray));
            
            if (Arrays.asList(topSymbols).contains(buttonValue)) {
                button.setBackground(colorFlint);
                button.setForeground(Color.white);
            }
            else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                button.setBackground(colorAlexandra);
                button.setForeground(Color.white);
            }
            else {
                button.setBackground(colorColdGray);
                button.setForeground(Color.white);
            }
            
            buttonsPanel.add(button);
            
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton btn = (JButton) e.getSource();
                    String val = btn.getText();
                    
                    try {
                        // 1. Jika menekan AC
                        if (val.equals("AC")) {
                            expression = "0";
                            isCalculated = false;
                        } 
                        // 2. Jika menekan SAMA DENGAN (=)
                        else if (val.equals("=")) {
                            double result = eval(expression); // Panggil mesin hitung
                            expression = removeZeroDecimal(result);
                            isCalculated = true;
                        } 
                        // 3. Jika menekan OPERATOR (+ - × ÷)
                        else if ("+-×÷".contains(val)) {
                            isCalculated = false;
                            char lastChar = expression.charAt(expression.length() - 1);
                            
                            // Mencegah error mengetik "++" atau "×÷"
                            // Jika karakter terakhir adalah operator, ganti dengan operator baru
                            if ("+-×÷".indexOf(lastChar) != -1) {
                                expression = expression.substring(0, expression.length() - 1) + val;
                            } else {
                                expression += val;
                            }
                        } 
                        else if (val.equals("MN")) {
                            new Menu();
                            frame.dispose();
                            

                        }
                        // 4. Jika menekan +/- (Hitung semua yang ada, lalu jadikan negatif)
                        else if (val.equals("+/-")) {
                            double result = eval(expression) * -1;
                            expression = removeZeroDecimal(result);
                            isCalculated = true;
                        }
                        // 5. Jika menekan % (Hitung semua yang ada, lalu bagi 100)
                        else if (val.equals("%")) {
                            double result = eval(expression) / 100;
                            expression = removeZeroDecimal(result);
                            isCalculated = true;
                        }
                        // 6. Jika mengetik ANGKA atau TITIK
                        else {
                            if (expression.equals("0") || isCalculated) {
                                // Jika layar 0, atau baru selesai dihitung, timpa angka barunya
                                if (val.equals(".")) expression = "0.";
                                else expression = val;
                                isCalculated = false;
                            } else {
                                // Jika sedang mengetik, sambung angkanya di belakang
                                expression += val;
                            }
                        }
                        
                        // Selalu perbarui layar setiap kali tombol ditekan
                        displayLabel.setText(expression);
                        
                    } catch (Exception ex) {
                        // Jika pengguna mengetik rumus yang salah/tidak masuk akal
                        displayLabel.setText("Error");
                        expression = "0";
                        isCalculated = true;
                    }
                }
            });
        } 
        frame.setVisible(true); 
    } 

    // --- FUNGSI BANTUAN ESTETIKA ---
    String removeZeroDecimal(double numDisplay) {
        if (numDisplay % 1 == 0) {
            return Integer.toString((int) numDisplay);
        }
        return Double.toString(numDisplay);
    }

    // =========================================================================
    // --- MESIN PARSER MATEMATIKA (EVALUATOR) ---
    // Fungsi canggih ini membaca teks string dari kiri ke kanan, 
    // membedakan mana angka dan mana operator, serta memprioritaskan perkalian & pembagian
    // =========================================================================
    double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Membaca Penjumlahan & Pengurangan
            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); 
                    else if (eat('-')) x -= parseTerm(); 
                    else return x;
                }
            }

            // Membaca Perkalian & Pembagian (Dihitung lebih dulu)
            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('×')) x *= parseFactor(); 
                    else if (eat('÷')) x /= parseFactor(); 
                    else return x;
                }
            }

            // Membaca Angka atau Tanda Negatif di depan angka
            double parseFactor() {
                if (eat('+')) return parseFactor(); 
                if (eat('-')) return -parseFactor(); 

                double x;
                int startPos = this.pos;
                if ((ch >= '0' && ch <= '9') || ch == '.') { 
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Syntax Error");
                }
                return x;
            }
        }.parse();
    }
}