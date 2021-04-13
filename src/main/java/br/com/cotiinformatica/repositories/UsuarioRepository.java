package br.com.cotiinformatica.repositories;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.interfaces.IUsuarioRepository;

@Service
@Transactional
public class UsuarioRepository {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	// método para cadastrar usuários
	public void save(Usuario usuario) throws Exception {
		usuarioRepository.save(usuario);
	}

	// método para buscar 1 usuario atraves do email
	public Usuario findByEmail(String email) throws Exception {
		return usuarioRepository.findByEmail(email);
	}

	// método para buscar 1 usuario atraves do email e senha
	public Usuario findByEmailSenha(String email, String senha) throws Exception {
		return usuarioRepository.findByEmailSenha(email, senha);
	}
}