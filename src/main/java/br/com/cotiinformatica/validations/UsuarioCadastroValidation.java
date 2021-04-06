package br.com.cotiinformatica.validations;

import java.util.ArrayList;
import java.util.List;

import br.com.cotiinformatica.dtos.UsuarioCadastroDTO;

/*
 * Classe de validação para os campos enviados
 * no serviço POST de cadastro de usuário
 */
public class UsuarioCadastroValidation {

	public static List<String> validate(UsuarioCadastroDTO dto) {

		List<String> mensagens = new ArrayList<String>();

		// verificando se o campo nome não foi preenchido
		if (dto.getNome() == null || dto.getNome().length() == 0) {
			mensagens.add("Por favor, informe o nome do usuário.");
		}
		// verificando o minimo e máximo de caracteres do campo nome do usuario
		else if (dto.getNome().trim().length() < 6 || dto.getNome().trim().length() > 150) {
			mensagens.add("Por favor, informe o nome do usuario contendo de 6 a 150 caracteres.");
		}

		// verificando se o campo email não foi preenchido
		if (dto.getEmail() == null || dto.getEmail().length() == 0) {
			mensagens.add("Por favor, informe o email do usuário");
		}

		// verificando se o campo senha não foi preenchido
		if (dto.getSenha() == null || dto.getSenha().length() == 0) {
			mensagens.add("Por favor, informe a senha do usuário.");
		}
		// verificando o minimo e máximo de caracteres do campo nome do usuario
		else if (dto.getSenha().trim().length() < 8 || dto.getSenha().trim().length() > 20) {
			mensagens.add("Por favor, informe a senha do usuario contendo de 8 a 20 caracteres.");
		}

		// verificando se o campo senhaConfirmacao não foi preenchido
		if (dto.getSenhaConfirmacao() == null || dto.getSenhaConfirmacao().length() == 0) {
			mensagens.add("Por favor, confirme a senha do usuário.");
		}
		// verificando se o campo senhaConfirmacao esta diferente do campo senha
		else if (!dto.getSenhaConfirmacao().equals(dto.getSenha())) {
			mensagens.add("Senhas não conferem.");
		}

		return mensagens;
	}

}