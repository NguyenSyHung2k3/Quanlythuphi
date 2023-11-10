/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.Conn;
import Model.nhankhauModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author pv
 */
public class nhankhauController implements Initializable{
    
    @FXML
    private TextField search;
    
    @FXML
    private AnchorPane nhankhau_view;
    
    @FXML
    private TextField CCCD;

    @FXML
    private TextField age;

    @FXML
    private TableColumn<nhankhauModel, String> col_CCCD;

    @FXML
    private TableColumn<nhankhauModel, Integer> col_age;

    @FXML
    private TableColumn<nhankhauModel, Integer> col_id;

    @FXML
    private TableColumn<nhankhauModel, String> col_name;
    
    @FXML
    private TableColumn<nhankhauModel, String> col_gender;
    
    @FXML
    private TableColumn<nhankhauModel, Integer> col_idHo;
    
    @FXML
    private TableColumn<nhankhauModel, String> col_updateTime;

    @FXML
    private ComboBox<?> gender;

    @FXML
    private TextField id;

    @FXML
    private TextField name;
    
    @FXML
    private TextField idHo;

    @FXML
    private TableView<nhankhauModel> table_view;
    
    @FXML
    private Button clear;

    @FXML
    private Button delete;

    @FXML
    private Button update;
    
    @FXML
    private Button exit;
    
    @FXML
    private Button minimize;
    
    @FXML
    private Button back;
        
    // gender
    private String[] comboGender = {"Nam", "Nữ", "Khác"};
    
    public void comboBox(){
        List<String> list = new ArrayList<>();
        for(String nhankhau : comboGender){
            list.add(nhankhau);
        }
        ObservableList dataList = FXCollections.observableArrayList(list);
        gender.setItems(dataList);
        
    }
    
    // showdata
    public ObservableList<nhankhauModel> dataList(){
        
        ObservableList<nhankhauModel> dataList = FXCollections.observableArrayList();
        
        String query = "select * from nhankhau";
        Conn c = new Conn();
        nhankhauModel nhankhau;
        try{
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
                nhankhau = new nhankhauModel(rs.getInt("id"), rs.getString("name"), 
                        rs.getInt("age"), rs.getString("gender"), rs.getString("CCCD"), rs.getInt("idHo"), rs.getString("updateTime"));
                dataList.add(nhankhau);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return dataList;
    }
    
    public void showData(){
        ObservableList<nhankhauModel> showList = dataList();
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_CCCD.setCellValueFactory(new PropertyValueFactory<>("CCCD"));
        col_idHo.setCellValueFactory(new PropertyValueFactory<>("idHo"));
        col_updateTime.setCellValueFactory(new PropertyValueFactory<>("updateTime"));
        table_view.setItems(showList);
    }
    
    // insert
//    public void insert(){
//        Conn c = new Conn();
//        String query = "insert into nhankhau values('"+id.getText()+"', '"+name.getText()+"', '"+age.getText()+"', '"+gender.getSelectionModel().getSelectedItem()+"', '"+CCCD.getText()+"', '"+idHo.getText()+"');";
//        String query1 = "insert into quanhe values ('"+id.getText()+"', '"+idHo.getText()+"')";
//        try {
//            
//            if(id.getText().isEmpty() || 
//                    name.getText().isEmpty() || 
//                    age.getText().isEmpty() || 
//                    gender.getSelectionModel().isEmpty() || 
//                    CCCD.getText().isEmpty() ||
//                    idHo.getText().isEmpty()){
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Enter all blank fields");
//                alert.showAndWait();
//            }
//            else{
//                c.s.executeUpdate(query);
//                c.s.executeUpdate(query1);
////                Conn c1 = new Conn();
////                try{
////                    c1.s.executeUpdate(queryUpdate);
////                }catch(Exception e){
////                    e.printStackTrace();
////                }
////                
//                showData();
//                clear();
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(nhankhauController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    // select data
    public void selectNhankhau(){
        nhankhauModel nhankhau = table_view.getSelectionModel().getSelectedItem();
        int num = table_view.getSelectionModel().getSelectedIndex();
        
        if((num-1) < -1)
            return;
        id.setText(String.valueOf(nhankhau.getId()));
        id.setDisable(true);
        name.setText(nhankhau.getName());
        age.setText(String.valueOf(nhankhau.getAge()));
        gender.getSelectionModel().getSelectedItem();
        CCCD.setText(nhankhau.getCCCD());
        idHo.setText(String.valueOf(nhankhau.getIdHo()));
        showData();
    }
    
    // update data
    public void update(){
        Conn c = new Conn();
        String query = "update nhankhau set name = '"+name.getText()+"', age = '"+age.getText()+"', gender = '"+gender.getSelectionModel().getSelectedItem()+"', CCCD = '"+CCCD.getText()+"', idHo = '"+idHo.getText()+"', updateTime = current_date() where id = '"+id.getText()+"'";
        String query1 = "update quanhe set id = '"+id.getText()+"', idHo = '"+idHo.getText()+"'";
        try{
            c.s.executeUpdate(query);
            c.s.executeUpdate(query1);
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
    
    //clear data
    public void clear(){
        
        id.setText("");
        name.setText("");
        age.setText("");
        gender.getSelectionModel().clearSelection();
        CCCD.setText("");
        idHo.setText("");
    }
    
    //delete data
    public void delete(){
        String query = "delete from nhankhau where id = '"+id.getText()+"'";
        String query1 = "delete from quanhe where id = '"+id.getText()+"'";
        Conn c = new Conn();
        try {
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure that you want to delete it?");
            
            Optional<ButtonType> buttonType = alert.showAndWait();
            
            if(buttonType.get() == ButtonType.OK){
                c.s.executeUpdate(query);
                c.s.executeUpdate(query1);
            } else {
                return;
            }
            
            showData();
            clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void exit(){
        System.exit(0);
    }
    
    public void minimize(){
        Stage stage = (Stage)nhankhau_view.getScene().getWindow();
        stage.setIconified(true);
    }
    
    private double x;
    private double y;
    
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
    
    public void search(){
        
        FilteredList<nhankhauModel> filter = new FilteredList<>(dataList(), e->true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
        
            filter.setPredicate((var predicate)->{
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                
                String keySearch = newValue.toLowerCase();
                if(predicate.getName().toLowerCase().contains(keySearch)){
                    return true;
                } else if(predicate.getCCCD().toLowerCase().contains(keySearch)){
                    return true;
                } else if(predicate.getId().toString().contains(keySearch)){
                    return true;
                } else if(predicate.getIdHo().toString().contains(keySearch)){
                    return true;
                }
                
                return false;
            });
            
        
        });
        SortedList<nhankhauModel> sortNhankhau = new SortedList<>(filter);
        sortNhankhau.comparatorProperty().bind(table_view.comparatorProperty());
        table_view.setItems(sortNhankhau);
        
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox();
        showData();
        
    }
    
}
