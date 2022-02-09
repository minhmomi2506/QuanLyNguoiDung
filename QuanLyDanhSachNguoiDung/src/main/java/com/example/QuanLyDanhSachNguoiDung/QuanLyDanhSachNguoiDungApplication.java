package com.example.QuanLyDanhSachNguoiDung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @date 2022-01-06 - CREATE NEW
 *
 * @author MinhHL
 */
@SpringBootApplication
@CrossOrigin(origins = "*")
@EnableCaching //Annotation này sẽ cho phép thực hiện cache ở những method được đánh dấu @Cachable
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class QuanLyDanhSachNguoiDungApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuanLyDanhSachNguoiDungApplication.class, args);
	}

}
