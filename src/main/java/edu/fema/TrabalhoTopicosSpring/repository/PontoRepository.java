package edu.fema.TrabalhoTopicosSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.fema.TrabalhoTopicosSpring.model.Funcionario;
import edu.fema.TrabalhoTopicosSpring.model.Ponto;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, Long> {

	public Integer countByFuncionario(Funcionario funcionario);

	@Query(value = "select * from ponto where funcionario_codigo = :id order by id desc limit 1", nativeQuery = true)
	public Ponto findUltimoPontoByFuncionarioId(@Param(value = "id") Long idFuncionario);

	public List<Ponto> findByFuncionario(Funcionario funcionario);

}
