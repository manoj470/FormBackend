package com.clover.form.miscellaneous;

import com.clover.form.model.Employee;
import com.clover.form.model.FamilyDetailsList;

import java.util.ArrayList;
import java.util.List;

public class ExtendedService {

    public static List<Employee> setDataProper( List<Employee> dataList){
        for (Employee emp:dataList) {
            List<FamilyDetailsList> familyDetailsList = new ArrayList<>();
            for (FamilyDetailsList f : emp.getFamilyDetailsList()) {
                FamilyDetailsList m = new FamilyDetailsList();
                m.setName(f.getName());
                m.setRelation(f.getRelation());
                m.setContactNumber(f.getContactNumber());
                m.setGender(f.getGender());
                m.setId(f.getId());
                m.setDateOfBirth(f.getDateOfBirth());
                familyDetailsList.add(m);
            }
            emp.setFamilyDetailsList(familyDetailsList);
        }
        return dataList;
    }

    public static Employee setDataProper( Employee emp){
        List<FamilyDetailsList> familyDetailsList = new ArrayList<>();
        for (FamilyDetailsList f : emp.getFamilyDetailsList()) {
            FamilyDetailsList m = new FamilyDetailsList();
            m.setName(f.getName());
            m.setRelation(f.getRelation());
            m.setContactNumber(f.getContactNumber());
            m.setGender(f.getGender());
            m.setId(f.getId());
            m.setDateOfBirth(f.getDateOfBirth());
            familyDetailsList.add(m);
        }
        emp.setFamilyDetailsList(familyDetailsList);
        return emp;
    }

    public static List<Employee> setNull(List<Employee> dataList){
        for (Employee emp:dataList) {
            emp.setFamilyDetailsList(null);
        }
        return dataList;
    }
}
