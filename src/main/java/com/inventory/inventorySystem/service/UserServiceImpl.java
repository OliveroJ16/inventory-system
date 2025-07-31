package com.inventory.inventorySystem.service;

import com.inventory.inventorySystem.dto.request.RegisterRequest;
import com.inventory.inventorySystem.dto.response.UserResponse;
import com.inventory.inventorySystem.mapper.interfaces.UserMapper;
import com.inventory.inventorySystem.model.User;
import com.inventory.inventorySystem.repository.UserRepository;
import com.inventory.inventorySystem.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse saveUser(RegisterRequest registerRequest){
        User user = userMapper.toEntity(registerRequest);

        String encodedPassword = passwordEncoder.encode(registerRequest.password());
        user.setPassword(encodedPassword);

        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }


}
