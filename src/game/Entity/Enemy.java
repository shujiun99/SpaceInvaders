/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Entity;

import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Enemy extends Entity{
    
    public Enemy(int x, int y) {
        initAlien(x, y);
        
    }

    private void initAlien(int x, int y) {

        this.x = x;
        this.y = y;

        var EnemyImg = "src/images/asd.jpg";
        var imageIcon = new ImageIcon(EnemyImg);
        
        

        setImage(imageIcon.getImage());
    }

    public void move(int direction) {
        this.x += direction;
    }

  
}