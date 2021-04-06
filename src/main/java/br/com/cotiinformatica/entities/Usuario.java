package br.com.cotiinformatica.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDUSUARIO")
	private Integer idUsuario;
	
	@Column(name = "NOME", length = 150, nullable = false)
	private String nome;
	
	@Column(name = "EMAIL", length = 100, nullable = false, unique = true)
	private String email;
	
	@Column(name = "SENHA", length = 50, nullable = false)
	private String senha;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(Integer idUsuario, String nome, String email, String senha) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nome=" + nome + ", email=" + email + ", senha=" + senha + "]";
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Usuario) {
			Usuario usuario = (Usuario) obj;
			return this.getIdUsuario().equals(usuario.getIdUsuario());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.idUsuario.hashCode();
	}
}