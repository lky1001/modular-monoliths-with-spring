package com.tistory.lky1001.configuration.security;

import com.tistory.lky1001.user.application.authorization.GetUserAuthenticationQuery;
import com.tistory.lky1001.user.application.contracts.IUserModule;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    private IUserModule userModule;

    public UserDetailsServiceImpl(IUserModule userModule) {
        this.userModule = userModule;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        var getUserAuthenticationResult = userModule.executeQuery(new GetUserAuthenticationQuery(username));

        if (!getUserAuthenticationResult.isAuthenticated()) {
            throw new UsernameNotFoundException("User not found");
        }

        var userAuthentication = getUserAuthenticationResult.getUserAuthentication();

        return new CustomUserDetails(userAuthentication.getUserId(), userAuthentication.getEmail(), userAuthentication.getPassword(),
                userAuthentication.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
    }
}
