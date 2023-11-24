/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.Conn;
import Model.khoanthuModel;
import Model.nhankhauModel;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import oracle.jdbc.driver.DatabaseError;

/**
 *
 * @author pv
 */
public class khoanthuController implements Initializable{
    
     @FXML
    private Button back;

    @FXML
    private Button close;
    
    @FXML
    private Button minimize;
    
    // Bảng

    @FXML
    private TableColumn<khoanthuModel, Integer> col_idHo;

    @FXML
    private TableColumn<khoanthuModel, String> col_idKT;

    @FXML
    private TableColumn<khoanthuModel, Double> col_soTienKT;

    @FXML
    private TableColumn<khoanthuModel, String> col_status;

    @FXML
    private TableColumn<khoanthuModel, String> col_tenKT;
    
    @FXML
    private TableColumn<khoanthuModel, String> col_time;
    
    @FXML
    private TableView<khoanthuModel> table_view;
    
    @FXML
    private AnchorPane khoanthu_view;

    // Bắt buộc
    
    @FXML
    private Button clearBB;
    
    @FXML
    private Button deleteBB;
    
    @FXML
    private TextField idKTBB;
    
    @FXML
    private TextField sotienKTBB;
    
    @FXML
    private TextField tenKTBB;
    
    @FXML
    private Button updateBB;
    
    // Tự nguyện
    
    @FXML
    private Button clearTN;
    
    @FXML
    private Button deleteTN;

    @FXML
    private TextField idHoTN;
    
    @FXML
    private TextField idKTTN;
    
    @FXML
    private TextField sotienKTTN;
    
    @FXML
    private TextField tenKTTN;
    
    @FXML
    private Button updateTN;
    
    private Integer idHoBB;
    
    public void close(){
        System.exit(0);
    }
    
    public void minimize(){
        Stage stage = (Stage)khoanthu_view.getScene().getWindow();
        stage.setIconified(true);
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
    
    // showdata
    public ObservableList<khoanthuModel> dataList(){
        
        ObservableList<khoanthuModel> dataList = FXCollections.observableArrayList();
        
        String query = "select * from khoanthu order by idHo asc;";
        Conn c = new Conn();
        khoanthuModel khoanthu;
        try{
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
                khoanthu = new khoanthuModel(rs.getInt("idHo"), rs.getString("idKT"), 
                        rs.getString("tenKT"), rs.getDouble("sotienKT"), rs.getString("status"), rs.getString("time"));
                dataList.add(khoanthu);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return dataList;
    }
    
    public void showData(){
        ObservableList<khoanthuModel> showList = dataList();
        col_idHo.setCellValueFactory(new PropertyValueFactory<>("idHo"));
        col_idKT.setCellValueFactory(new PropertyValueFactory<>("idKT"));
        col_tenKT.setCellValueFactory(new PropertyValueFactory<>("tenKT"));
        col_soTienKT.setCellValueFactory(new PropertyValueFactory<>("sotienKT"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        table_view.setItems(showList);
    }
    
    // Các khoản thu bắt buộc

    public void updateBB(){
        Conn c = new Conn();
        String totalHouseHoldQuery = "select idHo from hokhau where soThanhVien >= 1";
        if(idKTBB.getText().isEmpty() ||
                tenKTBB.getText().isEmpty() ||
                sotienKTBB.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Enter all blank fields");
            alert.showAndWait();
        }
        else if(!idKTBB.getText().startsWith("BB")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Wrong id");
            alert.showAndWait();
            clearBB();
        }
        else{
            
            Double sotienBB = Double.parseDouble(sotienKTBB.getText());
            
            try{
                try(ResultSet rs = c.s.executeQuery(totalHouseHoldQuery)){

                    while(rs.next()){    
                        Integer idHo = rs.getInt("idHo");
                        
                        try{
                            String query = "insert into khoanthu(idHo, idKT, tenKT, sotienKT, time) values('"+idHo+"', '"+idKTBB.getText()+"', '"+tenKTBB.getText()+"', '"+sotienKTBB.getText()+"', current_date());";
                            c.s.executeUpdate(query);    
                            String updatePayCheck = "insert into traphi(idKT, tenKT, idHo, sotienKT, time) values('"+idKTBB.getText()+"', '"+tenKTBB.getText()+"', '"+idHo+"', '"+sotienKTBB.getText()+"', current_date());";
                            c.s.executeUpdate(updatePayCheck);
                            String thongkeUpdate = "update thongke set debt = debt + '"+sotienBB+"', time = current_date() where idHo = '"+idHo+"'";
                            c.s.executeUpdate(thongkeUpdate);
                        } catch(Exception e){
                            
                        }
                        
                    }   
//                
                }
//                while(rs.next()){

//                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            

            showData();
            clearBB();
        }
    }
    
    public void updateTN(){
        Conn c = new Conn();
        String query = "insert into khoanthu(idHo, idKT, tenKT, sotienKT, time) values('"+idHoTN.getText()+"', '"+idKTTN.getText()+"', '"+tenKTTN.getText()+"', '"+sotienKTTN.getText()+"', current_date());";
        try {
            
            if(idKTTN.getText().isEmpty() || 
                    tenKTTN.getText().isEmpty() || 
                    sotienKTTN.getText().isEmpty() ||
                    idHoTN.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter all blank fields");
                alert.showAndWait();
            }
            else if(!idKTTN.getText().startsWith("TN")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong id");
                alert.showAndWait();
                clearTN();
            }
            else{
                Double sotienTN = Double.parseDouble(sotienKTTN.getText());
                c.s.executeUpdate(query);
                String updatePayCheck = "insert into traphi(idKT, tenKT, idHo, sotienKT, time) values('"+idKTTN.getText()+"', '"+tenKTTN.getText()+"', '"+idHoTN.getText()+"', '"+sotienKTTN.getText()+"', current_date());";
                String thongkeUpdate = "update thongke set debt = debt + '"+sotienTN+"', time = current_date() where idHo = '"+idHoTN.getText()+"'";
                Conn c2 = new Conn();
                    try{
                        c2.s.executeUpdate(updatePayCheck);
                        
                        c2.s.executeUpdate(thongkeUpdate);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                showData();
                clearTN();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(nhankhauController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clearBB(){
        idKTBB.setText("");
        tenKTBB.setText("");
        sotienKTBB.setText("");
    }
    
    public void clearTN(){
        idHoTN.setText("");
        idKTTN.setText("");
        tenKTTN.setText("");
        sotienKTTN.setText("");
        
    }
    
    public void deleteBB(){
        String query = "delete from khoanthu where idKT = '"+idKTBB.getText()+"' and idHo = '"+idHoBB+"'";
        String query1 = "delete from traphi where idKT = '"+idKTBB.getText()+"' and idHo = '"+idHoBB+"'";
        Conn c = new Conn();
        Alert alert;
        Alert alert1;
        try {
            
            if(Double.parseDouble(sotienKTBB.getText()) == 0){
                alert = new Alert(Alert.AlertType.CONFIRMATION);
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
            } else {
                alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error Message");
                alert1.setHeaderText(null);
                alert1.setContentText("You can't delete this bill");
                alert1.showAndWait();
            }
            
            showData();
            clearBB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteTN(){
        String query = "delete from khoanthu where idKT = '"+idKTTN.getText()+"' and idHo = '"+idHoTN.getText()+"';";
        String query1 = "delete from traphi where idKT = '"+idKTTN.getText()+"' and idHo = '"+idHoTN.getText()+"';";
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
            clearTN();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void selectKhoanThu(){
        khoanthuModel khoanthu = table_view.getSelectionModel().getSelectedItem();
        int num = table_view.getSelectionModel().getSelectedIndex();
        
        if((num-1) < -1)
            return;
        String s = col_idKT.getCellData(khoanthu);
        if(s.startsWith("BB")){
            idKTBB.setText(String.valueOf(khoanthu.getIdKT()));
            tenKTBB.setText(khoanthu.getTenKT());
            sotienKTBB.setText(String.valueOf(khoanthu.getSotienKT()));
            idHoBB = khoanthu.getIdHo();
            clearTN();
        } else if(s.startsWith("TN")){
            idKTTN.setText(String.valueOf(khoanthu.getIdKT()));
            tenKTTN.setText(khoanthu.getTenKT());
            sotienKTTN.setText(String.valueOf(khoanthu.getSotienKT()));
            idHoTN.setText(String.valueOf(khoanthu.getIdHo()));
            clearBB();
        } else if(s.startsWith("GX")){
            idKTBB.setText(String.valueOf(khoanthu.getIdKT()));
            tenKTBB.setText(khoanthu.getTenKT());
            sotienKTBB.setText(String.valueOf(khoanthu.getSotienKT()));
            idHoBB = khoanthu.getIdHo();
            clearTN();
        }

        showData();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showData();
    }
    
    
}







