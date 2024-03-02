package br.com.tcc.financas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@GetMapping("/")
	public String teste(Model model) {
		return "login";
	}

}
