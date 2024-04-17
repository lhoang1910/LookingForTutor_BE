package com.ltf.userservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ltf.userservice.repository.UserRepository;
import com.ltf.userservice.service.UserDetailService;

import jakarta.transaction.Transactional;

@Service
public class UserDetailServiceImpl implements UserDetailService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {	

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				// TODO Auto-generated method stub
				return userRepository.findByUsername(username)
						.orElseThrow(() -> new UsernameNotFoundException("username không tồn tại"));
			}
		};
	}

}