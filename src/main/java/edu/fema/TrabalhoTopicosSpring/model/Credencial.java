package edu.fema.TrabalhoTopicosSpring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "credencial")
@Data
@Builder
public class Credencial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 1, max = 15)
	private String nomeDeUsuario;

	@NotNull
	@Size(min = 1, max = 15)
	private String senha;

	@NotNull
	@OneToOne
	private Funcionario funcionario;
	
	private String hash;
}
