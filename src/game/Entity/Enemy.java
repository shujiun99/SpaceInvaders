/***************************************************************************************
*    Title: Java-Space-Invaders
*    Author: Janbodnar
*    Date: July 20, 2020
*    Availability: http://zetcode.com/javagames/spaceinvaders/
*    Respository: https://github.com/janbodnar/Java-Space-Invaders
* 
*Copyright <2020> <Janbodnar>

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A 
* PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
* PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
* OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***************************************************************************************/
package game.Entity;

import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class Enemy extends Entity {

    private Laser laser;
    private boolean visible;

    /**
     *
     * @return
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     *
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     *
     * @return
     */
    public Laser getLaser() {
        return laser;
    }

    public Enemy() {

    }

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param laser_width
     * @param laser_height
     * @param imgPath
     */
    public Enemy(int x, int y, int width, int height, int laser_width, int laser_height, String imgPath) {
        initEnemy(x, y, width, height, laser_width, laser_height, imgPath);
    }

    private void initEnemy(int x, int y, int width, int height, int laser_width, int laser_height, String imgPath) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        visible = true;

        laser = new Laser(x, y, laser_width, laser_height);

        var EnemyImg = imgPath;
        var imageIcon = new ImageIcon(EnemyImg);

        setImage(imageIcon.getImage());
    }

    /**
     *
     * @param direction
     */
    public void moveX(int direction) {
        this.x += direction;
    }

    /**
     *
     */
    public class Laser extends Entity {

        private boolean remove;

        public Laser() {
        }

        /**
         *
         * @param x
         * @param y
         * @param width
         * @param height
         */
        public Laser(int x, int y, int width, int height) {
            initLaser(x, y, width, height);
        }
        
        private void initLaser(int x, int y, int width, int height) {
            setRemove(true);

            this.x = x;
            this.y = y;
            this.width=width;
            this.height=height;

            var LaserImg = "src/images/laser.png";
            var ii = new ImageIcon(LaserImg);
            setImage(ii.getImage());
        }

        /**
         *
         * @return
         */
        public boolean isRemove() {
            return remove;
        }

        public void moveY(int speed) {
            this.y += speed;
        }

        /**
         *
         * @param remove
         */
        public void setRemove(boolean remove) {
            this.remove = remove;
        }

    }
}
