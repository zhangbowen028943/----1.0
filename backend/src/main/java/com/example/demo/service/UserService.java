package com.example.demo.service;

import com.example.demo.exception.UsernameExistsException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void register(User user) {
        if (userMapper.selectByUsername(user.getUsername()) != null) {
            throw new UsernameExistsException("用户名已存在");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(true);
        userMapper.insert(user);
    }

    public String login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        if (!user.getStatus()) {
            throw new RuntimeException("用户已被禁用");
        }
        return jwtUtil.generateToken(user);
    }
}