/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package Controller;

import Connection.Conn;
import java.net.URL;
import java.security.interfaces.RSAKey;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
/**
 *
 * @author pv
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button backBtn;
    
    @FXML
    private Button signin_close;

    @FXML
    private Hyperlink signin_createAcc;

    @FXML
    private AnchorPane signin_form;

    @FXML
    private Button signin_loginBtn;

    @FXML
    private Button signin_minimize;

    @FXML
    private PasswordField signin_password;

    @FXML
    private TextField signin_username;

    @FXML
    private Button signup_Btn;

    @FXML
    private Hyperlink signup_alreadyAcc;
    
    @FXML
    private Hyperlink forgotPassword;

    @FXML
    private Button signup_close;

    @FXML
    private TextField signup_email;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private Button signup_minimize;

    @FXML
    private PasswordField signup_password;

    @FXML
    private TextField signup_username;
    
    @FXML
    private AnchorPane forgot_form;
    
    @FXML
    private TextField forgot_email;
    
    @FXML
    private PasswordField forgot_password;
    
    @FXML
    private Button proceedBtn;
    
    @FXML
    private TextField forgot_confirmPassword;
    
    // Sign in form
    public void signIn_close(){
        System.exit(0);
    }
    
    public void signIn_minimize(){
        Stage stage = (Stage)signin_form.getScene().getWindow();
        stage.setIconified(true);
    }
    
    private double x=0;
    private double y=0;
    
    public void signin(){
        Conn c = new Conn();
        String username = signin_username.getText();
        String password = signin_password.getText();
        String query = "select * from account where username = '"+username+"'and password = '"+password+"'";
        
        try{
            
            ResultSet rs = c.s.executeQuery(query);
            Alert alert;
            if(signin_username.getText().isEmpty() || signin_password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else{
                if(rs.next()){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();
                    
                    signin_loginBtn.getScene().getWindow().hide();
                    
                    Parent root = FXMLLoader.load(getClass().getResource("/FXML/dashboard.fxml"));
                    
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
                    
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }
            
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    // Sign up form
    public void signUp_close(){
        System.exit(0);
    }
    
    public void signUp_minimize(){
        Stage stage = (Stage)signup_form.getScene().getWindow();
        stage.setIconified(true);
    }
    
        
    //checking email
    public static boolean validEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public void signup(){
        Conn c = new Conn();
        String username = signup_username.getText();
        String password = signup_password.getText();
        String email = signup_email.getText();
        String query = "insert into account values('"+username+"', '"+password+"', '"+email+"');";
        
        try{
            Alert alert;
            
            if(username.isEmpty() || password.isEmpty() || email.isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all blank fields");
                alert.showAndWait();
            } else if(password.length() < 8){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Password");
                alert.showAndWait();
            } else {
                
                if(!validEmail(email)){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Email");
                    alert.showAndWait();
                } else {                                
                    c.s.executeUpdate(query);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully create a new account!");
                    alert.showAndWait();

                    signup_email.setText("");
                    signup_username.setText("");
                    signup_password.setText("");   
                }

            } 
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    // Switch form
    public void switchForm(ActionEvent ae){
        if(ae.getSource() == signin_createAcc){
            signin_form.setVisible(false);
            signup_form.setVisible(true);
            forgot_form.setVisible(false);
        }
        else if(ae.getSource() == signup_alreadyAcc || ae.getSource() == backBtn){
            signup_form.setVisible(false);
            signin_form.setVisible(true);
            forgot_form.setVisible(false);
        }
        else if(ae.getSource() == forgotPassword){
            signup_form.setVisible(false);
            signin_form.setVisible(false);
            forgot_form.setVisible(true);
        }
        
    }
    
    // forgot Password
    
    public void proceed(){
        Alert alert;
        if(forgot_email.getText().isEmpty() || forgot_password.getText().isEmpty() || forgot_confirmPassword.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all blank fields");
            alert.showAndWait();
        }
        else {
            String checkData = "Select email from account where email = '"+forgot_email.getText()+"';";
            Conn c = new Conn();
            try{
                ResultSet rs = c.s.executeQuery(checkData);
                if(rs.next()){
                    if(forgot_password.getText().equals(forgot_confirmPassword.getText())){
                        if(forgot_password.getText().length() < 8){
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Invalid Password");
                            alert.showAndWait();
                        } else {
                            String updatePassword = "update account set password = '"+forgot_password.getText()+"' where email = '"+forgot_email.getText()+"'";
                            Conn c1 = new Conn();
                            try{
                                c1.s.executeUpdate(updatePassword);
                                alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Information Message");
                                alert.setHeaderText(null);
                                alert.setContentText("Successfully change Password!");
                                alert.showAndWait();
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                        
                        
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Password does not match!");
                        alert.showAndWait();
                    }
                }
                else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Email");
                    alert.showAndWait();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
