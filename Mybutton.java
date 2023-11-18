import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Mybutton extends JButton {
    private boolean clicked = false; 
    private String letter="";
    public String getLetter() {
        return letter;
    }
    public void setLetter(String letter) {
        this.letter = letter;
    }
    public void myseticon(String imagePath){
         super.setIcon(new ImageIcon(imagePath));
    }

    public boolean clicked(){
        return clicked;
    }
    public void setclicked(){
        clicked = true;
    }

    public boolean same(Mybutton obj) {
        if ((this.letter.equals(obj.getLetter()))&&(!obj.getLetter().equals(""))) {
            return true;
        }
        return false;
    }
}