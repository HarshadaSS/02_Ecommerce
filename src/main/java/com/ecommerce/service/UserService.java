package com.ecommerce.service;

import com.ecommerce.entity.User;
import com.ecommerce.exception.EntityNotFound;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){ //generally we give name as index for fetch all
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(getRoles(user))
                .build();
    }
    private String[] getRoles(User user) {
        if (user.getRole() == null) {
            return new String[]{"user"};
        }
        return user.getRole().split(",");
    }
    public String deleteById(Long id){
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            userRepository.deleteById(id);
            return ("user deleted");
        }
        throw new EntityNotFound("User not present for deletion");
    }

    public User updateUser(User user, Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User updateUser = userOptional.get();
            updateUser.setUsername(user.getUsername());
            updateUser.setEmail(user.getEmail());
            updateUser.setPassword(user.getPassword());
            updateUser.setRole(user.getRole());
            return userRepository.save(updateUser);
        }
       throw new EntityNotFound("User not available for update");
    }

    public void printAllUsers() {
      userRepository.findAll().stream().forEach(user -> {
          System.out.println(user);
      });
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }
}
