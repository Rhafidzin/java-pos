package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.ResponseRequest;
import com.example.demo.entity.UserAdmin;
import com.example.demo.repository.UserAdminRepo;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;


@Service
public class AuthService {
    @Autowired
    private UserAdminRepo userAdminRepo;
    @Autowired
    private JWTUtilsService jwtUtilsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseRequest signup(AuthRequest authRequest) {
        try{
            UserAdmin userAdmin = new UserAdmin();
            userAdmin.setEmail(authRequest.getEmail());
            userAdmin.setPassword(passwordEncoder.encode(authRequest.getPassword()));
            userAdmin.setRole(authRequest.getRole());
            userAdmin.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            userAdmin.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            UserAdmin result = userAdminRepo.save(userAdmin);
            if(result.getId() == null){
                throw new Exception();
            }
            return new ResponseRequest(200, "Akun user berhasil dibuat", result);
        } catch (Exception e){
            return  new ResponseRequest(500, e.getMessage());
        }
    }

    public ResponseRequest signIn(AuthRequest authRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            var user = userAdminRepo.findByEmail(authRequest.getEmail()).orElseThrow();
            System.out.println("User is: " + user);
            var jwt = jwtUtilsService.generateToken(user);
            var refreshToken = jwtUtilsService.generateRefreshToken(new HashMap<>(), user);
            AuthRequest response = new AuthRequest();
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24 Jam");
            return new ResponseRequest(200, "Berhasil login", response);

        } catch (Exception e){
            return new ResponseRequest(500, e.getMessage());
        }
    }

    public ResponseRequest refreshToken(AuthRequest authRequest){
        String userEmail = jwtUtilsService.extractUserName(authRequest.getToken());
        UserAdmin user = userAdminRepo.findByEmail(userEmail).orElseThrow();
        if(jwtUtilsService.isTokenValid(authRequest.getToken(), user)){
            var jwt = jwtUtilsService.generateToken(user);
            authRequest.setToken(jwt);
            authRequest.setRefreshToken(authRequest.getToken());
            authRequest.setExpirationTime("24 Jam");
            return new ResponseRequest(200, "Berhasil refresh token");
        }
        return new ResponseRequest(500, "Gagal refresh token");
    }
}
