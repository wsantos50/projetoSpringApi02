package br.com.cotiinformatica.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContaController {

	// declarando o ENDPOINT dos serviços de usuario
	private static final String ENDPOINT = "/api/contas";

	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<String>> cadastrarConta() {
		return null;
	}

	@RequestMapping(value = ENDPOINT, method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<List<String>> atualizarConta() {
		return null;
	}

	@RequestMapping(value = ENDPOINT, method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<List<String>> excluirConta() {
		return null;
	}

	@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<String>> consultarContas() {
		return null;
	}
}


