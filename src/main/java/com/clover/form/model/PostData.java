package com.clover.form.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostData {

    private Employee employee;
    private MultipartFile file;
}
