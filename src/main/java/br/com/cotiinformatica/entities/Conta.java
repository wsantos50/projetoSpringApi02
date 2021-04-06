package br.com.cotiinformatica.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.cotiinformatica.enums.TipoConta;

@Entity
@Table(name = "CONTA")
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDCONTA")
	private Integer idConta;

	@Column(name = "NOME", length = 150, nullable = false)
	private String nome;

	@Column(name = "VALOR", nullable = false)
	private Double valor;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA", nullable = false)
	private Date data;

	@Column(name = "DESCRICAO", length = 500, nullable = false)
	private String descricao;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO", nullable = false)
	private TipoConta tipo;

	@ManyToOne // Muitas CONTAS para 1 Usuario
	@JoinColumn(name = "IDUSUARIO") // campo chave estrangeira no banco de dados
	private Usuario usuario;

	public Conta() {
		// TODO Auto-generated constructor stub
	}

	public Conta(Integer idConta, String nome, Double valor, Date data, String descricao, TipoConta tipo,
			Usuario usuario) {
		super();
		this.idConta = idConta;
		this.nome = nome;
		this.valor = valor;
		this.data = data;
		this.descricao = descricao;
		this.tipo = tipo;
		this.usuario = usuario;
	}

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoConta getTipo() {
		return tipo;
	}

	public void setTipo(TipoConta tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Conta) {
			Conta conta = (Conta) obj;
			return this.idConta.equals(conta.getIdConta());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.idConta.hashCode();
	}
}