package com.nikhil.springsecuritybasic.SecurityConfig;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nikhil.springsecuritybasic.Entitu.Customer;
import com.nikhil.springsecuritybasic.Repository.CustomerRepository;

/*@Service
public class SecurityService implements UserDetailsService{
	
	@Autowired
	public CustomerRepository repo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		String mail,password=null;
		List<Customer> customer =repo.findByEmail(username);
		List<GrantedAuthority> authorities=null;
		if(customer.size()==0) {
			throw new UsernameNotFoundException("User not found");
		}
		else {
			mail=customer.get(0).getEmail();
			password=customer.get(0).getPwd();
			authorities=new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
		}
		return new User(mail,password,authorities);
	}
	
}*/
