package br.com.cotiinformatica.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.Conta;
import br.com.cotiinformatica.interfaces.IContaRepository;

@Service
@Transactional
public class ContaRepository {

	@Autowired
	private IContaRepository contaRepository;

	// método para cadastrar / atualizar conta no banco de dados
	public void save(Conta conta) throws Exception {
		contaRepository.save(conta);
	}

	// método para excluir uma conta
	public void delete(Conta conta) throws Exception {
		contaRepository.delete(conta);
	}
	
	//método para buscar 1 conta pelo id
	public Conta findById(Integer id) throws Exception {
		return contaRepository.findById(id).get();
	}

	// método para consultar contas por um periodo de data e usuario..
	public List<Conta> findAllByDatas(Date dataInicio, Date dataFim, Integer idUsuario) throws Exception {
		return contaRepository.findAllByDatas(dataInicio, dataFim, idUsuario);
	}

}

