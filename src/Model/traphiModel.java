/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import oracle.sql.DATE;

/**
 *
 * @author pv
 */
public class traphiModel {
    private final String idKT;
    private final String tenKT;
    private final Integer idHo;
    private final double sotienKT;
    private final double sotienTP;
    private final String time;
    private final String timeTP;

    public traphiModel(String idKT, String tenKT, Integer idHo, double sotienKT, double sotienTP, String time, String timeTP) {
        this.idKT = idKT;
        this.tenKT = tenKT;
        this.idHo = idHo;
        this.sotienKT = sotienKT;
        this.sotienTP = sotienTP;
        this.time = time;
        this.timeTP = timeTP;
    }

    public String getIdKT() {
        return idKT;
    }

    public String getTenKT() {
        return tenKT;
    }

    public Integer getIdHo() {
        return idHo;
    }

    public double getSotienKT() {
        return sotienKT;
    }

    public double getSotienTP() {
        return sotienTP;
    }

    public String getTime() {
        return time;
    }

    public String getTimeTP() {
        return timeTP;
    }
    
}
