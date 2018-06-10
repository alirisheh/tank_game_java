import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

/**
 * Created by My PC on 5/13/2017.
 */
public class EnemyBullet implements Runnable {

    Map map;

    public int enemies;
    public boolean enemyshutting[];
    public String enemybulletjahat[];
    public int enemybulletline[];
    public int x2x[];
    public int y2y[];
    public static ImageIcon bulle = new ImageIcon("bullet.png");
    public Tank te[];
    public Tank pt;
    public boolean tankhp;
    public boolean towerhp;
    public boolean finishattacking = false;
    public EnemyBullet(int enemies, Tank te[], Tank pt, Map map) {
        this.enemies = enemies;
        enemybulletjahat = new String[enemies];
        enemybulletline = new int[enemies];
        x2x = new int[enemies];
        y2y = new int[enemies];
        enemyshutting = new boolean[enemies];
        this.te = te;
        this.pt = pt;
        this.map = map;
    }

    public boolean checktankHp() {
        return tankhp;
    }
    public boolean checktowerHp() {
        return towerhp;
    }
    public void checkfinishing(boolean check){
        this.finishattacking = check;
    }

    @Override
    public void run() {
        while (!finishattacking) {
            Random rnd = new Random();
            for (int i = 0; i < enemies; i++) {
                if (!enemyshutting[i] && !Objects.equals(te[i].Jahatgiri, "dead") && ((int)rnd.nextInt(200) < 50)) {
                    enemyshutting[i] = true;
                    tankhp = false;
                    towerhp = false;
                    int finalI = i;
                    x2x[finalI] = te[finalI].x;
                    y2y[finalI] = te[finalI].y;
                    enemybulletjahat[finalI] = te[finalI].Jahatgiri;
                    new Thread() {
                        public void run() {
                            try {
                                boolean dot = true;
                                //start
                                int xx = x2x[finalI];
                                int yy = y2y[finalI];
                                for (int n = 0; n < 30; n++)
                                {

                                    if (Objects.equals(enemybulletjahat[finalI], "right")) {
                                        if (xx + enemybulletline[finalI] >= pt.x && pt.y <= yy + 10 && pt.y >= yy - 10)
                                            tankhp = true;
                                    }
                                    if (Objects.equals(enemybulletjahat[finalI], "left")) {
                                        if (xx - enemybulletline[finalI] <= pt.x && pt.y <= yy + 10 && pt.y >= yy - 10)
                                            tankhp = true;
                                    }
                                    if (enemybulletjahat[finalI] == "up") {
                                        if (yy - enemybulletline[finalI] <= pt.y && pt.x <= xx + 10 && pt.x >= xx - 10) {
                                            tankhp = true;
                                        }
                                        if (dot && yy - enemybulletline[finalI] <= 0 && xx < 50) {
                                            towerhp = true;
                                            dot = false;
                                        }
                                    }
                                    if (enemybulletjahat[finalI] == "down") {
                                        if (yy + enemybulletline[finalI] < pt.y && pt.x <= xx + 10 && pt.x >= xx - 10)
                                            tankhp = true;
                                    }
                                    for (int k = 0; k < enemies; k++) {
                                        if (enemyshutting[k]) {
                                            if (enemybulletjahat[k] == "left") {
                                                te[k].bulletx = x2x[k] - enemybulletline[k];
                                                te[k].bullety = y2y[k];
                                            }
                                            if (enemybulletjahat[k] == "right") {
                                                te[k].bulletx = x2x[k] + enemybulletline[k];
                                                te[k].bullety = y2y[k];
                                            }
                                            if (enemybulletjahat[k] == "up") {
                                                te[k].bulletx = x2x[k];
                                                te[k].bullety = y2y[k] - enemybulletline[k];
                                            }
                                            if (enemybulletjahat[k] == "down") {
                                                te[k].bulletx = x2x[k];
                                                te[k].bullety = y2y[k] + enemybulletline[k];
                                            }
                                        }
                                    }
                                    Thread.sleep(40);
                                    enemybulletline[finalI] += 5;
                                }
                                enemybulletline[finalI] = 5;
                                enemyshutting[finalI] = false;
                                towerhp = false;
                                tankhp = false;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
            }
//            update();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
