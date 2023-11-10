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
    private final String name;
    private final String kind;
    private final String carNumber;
    private final String CCCD;
    private final String time;
    private final String idKT;

    public guixeModel(Integer id, Integer idHo, String name, String kind, String carNumber, String CCCD, String time, String idKT) {
        this.id = id;
        this.idHo = idHo;
        this.name = name;
        this.kind = kind;
        this.carNumber = carNumber;
        this.CCCD = CCCD;
        this.time = time;
        this.idKT = idKT;
    }
    
    public Integer getId() {
        return id;
    }

    public Integer getIdHo() {
        return idHo;
    }

    public String getName() {
        return name;
    }

    public String getKind() {
        return kind;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getCCCD() {
        return CCCD;
    }

    public String getTime() {
        return time;
    }

    public String getIdKT() {
        return idKT;
    }
    
}
