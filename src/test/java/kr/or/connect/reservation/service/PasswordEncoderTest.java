package kr.or.connect.reservation.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.connect.reservation.ApplicationConfig;
import kr.or.connect.reservation.SecurityConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class, SecurityConfig.class })
public class PasswordEncoderTest {
	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	public void passwordEncode() throws Exception {
		System.out.println(passwordEncoder.encode("1234"));
	}

	@Test
	public void passwordTest() throws Exception {
		String encodePasswd = "$2a$10$xgWp2kXNabPQys6CBRShwOmz7f4/u6Gxf38XJkcGe/HHJak7t.Akm";
		String password = "1234";
		boolean test = passwordEncoder.matches(password, encodePasswd);
		System.out.println(test);
	}
}