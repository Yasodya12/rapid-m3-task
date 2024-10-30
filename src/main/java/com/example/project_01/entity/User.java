package com.example.project_01.entity;

import com.example.project_01.entity.embeded.Address;

import com.example.project_01.entity.enums.UserTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class User {
    public User(int id, String email, String username, String password, Address address, String nic, Date dateofBirth, String telNo, String type) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.address = address;
        this.nic = nic;
        this.dateofBirth = dateofBirth;
        this.telNo = telNo;
        this.type = type;
    }

    @Id
    private int id;
    private String email;
    private String username;
    private String password;
    @Embedded
    private Address address;
    private String nic;
    private Date dateofBirth;
    private String telNo;


    private String type;


}
