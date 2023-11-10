/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.Conn;
import Model.guixeModel;
import Model.hokhauModel;
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
import javafx.scene.control.ChoiceBox;
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
public class guixeController implements Initializable{
    
     @FXML
    private TextField CCCD;

    @FXML
    private Button back;

    @FXML
    private TextField carNumber;

    @FXML
    private Button close;

    @FXML
    private TableColumn<guixeModel, String> col_CCCD;

    @FXML
    private TableColumn<guixeModel, String> col_carNumber;

    @FXML
    private TableColumn<guixeModel, Integer> col_id;

    @FXML
    private TableColumn<guixeModel, Integer> col_idHo;
    
    @FXML
    private TableColumn<guixeModel, String> col_idKT;

    @FXML
    private TableColumn<guixeModel, String> col_kind;

    @FXML
    private TableColumn<guixeModel, String> col_name;

    @FXML
    private TableColumn<guixeModel, String> col_time;

    @FXML
    private AnchorPane guixe_view;

    @FXML
    private TextField id;

    @FXML
    private TextField idHo;

    @FXML
    private ChoiceBox<?> kind;

    @FXML
    private Button minimize;

    @FXML
    private TextField name;

    @FXML
    private TableView<guixeModel> table_view;
    
    @FXML
    private Button clear;
    
    @FXML
    private Button delete;
    
    @FXML
    private Button register;
    
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
    // kind of vehicle
    private String[] comboCar = {"xe đạp", "xe máy", "xe ô tô"};
    
    public void comboBox(){
        List<String> list = new ArrayList<>();
        for(String car : comboCar){
            list.add(car);
        }
        ObservableList dataList = FXCollections.observableArrayList(list);
        kind.setItems(dataList);
    }
    
    public ObservableList<guixeModel> dataList(){
        
        ObservableList<guixeModel> dataList = FXCollections.observableArrayList();
        
        String query = "select * from guixe";
        Conn c = new Conn();
        guixeModel guixe;
        try{
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
                guixe = new guixeModel(rs.getInt("id"), rs.getInt("idHo"), rs.getString("name"), rs.getString("kind"), rs.getString("carNumber"), rs.getString("CCCD"), rs.getString("time"), rs.getString("idKT"));
                dataList.add(guixe);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return dataList;
    }
    
    public void showData(){
        ObservableList<guixeModel> showList = dataList();
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_idHo.setCellValueFactory(new PropertyValueFactory<>("idHo"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_kind.setCellValueFactory(new PropertyValueFactory<>("kind"));
        col_carNumber.setCellValueFactory(new PropertyValueFactory<>("carNumber"));
        col_CCCD.setCellValueFactory(new PropertyValueFactory<>("CCCD"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        col_idKT.setCellValueFactory(new PropertyValueFactory<>("idKT"));
        table_view.setItems(showList);
    }
    
    
    
    public void register(){
        
        Conn c = new Conn();
        String s1 = "GX-";
        String idKT = s1.concat(carNumber.getText());
        String query = "insert into guixe values('"+id.getText()+"', '"+idHo.getText()+"', '"+name.getText()+"', '"+kind.getSelectionModel().getSelectedItem()+"', '"+carNumber.getText()+"', '"+CCCD.getText()+"', current_date(), '"+idKT+"')";               
        
        try {
            
            if(id.getText().isEmpty() || 
                    idHo.getText().isEmpty() ||  
                    name.getText().isEmpty() ||
                    kind.getSelectionModel().isEmpty() ||
                    carNumber.getText().isEmpty() ||
                    CCCD.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter all blank fields");
                alert.showAndWait();
            }
            else{
                c.s.executeUpdate(query);
                if(kind.getSelectionModel().getSelectedItem() == "xe đạp"){
                    String updateKhoanThu = "insert into khoanthu(idHo, idKT, tenKT, sotienKT) values('"+idHo.getText()+"', '"+idKT+"', '"+"gửi xe"+"', '"+15000+"')";
                    String updateTraPhi = "insert into traphi values('"+idKT+"', '"+"gửi xe"+"', '"+idHo.getText()+"', '"+15000+"', '"+15000+"', current_date())";
                    c.s.executeUpdate(updateKhoanThu);
                    c.s.executeUpdate(updateTraPhi);
                } else if(kind.getSelectionModel().getSelectedItem() == "xe máy"){
                    String updateKhoanThu = "insert into khoanthu(idHo, idKT, tenKT, sotienKT) values('"+idHo.getText()+"', '"+idKT+"', '"+"gửi xe"+"', '"+70000+"')";
                    String updateTraPhi = "insert into traphi values('"+idKT+"', '"+"gửi xe"+"', '"+idHo.getText()+"', '"+70000+"', '"+70000+"', current_date())";
                    c.s.executeUpdate(updateKhoanThu);
                    c.s.executeUpdate(updateTraPhi);
                } else if(kind.getSelectionModel().getSelectedItem() == "xe ô tô"){
                    String updateKhoanThu = "insert into khoanthu(idHo, idKT, tenKT, sotienKT) values('"+idHo.getText()+"', '"+idKT+"', '"+"gửi xe"+"', '"+1200000+"')";
                    String updateTraPhi = "insert into traphi values('"+idKT+"', '"+"gửi xe"+"', '"+idHo.getText()+"', '"+1200000+"', '"+1200000+"', current_date())";
                    c.s.executeUpdate(updateKhoanThu);
                    c.s.executeUpdate(updateTraPhi);
                }
                showData();
                clear();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(nhankhauController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void clear(){
        id.setText("");
        idHo.setText("");
        name.setText("");
        kind.getSelectionModel().clearSelection();
        carNumber.setText("");
        CCCD.setText("");
        
    }
    
    public void delete(){
        String s1 = "GX-";
        String idKT = s1.concat(carNumber.getText());
        String query = "delete from guixe where idKT = '"+idKT+"'";
        String deleteKhoanThu = "delete from khoanthu where idKT = '"+idKT+"'";
        String deleteTraPhi = "delete from traphi where idKT = '"+idKT+"'";
        Conn c = new Conn();
        try {
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure that you want to delete it?");
            
            Optional<ButtonType> buttonType = alert.showAndWait();
            
            if(buttonType.get() == ButtonType.OK){
                c.s.executeUpdate(query);
                c.s.executeUpdate(deleteKhoanThu);
                c.s.executeUpdate(deleteTraPhi);
            } else {
                return;
            }
            
            showData();
            clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void selectGuiXe(){
        guixeModel guixe = table_view.getSelectionModel().getSelectedItem();
        int num = table_view.getSelectionModel().getSelectedIndex();
        
        if((num-1) < -1)
            return;
        id.setText(String.valueOf(guixe.getId()));
        idHo.setText(String.valueOf(guixe.getIdHo()));
        name.setText(guixe.getName());
        kind.getSelectionModel().getSelectedItem();
        carNumber.setText(guixe.getCarNumber());
        CCCD.setText(guixe.getCCCD());
        showData();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showData();
        comboBox();
    }
    
}
