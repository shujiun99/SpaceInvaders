/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Entity;

import game.Client.Controller;
import game.Client.Game;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class Shot extends Entity{
    
    private Game game;
    private Controller c;
    private int speed;
    
    public Shot(double x, double y,Game game,Controller c,int speed){
        initShot(x,y);
        this.game = game;
        this.c = c;
        this.speed = speed;
    }
    
    public void initShot(double x, double y){
        
        height = 20;
        width = 25;
        var shotImg = "src/images/bullet.jpg";
        var ii = new ImageIcon(shotImg);
        setImage(ii.getImage());
        
        bounds = new Rectangle((int)x,(int)y,height,width);
        
        int H_SPACE = 6;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);
    }
    
    public void tick(){
        y-=speed;
        /*
        if(game.Collision(this,game.e)){
            System.out.println("COLLISION DETECDED");
            //c.removeBullet(this);
        }*/
        /*
        if(game.Collision(this, game.e)){
            
        }*/
    }

    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, width,height,null);
    }
}

