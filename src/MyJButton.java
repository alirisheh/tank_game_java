import javax.swing.*;
import java.awt.*;

/**
 * Created by My PC on 4/29/2017.
 */
public class MyJButton extends JButton{
    public static int com;
    public static JTextField tx;
    public static int difficulty;
    public MyJButton(String start, int enemies,JTextField tx, int difficulty) {
        super(start);
        com = enemies;
        this.tx = tx;
        this.difficulty = difficulty;
    }

    protected void processEvent(           AWTEvent arg0) {
        System.out.println(arg0.getID());
        if(arg0.getID() == 502 &&                       com != -1){
            Map m = new Map(com,this.difficulty,tx.getText());
            (new Thread(m)).start();
        }else if (arg0.getID() == 502 && com == -1){
            System.out.println("Exit");
        }
        super.processEvent(arg0);
    }
}
