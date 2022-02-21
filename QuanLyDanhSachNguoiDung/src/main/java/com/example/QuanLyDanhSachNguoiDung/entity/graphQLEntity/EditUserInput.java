package com.example.QuanLyDanhSachNguoiDung.entity.graphQLEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditUserInput {
    private Long id;
    private String fullName;
    private String description;
    private String address;
}
