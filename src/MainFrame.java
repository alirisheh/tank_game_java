import javax.swing.*;
import java.awt.event.KeyListener;

/**
 * Created by My PC on 7/16/2017.
 */
public class MainFrame extends JFrame {
    public MainFrame(){
        setSize(900,900);
        Map m = new Map(3,3,"ali");
        addKeyListener((KeyListener) m);
        getContentPane().add(m);
        setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame i = new MainFrame();
    }
}
