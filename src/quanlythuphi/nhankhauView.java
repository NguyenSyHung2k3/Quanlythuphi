/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class nhankhauView extends Application{
    
    private double x = 0;
    private double y = 0;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/nhankhau.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
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
    
    public static void main(String[] args) {
         launch(args);
    }

    
}
