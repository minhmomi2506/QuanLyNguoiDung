package com.example.QuanLyDanhSachNguoiDung.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.QuanLyDanhSachNguoiDung.entity.CreateUserInput;
import com.example.QuanLyDanhSachNguoiDung.entity.EditUserHistory;
import com.example.QuanLyDanhSachNguoiDung.entity.Role;
import com.example.QuanLyDanhSachNguoiDung.entity.Unit;
import com.example.QuanLyDanhSachNguoiDung.entity.User;
import com.example.QuanLyDanhSachNguoiDung.repo.EditUserHistoryRepo;
import com.example.QuanLyDanhSachNguoiDung.repo.RoleRepo;
import com.example.QuanLyDanhSachNguoiDung.repo.UnitRepo;
import com.example.QuanLyDanhSachNguoiDung.repo.UserRepo;

/**
 * @date 2022-01-06 - CREATE NEW
 *
 * @author MinhHL
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UnitRepo unitRepo;

    @Autowired
    private EditUserHistoryRepo editUserHistoryRepo;
    // @Autowired
    // private RedisTemplate<String, User> redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Khong tim thay");
        } else {
            return new MyUserDetail(user);
        }
    }

    public User createUser(CreateUserInput input) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
//        Role role = roleRepo.findRoleByRoleName("ROLE_USER");
//        input.getRoles().add(role);
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String encodedPassword = encode.encode(input.getPassword());
        input.setPassword(encodedPassword);
        return userRepo.saveAndFlush(new User(null, input.getUsername(), input.getPassword(), input.getFullName(),
        input.getDescription(), date, input.getAddress(), null, null, null));
    }

    /* REGISTER */
    public String register(User user) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        user.setCreateDate(date);
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String encodedPassword = encode.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Role role = roleRepo.findRoleByRoleName("ROLE_USER");
        user.getRoles().add(role);
        // redisTemplate.opsForHash().put("User", user.getId(), user);
        userRepo.save(user);
        return "your acc has been successfully registered!";
    }

    /* GET ALL USERS */
    public List<User> getAll() {
        return userRepo.findAll();
    }

    /* GET ALL USERS BY STR */
    public List<User> getAllUsersByString(String str) {
        List<User> users = userRepo.findAll();
        List<User> users1 = new ArrayList<User>();
        for (User user : users) {
            if (user.getFullName().toLowerCase().contains(str.toLowerCase())
                            || user.getDescription().toLowerCase().contains(str.toLowerCase())
                            || user.getAddress().toLowerCase().contains(str.toLowerCase())) {
                users1.add(user);
            }
        }
        return users1;
    }

    /* FIND USER BY ID */
    public User findUserById(Long id) {
        return userRepo.findUserById(id);
    }

    /* EDIT USER */
    public String editUserInformation(Long roleId, User user, User user1) {
        User user2 = userRepo.findUserById(roleId);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        user2.setFullName(user.getFullName());
        user2.setAddress(user.getAddress());
        user2.setDescription(user.getDescription());
        user2.setDateOfBirth(user.getDateOfBirth());
        user2.setUnit(user.getUnit());
        userRepo.save(user2);
        EditUserHistory editUserHistory = new EditUserHistory();
        editUserHistory.setUser(user2);
        editUserHistory.setUpdateDate(date);
        editUserHistory.setUserFullNameEdit(user2.getFullName());
        editUserHistory.setUserAddressEdit(user2.getAddress());
        editUserHistory.setUserDescriptionEdit(user2.getDescription());
        editUserHistory.setUserDateOfBirthEdit(user2.getDateOfBirth());
        editUserHistory.setUpdateUnitName(user2.getUnit().getUnitName());
        editUserHistory.setUpdateUserName(user1.getFullName());
        editUserHistoryRepo.save(editUserHistory);
        return "Edit user successfully!";
    }

    /* DELETE USER */
    public List<User> deleteUser(Long id) {
        userRepo.deleteById(id);
        return userRepo.findAll();
    }

    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }

    /* GET ALL UNITS EXCEPT THE ONE BELONGS TO THE USER BEING EDITED */
    public List<Unit> getAllExcept1(Long id) {
        User user = userRepo.findUserById(id);
        List<Unit> units = unitRepo.findAll();
        units.remove(user.getUnit());
        return units;
    }
}
