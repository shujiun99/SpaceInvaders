/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Entity;

import java.awt.Graphics;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import javax.swing.ImageIcon;



/**
 *
 * @author User
 */
public class Weapon extends Entity {
    
    Instant startTime;
    Instant endTime;
    int speed;
    int usingTime = 6;
    int waitingTime;
    
    public Weapon(double x, double y,Instant startTime){
        super(x,y);
        initWeapon();
        this.startTime = startTime;
        width = 40;
        height = 45;
        waitingTime = getRandomInRange(5,7);
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
    
    public void initWeapon(){
        
        var WeaponImg = "src/images/weapon.jpg";
        var ii = new ImageIcon(WeaponImg);

        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());
        
    }
    
    public boolean buffEnd(){
        Duration interval = duration();
        if(interval.getSeconds() == usingTime){
            return true;
        }
        return false;
    }
    
    public boolean timeToEnd(){
        Duration interval = duration();
        if(interval.getSeconds() == waitingTime){
            return true;
        }
        return false;
    }
    
    public Duration duration(){
        Duration interval;
        return interval = Duration.between(getStartTime(), getEndTime());
    }
    
    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, height,width,null);
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }
   
    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public int getSpeed() {
        return speed;
    }

    
    public int getUsingTime() {
        return usingTime;
    }

    
   
}
