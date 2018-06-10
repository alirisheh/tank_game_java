import java.util.Random;

/**
 * Created by My PC on 4/29/2017.
 */
public class TanksAction{
    public void act(Tank tanks[],int count){
        for(int i = 0 ; i < count ; i++){
            if(tanks[i].Jahatgiri == "dead")
                continue;
            if(tanks[i].x < 30 &&            tanks[i].y < 100)
                continue;
            if(tanks[i].y < 400 && tanks[i].x > 20 && tanks[i].move("left"))
                continue;
            if(tanks[i].move("up"))
                continue;
            if(tanks[i].x < 68 && tanks[i].y > 405 && tanks[i].move("right"))
                continue;
            if(tanks[i].x > 70 && tanks[i].y > 405 && tanks[i].move("left"))
                continue;
        }
    }
    public void attack(Tank tanks[],int count){
        Random rnd = new Random();
        for(int i = 0 ; i < count ; i++){
            if(rnd.nextInt(5) > 2){

            }
        }
    }
}
