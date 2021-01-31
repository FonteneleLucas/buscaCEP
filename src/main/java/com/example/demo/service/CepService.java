package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.model.Cep;

import reactor.core.publisher.Mono;



@Service
public class CepService {
	
	@Autowired
	private WebClient webClient;
	
	
	public Cep getEndereco(String cepNumber) {
		try {
			
			Mono<Cep> monoCep = this.webClient
					.method(HttpMethod.GET)
					.uri("/{cep}/json", cepNumber)
					.retrieve()
					.bodyToMono(Cep.class);
			
			Cep cep = monoCep.block();
			
			System.out.println(cep.getErro());
			
			return cep;
		}catch (Exception e) {
			
			System.out.println("Não encontrado!");
			
		
		}
		
		
		return null;
	}
	
	public void showCep(String cepNumber) {
		System.out.println(cepNumber);
	}
	
	public String clearCep(String cep) {
		StringBuilder sb = new StringBuilder(cep);
		sb.deleteCharAt(2);
		return sb.toString();
	}
	


	
	
}
