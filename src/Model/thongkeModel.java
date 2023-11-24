/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author pv
 */
public class thongkeModel {
    private String time;
    private Double soTien;
    private Integer idHo;
    private Double debt;

    public thongkeModel(String time, Double soTien, Integer idHo, Double debt) {
        this.time = time;
        this.soTien = soTien;
        this.idHo = idHo;
        this.debt = debt;
    }

    public String getTime() {
        return time;
    }

    public Double getSoTien() {
        return soTien;
    }

    public Integer getIdHo() {
        return idHo;
    }

    public Double getDebt() {
        return debt;
    }
    
}
