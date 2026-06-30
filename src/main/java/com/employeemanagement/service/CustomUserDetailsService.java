package com.employeemanagement.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.employeemanagement.entity.AppUser;
import com.employeemanagement.repository.UserRepository;

@Service
public class CustomUserDetailsService implements  UserDetailsService {
	
	private UserRepository userRepo;
	
	public CustomUserDetailsService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	//	AppUser appUser = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

    System.out.println("Username received: " + username);

    Optional<AppUser> optionalUser = userRepo.findByUsername(username);

    System.out.println("User found: " + optionalUser.isPresent());

    if (optionalUser.isEmpty()) {
        throw new UsernameNotFoundException("User not found");
    }

    AppUser appUser = optionalUser.get();

    System.out.println("DB Username: " + appUser.getUsername());
    System.out.println("DB Password: " + appUser.getPassword());
    System.out.println("DB Role: " + appUser.getRole());

    return User.builder()
            .username(appUser.getUsername())
            .password(appUser.getPassword())
            .authorities(appUser.getRole())
            .build();
}

}
