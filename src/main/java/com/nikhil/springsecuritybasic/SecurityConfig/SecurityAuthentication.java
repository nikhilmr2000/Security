package com.nikhil.springsecuritybasic.SecurityConfig;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.nikhil.springsecuritybasic.Entitu.Customer;
import com.nikhil.springsecuritybasic.Repository.CustomerRepository;

@Component
public class SecurityAuthentication implements AuthenticationProvider{
	
	@Autowired
	public CustomerRepository repo;
	
	@Autowired
	public PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username=authentication.getName();
		String password=authentication.getCredentials().toString();
		List<Customer> customer=repo.findByEmail(username);

		if(customer.size()>0) {
			if(passwordEncoder.matches(password,customer.get(0).getPwd())) {
				List<GrantedAuthority> authorities=new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
				return new UsernamePasswordAuthenticationToken(username,password,authorities);
			}
			else {
				throw new BadCredentialsException("Invalid Password");
			}
		}
		else {
			throw new BadCredentialsException("No User");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
