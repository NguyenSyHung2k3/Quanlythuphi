/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author pv
 */
public class dashboardController implements Initializable{
    
    @FXML
    private AnchorPane dashboard_view;
    
    @FXML
    private Button signout;
    
    @FXML
    private Button close;
    
    @FXML
    private Button minimize;
    
    @FXML
    private Button nhankhauBtn;
    
    @FXML
    private Button hokhauBtn;
    
    @FXML
    private Button khoanthuBtn;
    
    @FXML
    private Button guixeBtn;
    
    @FXML
    private Button traphiBtn;
    
    @FXML
    private Label user;
    
    public void close(){
        System.exit(0);
    }
    
    public void minimize(){
        Stage stage = (Stage)dashboard_view.getScene().getWindow();
        stage.setIconified(true);
    }
    
    private double x = 0;
    private double y = 0;
    
    public void signout(){
        signout.getScene().getWindow().hide();
        
        try{
    //                NAME OF LOGIN FORM
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/FXMLDocument.fxml"));

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
    
    public void changePage(){
        if(nhankhauBtn.isFocused()){
            nhankhauBtn.getScene().getWindow().hide();
            try{
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/nhankhau.fxml"));
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

            } catch(Exception e){

            }
        }
        if(hokhauBtn.isFocused()){
            hokhauBtn.getScene().getWindow().hide();
            try{
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/hokhau.fxml"));
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

            } catch(Exception e){

            }
        }
        if(khoanthuBtn.isFocused()){
            khoanthuBtn.getScene().getWindow().hide();
            try{
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/khoanthu.fxml"));
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

            } catch(Exception e){

            }
        }
        if(traphiBtn.isFocused()){
            traphiBtn.getScene().getWindow().hide();
            try{
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/traphi.fxml"));
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

            } catch(Exception e){

            }
        }
        if(guixeBtn.isFocused()){
            guixeBtn.getScene().getWindow().hide();
            try{
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/guixe.fxml"));
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

            } catch(Exception e){

            }
        }
        
    }
    
    public void account(){
        user.setText(User.username);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        account();
    }
    
    
    
}
