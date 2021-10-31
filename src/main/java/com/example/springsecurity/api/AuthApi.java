package com.example.springsecurity.api;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsecurity.config.JwtTokenUtil;
import com.example.springsecurity.domain.dto.AuthRequest;
import com.example.springsecurity.domain.dto.CreateUserRequest;
import com.example.springsecurity.domain.dto.UserView;
import com.example.springsecurity.domain.mapper.UserViewMapper;
import com.example.springsecurity.domain.model.User;

@RestController
@RequestMapping(path = "api/public")
public class AuthApi {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserViewMapper userViewMapper;
    // private final UserService userService;

    public AuthApi(
        AuthenticationManager authenticationManager,
        JwtTokenUtil jwtTokenUtil,
        UserViewMapper userViewMapper
        ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userViewMapper = userViewMapper;
    }

    @PostMapping("login")
    public ResponseEntity<UserView> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            User user = (User) authenticate.getPrincipal();

            return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
                .body(userViewMapper.toUserView(user));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

//    @PostMapping("register")
//    public UserView register(@RequestBody @Valid CreateUserRequest request) {
//        return userService.create(request);
//    }
}
