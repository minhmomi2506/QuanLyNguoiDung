package com.example.QuanLyDanhSachNguoiDung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class QuanLyDanhSachNguoiDungApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuanLyDanhSachNguoiDungApplication.class, args);
	}

}
