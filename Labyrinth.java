/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_roboter;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;


public class Labyrinth extends Canvas {

    public void setLayout(int[][] l) {
        layout = l;
        pointJumper = getStartPoint();      //Startpubkt soll entnommen werden
        Timer timer = new Timer();          //Timer wird erstellt
        timer.scheduleAtFixedRate(new FrameTimerTask(), 1000, 200); 
    }
    
    private class FrameTimerTask extends TimerTask {
        @Override
        public void run() {
            nextStep();
            Graphics g =  getGraphics();    //Grafik soll entnommen werden
            update(g);  //Grafik soll jede Sekunde geupdatet werden
        }
    }    

    private int FIELD_LENGTH = 50;
    
    public int getViewWidth() {
        return getLayoutWidth() * FIELD_LENGTH; 
    }
    
    public int getViewHeight() {
        return getLayoutHeight() * FIELD_LENGTH;
    }
    
    public int getLayoutWidth() {
        return layout[0].length;
    }
    public int getLayoutHeight() {
        return layout.length;
    }
    
    private int getFieldCode(int x, int y) {
        int code = -1;
        if (x >= 0 && y >= 0 && x < getLayoutWidth() && y < getLayoutHeight())
        {
            code = layout[y][x];
        }
        return code;
    }

    private int getFieldCode(Point p) {     //entnehme Position des Startpunktes
        return getFieldCode(p.x(), p.y());
    }

    private void drawLabyrinth(Graphics g) {
        int width = getLayoutWidth();   //Breite des Laybrinths
        int height = getLayoutHeight(); //Höhe des Labyrinths
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                Color color = Color.white;
                int code = getFieldCode(x, y);
                switch (code)
                {
                    case 0: color = Color.GRAY; break;
                    case 1: break;
                    case 2: break;
                    case 3: color = Color.black; break;
                }
                g.setColor(color);
                g.fillRect(x*FIELD_LENGTH, y*FIELD_LENGTH, FIELD_LENGTH-2, FIELD_LENGTH-2);
            }
        }
    }
    private void drawJumper(Graphics g) {
        int x = pointJumper.x()*FIELD_LENGTH;       
        int y = pointJumper.y()*FIELD_LENGTH;
        int w = FIELD_LENGTH-2;
        int h = FIELD_LENGTH-2;
        int reduce = FIELD_LENGTH / 6;
        switch (direction)
        {
            case UP:    
            case DOWN:
                x += reduce; 
                w -= 2*reduce; 
                break;
            case RIGHT:
            case LEFT: 
                y += reduce; 
                h -= 2*reduce; 
                break;
        }
        
        Color color = Color.red; 
        switch (state) 
        {
            case SEARCH: color = Color.green; break;
            case HAND:   color = Color.red;   break;
            case DONE:   color = Color.blue;  break;
        }
        g.setColor(color); //?
        g.fillRect(x, y, w, h);

        
        g.setColor(Color.black);
        g.drawString("" + turnCounter, x + FIELD_LENGTH/5, y + FIELD_LENGTH/2);
        
        // draw front
        switch (direction)
        {
            case UP: 
                h = reduce;   
                break;
            case RIGHT: 
                x += (FIELD_LENGTH - reduce); 
                w = reduce; 
                break;
            case DOWN: 
                y += (FIELD_LENGTH - reduce);  
                h = reduce;   
                break;
            case LEFT: 
                w = reduce;   
                break;
        }
        g.setColor(Color.black);
        g.fillRect(x, y, w, h);
    }
    
    
    @Override
    public void paint(Graphics g) {
        drawLabyrinth(g);
        drawJumper(g);
    }
    
    private class Point {
        
        public Point(int u, int v) {
            x = u;
            y = v;
        }

        public void setXY(int u, int v) {
            x = u;
            y = v;
        }
        
        public int x() {
            return x;
        }
        
        public int y() {
            return y;
        }
        
        private int x;
        private int y;
    }
    
    private Point getStartPoint() {
        int width = getLayoutWidth();
        int height = getLayoutHeight();
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                int code = getFieldCode(x, y);
                if (code == 2) {
                    return new Point(x, y);
                }
            }
        }
        return new Point(0, 0);
    }
  
    
    enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }
   
