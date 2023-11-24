/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.Conn;
import Model.User;
import Model.thongkeModel;
import Model.traphiModel;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    
    @FXML
    private TableColumn<thongkeModel, Integer> idHo_col;
    @FXML
    private TableView<thongkeModel> table_view;

    @FXML
    private TableColumn<thongkeModel, Double> tienNop_col;

    @FXML
    private TableColumn<thongkeModel, Double> tienThieu_col;

    @FXML
    private TableColumn<thongkeModel, String> time_col;
    
    @FXML
    private TextField soTienTungHo;
    
    @FXML
    private TextField idHo;
    
    @FXML
    private Label totalAmount;
    
    @FXML
    private Button clear;
    
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
    
    // showdata
    public ObservableList<thongkeModel> dataList(){
        
        ObservableList<thongkeModel> dataList = FXCollections.observableArrayList();
        
        String query = "select thongke.* from thongke, hokhau where thongke.idHo = hokhau.idHo and hokhau.soThanhVien >= 1";
        Conn c = new Conn();
        thongkeModel thongke;
        try{
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
                thongke = new thongkeModel(rs.getString("time"), rs.getDouble("sotien"), 
                        rs.getInt("idHo"), rs.getDouble("debt"));
                dataList.add(thongke);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return dataList;
    }
    
    public void showData(){
        ObservableList<thongkeModel> showList = dataList();
        time_col.setCellValueFactory(new PropertyValueFactory<>("time"));
        idHo_col.setCellValueFactory(new PropertyValueFactory<>("idHo"));
        tienNop_col.setCellValueFactory(new PropertyValueFactory<>("soTien"));
        tienThieu_col.setCellValueFactory(new PropertyValueFactory<>("debt"));
        table_view.setItems(showList);
    }
    
    public void account(){
        user.setText(User.username);
    }
    
    public void clear(){
        Conn c = new Conn();
        String deleteThongke = "update thongke set soTien = 0, debt = 0, time = null";
        try {
            c.s.executeUpdate(deleteThongke);
        } catch (Exception e) {
            
        }
    }
    
    public void searchIdHo(){
        Conn c = new Conn();
        String query = "select soTien from thongke where idHo = '"+idHo.getText()+"'";
        Double sum = 0.0;
        ResultSet rs;
        try {
            rs = c.s.executeQuery(query);
            while(rs.next()){
                sum+=Double.parseDouble(rs.getString("soTien"));
            }
        } catch (Exception e) {
            
        }
        
        soTienTungHo.setText(String.valueOf(sum));
        
    }
    
    public void total(){
        Conn c = new Conn();
        String query = "select soTien from thongke";
        Double sum = 0.0;
        try {
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
                sum+=Double.parseDouble(rs.getString("soTien"));
            }
        } catch (Exception e) {
            
        }
        totalAmount.setText(String.valueOf(sum));
    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        account();
        showData();
        total();
//        showInfo();
        soTienTungHo.setDisable(true);

    }
    
    
}
