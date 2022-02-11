package com.example.QuanLyDanhSachNguoiDung.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 09/02/2022 - MinhHL: Create new
 * 
 * @author MinhHL
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserInput {
    private String username;
    private String password;
    private String fullName;
    private String description;
    private String address;
    private String dateOfBirth;
    private Long unitId;
}
