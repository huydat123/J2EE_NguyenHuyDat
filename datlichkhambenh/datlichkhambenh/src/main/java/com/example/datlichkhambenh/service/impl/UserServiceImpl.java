package com.example.datlichkhambenh.service.impl;

import com.example.datlichkhambenh.dto.RegisterRequest;
import com.example.datlichkhambenh.entity.Role;
import com.example.datlichkhambenh.entity.User;
import com.example.datlichkhambenh.repository.RoleRepository;
import com.example.datlichkhambenh.repository.UserRepository;
import com.example.datlichkhambenh.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }

        Role patientRole = roleRepository.findByName("PATIENT")
                .orElseThrow(() -> new RuntimeException("Không tìm thấy role PATIENT"));

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword()); // tạm thời dùng plain text
        user.setGender(request.getGender());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setAddress(request.getAddress());
        user.setRole(patientRole);
        user.setStatus("ACTIVE");

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}