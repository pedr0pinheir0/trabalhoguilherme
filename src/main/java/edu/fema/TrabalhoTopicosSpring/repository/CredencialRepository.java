package edu.fema.TrabalhoTopicosSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.fema.TrabalhoTopicosSpring.model.Credencial;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Long>{

}
