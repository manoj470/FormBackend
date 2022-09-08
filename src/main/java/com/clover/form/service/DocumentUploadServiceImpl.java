package com.clover.form.service;

import com.clover.form.model.DocumentDetails;
import com.clover.form.repository.DocumentDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
public class DocumentUploadServiceImpl implements DocumentUploadService{

    @Autowired
    DocumentDetailsRepo documentDetailsRepo;

    @Override
    public List<DocumentDetails> getAllDoc() {
        return documentDetailsRepo.findAll();
    }

    @Override
    public void saveDoc(DocumentDetails documentDetails) {
        documentDetailsRepo.save(documentDetails);
    }

    @Override
    public DocumentDetails getDocById(long id) {
        List<DocumentDetails> list = documentDetailsRepo.findAllById(Collections.singleton(id));
        if(!list.isEmpty()){
            if(list.get(0)!= null){
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public List<DocumentDetails> getAllDocByEmpId(long empId) {
        return documentDetailsRepo.findAllByEmpId(empId);
    }
}
