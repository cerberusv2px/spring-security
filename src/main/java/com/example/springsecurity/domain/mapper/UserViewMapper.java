package com.example.springsecurity.domain.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.springsecurity.domain.dto.UserView;
import com.example.springsecurity.domain.model.User;
import com.example.springsecurity.domain.repository.UserRepo;

@Mapper(componentModel = "spring")
public interface UserViewMapper {

    public UserView toUserView(User user);

    public User toUser(UserView userView);

}
