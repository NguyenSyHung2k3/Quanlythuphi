/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package quanlythuphi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author pv
 */
public class Quanlythuphi extends Application {
    
    private double x;
    private double y;
  
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        root.setOnMousePressed((MouseEvent e)->{
            x = e.getSceneX();
            y = e.getSceneY();
        });
        
        root.setOnMouseDragged((MouseEvent e)->{
            stage.setX(e.getScreenX() - x);
            stage.setY(e.getScreenY() - y);
        });
        stage.initStyle(StageStyle.TRANSPARENT);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
