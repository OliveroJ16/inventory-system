package com.inventory.inventorySystem.service;

import com.inventory.inventorySystem.dto.request.UserRequest;
import com.inventory.inventorySystem.dto.response.UserResponse;
import com.inventory.inventorySystem.mapper.UserMapper;
import com.inventory.inventorySystem.model.User;
import com.inventory.inventorySystem.repository.UserRepository;
import com.inventory.inventorySystem.service.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse registerUser(UserRequest userRequest){
        User user = userMapper.toEntity(userRequest);

        String encodedPassword = passwordEncoder.encode(userRequest.password());
        user.setPassword(encodedPassword);

        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }
}
