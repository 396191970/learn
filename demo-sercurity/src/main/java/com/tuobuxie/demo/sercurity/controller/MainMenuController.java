package com.tuobuxie.demo.sercurity.controller;

import com.tuobuxie.demo.sercurity.entity.Users;
import com.tuobuxie.demo.sercurity.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
@Controller

public class MainMenuController {

	@Autowired
	UsersRepository usersRepository;
	
	@RequestMapping("/main_menu")
	public String mainMenu(HttpServletRequest request, Principal principal, ModelAndView model) throws Exception  {
		String loginId = principal.getName();
		Users authorities  = usersRepository.findByUserId(loginId);
		System.out.println("authorities:"+authorities.toString());
		String auth =authorities.getAuthorityKind();
		System.out.println("auth:"+auth);

		request.getSession().setAttribute("authorityKindMap", auth);
    	return "main_menu";
	}
}	
