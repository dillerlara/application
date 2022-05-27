package br.com.application.provider.company;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.application.model.company.MenuItem;

@Repository
public interface MenuItemProvider extends JpaRepository<MenuItem, Integer> {

	List<MenuItem> findAllByApplication(String application);

	MenuItem findByLabel(String label);

	// @Query("from MenuItem M where m.level=?1 and ")
	// List<MenuItem> findAllByLevelAndPermission(Integer Lvl, Integer Permission, Integer Parrent);

	@Query("from MenuItem m where m.parent=?1 and m.permission<= ?2 order by order")
	List<MenuItem> findAllByParentaAndPermission( Integer Parent, Integer permission );

	@Query("from MenuItem m where m.permission<= ?1 order by order")
	List<MenuItem> findAllByPermission(Integer permission);


	
	
	
}
