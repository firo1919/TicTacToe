import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TicTacToe extends JFrame implements ActionListener {
    public static boolean isEven(int num) {
        if (num % 2 == 0) return true;
        return false;
    }

    public static boolean row(ArrayList<Mybutton> board, int index, Mybutton clickedButton) {
        boolean allEqual = true;
        int i;
        if (index >= 0 && index <= 2) i = 0;
        else if (index >= 3 && index <= 5) i = 3;
        else i = 6;

        for (int j = i; j < i + 3; j++) {
            if (!board.get(j).same(clickedButton)) {
                allEqual = false;
                break;
            }
        }
        return allEqual;
    }

    public static boolean col(ArrayList<Mybutton> board, int index, Mybutton clickedButton) {
        boolean allEqual = true;
        int i;
        if (index >= 0 && index <= 2) i = index;
        else if (index >= 3 && index <= 5) i = index - 3;
        else i = index - 6;

        for (int j = i; j <= i + 6; j += 3) {
            if (!board.get(j).same(clickedButton)) {
                allEqual = false;
                break;
            }
        }
        return allEqual;
    }

    public static boolean diag(ArrayList<Mybutton> board, int index, Mybutton clickedButton) {
        if (isEven(index)) {
            return ((board.get(0).same(board.get(4))) &&
                    (board.get(8).same(board.get(4)))) ||
                    ((board.get(2).same(board.get(4))) &&
                            (board.get(6).same(board.get(4))));
        }
        return false;
    }

    public static boolean win(ArrayList<Mybutton> board, int index, Mybutton clickedButton) {
        if (row(board, index, clickedButton) || col(board, index, clickedButton) || diag(board, index, clickedButton)) {
            return true;
        }
        return false;
    }

    public static int count = 0;
    ArrayList<Mybutton> buttons;
    Font myfont = new Font("ink font", Font.BOLD, 30);
    static JPanel panel;
    ImageIcon winner = new ImageIcon("win.jpg");
    ImageIcon labelicon = new ImageIcon("labeltic.jpg");
    ImageIcon sadicon = new ImageIcon("sad.jpg");
    JLabel label1;
    TicTacToe() {
        buttons = new ArrayList<>(9);
        panel = new JPanel();
        panel.setBounds(50, 10, 500, 500);
        panel.setLayout(new GridLayout(3, 3, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(201, 99, 196));
        label1 = new JLabel(labelicon);
        label1.setHorizontalAlignment(JLabel.LEFT);
        label1.setBounds(50, 505, 500, 150);
        label1.setOpaque(true);
        for (int i = 0; i < 9; i++) {
            buttons.add(new Mybutton());
            buttons.get(i).addActionListener(this);
            panel.add(buttons.get(i));
        }
        ImageIcon background = new ImageIcon("tictac.jpg");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setSize(new Dimension(super.getWidth(), super.getHeight()));
        super.setContentPane(backgroundLabel);
        super.setLayout(null);
        super.setSize(600, 700);
        super.setLocationRelativeTo(null);
        super.setTitle("TicTacToe");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setResizable(false);
        super.add(panel);
        super.add(label1);
        super.setVisible(true);
    }

    public void display(Mybutton clickedButton) {
        int selectedOption =  JOptionPane.showOptionDialog(null, "Congratulations you have won!!!, Do u want to play again", null,JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, winner, null, 0);
        if(selectedOption==1){
            dispose();
        }
        else{
            count = -1;
            dispose();
            new TicTacToe();
        }
    }

    public void display(Mybutton clickedButton, boolean result) {
         int selectedOption = JOptionPane.showOptionDialog(null, "Its a Draw fellas!!!, Do u want to play again", null,JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, sadicon, null, 0);
        if(selectedOption==1){
            dispose();
        }
        else{
            dispose();

            new TicTacToe();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Mybutton clickedButton = (Mybutton) e.getSource();
        if(!clickedButton.clicked()){
        if (isEven(count)) {
            clickedButton.myseticon("xmark.jpg");
        } else {
            clickedButton.myseticon("Omark.jpg");
        }
         if (win(buttons, buttons.indexOf(clickedButton), clickedButton)) {
            display(clickedButton);
        } else if (count == 8) {
            display(clickedButton, true);
        }
        clickedButton.setclicked();
         count+=1;}
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
