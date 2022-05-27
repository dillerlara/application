package br.com.application.service;

import java.util.List;

import br.com.application.model.company.MenuItem;



public interface MenuItemService {
	
	MenuItem save(MenuItem role);
	
	List<MenuItem> findAll();
	
	MenuItem findById(Integer id);
	
	void delete(Integer id);

	List<MenuItem> findAllbyApplication(String application);

	List<String> findRouterLinkAccessByUser(Integer companyId, Integer userId);

	List<MenuItem> findAllbyApplicationAndRole(String application, Integer userId, Integer companyId);

	 List<MenuItem> listMenuItens (Integer userId, Integer parent);
	

}
