/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Isaac Wahba
 */
public class Draw extends Applet {

    int startPointX;
    int startPointY;
    int endPointX;
    int endPointY;
    
    Button line, rect, oval;
    Button red,blue,green;
    Button fill,unfill,clear;
     private String shapeName = "line";
    private Color color = Color.BLACK;
    private boolean filled = false;

    Shape myCurrentShape;
    List<Shape> shapesArray = new ArrayList<>();
    
    int startXPoint;
    int startYPoint;
    
    @Override
    public void init() {
    //Shapes buttons:
        line = new Button("Line");
        rect = new Button("Rect");
        oval = new Button("Oval");
        //Filling buttons:
        red = new Button("Red");
        blue = new Button("Blue");
        green = new Button("Green");

        //Options buttons:
        fill = new Button("Filled");
        unfill = new Button("Unfill");
        clear = new Button("Clear");

        addButtonsActionListeners();
        addButtonsToApplet();
        this.addMouseListener(new MouseActionListener());
        this.addMouseMotionListener(new MouseActionListener());
        resize(5000, 500);
    
    }
    
    @Override
    public void paint(Graphics g) {
        drawShapesArray(g);
        myCurrentShape.draw(g);
    }

    private void drawShapesArray(Graphics g) {
        for (int i = 0; i < shapesArray.size(); i++) {
            shapesArray.get(i).draw(g);
        }
    }
    
    private void addButtonsActionListeners() {
        line.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setShapeName(Shape.LINE);
            }
        });
        rect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setShapeName(Shape.RECTANGULAR);
            }
        });
        oval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setShapeName(Shape.OVAL);
            }
        });

        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setColor(Color.RED);
            }
        });
        blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setColor(Color.BLUE);
            }
        });
        green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setColor(Color.GREEN);
            }
        });
        fill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFilled(true);
            }
        });
        unfill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFilled(false);
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapesArray.clear();
                repaint();
            }
        });
    }

    private void addButtonsToApplet() {
        add(line);
        add(rect);
        add(oval);

        add(blue);
        add(red);
        add(green);

        add(clear);
        add(fill);
        add(unfill);
    }

    public class MouseActionListener implements MouseListener, MouseMotionListener {

        @Override
        public void mousePressed(MouseEvent e) {
            myCurrentShape = ShapeFactory.getShape(getShapeName());
            myCurrentShape.setX1(e.getX());
            myCurrentShape.setY1(e.getY());
            myCurrentShape.setX2(e.getX());
            myCurrentShape.setY2(e.getY());
            myCurrentShape.setColor(color);
            myCurrentShape.setFilled(filled);
            startXPoint = myCurrentShape.getX1();
            startYPoint = myCurrentShape.getY1();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            shapesArray.add(myCurrentShape);
            myCurrentShape = null;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            myCurrentShape.setX1(startXPoint);
            myCurrentShape.setY1(startYPoint);
            myCurrentShape.setX2(e.getX());
            myCurrentShape.setY2(e.getY());
            if (!getShapeName().equals(Shape.LINE)) {
                if (myCurrentShape.getX2() < myCurrentShape.getX1()) {
                    myCurrentShape.setX1(myCurrentShape.getX2());
                    myCurrentShape.setX2(startXPoint);
                }
                if (myCurrentShape.getY2() < myCurrentShape.getY1()) {
                    myCurrentShape.setY1(myCurrentShape.getY2());
                    myCurrentShape.setY2(startYPoint);
                }
            }
            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }
        
        @Override
        public void mouseMoved(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }

    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }

    public String getShapeName() {
        return shapeName;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public boolean isFilled() {
        return filled;
    }

}
