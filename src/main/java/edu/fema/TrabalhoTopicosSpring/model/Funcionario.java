package edu.fema.TrabalhoTopicosSpring.model;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import edu.fema.TrabalhoTopicosSpring.model.enums.GeneroEnum;
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

	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private Date admissao;

	private String cargo;

	@OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Ponto> pontos;

	@Embedded
	private Credencial credencial;

	private Long saldoDeHoras;

	public List<Ponto> getPontos() {
		return Collections.unmodifiableList(pontos);
	}

}
