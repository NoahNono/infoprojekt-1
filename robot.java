package robot;

import Roboter.Labyrinth;
import Roboter.Statisch;
import javax.swing.JFrame;
  

public class robot {
    
    public static void main(String[] args) {  
        Labyrinth labyrinth = new Labyrinth();
        labyrinth.setLayout(Statisch.flaeche);
        
        
        JFrame f = new JFrame();  
        f.add(labyrinth);  
        f.setSize(labyrinth.getViewWidth()+15, labyrinth.getViewHeight()+40);  
        f.setVisible(true);  
    }  
  
}  