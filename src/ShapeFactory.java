/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Isaac Wahba
 */
public class ShapeFactory {
    
    public static Shape getShape(String shapeName) {
        switch (shapeName) {
            case (Shape.LINE):
                return new Line();
            case (Shape.RECTANGULAR):
               return new Rectangle();
            case (Shape.OVAL):
                return new Oval();
            default:
               return null;
        }
    }
}
