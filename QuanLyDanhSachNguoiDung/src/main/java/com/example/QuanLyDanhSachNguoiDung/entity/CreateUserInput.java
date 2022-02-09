package com.example.QuanLyDanhSachNguoiDung.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * 09/02/2022 - MinhHL: Create new
 * 
 * @author MinhHL
 */
@Data
@AllArgsConstructor
public class CreateUserInput {
    private String username;
    private String password;
    private String fullName;
    private String description;
    private Date createDate;
    private String address;
}
