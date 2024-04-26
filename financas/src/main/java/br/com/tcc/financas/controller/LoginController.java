/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Revisão : 06/03/2024
 * Classe - Controle da tela de login
 * */


package br.com.tcc.financas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {
	
	/*
	* Carrega pagina de login
	 * */
	@GetMapping
	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	/*
	* Carrega pagina de login
	 * */
	@GetMapping
	@RequestMapping("/")
	public String login2(Model model) {
		return "login";
	}

}
