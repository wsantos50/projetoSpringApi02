package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.dtos.UsuarioLoginDTO;
import br.com.cotiinformatica.repositories.UsuarioRepository;
import br.com.cotiinformatica.validations.UsuarioLoginValidation;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
public class LoginController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	// declarando o ENDPOINT dos serviços de usuario
	private static final String ENDPOINT = "/api/login";

	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<String>> autenticarUsuario(@RequestBody UsuarioLoginDTO dto) {

		List<String> mensagens = new ArrayList<String>();

		try {
			
			//aplicar as validações no DTO..
			mensagens = UsuarioLoginValidation.validate(dto);
			
			//verificando se não ocorreram erros de validação..
			if(mensagens.size() == 0) {
				
				//verificar se o usuario existe no banco de dados (email e senha)
				if(usuarioRepository.findByEmailSenha(dto.getEmail(), dto.getSenha()) != null) {
					
					//gerando o TOKEN de acesso do usuário
					mensagens.add(getJWTToken(dto.getEmail()));
					
					//retornar status de sucesso 200 (OK)
					return ResponseEntity
							.status(HttpStatus.OK)
							.body(mensagens);		
				}
				else {
					throw new Exception("Acesso negado. Usuário inválido.");
				}		
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
	
	//método utilizado para gerar o TOKEN do usuário..
	private String getJWTToken(String email) {
		
		//Todo TOKEN é gerado de forma criptografada, mas precisamos gerar essa criptografia
		//utilizando uma palavra secreta, isso irá garantir que nenhum outro projeto conseguirá
		//falsificar TOKENs da nossa aplicação.
		String secretKey = "5eebb082-4046-4d7f-a638-3c16d9dec4f8";
		
		//Gerando o TOKEN de acesso do usuário
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("COTI_JWT")
				.setSubject(email)
				.claim("authorities", grantedAuthorities.stream()
						.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000)) //expira em 10 minutos
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
				.compact();
		
		return token;
	}
	
}