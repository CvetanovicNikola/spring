package com.example.demo.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.security.ApplicationUserRole;
@Repository("fake2")
public class FakeApplicationUserDaoService2 implements ApplicationUserDao{
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public FakeApplicationUserDaoService2(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		return getApplicationUsers()
				.stream()
				.filter(user -> user.getUsername().equals(username))
				.findFirst();
	}
	
	private List<ApplicationUser> getApplicationUsers(){
		List<ApplicationUser> applicationUsers = List.of(
					new ApplicationUser(
							"mikica",
							passwordEncoder.encode("password"),
							ApplicationUserRole.ADMIN.getGrantedAuthorities(),
							true,
							true,
							true,
							true)
			);
		return applicationUsers;
	}

}
