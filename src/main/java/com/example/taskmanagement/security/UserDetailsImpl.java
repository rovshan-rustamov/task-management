package com.example.taskmanagement.security;


import com.example.taskmanagement.model.GrantedUserAuthority;
import com.example.taskmanagement.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    public User user;
    private Collection<GrantedAuthority> authorities = new ArrayList<>();

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedUserAuthority> authorities1 = user.getAuthorities();
        authorities1.forEach(authority1 -> {
                authorities.add(new SimpleGrantedAuthority(authority1.getUserAuthority().name()));
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
}
