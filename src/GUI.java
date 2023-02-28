import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {
    final String_Op o = new String_Op();
    final JFrame frame = new JFrame("Calculator");
    final JPanel mainPanel = new JPanel(new BorderLayout());
    final JTextField textField = new JTextField(10);
    final JPanel math = new JPanel(new GridLayout(4, 0));
    final JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
    final JPanel math2 = new JPanel(new GridLayout(3, 0));
    final JPanel math3 = new JPanel(new GridLayout(0, 4));
    final JButton par1 = new JButton("(");
    final JButton par2 = new JButton(")");
    final JButton pow = new JButton("^");
    final JButton sqrt = new JButton("√");
    final JButton equals = new JButton("=");
    final JButton zero = new JButton("0");
    final JButton comma = new JButton(",");
    final JButton modulus = new JButton("%");
    final JButton reverse = new JButton("<-");
    final JButton deleteAll = new JButton("C");

    GUI(){
        createCalculator();
    }

    List<JButton> createNumericButtons() {
        List<JButton> b = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            JButton button = new JButton(String.valueOf(i));
            b.add(button);
        }
        return b;
    }

    List<JButton> createMathematicalButtons() {
        String[] b = new String[]{"+", "-", "/", "*"};
        List<JButton> b2 = new ArrayList<>();
        for (String s : b) {
            JButton button = new JButton(s);
            b2.add(button);
        }
        return b2;
    }

    void setColors(JButton button, int c) {
        if (c == 1) {
            button.setBackground(Color.gray);
            button.setForeground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(Color.white));
        } else {
            button.setBackground(Color.darkGray);
            button.setForeground(Color.WHITE);
        }
    }

    void setTextOnDisplay(JButton button) {
        textField.setText(textField.getText() + button.getText());
    }

    void createCalculator() {

        frame.add(mainPanel);

        for (JButton button : createNumericButtons()) {
            buttonPanel.add(button);
            setColors(button, 1);
            button.addActionListener(e -> setTextOnDisplay(button));
        }
        buttonPanel.add(zero);
        zero.addActionListener(e -> setTextOnDisplay(zero));
        setColors(zero, 1);
        zero.setBorder(BorderFactory.createLineBorder(Color.white));
        buttonPanel.add(equals);
        buttonPanel.add(comma);
        comma.addActionListener(e -> setTextOnDisplay(comma));
        setColors(equals, 0);
        setColors(comma, 0);
        equals.addActionListener(e -> textField.setText(o.parseExpressionToTokens(String.valueOf(textField.getText()))));


        for (JButton button : createMathematicalButtons()) {
            math.add(button);
            setColors(button,0);
            button.addActionListener(e -> setTextOnDisplay(button));

        }
        math2.add(reverse);
        math2.add(deleteAll);
        math2.add(modulus);
        setColors(modulus, 0);
        setColors(deleteAll, 0);
        setColors(reverse, 0);

        modulus.addActionListener(e -> textField.setText(textField.getText() + modulus.getText()));
        deleteAll.addActionListener(e -> textField.setText(o.deleteAll(textField.getText())));
        reverse.addActionListener(e -> textField.setText(o.deleteChar(textField.getText())));

        textField.setBorder(BorderFactory.createLineBorder(Color.black));
        textField.setPreferredSize(new Dimension(20, 40));
        Font boldFont = new Font(textField.getFont().getName(), Font.BOLD, textField.getFont().getSize());
        textField.setFont(boldFont);
        Border border = BorderFactory.createLineBorder(Color.darkGray, 5, true);
        textField.setBorder(border);

        math3.add(pow);
        math3.add(sqrt);
        math3.add(par1);
        math3.add(par2);
        setColors(pow, 0);
        setColors(sqrt, 0);
        setColors(par1, 0);
        setColors(par2, 0);

        pow.addActionListener(e -> setTextOnDisplay(pow));
        sqrt.addActionListener(e -> {
            setTextOnDisplay(sqrt);
            textField.setText(o.parseExpressionToTokens(String.valueOf(textField.getText())));
        });
        par1.addActionListener(e -> setTextOnDisplay(par1));
        par2.addActionListener(e -> setTextOnDisplay(par2));

        mainPanel.add(textField, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(math2, BorderLayout.WEST);
        mainPanel.add(math, BorderLayout.EAST);
        mainPanel.add(math3, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    textField.setText(o.parseExpressionToTokens(String.valueOf(textField.getText())));
                }
            }
        });


    }

}
