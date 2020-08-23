/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class Menu {
    
    public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120, 120, 100, 50);
    public Rectangle lvlButton = new Rectangle(Game.WIDTH / 2 + 120, 180, 100, 50);
    public Rectangle scoreButton = new Rectangle(Game.WIDTH / 2 + 120, 240, 100, 50);
    public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 120, 300, 100, 50);
    public Rectangle exitButton = new Rectangle(Game.WIDTH / 2 + 120, 360, 100, 50);
    
    public void render(Graphics g){
        
        Graphics2D g2d = (Graphics2D)g;
        
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.red);
        g.drawString("Space Invaders", Game.WIDTH / 2, 90);
        
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.drawString("Play", playButton.x + 19, playButton.y + 35);
        g2d.draw(playButton);
        
        Font fnt2 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt2);
        g.drawString("Level", lvlButton.x + 12, lvlButton.y + 36);
        g2d.draw(lvlButton);
        
        Font fnt3 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt3);
        g.drawString("Score", scoreButton.x + 9, scoreButton.y + 35);
        g2d.draw(scoreButton);
        
        Font fnt4 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt4);
        g.drawString("Help", helpButton.x + 19, helpButton.y + 35);
        g2d.draw(helpButton);
        
        Font fnt5 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt5);
        g.drawString("Exit", exitButton.x + 21, exitButton.y + 35);
        g2d.draw(exitButton);
        
        
        String img = "src/images/menu.png";
        ImageIcon icon = new ImageIcon(img);
        
        g.drawImage(icon.getImage(), 0, 340, 136, 145, null);
        
    }
}
