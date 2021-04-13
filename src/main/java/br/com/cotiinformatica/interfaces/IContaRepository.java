package br.com.cotiinformatica.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.entities.Conta;

public interface IContaRepository extends CrudRepository<Conta, Integer> {

	@Query("select c from Conta c where c.data >= :param1 and c.data <= :param2 and c.usuario.idUsuario = :param3")
	List<Conta> findAllByDatas(@Param("param1") Date dataMin, @Param("param2") Date dataMax,
			@Param("param3") Integer idUsuario);

}