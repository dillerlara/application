package br.com.application.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.application.model.company.MenuItem;
import br.com.application.model.user.User;
import br.com.application.provider.UserProvider;
import br.com.application.provider.company.MenuItemProvider;
import br.com.application.service.MenuItemService;


@Service
public class MenuItemServiceImpl implements MenuItemService {

	@Autowired
	private MenuItemProvider menuItemProvider;

	@Autowired
	private UserProvider userProvider;

	@Override
	public MenuItem save(MenuItem menuItem) {
		menuItemProvider.save(menuItem);
		return null;
	}

	@Override
	public List<MenuItem> findAll() {
		List<MenuItem> menuItens = menuItemProvider.findAll();
		menuItens.sort(Comparator.comparing(MenuItem::getId));
		return menuItens;
	}

	@Override
	public MenuItem findById(Integer id) {
		return menuItemProvider.findOne(id);

	}

	@Override
	public void delete(Integer id) {
		menuItemProvider.delete(id);
	}

	@Override
	public List<MenuItem> findAllbyApplication(String application) {
		List<MenuItem> menuItens = menuItemProvider.findAllByApplication(application);
		return menuItens;
	}

	@Override
	public List<MenuItem> listMenuItens (Integer userId, Integer parent){
		User user = userProvider.findById(userId);
		List <MenuItem> listaCompleta = menuItemProvider.findAllByParentaAndPermission(parent, user.getType());
		for(MenuItem item : listaCompleta){
			item.setChildren(listMenuItens(userId, item.getId()));
		}
		return listaCompleta;
	}

	@Override
	public List<String> findRouterLinkAccessByUser(Integer companyId, Integer userId) {
		User user = userProvider.findById(userId);
		
		List<MenuItem> menus = menuItemProvider.findAllByPermission(user.getType());

		List<String> retorno =new ArrayList<>()  ;

		for(MenuItem item : menus){
			if (item.getParent()==0 && item.getId()>1) 
			{retorno.add("#"+item.getRouterLink());}
			else{
				retorno.add(item.getRouterLink());
			}
			item.setChildren(listMenuItens(userId, item.getId()));
		}

		return retorno;
	}


	@Override
	public List<MenuItem> findAllbyApplicationAndRole(String application, Integer userId, Integer companyId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
