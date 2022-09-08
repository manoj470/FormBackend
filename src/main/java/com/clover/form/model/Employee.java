package com.clover.form.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    @Column(name = "employee_name")
    private String employeeName;
    @Column(name = "date_of_birth")
    @Type(type="date")
    private Date dateOfBirth;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "gender")
    private String gender;
    @Column(name = "hobbies")
    private String hobbies;
    @Column(name = "address_line1")
    private String addressLine1;
    @Column(name = "address_line2")
    private String addressLine2;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "pan_number")
    private String panNumber;
    @Lob
    @Column(name="avatar")
    private byte[] avatar;
    @OneToMany(targetEntity = FamilyDetailsList.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="emp_id",referencedColumnName = "id")
    private List<FamilyDetailsList> familyDetailsList;

    public void add(FamilyDetailsList familyDetailsList){
        if (familyDetailsList != null) {
            if(this.familyDetailsList ==null){
                this.familyDetailsList = new ArrayList<>();
            }
            this.familyDetailsList.add(familyDetailsList);
        }
    }

}
