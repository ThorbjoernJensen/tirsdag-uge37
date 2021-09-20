/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tha
 */
public class EmployeeDTO {
    private long id;
    private String str1;
    private String str2;

//    public EmployeeDTO(String name, String address) {
//        this.str1 = name;
//        this.str2 = address;
//    }

    public EmployeeDTO(Employee e) {
        if (e.getId() != null)
            this.id = e.getId();
        this.str1 = e.getName();
        this.str2 = e.getAddress();
    }


    public static List<EmployeeDTO> getDtos(List<Employee> rms) {
        List<EmployeeDTO> rmdtos = new ArrayList();
        rms.forEach(rm -> rmdtos.add(new EmployeeDTO(rm)));
        return rmdtos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDummyStr1() {
        return str1;
    }

    public void setDummyStr1(String dummyStr1) {
        this.str1 = dummyStr1;
    }

    public String getDummyStr2() {
        return str2;
    }

    public void setDummyStr2(String dummyStr2) {
        this.str2 = dummyStr2;
    }

    @Override
    public String toString() {
        return "RenameMeDTO{" + "id=" + id + ", str1=" + str1 + ", str2=" + str2 + '}';
    }


}
