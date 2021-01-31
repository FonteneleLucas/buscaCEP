package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Cep;
import com.example.demo.service.CepService;

@Controller
@RequestMapping("/")
public class CepController {

	@Autowired
	private CepService cepService;

	@GetMapping()
	public String greetingForm(Model model) {
		model.addAttribute("cep", new Cep());
		return "index";
	}

	@PostMapping()
	public String searchCep(@ModelAttribute Cep cepNumber, Model model) {
		
		if(cepNumber.getCep().indexOf("_") >= 0 || cepNumber.getCep().indexOf(".") < 0) {
			System.out.println("Faltante");
			return "redirect:/?error";
		}else {
			Cep cep = cepService.getEndereco(cepService.clearCep(cepNumber.getCep()));
			model.addAttribute("cep", cep);
			
			if(cep.getErro() == "true")
				return "redirect:/?error";	
			else
				return "result";
		}
	}
}
