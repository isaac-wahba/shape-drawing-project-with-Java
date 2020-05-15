
import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Isaac Wahba
 */
public class Oval extends Shape{
    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        if (isFilled()) {
            g.fillOval(getX1(), getY1(), getX2() - getX1(), getY2() - getY1());
        } else {
            g.drawOval(getX1(), getY1(), getX2() - getX1(), getY2() - getY1());
        }
    }
}

