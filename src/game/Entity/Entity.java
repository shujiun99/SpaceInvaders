/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Entity;

import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author User
 */
public abstract class Entity {

    double x;
    double y;
    Image image;
    protected Rectangle bounds;
    int width;
    int height;

    public Entity() {
        x = 0;
        y = 0;
        width = 0;
        height = 0;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;

    }

}
