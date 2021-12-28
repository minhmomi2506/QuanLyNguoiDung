package com.example.QuanLyDanhSachNguoiDung;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.QuanLyDanhSachNguoiDung.entity.Unit;
import com.example.QuanLyDanhSachNguoiDung.repo.UnitRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class AddUnitTest {
	@Autowired
	private UnitRepo unitRepo;
	
	@Test
	public void testCreateUser() {
		Long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		Unit unit = new Unit();
		unit.setId(2L);
		unit.setUnitId("UNIT"+unit.getId());
		unit.setUnitName("Nhân Sự");
		unit.setDescription("Phòng nhân sự");
		unit.setCreateDate(date);
		unitRepo.save(unit);
		assertThat(unit.getId() > 0);
	}
}
