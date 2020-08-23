/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Entity;

import java.awt.Graphics;
import java.time.Instant;
import java.util.Random;
import javax.swing.ImageIcon;



/**
 *
 * @author User
 */
public class Weapon extends Entity {
    
    Instant startTime;
    long endTime;
    int speed;
    int usingTime = 6;
    
    public Weapon(double x, double y,Instant startTime){
        initWeapon(x,y);
        this.x = x;
        this.y = y;
        this.startTime = startTime;
    }

    public Weapon() {
        speed = createSpeed();
    }
    
    public int createSpeed(){
        return getRandomInRange(12, 15);
    }
    
    public int getRandomInRange(int start, int end) {
        Random r = new Random();
        return start + r.nextInt(end - start + 1);
    }
    
    public void initWeapon(double x, double y){
        
        width = 40;
        height = 45;
        
        var playerImg = "src/images/weapon.jpg";
        var ii = new ImageIcon(playerImg);

        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());
        
    }
    
    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, 45,40,null);
    }

    public Instant getStartTime() {
        return startTime;
    }
    
    public void setEndTime(long endTime){
        this.endTime = endTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public int getSpeed() {
        return speed;
    }

    public long getEndTime() {
        return endTime;
    }

    public int getUsingTime() {
        return usingTime;
    }

    
   
}
