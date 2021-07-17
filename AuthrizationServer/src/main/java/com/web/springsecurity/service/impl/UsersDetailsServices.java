package com.web.springsecurity.service.impl;

import com.web.springsecurity.model.Role;
import com.web.springsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsersDetailsServices implements UserDetailsService {

    private final UserServiceImpl userService;

    @Autowired
    public UsersDetailsServices(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            User user = userService.findUserByUsername(username);
            if (user.getId() != null) {
                List<GrantedAuthority> authorities = getUserAuthority(user.getRole());
                return buildUserForAuthentication(user, authorities);
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRole) {
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (Role role: userRole) {
            System.out.println(role.getRole());
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }


}
