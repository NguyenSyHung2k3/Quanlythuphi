/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.Conn;
import Model.User;
import Model.khoanthuModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
/**
 *
 * @author pv
 */
public class userController implements Initializable{
    
    @FXML
    private TextField CCCD;

    @FXML
    private TextField age;

    @FXML
    private Button close;

    @FXML
    private ChoiceBox<?> gender;

    @FXML
    private Button hokhauBtn;

    @FXML
    private AnchorPane hokhau_view;
    
    @FXML
    private Button updateHoKhau;
    
    @FXML
    private AnchorPane khoanthu_view;

    @FXML
    private TextField id;

    @FXML
    private TextField idHo;

    @FXML
    private Button khoanthuBtn;

    @FXML
    private Button minimize;

    @FXML
    private TextField name;

    @FXML
    private Button nhankhauBtn;

    @FXML
    private AnchorPane nhankhau_view;

    @FXML
    private AnchorPane user_view;
    
    @FXML
    private Button insertNhanKhau;
    
    @FXML
    private Label address;
    
    @FXML
    private Label area;
    
    @FXML
    private Label idHoKhau;
    
    @FXML
    private Label soPhong;
    
    @FXML
    private Label soThanhVien;
    
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
    private Label user;
    
    @FXML
    private AnchorPane nhankhau_afterview;
    
    @FXML
    private Label CCCD_after;
    
    @FXML
    private Label age_after;
    
    @FXML
    private Label gender_after;
    
    @FXML
    private Label idHo_after;

    @FXML
    private Label id_after;
    
    @FXML
    private Label name_after;
    
    @FXML
    private Button signout;
    
    public void minimize(){
        Stage stage = (Stage)user_view.getScene().getWindow();
        stage.setIconified(true);
    }
    
    public void close(){
        System.exit(0);
    }
    
    // nhankhau
    
    private String[] comboGender = {"Nam", "Nữ", "Khác"};
    
    public void comboBox(){
        List<String> list = new ArrayList<>();
        for(String nhankhau : comboGender){
            list.add(nhankhau);
        }
        ObservableList dataList = FXCollections.observableArrayList(list);
        gender.setItems(dataList);
        
    }
    
    public void insertNhanKhau(){
        
        Conn c = new Conn();
        String query = "insert into nhankhau values('"+id.getText()+"', '"+name.getText()+"', '"+age.getText()+"', '"+gender.getSelectionModel().getSelectedItem()+"', '"+CCCD.getText()+"', '"+idHo.getText()+"', current_date())";    
        String updateQuanHe = "insert into quanhe values ('"+id.getText()+"', '"+idHo.getText()+"')";
        String updateUser_Id = "update user_id set id = '"+id.getText()+"' where username = '"+User.username+"'";
        try {
            if(id.getText().isEmpty() || 
                    name.getText().isEmpty() || 
                    age.getText().isEmpty() || 
                    gender.getSelectionModel().isEmpty() || 
                    CCCD.getText().isEmpty() ||
                    idHo.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter all blank fields");
                alert.showAndWait();
            }
            else {
                c.s.executeUpdate(query);
                c.s.executeUpdate(updateQuanHe);
                c.s.executeUpdate(updateUser_Id);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public void clear(){
        id.setText("");
        name.setText("");
        age.setText("");
        gender.getSelectionModel().clearSelection();
        CCCD.setText("");
        idHo.setText("");
    }
    
    private double x=0;
    private double y=0;
    
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
    
    // Hokhau
    public void updateHoKhau(){
        
        Conn c = new Conn();
        String update = "update hokhau set soThanhVien = (select count(*) from quanhe where quanhe.idHo = '"+idHo.getText()+"')";
        String select = "select * from hokhau where idHo = '"+idHo_after.getText()+"'";
        try{
            c.s.executeUpdate(update);
            ResultSet rs = c.s.executeQuery(select);
            while(rs.next()){
                idHoKhau.setText(String.valueOf(rs.getInt("idHo"))) ;
                soThanhVien.setText(String.valueOf(rs.getInt("soThanhVien")));
                soPhong.setText(String.valueOf(rs.getInt("soPhong")));
                address.setText(rs.getString("address"));
                area.setText(String.valueOf(rs.getInt("area")));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // khoanthu
    
    public ObservableList<khoanthuModel> dataList(){
        ObservableList<khoanthuModel> dataList = FXCollections.observableArrayList();
        String query = "select * from khoanthu where idHo = '"+idHo_after.getText()+"';";
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
        col_idKT.setCellValueFactory(new PropertyValueFactory<>("idKT"));
        col_tenKT.setCellValueFactory(new PropertyValueFactory<>("tenKT"));
        col_soTienKT.setCellValueFactory(new PropertyValueFactory<>("sotienKT"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        table_view.setItems(showList);
    }
    
    public void updateKhoanThu(){

        Conn c = new Conn();
        String query = "select * from khoanthu where idHo = '"+idHo_after.getText()+"'";
        khoanthuModel khoanthu;
        try{
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
                khoanthu = new khoanthuModel(rs.getInt("idHo"), rs.getString("idKT"), 
                        rs.getString("tenKT"), rs.getDouble("sotienKT"), rs.getString("status"), rs.getString("time"));
                showData();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void account(){
        Conn c = new Conn();
        String query = "select name from nhankhau, account, user_id\n" +
                        "where user_id.username = account.username "
                        + "and user_id.id = nhankhau.id and account.username = '"+User.username+"';";
        try {
             ResultSet rs = c.s.executeQuery(query);
             while(rs.next()){
                 if(rs.getString("name") == null){
                     user.setText("User");
                 }
                 user.setText(rs.getString("name"));
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void afterInsert(){
        Conn c = new Conn();
        String query = "select nhankhau.id, name, age, gender, CCCD, idHo from user_id, nhankhau where nhankhau.id = user_id.id and user_id.username = '"+User.username+"'";
        try{
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
                id_after.setText(rs.getString("id"));
                name_after.setText(rs.getString("name"));
                age_after.setText(rs.getString("age"));
                gender_after.setText(rs.getString("gender"));
                CCCD_after.setText(rs.getString("CCCD"));
                idHo_after.setText(rs.getString("idHo"));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void info(){
        
        Conn c = new Conn();
        String query = "select id from account, user_id\n" +
                        "where user_id.username = account.username "
                        + "and account.username = '"+User.username+"';";
        try{
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
                if(rs.getString("id") == null){
                    nhankhau_view.setVisible(true);
                    nhankhau_afterview.setVisible(false);
                }
                else if(rs.getString("id") != null){
                    nhankhau_view.setVisible(false);
                    nhankhau_afterview.setVisible(true);
                    afterInsert();
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void changeForm(){
        
        if(nhankhauBtn.isFocused()){
            info();
            hokhau_view.setVisible(false);
            khoanthu_view.setVisible(false);
            
        } else if(hokhauBtn.isFocused()){
            nhankhau_view.setVisible(false);
            hokhau_view.setVisible(true);
            khoanthu_view.setVisible(false);
            nhankhau_afterview.setVisible(false);
        } else if(khoanthuBtn.isFocused()){
            nhankhau_view.setVisible(false);
            hokhau_view.setVisible(false);
            nhankhau_afterview.setVisible(false);
            khoanthu_view.setVisible(true);
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox();
        showData();
        account();
        info();
    }   
}
