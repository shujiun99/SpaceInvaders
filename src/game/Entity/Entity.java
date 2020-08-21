/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Entity;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Color;

/**
 *
 * @author User
 */
public abstract class Entity {

    double x;
    double y;
    double dx;
    double dy;
    Image image;
    protected Rectangle bounds;
    int width;
    int height;
    private boolean visible;
    Color color;
    boolean isColliding;
    

    public Entity() {
        visible = true;
    }

    public void dead() {
        visible = false;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    public boolean isVisible() {

        return visible;
    }

    public void setVisible(boolean visible) {

        this.visible = visible;
    }
    
    public Entity(double x, double y, Color color)
    {
        this.x = x;
        this.y = y;
        this.color = color;
    }
    

}
