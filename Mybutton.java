import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Mybutton extends JButton {
    private String imagePath;
    public void myseticon(String imagePath){
         this.imagePath = imagePath;
         super.setIcon(new ImageIcon(imagePath));
    }
    public String getImagePath() {
        if(imagePath != null)
        {return imagePath;}
        return " ";
    }

    public boolean same(Mybutton obj) {
        if ((this.getImagePath().equals(obj.getImagePath()))&&(!obj.getImagePath().equals(" "))) {
            return true;
        }
        return false;
    }
}