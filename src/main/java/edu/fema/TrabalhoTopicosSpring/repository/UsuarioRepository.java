package edu.fema.TrabalhoTopicosSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.fema.TrabalhoTopicosSpring.model.Funcionario;

@Repository
public interface UsuarioRepository extends JpaRepository<Funcionario, Long>{

}
