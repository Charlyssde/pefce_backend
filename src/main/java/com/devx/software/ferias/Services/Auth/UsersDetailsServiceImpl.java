package com.devx.software.ferias.Services.Auth;


import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Repositories.Users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UsersDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersDetailsServiceImpl(
            UsersRepository usersRepository
    ) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = this.usersRepository.userAuthentication(email);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        return new User(userEntity.getEmail(), userEntity.getPassword(), grantedAuthorities);
    }
}
