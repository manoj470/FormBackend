package com.clover.form.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "emp_doc")
public class DocumentDetails implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Lob
    @Column(name="file")
    private byte[] file;
    @Column(name = "type")
    private String type;
    @Column(name = "size")
    private long size;
    @Column(name = "empId")
    private long empId;
}
