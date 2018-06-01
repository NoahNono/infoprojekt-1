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
    
   