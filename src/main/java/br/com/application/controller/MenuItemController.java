package br.com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import br.com.application.service.MenuItemService;
import br.com.application.utils.JsonStruct;



@Controller
@RequestMapping(path = "/secure/menu")
public class MenuItemController {

	@Autowired
	private MenuItemService menuItemService;


	@GetMapping("/structure/")
	public ResponseEntity<JsonStruct> findAllbyRole(@RequestHeader("OriginEasy") String originEasy,
			@RequestHeader("UserId") Integer userId) {
		JsonStruct struct = new JsonStruct();
		struct.setStatusToSuccess();
		struct.put("menuItens", menuItemService.listMenuItens(userId, 0));
		return new ResponseEntity<JsonStruct>(struct, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/router-link-access", method = RequestMethod.GET)
	public ResponseEntity<JsonStruct> findRouterLinkAccessByUser(
			@RequestHeader("UserId") Integer userId,
			@RequestHeader("EmpresaId") Integer companyId)
	{
		JsonStruct struct = new JsonStruct();
		struct.setStatusToSuccess();
		struct.put("menuItens", menuItemService.findRouterLinkAccessByUser(companyId, userId));
		return new ResponseEntity<JsonStruct>(struct, HttpStatus.OK);
	}

	
}
