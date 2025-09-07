package br.com.petpoints.back.features.user.controller;

import br.com.petpoints.back.features.user.forms.LoginForm;
import br.com.petpoints.back.features.user.forms.RegisterForm;
import br.com.petpoints.back.features.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm payload) {
        return ResponseEntity.ok(userService.loginUser(payload));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterForm payload) {
        return ResponseEntity.ok(userService.registerUser(payload));
    }
}
