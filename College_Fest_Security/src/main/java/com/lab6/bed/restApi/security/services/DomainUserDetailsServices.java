package com.lab6.bed.restApi.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lab6.bed.restApi.security.model.DomainUserDetails;
import com.lab6.bed.restApi.security.model.User;
import com.lab6.bed.restApi.security.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DomainUserDetailsServices implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return this.userRepository.findByUserName(username).map(DomainUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("bad credentials"));

	}

}
