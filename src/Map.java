import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Random;

/**
 * Created by My PC on 4/29/2017.
 */

public class Map extends JPanel implements Runnable{
    public static boolean stableshutplace2[];
    public static Rectangle tower;
    public static boolean stableshutplace1 = true;
    public static String jahatbu = new String();
    public static String enemybulletjahat[];
    public static int xx;
    public static int yy;
    public static int x2x[];
    public static int y2y[];
    public static boolean enemyshutting[];
    public static int enemybulletline[];
    public static int h = 800;
    public static ImageIcon bulle;
    public static boolean shuting;
    public static int bulletline = 5;
    public static int w = 900;
    public static int tankhp = 3;
    public static int towerhp = 5;
    private static int speed;
    public static int dochange = 1;
    public static String jahat[];
    private static int status = 0;
    public static String playername;
    private static String map[][] = new String[27][17];
    public static ImageIcon i1,i2,i3,i4,i5,i6,i7,i8,i9,i10;
    public static String tanks[][] = new String[27][17];
    public static JLabel j2 = new JLabel(String.valueOf(tankhp));
    public static JLabel j3 = new JLabel("Tower :");
    public static JLabel j4 = new JLabel(String.valueOf(towerhp));
    public static Tank pt;
    public static Tank te[];
    public static int enemies;
    public static EnemyBullet enemyBullet;
    public Map(int enemies,int difficulty,String playername){
        this.enemies = enemies;
        te = new Tank[enemies];
        x2x = new int[enemies];
        y2y = new int[enemies];
        enemybulletjahat = new String[enemies];
        enemyshutting = new boolean[enemies];
        enemybulletline  = new int[enemies];
        stableshutplace2 = new boolean[enemies];
        pt = new Tank(map);
        pt.setSize(40,50);
        pt.x = 50;
        pt.y = 150;
        enemyBullet = new EnemyBullet(enemies,te,pt,this);
        this.addKeyListener(pt);
        for(int i = 0 ; i < enemies  ; i++){
            te[i] = new Tank(map);
            if(te[i] != null) {
                te[i].x = i * 100 +               100;
                te[i].y = 650;
                te[i].Jahatgiri = "up";
            }
        }
        for(int i = 0 ; i < enemies ; i++)
            stableshutplace2[i] = true;
        pt.hp = 3;
        this.playername = playername;
        setSize(w,h);
        setLocation(0,0);
        setLayout(null);
        setVisible(true);
        bulle = new ImageIcon("bullet.png");
        i1 = new ImageIcon("epicearth.png");
        i2 = new ImageIcon("normalearth.jpg");
        i3 = new ImageIcon("StoneBridge.jpg");
        i4 = new ImageIcon("Water.jpg");
        i5 = new ImageIcon("Tower.jpg");
        i6 = new ImageIcon("health.jpg");
        speed = (int) 1000/difficulty;
        File fin = new File("src/Map.txt");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fin);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Construct BufferedReader from InputStreamReader
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        int i = 0;
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                map[i] = line.trim().split(" ");
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel j1 = new JLabel(playername + " : ");
        j1.setSize(100,20);
        j1.setLocation(500,200);
        JLabel j3 = new JLabel("Tower : ");
        j3.setSize(100,20);
        j3.setLocation(500,400);
        add(j1);
        add(j3);
        for(int d = 0 ; d < 27 ; d++)
            for (int j = 0 ; j < 17 ; j++)
                tanks[d][j] = "0";
        tanks[4][0] = "1";
           }
    protected static TanksAction play = new TanksAction();
    @Override
    public void run() {
        (new Thread(enemyBullet)).start();
        while (status == 0){
            for(int d = 0 ; d < enemies ; d++){
                this.enemyshutting[d] = enemyBullet.enemyshutting[d];
            }
            if(enemyBullet.towerhp){
                towerhp--;
            }
            if(enemyBullet.tankhp){
                tankhp--;
            }
            if(tankhp <= 0){
                status = -1;
            }
            if(status == -1){
                System.out.println("You losed!");
                System.exit(1);
            }
            if(towerhp <= 0){
                status = -2;
            }
            if(status == -2){
                System.out.println("Tower destroyed");
                System.exit(1);
            }
            if(pt.shuting){
                this.shuting = true;
                if(stableshutplace1){
                    xx = pt.x;
                    yy = pt.y;
                    jahatbu = pt.Jahatgiri;
                    stableshutplace1 = false;
                }
                new Thread(){
                    public void run(){
                        try {
                            for(int i = 0 ; i < 15 ; i++) {
                                if(jahatbu == "right"){
                                    for(int j = 0 ; j < enemies ; j++){
                                        if(xx + bulletline >= te[j].x && xx < te[j].x && te[j].y < yy + 15 && te[j].y > yy - 15)
                                            te[j].Jahatgiri = "dead";
                                    }
                                }
                                if(jahatbu == "left"){
                                    for(int j = 0 ; j < enemies ; j++){
                                        if(xx - bulletline <= te[j].x && xx > te[j].x && te[j].y < yy + 15 && te[j].y > yy - 15)
                                            te[j].Jahatgiri = "dead";
                                    }
                                }
                                if(jahatbu == "up"){
                                    for(int j = 0 ; j < enemies ; j++){
                                        if(yy - bulletline < te[j].y && yy > te[j].y && te[j].x < xx + 15 && te[j].x > xx - 15)
                                            te[j].Jahatgiri = "dead";
                                    }
                                }
                                if(jahatbu == "down"){
                                    for(int j = 0 ; j < enemies ; j++){
                                        if(yy + bulletline > te[j].y && yy < te[j].y && te[j].x < xx + 15 && te[j].x > xx - 15) {
                                            te[j].Jahatgiri = "dead";
                                        }
                                    }
                                }
                                Thread.sleep(150);
                                bulletline += 5;
                            }
                            bulletline = 5;
                            stableshutplace1 = true;
                            shuting = false;
                            pt.shuting = false;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }

            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            play.act(te,enemies);
            repaint();
        }
    }
    Random rand = new Random();
    public void paint(Graphics arg0){
        super.paint(arg0);
        int x = 0,y = 0;
        for(int i = 0;i<27;i++){
            for(int j = 0;j<17;j++){
                if(Integer.parseInt(map[i][j].trim()) == 0)
                    arg0.drawImage(i1.getImage(),x,y,null);
                else if(Integer.parseInt(map[i][j].trim()) == 3)
                    arg0.drawImage(i2.getImage(),x,y,null);
                if(Integer.parseInt(map[i][j].trim()) == 9)
                    arg0.drawImage(i3.getImage(),x,y,null);
                if (Integer.parseInt(map[i][j].trim()) == 6)
                    arg0.drawImage(i4.getImage(),x,y,null);
                x += 27;
            }
            y += 31;
            x = 0;
        }
        for(int i = 0 ; i < tankhp ; i++)
            arg0.drawImage(i6.getImage(),600 + i*50,200,null);
        for(int i = 0 ; i < towerhp ; i++)
            arg0.drawImage(i6.getImage(),600 + i*50,400,null);
        arg0.drawImage(i5.getImage(),0,0,null);
        i7 = new ImageIcon("personaltank-" + pt.Jahatgiri + ".jpg");
        arg0.drawImage(i7.getImage(),pt.x,pt.y,null);
        for (int i = 0; i < enemies ; i++){
            if(te[i] != null && !te[i].Jahatgiri.equals("dead")) {
                i8 = new ImageIcon("enemytank-" + te[i].Jahatgiri + ".jpg");
                arg0.drawImage(i8.getImage(), te[i].x, te[i].y, null);
            }
            for(int k = 0 ; k < enemies ; k++){
                if(enemyshutting[k]){
                    arg0.drawImage(bulle.getImage(),te[k].bulletx,te[k].bullety,null);
                }
            }
            if(shuting){
                if(jahatbu == "left")
                    arg0.drawImage(bulle.getImage(),xx - bulletline,yy,null);
                if(jahatbu == "right")
                    arg0.drawImage(bulle.getImage(),xx + bulletline,yy,null);
                if(jahatbu == "up")
                    arg0.drawImage(bulle.getImage(),xx,yy - bulletline,null);
                if(jahatbu == "down")
                    arg0.drawImage(bulle.getImage(),xx,yy + bulletline,null);
            }
        }
    }
}
