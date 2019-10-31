package edu.fema.TrabalhoTopicosSpring.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import edu.fema.TrabalhoTopicosSpring.model.enums.GeneroEnum;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "funcionario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	private String nome;
	private String cpf;
	private GeneroEnum genero;

	@JsonIgnore
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private Date admissao;

	@JsonIgnore
	@OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Ponto> pontos;

	@OneToOne(mappedBy = "funcionario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	@JsonIgnore
	private Credencial credencial;
	
	public String getGenero() {
		return genero.getGenero();
	}
}
