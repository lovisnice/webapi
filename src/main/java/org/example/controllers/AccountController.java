package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.account.AuthResponseDto;
import org.example.dto.account.LoginDto;
import org.example.dto.account.RegisterDto;
import org.example.dto.account.UserDto;
import org.example.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService service;

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto dto) {
        try {
            var auth = service.login(dto);
            return ResponseEntity.ok(auth);
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterDto dto) {
        try {
            if(!dto.getPassword().equals(dto.getConfirmPassword()))
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            var auth = service.register(dto);

            return ResponseEntity.ok(auth);

        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getusers")
    public ResponseEntity<List<UserDto>> getUsersWithRoles() {
        List<UserDto> usersWithRoles = service.getUsersWithRole();
        return new ResponseEntity<>(usersWithRoles, HttpStatus.OK);
    }

}