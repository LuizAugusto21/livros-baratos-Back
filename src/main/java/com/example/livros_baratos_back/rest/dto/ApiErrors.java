package com.example.livros_baratos_back.rest.dto;

import java.util.List;
import java.util.Arrays;


public class ApiErrors {
	private List<String> errors;
	
	public ApiErrors(String mensagemErro) {
		this.errors = (Arrays.asList(mensagemErro));
	}

	public List<String> getErros() {
		return errors;
	}

	public void setErros(List<String> errors) {
		this.errors = errors;
	}
}
