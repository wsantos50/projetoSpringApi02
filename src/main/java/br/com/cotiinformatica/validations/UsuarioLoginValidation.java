package br.com.cotiinformatica.validations;

import java.util.ArrayList;
import java.util.List;

import br.com.cotiinformatica.dtos.UsuarioLoginDTO;

public class UsuarioLoginValidation {

	public static List<String> validate(UsuarioLoginDTO dto) {

		List<String> mensagens = new ArrayList<String>();

		// verificando se o campo email não foi preenchido
		if (dto.getEmail() == null || dto.getEmail().length() == 0) {
			mensagens.add("Por favor, informe o email do usuário");
		}

		// verificando se o campo senha não foi preenchido
		if (dto.getSenha() == null || dto.getSenha().length() == 0) {
			mensagens.add("Por favor, informe a senha do usuário");
		}

		return mensagens;
	}

}