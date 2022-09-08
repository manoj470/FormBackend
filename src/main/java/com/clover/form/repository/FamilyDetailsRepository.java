package com.clover.form.repository;

import com.clover.form.model.FamilyDetailsList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyDetailsRepository extends JpaRepository<FamilyDetailsList,Long> {
}
