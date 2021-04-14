package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.dtos.ContaCadastroDTO;
import br.com.cotiinformatica.dtos.ContaConsultaDTO;
import br.com.cotiinformatica.dtos.ContaEdicaoDTO;
import br.com.cotiinformatica.entities.Conta;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.enums.TipoConta;
import br.com.cotiinformatica.repositories.ContaRepository;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class ContaController {

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	// declarando o ENDPOINT dos serviços de usuario
	private static final String ENDPOINT = "/api/contas";

	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<String>> cadastrarConta(@RequestBody ContaCadastroDTO dto) {

		List<String> mensagens = new ArrayList<String>();
		
		try {
			
			String email = (String) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			
			//obtendo o usuario para relacionar com a conta
			Usuario usuario = usuarioRepository.findByEmail(email);
			
			Conta conta = new Conta();
			
			conta.setNome(dto.getNome());
			conta.setData(dto.getData());
			conta.setValor(dto.getValor());
			conta.setDescricao(dto.getDescricao());
			conta.setTipo(TipoConta.valueOf(dto.getTipo()));
			conta.setUsuario(usuario);
			
			contaRepository.save(conta);
			
			mensagens.add("Conta cadastrada com sucesso.");
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(mensagens);	
		}
		catch(Exception e) {
			
			mensagens.add("Ocorreu um erro: " + e.getMessage());
			
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(mensagens);		
		}
	}

	@RequestMapping(value = ENDPOINT, method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<List<String>> atualizarConta(@RequestBody ContaEdicaoDTO dto) {

		List<String> mensagens = new ArrayList<String>();
		
		try {
			
			Conta conta = new Conta();
			
			conta.setIdConta(dto.getIdConta());
			conta.setNome(dto.getNome());
			conta.setData(dto.getData());
			conta.setValor(dto.getValor());
			conta.setDescricao(dto.getDescricao());
			conta.setTipo(TipoConta.valueOf(dto.getTipo()));
			
			contaRepository.save(conta);
			
			mensagens.add("Conta atualizada com sucesso.");
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(mensagens);	
		}
		catch(Exception e) {
			
			mensagens.add("Ocorreu um erro: " + e.getMessage());
			
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(mensagens);		
		}
	}

	@RequestMapping(value = ENDPOINT + "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<List<String>> excluirConta(@PathVariable("id") Integer id) {

		List<String> mensagens = new ArrayList<String>();
		
		try {
			
			//buscar o registro da conta pelo id..
			Conta conta = contaRepository.findById(id);
			
			//excluindo a conta
			contaRepository.delete(conta);
			
			mensagens.add("Conta excluída com sucesso.");
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(mensagens);	
		}
		catch(Exception e) {
			
			mensagens.add("Ocorreu um erro: " + e.getMessage());
			
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(mensagens);		
		}
	}

	@RequestMapping(value = ENDPOINT + "/{dataInicio}/{dataFim}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ContaConsultaDTO>> consultarContas(
			@PathVariable("dataInicio") Date dataInicio, 
			@PathVariable("dataFim") Date dataFim) {
		
		//TODO
		
		return null;
	}
}


