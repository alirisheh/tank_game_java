import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

/**
 * Created by My PC on 4/30/2017.
 */
public class Tank extends JTextField implements KeyListener{
    public int bulletx;
    public int bullety;
    public int x;
    public int y;
    public static boolean shuting = false;
    public static int bx;
    public static int by;
    public int hp;
    public String Jahatgiri = new String("up");
    public static String map[][] = new String[27][17];

    private Rectangle rectangle;

    public Tank(String map[][]){
        addKeyListener(this);
        File fin = new File("src/Map.txt");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fin);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        int i = 0;
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                this.map[i] = line.trim().split(" ");
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        int a,b;
        // tool = 27
        // arz = 31
        if(arg0.getKeyCode() ==               arg0.VK_LEFT &&           ((this.y > 350 && this.y < 434 && (this.x - 20 > 81 || this.x - 20 > 405 ) && this.x - 20 > 0) || this.y < 350 || this.y > 434)) {
            this.x -= 20;
            this.Jahatgiri = "left";
        }
        if(arg0.getKeyCode() ==               arg0.VK_RIGHT &&          ((this.y > 350 && this.y < 434 && (this.x + 20 < 160) && this.x + 20 < 600) || this.y < 350 || this.y > 434)) {
            this.x += 20;
            this.Jahatgiri = "right";
        }
        if(arg0.getKeyCode() ==               arg0.VK_UP && (this.y - 20 > 434 || this.y - 20 < 350 || (this.x > 108 && this.x < 162))) {
            this.y -= 20;
            this.Jahatgiri = "up";
        }
        if(arg0.getKeyCode() ==               arg0.VK_DOWN && (this.y + 20 < 350 || this.y + 20 > 434 || (this.x > 108 && this.x < 162))) {
            this.y += 20;
            this.Jahatgiri = "down";
        }
        if(arg0.getKeyCode() == arg0.VK_SPACE        ){
            shuting = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }

    public boolean move(String go){
        if(go.equals("left") && ((this.y > 350 && this.y < 434 && (this.x - 20 > 81 || this.x - 20 > 405 ) && this.x - 20 > 50) || this.y < 350 || this.y > 434)) {
            this.x -= 20;
            this.Jahatgiri = "left";
            return true;
        }
        if(go.equals("right") && ((this.y > 350 && this.y < 434 && (this.x + 20 < 160) && this.x + 20 < 600) || this.y < 350 || this.y > 434)) {
            this.x += 20;
            this.Jahatgiri = "right";
            return true;
        }
        if(go.equals("up") && (this.y - 20 > 434 || this.y - 20 < 350 || (this.x > 90 && this.x < 150))) {
            this.y -= 20;
            this.Jahatgiri = "up";
            return true;
        }
        if(go.equals("down") && (this.y + 20 < 350 || this.y + 20 > 434 || (this.x > 108 && this.x < 162))) {
            this.y += 20;
            this.Jahatgiri = "down";
            return true;
        }
        return false;
    }
}
