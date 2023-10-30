/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author pv
 */
public class guixeModel {
    private final Integer id;
    private final Integer idHo;
    private final String ten;
    private final String kieu;
    private final String bienso;
    private final String cccd;

    public guixeModel(Integer id, Integer idHo, String ten, String kieu, String bienso, String cccd) {
        this.id = id;
        this.idHo = idHo;
        this.ten = ten;
        this.kieu = kieu;
        this.bienso = bienso;
        this.cccd = cccd;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdHo() {
        return idHo;
    }

    public String getTen() {
        return ten;
    }

    public String getKieu() {
        return kieu;
    }

    public String getBienso() {
        return bienso;
    }

    public String getCccd() {
        return cccd;
    }
    
    
    
}
