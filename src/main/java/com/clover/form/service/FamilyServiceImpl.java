package com.clover.form.service;

import com.clover.form.model.Employee;
import com.clover.form.model.FamilyDetailsList;
import com.clover.form.repository.FamilyDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class FamilyServiceImpl implements FamilyService{

    @Autowired
    FamilyDetailsRepository familyDetailsRepository;

    @Override
    public List<FamilyDetailsList> getAllFamilyMembersByEmployeeId(Employee employee) {
        return familyDetailsRepository.findAllById(Collections.singleton(employee.getId()));
    }

    @Override
    public void saveFamilyMembers(FamilyDetailsList familyDetailsList) {
        this.familyDetailsRepository.save(familyDetailsList);
    }

    @Override
    public void deleteFamilyMemberById(long id) {
        familyDetailsRepository.deleteById(id);
    }

    @Override
    public void updateFamilyMember(FamilyDetailsList familyDetailsList) {
        familyDetailsRepository.save(familyDetailsList);
    }
}
