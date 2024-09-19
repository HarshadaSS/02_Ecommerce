package com.ecommerce.controller;

import com.ecommerce.bean.GlobalResponseHandler;
import com.ecommerce.entity.User;
import com.ecommerce.service.UserService;
import com.ecommerce.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/users")
    public ResponseEntity<Object> fetchAllUsers(){
        return GlobalResponseHandler.createResponse("Found Users!",userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@RequestBody User user){
        return GlobalResponseHandler.createResponse("User created!",userService.create(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
        return GlobalResponseHandler.createResponse("User deleted!",userService.deleteById(id), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        return GlobalResponseHandler.createResponse("Found User!",userService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody User user){
        return GlobalResponseHandler.createResponse("Updated User!",userService.updateUser(user,id), HttpStatus.OK);
    }

    @PostMapping("/login")
    public String login(@RequestBody User authRequest)
    {
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
        );

        if(authentication.isAuthenticated())
        {
            return jwtService.generateToken(authRequest.getUsername());
        }
        else
        {
            return "Something wrong";
        }
    }

}
