/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roboter.im.labyrinth;

/**
 *
 * @author jeffr
 */
public class Statisch {
    
    public static int [][] flaeche =           //Labyrinth, das vorgegeben ist und zu der zur verarbeitetenden Klasse übergeben wird
    { {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
      {3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3},       //Nullen sind die Hindernisse/"Mauern"; Einser beschreiben den freien begehbaren Weg
      {3,1,1,1,1,1,1,1,0,0,0,0,1,1,1,3},
      {3,1,1,1,1,1,1,1,1,1,1,0,1,1,1,3},
      {3,1,0,0,0,0,0,0,0,1,1,0,1,1,1,3},
      {3,1,0,1,1,1,1,1,0,1,1,0,1,1,1,3},
      {3,1,0,1,1,0,1,1,0,1,1,0,1,1,1,3},
      {3,1,0,1,1,1,1,1,0,1,1,0,1,1,1,3},          
      {3,1,0,1,1,2,1,0,0,1,1,0,0,0,1,3},       //2 ist der Startpunkt des Springers
      {3,1,0,1,1,1,1,1,1,1,1,0,1,1,1,3},
      {3,1,0,0,0,0,0,0,0,0,0,0,0,1,1,3},
      {3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3},
      {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}
      
       };                                      //Der Springer ist dann aus dem Laybrinth, wenn er die äußeren Dreier erreicht hat(ist aus dem Käfig/Labyrinth raus)
    
    public static int [][] genflaeche =        //Das Labyrinth, die das Programm selbst generiert, wird hierher übergeben
    { {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
      {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
      {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
      {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
      {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
      {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
      {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
      {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},          //2 ist der Satrtpunkt des Springers
      {3,0,0,0,0,2,0,0,0,0,0,0,0,0,0,3},
      {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
      {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
      {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
      {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}
      
       };     
}
