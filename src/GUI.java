import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame{
    String_Op o = new String_Op();
    JFrame frame = new JFrame("Calculator");
    JPanel mainPanel = new JPanel(new BorderLayout());
    JTextField textField = new JTextField(10);
    JPanel math = new JPanel(new GridLayout(4,0));
    JPanel buttonPanel = new JPanel(new GridLayout(3,3));
    JPanel math2 = new JPanel(new GridLayout(3,0));
    JPanel math3 = new JPanel(new GridLayout(0,4));

    JButton par1 = new JButton("(");
    JButton par2 = new JButton(")");
    JButton pow = new JButton("a^b");
    JButton sqrt = new JButton("√");
    JButton equals = new JButton("=");
    JButton zero = new  JButton("0");
    JButton comma = new JButton(",");
    JButton modulus = new JButton("%");
    JButton reverse = new JButton("<-");
    JButton deleteAll = new JButton("C");

    List<JButton> createNumericButtons() {
        List<JButton> b = new ArrayList<>();
        for (int i = 1; i < 10;i++) {
            JButton button = new JButton(String.valueOf(i));
            b.add(button);
        }
        return b;
    }
    List <JButton> createMathematicalButtons(){
        String[] b = new String[]{"+","-","/","*"};
        List<JButton> b2 = new ArrayList<>();
        for (String s : b) {
            JButton button = new JButton(s);
            b2.add(button);
        }
        return b2;
    }
    void setColors(JButton button, int c){
        if (c==1){
            button.setBackground(Color.gray);
            button.setForeground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(Color.white));
        }else{
            button.setBackground(Color.darkGray);
            button.setForeground(Color.WHITE);
        }
    }
    void setTextOnDisplay(JButton button){
        textField.setText(textField.getText()+ button.getText());
    }

    //TODO nytt sett knappar med pow, paranteser osv
    void createCalculator(){

        frame.add(mainPanel);

        for (JButton button: createNumericButtons()){
            buttonPanel.add(button);
            setColors(button,1);
            button.addActionListener(e->setTextOnDisplay(button));
        }
        buttonPanel.add(zero);
        zero.addActionListener(e->setTextOnDisplay(zero));
        setColors(zero,1);
        zero.setBorder(BorderFactory.createLineBorder(Color.white));
        buttonPanel.add(equals);
        buttonPanel.add(comma);
        comma.addActionListener(e->setTextOnDisplay(comma));
        setColors(equals,0);
        setColors(comma,0);
        equals.addActionListener(e->{
            textField.setText(o.parseExpression(String.valueOf(textField.getText())));
        });



        for (JButton button:createMathematicalButtons()){
            math.add(button);
            button.setBackground(Color.DARK_GRAY);
            button.setForeground(Color.white);
            button.addActionListener(e->setTextOnDisplay(button));

        }
        math2.add(reverse);
        math2.add(deleteAll);
        math2.add(modulus);
        setColors(modulus,0);
        setColors(deleteAll,0);
        setColors(reverse,0);

        modulus.addActionListener(e->textField.setText(textField.getText()+modulus.getText()));
        deleteAll.addActionListener(e->textField.setText(o.deleteAll(textField.getText())));
        reverse.addActionListener(e->textField.setText(o.deleteChar(textField.getText())));

        textField.setBorder(BorderFactory.createLineBorder(Color.black));
        textField.setEditable(false);
        textField.setPreferredSize(new Dimension(20,40));
        Font boldFont = new Font(textField.getFont().getName(), Font.BOLD, textField.getFont().getSize());
        textField.setFont(boldFont);
        Border border = BorderFactory.createLineBorder(Color.darkGray,5,true);
        textField.setBorder(border);

        math3.add(pow);
        math3.add(sqrt);
        math3.add(par1);
        math3.add(par2);
        setColors(pow,0);
        setColors(sqrt,0);
        setColors(par1,0);
        setColors(par2,0);

        pow.addActionListener(e->setTextOnDisplay(pow));
        sqrt.addActionListener(e->setTextOnDisplay(sqrt));
        par1.addActionListener(e->setTextOnDisplay(par1));
        par2.addActionListener(e->setTextOnDisplay(par2));

        mainPanel.add(textField,BorderLayout.NORTH);
        mainPanel.add(buttonPanel,BorderLayout.CENTER);
        mainPanel.add(math2,BorderLayout.WEST);
        mainPanel.add(math,BorderLayout.EAST);
        mainPanel.add(math3,BorderLayout.SOUTH);
        mainPanel.setSize(500,400);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

    }

}
