package br.com.application.provider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.application.model.user.User;

@Repository
public interface UserProvider extends JpaRepository<User, Integer> {

	@SuppressWarnings("unchecked")
	User save(User user);

	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.refreshToken = ?1  where u.id = ?2")
	void updateRefreshToken(String refreshToken, Integer id);

	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.password = ?1 where u.id = ?2")
	void updatePassword(String newPassword, Integer id);

	User findByEmail(String email);

	User findById(Integer userId);

	List<User> findByCompanyId(Integer companyId);

	User findOneByEmailAndCompanyId(String email, Integer companyId);

	@Query("From User where refreshToken = ?1")
	User findByRefreshToken(String refreshToken);

	User findByEmailAndPassword(String email, String password);





}
