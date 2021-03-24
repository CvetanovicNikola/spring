package com.example.demo.auth;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.security.ApplicationUserRole;
import com.example.demo.student.Student;
@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
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
							"mica",
							passwordEncoder.encode("password"),
							ApplicationUserRole.STUDENT.getGrantedAuthorities(),
							true,
							true,
							true,
							true),
					new ApplicationUser(
							"cale",
							passwordEncoder.encode("password"),
							ApplicationUserRole.ADMIN.getGrantedAuthorities(),
							true,
							true,
							true,
							true),
					new ApplicationUser(
							"bransa",
							passwordEncoder.encode("password"),
							ApplicationUserRole.ADMINTRAINEE.getGrantedAuthorities(),
							true,
							true,
							true,
							true)
				);
		return applicationUsers;
	}
}
