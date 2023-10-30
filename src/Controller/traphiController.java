/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.Conn;
import Model.khoanthuModel;
import Model.traphiModel;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class traphiController implements Initializable{
    
    @FXML
    private Button back;

    @FXML
    private Button close;

    @FXML
    private TableColumn<traphiModel, Integer> col_idHo;

    @FXML
    private TableColumn<traphiModel, Integer> col_idKT;

    @FXML
    private TableColumn<traphiModel, Double> col_sotienKT;

    @FXML
    private TableColumn<traphiModel, Double> col_sotienTP;

    @FXML
    private TableColumn<traphiModel, String> col_tenKT;

    @FXML
    private TableColumn<traphiModel, Date> col_time;
    
    @FXML
    private TableView<traphiModel> table_view;

    @FXML
    private TextField idHo;

    @FXML
    private TextField idKT;

    @FXML
    private Button minimize;

    @FXML
    private Button payBtn;

    @FXML
    private TextField sotienKT;

    @FXML
    private TextField sotienTP;

    @FXML
    private TextField tenKT;

    @FXML
    private AnchorPane traphi_view;
    
    public void close(){
        System.exit(0);
    }
    
    public void minimize(){
        Stage stage = (Stage)traphi_view.getScene().getWindow();
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
    public ObservableList<traphiModel> dataList(){
        
        ObservableList<traphiModel> dataList = FXCollections.observableArrayList();
        
        String query = "select * from traphi order by idHo asc;";
        Conn c = new Conn();
        traphiModel traphi;
        try{
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
                traphi = new traphiModel(rs.getString("idKT"), rs.getString("tenKT"), 
                        rs.getInt("idHo"), rs.getDouble("sotienKT"), rs.getDouble("sotienTP"), rs.getString("time"));
                dataList.add(traphi);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return dataList;
    }
    
    public void showData(){
        ObservableList<traphiModel> showList = dataList();
        col_idHo.setCellValueFactory(new PropertyValueFactory<>("idHo"));
        col_idKT.setCellValueFactory(new PropertyValueFactory<>("idKT"));
        col_tenKT.setCellValueFactory(new PropertyValueFactory<>("tenKT"));
        col_sotienKT.setCellValueFactory(new PropertyValueFactory<>("sotienKT"));
        col_sotienTP.setCellValueFactory(new PropertyValueFactory<>("sotienTP"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        table_view.setItems(showList);
    }
    
    public void selectTraPhi(){
        traphiModel traphi = table_view.getSelectionModel().getSelectedItem();
        int num = table_view.getSelectionModel().getSelectedIndex();
        
        if((num-1) < -1)
            return;
        idKT.setText(traphi.getIdKT());
        tenKT.setText(traphi.getTenKT());
        idHo.setText(String.valueOf(traphi.getIdHo()));
        sotienKT.setText(String.valueOf(traphi.getSotienKT()));

        showData();
    }
    
    public void pay(){
        Conn c =  new Conn();
        Alert alert;
        double traphi = Double.parseDouble(sotienTP.getText());
        double khoanthu = Double.parseDouble(sotienKT.getText());
        String query = "update traphi set sotienTP = '"+traphi+"' where idKT = '"+idKT.getText()+"' and idHo = '"+idHo.getText()+"' ";
        try{
            
            if(traphi < khoanthu){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Not enough money!");
                alert.showAndWait();
            } else if(traphi > khoanthu){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Exceed the specified amount!");
                alert.showAndWait();
            } else if(traphi == khoanthu){
                c.s.executeUpdate(query);
                String updateKhoanThu = "update khoanthu set sotienKT = '"+0+"' where idKT = '"+idKT.getText()+"' and idHo = '"+idHo.getText()+"'";
                String updateStatus = "update khoanthu set status = '"+"hoanthanh"+"' where idKT = '"+idKT.getText()+"' and idHo = '"+idHo.getText()+"'";
                c.s.executeUpdate(updateKhoanThu);
                c.s.executeUpdate(updateStatus);
                showData();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showData();
    }
    
}
