package com.clover.form.model;

import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Entity
@Table(name = "family_details")
public class FamilyDetailsList implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
//    @ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
//    @JoinColumn(name="employee_id")
//    private Employee employee;
    @Column(name = "name")
    private String name;
    @Column(name = "gender")
    private String gender;
    @Column(name = "relation")
    private String relation;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "contact_number")
    private long contactNumber;
}
