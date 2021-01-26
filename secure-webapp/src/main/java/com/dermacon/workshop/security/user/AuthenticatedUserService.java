package com.dermacon.workshop.security.user;

import com.dermacon.workshop.data.AppUserRepository;
import com.dermacon.workshop.data.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser user = appUserRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("The user " + username + " does not exist.");
        }
        return new AuthenticatedUser(user);
    }
}
