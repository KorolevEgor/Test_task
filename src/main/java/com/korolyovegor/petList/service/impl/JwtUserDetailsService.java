package com.korolyovegor.petList.service.impl;

import com.korolyovegor.petList.Security.LoginAttemptService;
import com.korolyovegor.petList.model.security.JwtUser;
import com.korolyovegor.petList.model.security.JwtUserFactory;
import com.korolyovegor.petList.model.security.User;
import com.korolyovegor.petList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private HttpServletRequest request;

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }

        User user = userService.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User with username: " + username + " not found"));
//        User user = userService.findByUsername(username).get();

        JwtUser jwtUser = JwtUserFactory.create(user);
        System.out.println(jwtUser);
        return jwtUser;
    }

    public PasswordEncoder getEncoder() {
        return userService.getEncoder();
    }
}
