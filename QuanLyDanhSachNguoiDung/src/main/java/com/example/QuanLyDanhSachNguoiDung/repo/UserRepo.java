package com.example.QuanLyDanhSachNguoiDung.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.example.QuanLyDanhSachNguoiDung.entity.Unit;
import com.example.QuanLyDanhSachNguoiDung.entity.User;

/**
 * @date 2022-01-06 - CREATE NEW
 *
 * @author MinhHL
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findUserById(Long id);

    User findByUsernameAndPassword(String username, String password);

    List<User> findByUnit(Unit unit);

    Boolean existsByUsername(String username);

    @Modifying
    int deleteUserById(Long userId);

}
