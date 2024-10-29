package com.example.project_01.dto;

import com.example.project_01.entity.embeded.Address;
import com.example.project_01.entity.enums.UserTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data

@NoArgsConstructor
public class UserDTO {
    public UserDTO(int id, String email, String username, String password, Address address, String nic, Date dateofBirth, String telNo, String[] type) {
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

    private int id;
    private String email;
    private String username;
    private String password;
    private Address address;
    private String nic;
    private Date dateofBirth;
    private String telNo;
    private String[] type;
}
