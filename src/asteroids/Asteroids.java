/* To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates and open the template in the editor.*/

package asteroids;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 *
 * @author Javier all right reserved
 */
public class Asteroids extends Application {
    
    int SCENE_TAM_X = 800;
    int SCENE_TAM_Y = 600;
    
    double anguloNave = 360;
    int anguloVelNave;
    
//    int centroNaveX = 30/2; 
//    int centroNaveY = 5;
    
    double posNaveX = SCENE_TAM_X/2;
    double posNaveY = SCENE_TAM_Y/2;
    
    double naveVelocidadX;
    double naveVelocidadY;
    
    double naveAceleracion;
    
    double misilAce;
    
    double misilVelocidadX;
    double misilVelocidadY;
    
    double posMisilX; 
    double posMisilY;
    
    Circle misil = new Circle();
    Pane root;
    
    @Override
    public void start(Stage primaryStage) {
        root = new Pane();
        Scene scene = new Scene(root, SCENE_TAM_X, SCENE_TAM_Y);
        
        primaryStage.setTitle("Asteroids");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //Objeto nave
        Polygon nave = new Polygon();
        nave.getPoints().addAll(new Double []{
            0.0, 0.0,
            30.0, 10.0,
            0.0, 20.0
        });
        //posicion de la nave
        nave.setTranslateX(posNaveX);
        nave.setTranslateY(posNaveY);
        nave.setFill(Color.BLACK);
        nave.setRotate(anguloNave);
//        nave.setCenterX(15);
//        nave.setCenterY(5);
        root.getChildren().add(nave);
        //Objeto fuego rojo
        Polygon furo = new Polygon();
        furo.getPoints().addAll(new Double []{
            0.0, 0.0,
            10.0, 0.0,
            5.0, 10.0
        });
        furo.setFill(Color.RED);
        root.getChildren().add(furo);
        //Objeto fuego amarillo
        Polygon fuam = new Polygon();
        fuam.getPoints().addAll(new Double []{
            0.0, 0.0,
            10.0, 0.0,
            5.0, 10.0
        });
        fuam.setFill(Color.YELLOW);
        root.getChildren().add(fuam);
        AnimationTimer animationnave = new AnimationTimer(){
            @Override
            public void handle(long now) {
                
                //El misil aumenta la velocidad en X
                posMisilX += misilVelocidadX;
                //La nave se mueve en X constantemente
                posNaveX += naveVelocidadX;
                nave.setTranslateX(posNaveX);
                //La nave se mueve en Y constantemente   
                nave.setTranslateY(posNaveY);
                posNaveY += naveVelocidadY;
                //Posicion de la bola en Y
                posMisilY += misilVelocidadY;
                //La nave gira constantemente
                anguloNave += anguloVelNave;
                nave.setRotate(anguloNave);
                
                double anguloNaveRadianes = Math.toRadians(anguloNave);
//                System.out.println("Radianes :" + anguloNaveRadianes);
                naveVelocidadX = Math.cos(anguloNaveRadianes) * naveAceleracion;
                naveVelocidadY = Math.sin(anguloNaveRadianes) * naveAceleracion;
                
                
                //En case de que la nave toque cada borde, vuelve al otro lado de la pantalla
                if(posNaveX > SCENE_TAM_X){
                    posNaveX = 0;
                }
                if(posNaveX < 0){
                    posNaveX = SCENE_TAM_X;
                }
                if(posNaveY > SCENE_TAM_Y){
                    posNaveY = 0;
                }
                if(posNaveY < 0){
                    posNaveY = SCENE_TAM_Y;
                }
                
                //Si el valor es mayor que 360 cambia a 0
                if (anguloNave>360){
                    anguloNave=0;
                } 
                //Si el valor es menor de 0 cambia a 360
                else if (anguloNave<0){
                    anguloNave=360;
                }
            };
        };
        animationnave.start();        
        //Reaccion a los botones
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch(event.getCode()) {
                case SPACE:
                    misil = new Circle();
                    misil.setFill(Color.RED);
                    misil.setRadius(3);
                    root.getChildren().add(misil);
                    misil.setTranslateX(posMisilX);
                    misil.setTranslateY(posMisilY);
                    //Posicion de la bola en X
                    misil.setTranslateX(posNaveX);
                    //Posicion de la bola en Y
                    misil.setTranslateY(posNaveY);
                    
                    double anguloMisilRadianes = Math.toRadians(anguloNave);
                    
                    misilVelocidadX = Math.cos(anguloMisilRadianes) * misilAce;
                    misilVelocidadY = Math.sin(anguloMisilRadianes) * misilAce;
                    //Si se pulsa espacio dispara
                    misilAce = 3;
//                    misil.setTranslateX();
//                    misil.setTranslateY();
                    break;
                case D:
                    //Si se pulsa derecha la nave gira 90 grados//
                    anguloVelNave = +5;
                    break;
                case A:
                     //Si se pulsa izquierda la nave gira -90 grados//                   
                    anguloVelNave = -5;
                    break;
                case W:
                    //Si se pulsa arriba la nave avanza segun donde este mirando//  
                    naveAceleracion = 5;
            }
            
        });
        //Soltar boton//
        scene.setOnKeyReleased((KeyEvent event) -> {
            switch(event.getCode()){
                case W:
                    naveAceleracion = 0;
                case A:
                    anguloVelNave = 0;
                case D:
                    anguloVelNave = 0;
            }
        });
    }
}
//nave.setRotate(angulonave);
//nave.setCenterX(naveCenterX);
//nave.setCenterY(naveCenterY);

//system.out.println(naveAngulo)


//AREA 51
//naveVelocidadX += naveAceleracionX

//AREA 51