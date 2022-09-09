package com.clover.form.service;

import com.clover.form.miscellaneous.ExtendedService;
import com.clover.form.model.DocumentDetails;
import com.clover.form.model.ResponseMsg;
import com.clover.form.repository.DocumentDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseMsg saveDoc(DocumentDetails documentDetails, MultipartFile file) {
        try {
            if(file!=null){
                try {
                    documentDetails.setFile(file.getBytes());
                }catch (Exception exception){
                    return new ResponseMsg(null,"Media type is not proper");
                }
            }else {
                return new ResponseMsg(null,"Media is not present.");
            }
            documentDetails.setType(documentDetails.getName().split("\\.")[1]);
            documentDetails.setName(documentDetails.getName().split("\\.")[0]);
            System.out.println(documentDetails);
            if(ExtendedService.checkMediaType(documentDetails.getType())){
                DocumentDetails details = documentDetailsRepo.save(documentDetails);
                return new ResponseMsg(details.getId(),"Uploaded Successfully");
            }else{
                return new ResponseMsg(null,"Media type is not proper");
            }
        }catch (Exception e){
            throw new RuntimeException();
        }

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

    @Override
    public ResponseMsg deleteDocById(long id) {
        try {
            documentDetailsRepo.deleteById(id);
            return new ResponseMsg(id,"Deleted Successfully..");
        }catch (Exception e){
            throw new RuntimeException("Unable to delete for id: "+id);
        }
    }


}
