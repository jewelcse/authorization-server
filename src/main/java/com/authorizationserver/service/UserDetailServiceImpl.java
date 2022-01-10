package com.authorizationserver.service;

import com.authorizationserver.dto.RegisterDto;
import com.authorizationserver.model.AuthUserDetail;
import com.authorizationserver.model.Permission;
import com.authorizationserver.model.Role;
import com.authorizationserver.model.User;
import com.authorizationserver.repository.RoleRepository;
import com.authorizationserver.repository.UserDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService,CustomUserService {

    private final UserDetailRepository userDetailRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<User> optionalUser = userDetailRepository.findByUsername(name);

        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));

        UserDetails userDetails = new AuthUserDetail(optionalUser.get());
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;


    }

    @Override
    public User registerUser(RegisterDto registerDto) {

        //  create new user
        User newUser = new User();
        newUser.setEmail(registerDto.getEmail());
        newUser.setUsername(registerDto.getUsername());
        newUser.setAccountNonExpired(true);
        newUser.setEnabled(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        // get role
        Optional<Role> doesExistCustomerRole = roleRepository.findByName("ROLE_CUSTOMER");
        // added role to the list
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(doesExistCustomerRole.get());


        List<Permission> permissions = new ArrayList<>();
        doesExistCustomerRole.get().setPermissions(permissions);

        newUser.setRoles(userRoles);
        return userDetailRepository.save(newUser);
    }

    @Override
    public User userDetailsByUsername(String username) {
        return userDetailRepository.findByUsername(username).get();
    }
}
