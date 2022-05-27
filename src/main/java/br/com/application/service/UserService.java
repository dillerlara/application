package br.com.application.service;

import java.util.List;

import br.com.application.dto.AuthenticationDTO;
import br.com.application.model.user.User;

public interface UserService {
	
	User save(User user);
	
	List<User> findAll();

	List<User> findByCompany(Integer companyId);
		
	User findById(Integer id);
	
	User findByEmail(String email);

	User findByEmailAndCompany(String email, Integer companyId);
	
	void delete(Integer id);

	User newPassword(String email);

	void verifyPassword(String email, String password);

	User findByEmailAndPassword(AuthenticationDTO login);

	User saveUserCustomer(User user, Boolean change);

	
}

