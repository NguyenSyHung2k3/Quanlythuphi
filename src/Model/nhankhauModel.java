/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import Model.hokhauModel;

/**
 *
 * @author pv
 */
public class nhankhauModel {
    private final Integer id;
    private final String name;
    private final Integer age;
    private final String gender;
    private final String CCCD;
    private final Integer idHo;
    private final String updateTime;
    
    public nhankhauModel(Integer id, String name, Integer age, String gender, String CCCD, Integer idHo, String updateTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.CCCD = CCCD;
        this.idHo = idHo;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public Integer getAge() {
        return age;
    }


    public String getGender() {
        return gender;
    }


    public String getCCCD() {
        return CCCD;
    }

    public Integer getIdHo() {
        return idHo;
    }

    public String getUpdateTime() {
        return updateTime;
    }
}
