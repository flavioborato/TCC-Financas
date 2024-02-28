package br.com.tcc.financas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.tcc.financas.model.GastosMensais;
import br.com.tcc.financas.repository.GastosMensaisRepository;

@Controller
@RequestMapping("/gastosmensais")
public class GastosMensaisController {
	
	@Autowired
	private GastosMensaisRepository gastosmensaisrepository;
	
	@GetMapping
	public String gastosmensaisclass(Model model) {
		
		List<GastosMensais> gastosmesal = gastosmensaisrepository.findAll();
		model.addAttribute("gastosmensais", gastosmesal);
		return "gastosmensais";
	}
	
}
