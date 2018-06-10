import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

/**
 * Created by My PC on 4/29/2017.
 */
public class LoginPannel extends JFrame  {
    public static int enemiesnumber = -1;
    public static int diff = 1;
    public static MyJButton b1;
    public LoginPannel(){
        JLabel l1 = new JLabel("enemies number : ");
        JLabel l2 = new JLabel("Player name : ");
        JLabel l3 = new JLabel("Difficulty : ");
        JTextField t1 = new JTextField("player name");
        b1 = new MyJButton("Start",1,t1,diff);
        MyJButton b2 = new MyJButton("Exit",-1,t1,diff);
        JComboBox<Integer> enemies = new JComboBox<Integer>();
        JComboBox<Integer> difficulty = new JComboBox<>();
        enemies.addItem(1);
        enemies.addItem(2);
        enemies.addItem(3);
        difficulty.addItem(1);
        difficulty.addItem(2);
        difficulty.addItem(3);
        difficulty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> combo = (JComboBox<Integer>)e.getSource();
                diff = (int)combo.getSelectedItem();
                MyJButton b1 = new MyJButton("Start",enemiesnumber,t1,diff);
            }
        });
        enemies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> combo = (JComboBox<Integer>)e.getSource();
                enemiesnumber = (int)combo.getSelectedItem();
                MyJButton b1 = new MyJButton("Start",enemiesnumber,t1,diff);
                System.out.println(enemiesnumber);
            }
        });

        setLayout(null);
        l1.setSize(150,20);
        l1.setLocation(5,20);
        enemies.setSize(10,20);
        enemies.setLocation(170,20);
        difficulty.setSize(10,20);
        difficulty.setLocation(170,90);
        l2.setSize(80,20);
        l2.setLocation(5,50);
        l3.setSize(80,20);
        l3.setLocation(5,90);
        t1.setSize(120,30);
        t1.setLocation(100,50);
        b1.setSize(100, 20);
        b1.setLocation(105, 150);
        b2.setSize(100,20);
        b2.setLocation(2,150);
        getContentPane().add(difficulty);
        getContentPane().add(l3);
        getContentPane().add(enemies);
        getContentPane().add(t1);
        getContentPane().add(b1);
        getContentPane().add(l1);
        getContentPane().add(l2);
        getContentPane().add(b2);
        getContentPane().setBackground(new Color(0x1EB2CC));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((d.width - 350) / 2, (d.height - 200) / 2);
        setSize(350,300);

        enemies.setSelectedIndex(1);
        difficulty.setSelectedIndex(2);

        setVisible(true);
    }
}
