package br.com.application.provider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.application.enums.Application;
import br.com.application.enums.Platform;
import br.com.application.model.AppVersion;

@Repository
public interface AppVersionProvider extends JpaRepository<AppVersion, Integer> {

	AppVersion findFirst1ByPlatformAndApplicationOrderByVersionDesc(Platform platform, Application application);

	List<AppVersion> findAllByOrderByVersionDesc();

	AppVersion findById(Integer id);

}
