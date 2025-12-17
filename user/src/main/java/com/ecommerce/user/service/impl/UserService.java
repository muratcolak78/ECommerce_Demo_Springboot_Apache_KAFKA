package com.ecommerce.user.service.impl;


import com.ecommerce.user.dto.AuthResponse;
import com.ecommerce.user.dto.LoginRequest;
import com.ecommerce.user.dto.RegisterRequest;

public interface UserService {
  AuthResponse register(RegisterRequest registerRequest);
  AuthResponse login(LoginRequest loginRequest);
}
