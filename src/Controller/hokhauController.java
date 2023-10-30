/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.Conn;
import Model.hokhauModel;
import Model.nhankhauModel;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author pv
 */
public class hokhauController implements Initializable{
    
    @FXML
    private TextField address;

    @FXML
    private Button back;

    @FXML
    private Button close;

    @FXML
    private TableColumn<hokhauModel, String> col_address;

    @FXML
    private TableColumn<hokhauModel, Integer> col_idHo;

    @FXML
    private TableColumn<hokhauModel, Integer> col_soPhong;

    @FXML
    private TableColumn<hokhauModel, Integer> col_soTV;

    @FXML
    private AnchorPane hokhau_view;

    @FXML
    private TextField idHo;

    @FXML
    private Button minimize;

    @FXML
    private TextField soPhong;

    @FXML
    private TableView<hokhauModel> table_view;
    
    @FXML
    private Button clear;
    
    @FXML
    private Button delete;
    
    @FXML
    private Button insert;
    
    @FXML
    private Button update;
    
    private double x = 0;
    private double y = 0;
    
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
    
    public void close(){
        System.exit(0);
    }
    
    public void minimize(){
        Stage stage = (Stage)hokhau_view.getScene().getWindow();
        stage.setIconified(true);
    }
    
    
    //show data
    
    public ObservableList<hokhauModel> dataList(){
        
        ObservableList<hokhauModel> dataList = FXCollections.observableArrayList();
        
        String query = "select * from hokhau";
        Conn c = new Conn();
        hokhauModel hokhau;
        try{
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
                hokhau = new hokhauModel(rs.getInt("idHo"), rs.getInt("soThanhVien"), rs.getInt("soPhong"), rs.getString("address"));
                dataList.add(hokhau);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return dataList;
    }
    
    public void showData(){
        ObservableList<hokhauModel> showList = dataList();
        col_idHo.setCellValueFactory(new PropertyValueFactory<>("idHo"));
        col_soTV.setCellValueFactory(new PropertyValueFactory<>("soThanhVien"));
        col_soPhong.setCellValueFactory(new PropertyValueFactory<>("soPhong"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));      
        table_view.setItems(showList);
    }
    
    //insert hokhau
    
    public void insert(){
        Conn c = new Conn();
        String query = "insert into hokhau(idHo, soPhong, address) values('"+idHo.getText()+"', '"+soPhong.getText()+"', '"+address.getText()+"');";
        
        try {
            
            if(idHo.getText().isEmpty() || 
                    soPhong.getText().isEmpty() ||  
                    address.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter all blank fields");
                alert.showAndWait();
            }
            else{
                c.s.executeUpdate(query);
                showData();
                clear();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(nhankhauController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //clear data
    
    public void clear(){
        idHo.setText("");
        soPhong.setText("");
        address.setText("");
    }
    
    //update data
    
    public void update(){
        Conn c = new Conn();
        Conn c1 = new Conn();
        String query = "update hokhau set idHo = '"+idHo.getText()+"', soPhong = '"+soPhong.getText()+"', address = '"+address.getText()+"' where idHo = '"+idHo.getText()+"'";
        String query1 = "update hokhau set soThanhVien = (select count(*) from quanhe where quanhe.idHo = hokhau.idHo); ";
        try{
            c.s.executeUpdate(query);
            c1.s.executeUpdate(query1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Update the data!");
            alert.showAndWait();
            showData();
            clear();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //delete data
    
    public void delete(){
        String query = "delete from hokhau where idHo = '"+idHo.getText()+"'";
        Conn c = new Conn();
        try {
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure that you want to delete it?");
            
            Optional<ButtonType> buttonType = alert.showAndWait();
            
            if(buttonType.get() == ButtonType.OK){
                c.s.executeUpdate(query);
            } else {
                return;
            }
            
            showData();
            clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void selectHokhau(){
        hokhauModel hokhau = table_view.getSelectionModel().getSelectedItem();
        int num = table_view.getSelectionModel().getSelectedIndex();
        
        if((num-1) < -1)
            return;
        idHo.setText(String.valueOf(hokhau.getIdHo()));
        soPhong.setText(String.valueOf(hokhau.getSoPhong()));
        address.setText(hokhau.getAddress());
        showData();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        showData();
    }
}
