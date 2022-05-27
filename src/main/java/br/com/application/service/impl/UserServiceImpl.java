package br.com.application.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.application.dto.AuthenticationDTO;
import br.com.application.model.user.User;
import br.com.application.provider.UserProvider;
import br.com.application.service.UserService;
import br.com.application.utils.AplicationException;
import br.com.application.utils.JsonStatusEnum;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserProvider userProvider;

	@PersistenceContext
	private EntityManager manager;

	private void verifyEmail(String email, Integer userFormId) {
		User u = userProvider.findByEmail(email);
		if (u != null && (userFormId == null || u.getId().compareTo(userFormId) != 0)) {
			throw new AplicationException(JsonStatusEnum.WARNING.getStatus(), "SUMMARY.WARNING",
					"O email " + email + " já existe.");
		}
		return;
	}

	@Transactional
	public User save(User userForm) {
		verifyEmail(userForm.getEmail(), userForm.getId());
		User user = userProvider.save(userForm);
		return user;
	}

	@Override
	public List<User> findAll() {
		return userProvider.findAll();
	}

	public User findById(Integer userId) {
		return userProvider.findById(userId);
	}

	@Override
	public List<User> findByCompany(Integer companyId) {
		return userProvider.findByCompanyId(companyId);
	}

	@Override
 	public User findByEmail(String email) {
 		return userProvider.findByEmail(email);
 	}

	@Override
	public User findByEmailAndCompany(String email, Integer companyId) {
		return userProvider.findOneByEmailAndCompanyId(email,companyId);
	}

	@Override
	public void delete(Integer id) {
		try {
			userProvider.delete(id);
		
		} catch (Exception e) {
			throw new AplicationException(JsonStatusEnum.ERROR.getStatus(), "SUMMARY.ERROR", e.getMessage());
		}
	}

	@Override
	public User newPassword(String email) {
		User user = findByEmail(email);

		String newPassword = Integer.toString((int) Math.floor(100000 + Math.random() * 9000));
		user.setPassword(DigestUtils.sha1Hex(newPassword));

		User u = save(user);
		u.setPassword(newPassword);
		return u;
	}

	@Override
	public void verifyPassword(String email, String password) {
		User user = userProvider.findByEmail(email);
		if (!user.getPassword().equals(DigestUtils.sha1Hex(password))) {
			throw new AplicationException(JsonStatusEnum.ERROR.getStatus(), "SUMMARY.WARNING", "Senha inválida");
		}
	}

	@Override
	public User findByEmailAndPassword(AuthenticationDTO login) {
		String email = login.getEmail();
		String password = DigestUtils.sha1Hex(login.getPassword());
		return userProvider.findByEmailAndPassword(email, password);
	}


	@Transactional
	@Override
	public User saveUserCustomer(User user , Boolean change) {
		verifyEmail(user.getEmail(), user.getId());
		String token = user.getToken();
		if (change) {
    	user.setPassword(DigestUtils.sha1Hex(user.getPassword()));}
    	User userSaved = userProvider.save(user);
    	userSaved.setToken(token);
    	return userSaved;
	}


	


	

}
