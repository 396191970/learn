package com.tuobuxie.demo.sercurity.service;

import com.tuobuxie.demo.sercurity.entity.Users;
import com.tuobuxie.demo.sercurity.repository.AuthoritiesRepository;
import com.tuobuxie.demo.sercurity.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Shaofeng Li
 */
@Service
@Slf4j
public class AuthUserService implements UserDetailsService{


	@Autowired
	UsersRepository usersRepository;

	@Autowired
	AuthoritiesRepository authoritiesRepository;


	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Users users =usersRepository.findByUserId(userId);
		if(users == null) {
			throw new UsernameNotFoundException("User not found for name:"+userId);
		}
		System.out.println("users:"+users.toString());

		return new AuthUser(users);
	}
	

}
