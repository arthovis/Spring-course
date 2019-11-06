package com.sda.spring.data.jpa.mapper;

import com.sda.spring.data.jpa.dto.UserDTO;
import com.sda.spring.data.jpa.validation.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static List<User> toEntity(List<UserDTO> dtos) {
        return dtos.stream().map(userDto -> toEntity(userDto)).collect(Collectors.toList());
    }

    public static List<UserDTO> toDto(List<User> users) {
        return users.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }

    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        return user;
    }

    public static UserDTO toDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());

        return dto;
    }
}
