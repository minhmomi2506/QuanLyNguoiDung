package com.example.QuanLyDanhSachNguoiDung.graphql;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.QuanLyDanhSachNguoiDung.entity.CreateUserInput;
import com.example.QuanLyDanhSachNguoiDung.entity.User;
import com.example.QuanLyDanhSachNguoiDung.service.UserService;

@Component
public class MutationResolver implements GraphQLMutationResolver {
    @Autowired
    private UserService userService;

    public User createUser(CreateUserInput input) throws ParseException {
        return userService.createUser(input);
    }

    @Transactional
    public int deleteUserById(Long id) {
        return userService.deleteUserById(id);
    }
}
