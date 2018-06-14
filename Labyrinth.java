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

    private int FIELD_LENGTH = 100;
    
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
        int code = -1; //initalisierung (holt code aus dem layout raus) alles auuserhalb des laybrinths ist -1
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
        int x = pointJumper.x()*FIELD_LENGTH;       //?
        int y = pointJumper.y()*FIELD_LENGTH;
        int w = FIELD_LENGTH;
        int h = FIELD_LENGTH;
        int reduce = FIELD_LENGTH / 6; //um die 6tel der feldlänge reduziert
        switch (direction)
        {
            case UP:    
            case DOWN:
                x += reduce; //verschoben nach rechts
                w -= 2*reduce; //verringert sich um reduce auf beiden seiten
                break;
            case RIGHT:
            case LEFT: 
                y += reduce; 
                h -= 2*reduce; 
                break;
        }
        
        Color color = Color.red; 
        switch (state) //bei Änderung des Statuses setzte entsprechende farbe fest
        {
            case SEARCH: color = Color.green; break;
            case HAND:   color = Color.red;   break;
            case DONE:   color = Color.blue;  break;
        }
        g.setColor(color); //nehme jeweilige farbe an
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
    
    
    @Override //eigene Methode(aus der basisklasse)muss überschrieben, ansonsten würd enichts gezeichnet werden (würde eigene methode aurfuen, ist leer implementiert)wie ein plug in
    public void paint(Graphics g) {
        drawLabyrinth(g);
        drawJumper(g);
    }
    
    
    private class Point { //darstellung der position
        
        public Point(int u, int v) {
            x = u;
            y = v;
        }

        public void setXY(int u, int v) {
            x = u;
            y = v;
        }
        
        public int x() { //Auswertung der Werte
            return x;
        }
        
        public int y() { //Auswertung der WErte
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
    
    //////////////////////
    // ALGORITHMUS
    //////////////////////
    
    enum State {
        SEARCH,
        HAND,
        DONE
    }
    
    private Point getForwardPoint()
    {
        int x = pointJumper.x();
        int y = pointJumper.y();
        switch (direction)
        {
            case UP:    y--; break;
            case RIGHT: x++; break;
            case DOWN:  y++; break;
            case LEFT:  x--; break;
        }
        return new Point(x, y);
    }
    
    private Point getRightPoint()
    {
        int x = pointJumper.x();
        int y = pointJumper.y();
        switch (direction)
        {
            case UP:    x++; break;
            case RIGHT: y++; break;
            case DOWN:  x--; break;
            case LEFT:  y--; break;
        }
        return new Point(x, y);
    
    }
    
    private Point getLeftPoint()
    {
        int x = pointJumper.x();
        int y = pointJumper.y();
        switch (direction)
        {
            case UP:    x--; break;
            case RIGHT: y--; break;
            case DOWN:  x++; break;
            case LEFT:  y++; break;
        }
        return new Point(x, y);
    }
    
    private void turnRight()
    {
        turnCounter++;
        switch (direction)
        {
            case UP:    direction = Direction.RIGHT; break;
            case RIGHT: direction = Direction.DOWN;  break;
            case DOWN:  direction = Direction.LEFT;  break;
            case LEFT:  direction = Direction.UP;    break;
        }
    }
    
    private void turnLeft()
    {
        turnCounter--;
        switch (direction)
        {
            case UP:    direction = Direction.LEFT;  break;
            case RIGHT: direction = Direction.UP;    break;
            case DOWN:  direction = Direction.RIGHT; break;
            case LEFT:  direction = Direction.DOWN;  break;
        }
    }
    
    private boolean isWall(int code)
    {
        boolean wall = (code == 0 || code == 3);
        return wall;
    }
    
    
    private void nextStep() {                                 

        int code;
        code = -1; 
        if (state == State.SEARCH) {
            Point forward = getForwardPoint();
            code = getFieldCode(forward);
            if (isWall(code)) {
                turnRight();
                state = State.HAND;
            }
            else {
                if (code != -1)
                {
                    pointJumper = forward;
                }
            }
        }
        else if (state == State.HAND) {
            Point left = getLeftPoint();
            code = getFieldCode(left);
            if (!isWall(code)) {
                turnLeft();
                if (code != -1)
                {
                    pointJumper = left;
                }
            }
            else {
                Point forward = getForwardPoint(); //entnehmen psoition
                code = getFieldCode(forward); //was ist vor dem springer
                if (isWall(code)) {
                    turnRight();
                }
                else {
                    if (code != -1)
                    {
                        pointJumper = forward;
                    }
                }
            }
            if (turnCounter == 0) {
                state = State.SEARCH;
            }
            if (code == -1) //ist ausserhalb vom labyrinth -->hört mit bewegung auf
            {
                state = State.DONE;
                System.out.println("FINISH");
            }
        }                                                   
     }
            
    Point pointJumper; 
    Direction direction = Direction.UP; //Springer ist am Anfagn nach vorne ausgerichtet
    State state = State.SEARCH; //geht in den zu+stand search
    int turnCounter = 0; //Umdrehungszähler wird auf 0 gesetzt
    
    private int[][] layout; //Datenstruktur aus Statisch.java entnommen
    
    
}
