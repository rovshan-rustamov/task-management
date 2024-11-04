package com.example.taskmanagement.security;

import com.example.taskmanagement.model.User;
import com.example.taskmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> found = repository.findByUsername(username);
        if (found.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Username %s not found", username));
        }
        return new UserDetailsImpl(found.get());
    }

}
