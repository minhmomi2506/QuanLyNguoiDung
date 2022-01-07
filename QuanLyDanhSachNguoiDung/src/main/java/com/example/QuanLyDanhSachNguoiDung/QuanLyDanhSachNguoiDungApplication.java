package com.example.QuanLyDanhSachNguoiDung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @date 2022-01-06 - CREATE NEW
 *
 * @author MinhHL
 */
@SpringBootApplication
@CrossOrigin(origins = "*")
public class QuanLyDanhSachNguoiDungApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuanLyDanhSachNguoiDungApplication.class, args);
	}

}
