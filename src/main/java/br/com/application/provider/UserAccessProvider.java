package br.com.application.provider;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.application.model.user.UserAccess;

@Repository
public interface UserAccessProvider extends JpaRepository<UserAccess, Integer> {

	@SuppressWarnings("unchecked")
	UserAccess save(UserAccess userAccess);
	
	Optional<UserAccess> findTopByUserIdOrderByIdDesc(Integer userId);

}
