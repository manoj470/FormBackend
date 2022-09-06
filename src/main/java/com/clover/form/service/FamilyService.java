package com.clover.form.service;

import com.clover.form.model.Employee;
import com.clover.form.model.FamilyDetailsList;

import java.util.List;

public interface FamilyService {
    List<FamilyDetailsList> getAllFamilyMembersByEmployeeId(Employee employee);
    void saveFamilyMembers(FamilyDetailsList familyDetailsList);
    void deleteFamilyMemberById(long id);
    void updateFamilyMember(FamilyDetailsList familyDetailsList);
}
