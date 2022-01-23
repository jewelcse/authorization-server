package com.authorizationserver.service;

import com.authorizationserver.dto.CustomerRegisterDto;
import com.authorizationserver.dto.DeliveryManRegisterDto;
import com.authorizationserver.dto.SellerRegisterDto;
import com.authorizationserver.exception.ApplicationException;
import com.authorizationserver.model.AuthUserDetail;
import com.authorizationserver.model.Permission;
import com.authorizationserver.model.Role;
import com.authorizationserver.model.User;
import com.authorizationserver.repository.RoleRepository;
import com.authorizationserver.repository.UserDetailRepository;
import lombok.AllArgsConstructor;
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
    public User registerUser(SellerRegisterDto registerDto) {
        Optional<User> doesExist = userDetailRepository.findByUsername(registerDto.getUsername());
        if (doesExist.isPresent()){
            throw new ApplicationException("Username/Mobile Already exist");
        }
        User newUser = new User();
        newUser.setEmail(registerDto.getEmail());
        newUser.setUsername(registerDto.getUsername());
        newUser.setAccountNonExpired(true);
        newUser.setEnabled(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Optional<Role> doesExistRole = roleRepository.findByName("ROLE_SELLER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(doesExistRole.get());
        List<Permission> permissions = new ArrayList<>();
        doesExistRole.get().setPermissions(permissions);
        newUser.setRoles(userRoles);
        return userDetailRepository.save(newUser);
    }

    @Override
    public User registerUser(CustomerRegisterDto customerDto) {
        Optional<User> doesExist = userDetailRepository.findByUsername(customerDto.getMobile());
        if (doesExist.isPresent()){
            throw new ApplicationException("Username/Mobile Already exist");
        }
        User newUser = new User();
        newUser.setEmail("test@gmail.com");
        newUser.setFirstName(customerDto.getFirstName());
        newUser.setLastName(customerDto.getLastName());
        newUser.setUsername(customerDto.getMobile());
        newUser.setAccountNonExpired(true);
        newUser.setEnabled(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        Optional<Role> doesExistCustomerRole = roleRepository.findByName("ROLE_CUSTOMER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(doesExistCustomerRole.get());
        List<Permission> permissions = new ArrayList<>();
        doesExistCustomerRole.get().setPermissions(permissions);
        newUser.setRoles(userRoles);
        return userDetailRepository.save(newUser);
    }

    @Override
    public User registerUser(DeliveryManRegisterDto deliveryManRegisterDto) {
        Optional<User> doesExist = userDetailRepository.findByUsername(deliveryManRegisterDto.getMobile());
        if (doesExist.isPresent()){
            throw new ApplicationException("Username/Mobile Already exist");
        }
        User newUser = new User();
        newUser.setEmail("test_delivery@gmail.com");
        newUser.setFirstName(deliveryManRegisterDto.getFirstName());
        newUser.setLastName(deliveryManRegisterDto.getLastName());
        newUser.setUsername(deliveryManRegisterDto.getMobile());
        newUser.setAccountNonExpired(true);
        newUser.setEnabled(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setPassword(passwordEncoder.encode(deliveryManRegisterDto.getPassword()));
        Optional<Role> doesExistCustomerRole = roleRepository.findByName("ROLE_DELIVERY");
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
