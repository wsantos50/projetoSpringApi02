package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.dtos.UsuarioCadastroDTO;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.UsuarioRepository;
import br.com.cotiinformatica.validations.UsuarioCadastroValidation;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	// declarando o ENDPOINT dos serviços de usuario
	private static final String ENDPOINT = "/api/usuarios";

	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<String>> cadastrarUsuario(@RequestBody UsuarioCadastroDTO dto) {
		
		List<String> mensagens = new ArrayList<String>();
		
		try {
			
			//aplicando as validações..
			mensagens = UsuarioCadastroValidation.validate(dto);
			
			//verificando se não ocorreram erros de validação..
			if(mensagens.size() == 0) {
				
				//verificar se o email informado já está cadastrado..
				if(usuarioRepository.findByEmail(dto.getEmail()) != null) {					
					throw new Exception("O email informado já encontra-se cadastrado.");
				}
				
				//realizar o cadastro do usuário
				Usuario usuario = new Usuario();
				
				usuario.setNome(dto.getNome());
				usuario.setEmail(dto.getEmail());
				usuario.setSenha(dto.getSenha());
				
				usuarioRepository.save(usuario);
				
				mensagens.add("Usuário cadastrado com sucesso.");
				
				//retornar status de sucesso 201 (CREATED)
				return ResponseEntity
						.status(HttpStatus.CREATED)
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
			
			mensagens.add("Ocorreu um erro: " + e.getMessage());
			
			//retornar status de erro 500 (INTERNAL SERVER ERROR)
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(mensagens);			
		}
	}

}



