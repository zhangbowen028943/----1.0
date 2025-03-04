package com.example.demo.service.impl;

import com.example.demo.service.PermissionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        // 实现动态权限校验逻辑
        return true;
    }
}