package com.clover.form.miscellaneous;

import com.clover.form.model.Employee;
import com.clover.form.model.FamilyDetailsList;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public static MediaType getMediaType(String type){
        if(Objects.equals(type, "jpeg")){
            return MediaType.IMAGE_JPEG;
        } else if (Objects.equals(type, "png")) {
            return MediaType.IMAGE_PNG;
        } else if (Objects.equals(type, "pdf")) {
            return MediaType.APPLICATION_PDF;
        }else {
            return MediaType.APPLICATION_JSON;
        }
    }

    public static boolean checkMediaType(String type){
        if(type.equalsIgnoreCase("jpeg")){
            return true;
        } else if (type.equalsIgnoreCase("png")) {
            return true;
        }else if (type.equalsIgnoreCase("jpg")) {
            return true;
        }else return type.equalsIgnoreCase("pdf");
    }
}
