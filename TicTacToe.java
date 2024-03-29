import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TicTacToe extends JFrame implements ActionListener {
    public static boolean isEven(int num) {
        if (num % 2 == 0) return true;
        return false;
    }
// to determine a win across a row
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
// to determine a win across a column
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
// to determine a win across a diagonal
    public static boolean diag(ArrayList<Mybutton> board, int index, Mybutton clickedButton) {
        if (isEven(index)) {
            return ((board.get(0).same(board.get(4))) &&
                    (board.get(8).same(board.get(4)))) ||
                    ((board.get(2).same(board.get(4))) &&
                            (board.get(6).same(board.get(4))));
        }
        return false;
    }
//overall win
    public static boolean win(ArrayList<Mybutton> board, int index, Mybutton clickedButton) {
        if (row(board, index, clickedButton) || col(board, index, clickedButton) || diag(board, index, clickedButton)) {
            return true;
        }
        return false;
    }
    public static int count = 0;// counter for clicks
    ArrayList<Mybutton> buttons;
    Font myfont = new Font("ink font", Font.BOLD, 30);
    static JPanel panel;// panel for setting the buttons
    ImageIcon winner = new ImageIcon("images/win.jpg");
    ImageIcon labelicon = new ImageIcon("images/labeltic.jpg");
    ImageIcon sadicon = new ImageIcon("images/sad.jpg");
    JLabel label1;
    TicTacToe() {
        buttons = new ArrayList<>(9);//buttons
        panel = new JPanel();
        panel.setBounds(50, 10, 500, 500);
        panel.setLayout(new GridLayout(3, 3, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(201, 99, 196));
        label1 = new JLabel(labelicon);
        label1.setHorizontalAlignment(JLabel.LEFT);
        label1.setBounds(50, 505, 500, 150);
        label1.setOpaque(true);
// adding buttons to panel
        for (int i = 0; i < 9; i++) {
            buttons.add(new Mybutton());
            buttons.get(i).addActionListener(this);
            panel.add(buttons.get(i));
        }
        ImageIcon background = new ImageIcon("images/tictac.jpg");
        JLabel backgroundLabel = new JLabel(background);
// setting up JFrame
        backgroundLabel.setSize(new Dimension(super.getWidth(), super.getHeight()));
        setContentPane(backgroundLabel);
        setLayout(null);
        setSize(600, 700);
        setLocationRelativeTo(null);
        setTitle("TicTacToe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(panel);
        add(label1);
        setVisible(true);
    }
// display content for when the game ends by a win or draw
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
// action listener implementation
    @Override
    public void actionPerformed(ActionEvent e) {
        Mybutton clickedButton = (Mybutton) e.getSource();
        if(!clickedButton.clicked()){
        if (isEven(count)) {
            clickedButton.myseticon("images/xmark.jpg");
            clickedButton.setLetter("X");
        } else {
            clickedButton.myseticon("images/Omark.jpg");
            clickedButton.setLetter("O");
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
