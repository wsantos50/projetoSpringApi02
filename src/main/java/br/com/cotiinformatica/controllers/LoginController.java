package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.dtos.UsuarioLoginDTO;
import br.com.cotiinformatica.validations.UsuarioLoginValidation;

@Controller
public class LoginController {

	// declarando o ENDPOINT dos serviços de usuario
	private static final String ENDPOINT = "/api/login";

	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<String>> autenticarUsuario(UsuarioLoginDTO dto) {

		List<String> mensagens = new ArrayList<String>();

		try {
			
			//aplicar as validações no DTO..
			mensagens = UsuarioLoginValidation.validate(dto);
			
			//verificando se não ocorreram erros de validação..
			if(mensagens.size() == 0) {
				
				//TODO			
				mensagens.add("Usuário autenticado com sucesso.");
				
				//retornar status de sucesso 200 (OK)
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(mensagens);				
			}
			else { //se houverem erros de validação
				
				//retornar status de erro de requisição inválida 400 (BAD REQUEST)
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(mensagens);				
			}
			
		}
		catch(Exception e) {
			
			mensagens.add("Erro: " + e.getMessage());
			
			//retornar status de erro 500 (INTERNAL SERVER ERROR)
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(mensagens);	
		}
	}
}
