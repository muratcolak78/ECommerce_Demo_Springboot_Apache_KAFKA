package com.ecommerce.user.service;


import com.ecommerce.user.dto.AuthResponse;
import com.ecommerce.user.dto.LoginRequest;
import com.ecommerce.user.dto.RegisterRequest;
import com.ecommerce.user.model.Role;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;
import com.ecommerce.user.service.impl.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        /// Email control
        if(registerRequest.getEmail()==null || registerRequest.getEmail().isBlank()){
            throw new IllegalArgumentException("Email is required");
        }
        /// Password control
        if (registerRequest.getPassword() == null || registerRequest.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }
        /// Is there same Email?
        if (repository.existsByEmail(registerRequest.getEmail().trim().toLowerCase())) {
            throw new IllegalArgumentException("Email already exists");
        }
        /// create the user to be saved
        User user =new User();
        user.setEmail(registerRequest.getEmail().trim().toLowerCase());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setRole(Role.USER);
        user.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEnabled(true);

        ///Get the saved user and first create a jwttoken
        User savedUser=repository.save(user);
        String token= jwtService.generateAccessToken(savedUser.getId(), savedUser.getEmail(), savedUser.getRole().name());

        /// then return the authorization object with the generated token
        return new AuthResponse(savedUser.getId(), savedUser.getEmail(), savedUser.getRole().name(),token);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        ///Email control
        if (loginRequest.getEmail() == null || loginRequest.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        /// Password control
        if (loginRequest.getPassword() == null || loginRequest.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }
        /// find user by email, if the user's email address is not found, throw an exception.
        User user = repository.findByEmail(loginRequest.getEmail().trim().toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!user.isEnabled()) {
            throw new IllegalArgumentException("User is disabled");
        }
        /// After retrieving from the user database, compare the password in the request with the registered password.
        boolean ok = passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash());
        if (!ok) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        /// If the password is correct, then generate a token and create an authorization object with this token and user information.
        String token = jwtService.generateAccessToken(user.getId(), user.getEmail(), user.getRole().name());
        return new AuthResponse(user.getId(), user.getEmail(), user.getRole().name(), token);

    }
}
