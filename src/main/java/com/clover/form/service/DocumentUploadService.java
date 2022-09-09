package com.clover.form.service;

import com.clover.form.model.DocumentDetails;
import com.clover.form.model.ResponseMsg;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentUploadService {

    List<DocumentDetails> getAllDoc();
    ResponseMsg saveDoc(DocumentDetails documentDetails, MultipartFile file);
    DocumentDetails getDocById(long docId);
    List<DocumentDetails> getAllDocByEmpId(long empId);

    ResponseMsg deleteDocById(long id);



}
