
import Connection.Conn;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pv
 */
public class test {
    public static void main(String[] args) {
        
        Conn c = new Conn();
        Random r = new Random();
        
        try{
            for(int i=100; i<200; i++){
                int random = r.nextInt(21)+30;
                c.s.executeUpdate("insert into hokhau values('"+i+"', 0, '"+i+"', '"+"Hanoi"+"', '"+random+"')");
            }
            
        }catch(Exception e){
            
        }
        
    }
}
