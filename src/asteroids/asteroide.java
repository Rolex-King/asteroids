/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;

import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Javier
 */



public class asteroide {
    int SCENE_TAM_X = 800;
    int SCENE_TAM_Y = 600;

    Pane root;
    
    double asteroideX;
    double asteroideY;
    
    void asteroideObj(){
        for (int i= 0; i <3; i++){    	
            Polygon asteroide = new Polygon();
            asteroide.getPoints().addAll(new Double []{
                0.0, 0.0,
                40.0, 20.0,
                60.0, 60.0,
                10.0, 60.0
            });
            asteroide.setFill(Color.RED);
            root.getChildren().add(asteroide);
            Random randomAsteroide = new Random();
            asteroideX = randomAsteroide.nextInt(800);
            asteroideY = randomAsteroide.nextInt(600);
            asteroide.setTranslateX(asteroideX);
            asteroide.setTranslateY(asteroideY);
        }
    }
}
