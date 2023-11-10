/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author pv
 */
public class hokhauModel {
    private final Integer idHo;
    private final Integer soThanhVien;
    private final String address;
    private final Integer soPhong;
    private final Integer area;

    public hokhauModel(Integer idHo, Integer soThanhVien, Integer soPhong, String address, Integer area) {
        this.idHo = idHo;
        this.soThanhVien = soThanhVien;
        this.soPhong = soPhong;
        this.address = address;
        this.area = area;
    }

    public Integer getIdHo() {
        return idHo;
    }

    public Integer getSoThanhVien() {
        return soThanhVien;
    }

    public String getAddress() {
        return address;
    }

    public Integer getSoPhong() {
        return soPhong;
    }

    public Integer getArea() {
        return area;
    }
    
}
