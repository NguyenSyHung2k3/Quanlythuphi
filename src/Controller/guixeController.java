/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author pv
 */
public class guixeController implements Initializable{
    
    @FXML
    private Button back;

    @FXML
    private Button close;

    @FXML
    private Button minimize;
    
    @FXML
    private AnchorPane guixe_view;
    
    public void close(){
        System.exit(0);
    }
    
    private double x=0;
    private double y=0;
    
    public void back(){
        back.getScene().getWindow().hide();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/dashboard.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            root.setOnMousePressed((MouseEvent event) ->{
                
                x = event.getSceneX();
                y = event.getSceneY();
                              
            });
            
            root.setOnMouseDragged((MouseEvent event) ->{
                
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
                
                stage.setOpacity(0.8);
                
            });
            
            root.setOnMouseReleased((MouseEvent event) ->{
                
                stage.setOpacity(1);
                
            });
            
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void minimize(){
        Stage stage = (Stage)guixe_view.getScene().getWindow();
        stage.setIconified(true);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
