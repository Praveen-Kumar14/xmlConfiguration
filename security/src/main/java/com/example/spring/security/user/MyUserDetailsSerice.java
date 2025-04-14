// package com.example.spring.security.user;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// @Service
// public class MyUserDetailsSerice implements UserDetailsService{
	
// 	@Autowired
// 	private userRepo repo;
	

// 	@Override
// 	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
// 		  User user = repo.findByUsername(username);
// 	        if (user == null) {
// 	            System.out.println("User Not Found");
// 	            throw new UsernameNotFoundException("user not found");
// 	        }
	        
// 	        return new UserPrincipal(user);
// 	    }
// 	}


