/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author pv
 */
public class khoanthuModel {
    private final Integer idHo;
    private final String idKT;
    private final String tenKT;
    private final Double sotienKT;
    private final String status;
    private final String time;

    public khoanthuModel(Integer idHo, String idKT, String tenKT, Double sotienKT, String status, String time) {
        this.idHo = idHo;
        this.idKT = idKT;
        this.tenKT = tenKT;
        this.sotienKT = sotienKT;
        this.status = status;
        this.time = time;
    }

    public Integer getIdHo() {
        return idHo;
    }

    public String getIdKT() {
        return idKT;
    }

    public String getTenKT() {
        return tenKT;
    }

    public Double getSotienKT() {
        return sotienKT;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }
}
