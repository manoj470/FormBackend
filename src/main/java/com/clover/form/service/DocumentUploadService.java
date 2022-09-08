package com.clover.form.service;

import com.clover.form.model.DocumentDetails;

import java.util.List;

public interface DocumentUploadService {

    List<DocumentDetails> getAllDoc();
    void saveDoc(DocumentDetails documentDetails);
    DocumentDetails getDocById(long docId);
    List<DocumentDetails> getAllDocByEmpId(long empId);



}
