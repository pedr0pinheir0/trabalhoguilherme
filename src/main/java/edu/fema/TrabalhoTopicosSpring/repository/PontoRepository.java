package edu.fema.TrabalhoTopicosSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.fema.TrabalhoTopicosSpring.model.Ponto;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, Long>{

}
